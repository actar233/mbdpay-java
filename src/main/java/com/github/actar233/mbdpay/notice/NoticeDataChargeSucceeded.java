package com.github.actar233.mbdpay.notice;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NoticeDataChargeSucceeded implements NoticeData {

    /**
     * description
     * 类型 string
     * 说明 商品描述
     */
    @SerializedName("description")
    private String description;

    /**
     * out_trade_no
     * 类型 string
     * 说明 订单号
     */
    @SerializedName("out_trade_no")
    private String outTradeNo;

    /**
     * amount
     * 类型 int
     * 说明 金额，单位为分
     */
    @SerializedName("amount")
    private int amount;

    /**
     * openid
     * 类型 string
     * 说明 支付者 openid (仅微信支付)
     */
    @SerializedName("openid")
    private String openid;

    /**
     * charge_id
     * 类型 string
     * 说明 支付渠道流水号
     */
    @SerializedName("charge_id")
    private String chargeId;

    /**
     * payway
     * 类型 int
     * 说明 支付渠道，微信支付为 1 ，支付宝支付为 2
     */
    @SerializedName("payway")
    private int payway;


}
