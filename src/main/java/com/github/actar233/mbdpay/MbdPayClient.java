package com.github.actar233.mbdpay;

import com.github.actar233.mbdpay.exception.MbdPayException;
import com.github.actar233.mbdpay.model.*;
import com.github.actar233.mbdpay.notice.*;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class MbdPayClient {

    private final Gson gson;
    private final OkHttpClient okHttpClient;

    /**
     * app_id
     */
    private final String app_id;

    /**
     * app_key
     */
    private final String app_key;

    public MbdPayClient(String app_id, String app_key) {
        this.app_id = app_id;
        this.app_key = app_key;
        this.gson = new Gson();
        this.okHttpClient = new OkHttpClient().newBuilder().build();
    }

    public <REQ, RSP> RSP post(String url, REQ request, Class<RSP> clazz) throws MbdPayException {
        try {
            Map<String, String> params = params(request);
            params.put("app_id", this.app_id);
            String sign = sign(params);
            params.put("sign", sign);

            String json = gson.toJson(params);

            System.out.println(json);

            RequestBody requestBody = RequestBody.create(json.getBytes(StandardCharsets.UTF_8));

            Request req = new Request.Builder()
                    .url(url)
                    .method("POST", requestBody)
                    .addHeader("Content-Type", "application/json;charset=utf-8")
                    .build();

            Response rsp = okHttpClient.newCall(req).execute();
            if (rsp.code() != 200) {
                throw new MbdPayException(String.format("请求失败,响应码: %d", rsp.code()));
            }

            ResponseBody responseBody = rsp.body();
            if (responseBody == null) {
                throw new MbdPayException("请求失败");
            }

            String string = responseBody.string();
            System.out.println(string);

            return gson.fromJson(string, clazz);
        } catch (IOException | MbdPayException e) {
            if (e instanceof MbdPayException) {
                throw (MbdPayException) e;
            }
            throw new MbdPayException(e);
        }
    }

    private Map<String, String> params(Object src) {
        JsonElement tree = gson.toJsonTree(src);
        JsonObject object = tree.getAsJsonObject();
        HashMap<String, String> map = new HashMap<>();
        for (String key : object.keySet()) {
            String value = object.get(key).getAsString();
            map.put(key, value);
        }
        return map;
    }

    private String sign(Map<String, String> map) throws MbdPayException {
        QueryStringBuilder builder = QueryStringBuilder.create();
        for (String key : map.keySet()) {
            String value = map.get(key);
            builder.put(key, value);
        }
        String signString = String.format("%s&key=%s", builder.build(), this.app_key);
        return md5(signString);
    }

    public static String md5(String src) throws MbdPayException {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(src.getBytes(StandardCharsets.UTF_8));
            StringBuilder builder = new StringBuilder();
            for (byte b : digest.digest()) {
                builder.append(Integer.toHexString((0x000000FF & b) | 0xFFFFFF00).substring(6));
            }
            return builder.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new MbdPayException(e);
        }
    }

    /**
     * openid
     *
     * @param target_url 目标url
     * @return url
     */
    public String openid(String target_url) {
        String qs = QueryStringBuilder.create()
                .put("target_url", target_url)
                .put("app_id", this.app_id)
                .build();
        return String.format("%s?%s", Constant.OPENID, qs);
    }

    /**
     * 微信JSAPI支付
     *
     * @param params 参数
     * @return 结果
     * @throws MbdPayException 异常
     */
    public WxJsApiResult wxJsApi(WxJsApiParams params) throws MbdPayException {
        return post(Constant.WX_JSAPI, params, WxJsApiResult.class);
    }

    /**
     * 微信H5支付
     *
     * @param params 参数
     * @return 结果
     * @throws MbdPayException 异常
     */
    public WxH5Result wxH5(WxH5Params params) throws MbdPayException {
        return post(Constant.WX_H5, params, WxH5Result.class);
    }

    /**
     * 支付宝支付
     *
     * @param params 参数
     * @return 结果
     * @throws MbdPayException 异常
     */
    public AliPayResult aliPay(AliPayParams params) throws MbdPayException {
        return post(Constant.ALI_PAY, params, AliPayResult.class);
    }

    /**
     * 退款
     *
     * @param params 参数
     * @return 结果
     * @throws MbdPayException 异常
     */
    public RefundResult refund(RefundParams params) throws MbdPayException {
        return post(Constant.REFUND, params, RefundResult.class);
    }

    /**
     * 订单查询
     *
     * @param params 参数
     * @return 结果
     * @throws MbdPayException 异常
     */
    public SearchOrderResult searchOrder(SearchOrderParams params) throws MbdPayException {
        return post(Constant.SEARCH_ORDER, params, SearchOrderResult.class);
    }

    /**
     * 解析通知
     *
     * @param json json字符串
     * @return 通知
     */
    @SuppressWarnings("DuplicatedCode")
    public Notice parseNotice(String json) throws MbdPayException {
        JsonObject object = new JsonParser().parse(json).getAsJsonObject();
        NoticeType type = NoticeType.valueOf(object.get("type").getAsString());
        Class<? extends NoticeData> clazz;
        switch (type) {
            case charge_succeeded:
                clazz = NoticeDataChargeSucceeded.class;
                break;
            case complaint:
                clazz = NoticeDataComplaint.class;
                break;
            default:
                throw new MbdPayException("未知的type类型");
        }
        JsonElement objData = object.get("data");
        if (!objData.isJsonObject()) {
            throw new MbdPayException("data类型错误");
        }
        return Notice.builder()
                .type(type)
                .data(gson.fromJson(objData.getAsJsonObject(), clazz))
                .build();
    }

    @SuppressWarnings("DuplicatedCode")
    public Notice parseNotice(Map<String, Object> map) throws MbdPayException {
        Object objType = map.get("type");
        if (!(objType instanceof String)) {
            throw new MbdPayException("未知的type类型");
        }
        NoticeType type = NoticeType.valueOf((String) objType);
        Class<? extends NoticeData> clazz;
        switch (type) {
            case charge_succeeded:
                clazz = NoticeDataChargeSucceeded.class;
                break;
            case complaint:
                clazz = NoticeDataComplaint.class;
                break;
            default:
                throw new MbdPayException("未知的type类型");
        }
        JsonElement objData = gson.toJsonTree(map.get("data"));
        if (!objData.isJsonObject()) {
            throw new MbdPayException("data类型错误");
        }
        return Notice.builder()
                .type(type)
                .data(gson.fromJson(objData.getAsJsonObject(), clazz))
                .build();
    }

}
