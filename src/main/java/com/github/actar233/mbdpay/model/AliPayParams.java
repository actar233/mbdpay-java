package com.github.actar233.mbdpay.model;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AliPayParams {

    /**
     * 必选 是
     * 类型 string
     * 说明 支付后跳转的URL地址
     */
    @SerializedName("url")
    private String url;

    /**
     * 必选 是
     * 类型 string
     * 说明 支付描述，一般为商品名称
     */
    @SerializedName("description")
    private String description;

    /**
     * 必选 是
     * 类型 number
     * 说明 金额，单位为分
     */
    @SerializedName("amount_total")
    private int amountTotal;

    /**
     * 必选 否
     * 类型 string
     * 说明 订单号，如不填，面包多将随机生成订单号
     */
    @SerializedName("out_trade_no")
    private String outTradeNo;

    /**
     * 必选 否
     * 类型 string
     * 说明 支付后跳转地址，如不填会只显示「支付成功」
     */
    @SerializedName("callback_url")
    private String callbackUrl;

}
