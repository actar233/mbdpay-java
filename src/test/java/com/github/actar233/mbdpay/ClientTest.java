package com.github.actar233.mbdpay;

import com.github.actar233.mbdpay.exception.MbdPayException;
import com.github.actar233.mbdpay.model.*;

public class ClientTest {

    public static void main(String[] args) {
        MbdPayClient client = new MbdPayClient("<app_id>", "<app_key>");
        aliPay(client);
        openid(client);
        wxH5(client);
        wxJsApi(client);
        searchOrder(client);
        refund(client);
    }

    private static void aliPay(MbdPayClient client) {
        try {
            AliPayResult result = client.aliPay(AliPayParams.builder()
                    .url("<url>")
                    .amountTotal(1)
                    .callbackUrl("<callbackUrl>")
                    .description("<description>")
                    .outTradeNo("<可选:outTradeNo>")
                    .build());
            if (result.isSuccess()) {
                System.out.println("查询成功");
                System.out.println(result);
            } else {
                System.out.println("查询失败");
                System.out.println(result.getError());
            }
        } catch (MbdPayException e) {
            System.out.println("请求异常");
            e.printStackTrace();
        }
    }

    private static void openid(MbdPayClient client){
        System.out.println(client.openid("https://www.baidu.com"));
    }

    private static void wxH5(MbdPayClient client) {
        try {
            WxH5Result result = client.wxH5(WxH5Params.builder()
                    .amountTotal(1)
                    .description("<description>")
                    .channel("h5")
                    .outTradeNo("<可选:outTradeNo>")
                    .build());
            if (result.isSuccess()) {
                System.out.println("查询成功");
                System.out.println(result);
            } else {
                System.out.println("查询失败");
                System.out.println(result.getError());
            }
        } catch (MbdPayException e) {
            System.out.println("请求异常");
            e.printStackTrace();
        }
    }

    private static void wxJsApi(MbdPayClient client) {
        try {
            WxJsApiResult result = client.wxJsApi(WxJsApiParams.builder()
                    .openid("<openid>")
                    .amountTotal(1)
                    .callbackUrl("<callbackUrl>")
                    .description("<description>")
                    .outTradeNo("<可选:outTradeNo>")
                    .build());
            if (result.isSuccess()) {
                System.out.println("查询成功");
                System.out.println(result);
                // 获取支付参数
                System.out.println(result.getPayParams());
            } else {
                System.out.println("查询失败");
                System.out.println(result.getError());
            }
        } catch (MbdPayException e) {
            System.out.println("请求异常");
            e.printStackTrace();
        }
    }

    private static void searchOrder(MbdPayClient client) {
        try {
            SearchOrderResult result = client.searchOrder(SearchOrderParams.builder()
                    .outTradeNo("<outTradeNo>")
                    .build());
            if (result.isSuccess()) {
                System.out.println("查询成功");
                System.out.println(result);
            } else {
                System.out.println("查询失败");
                System.out.println(result.getError());
            }
        } catch (MbdPayException e) {
            System.out.println("请求异常");
            e.printStackTrace();
        }
    }

    private static void refund(MbdPayClient client) {
        try {
            RefundResult result = client.refund(RefundParams.builder()
                    .orderId("<orderId>")
                    .build());
            if (result.isSuccess()) {
                System.out.println("查询成功");
                System.out.println(result);
            } else {
                System.out.println("查询失败");
                System.out.println(result.getError());
            }
        } catch (MbdPayException e) {
            System.out.println("请求异常");
            e.printStackTrace();
        }
    }

}
