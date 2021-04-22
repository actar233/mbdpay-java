package com.github.actar233.mbdpay.notice;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoticeDataComplaint implements NoticeData {

    /**
     * out_trade_no
     * 类型 string
     * 说明 订单号
     */
    @SerializedName("out_trade_no")
    private String outTradeNo;

    /**
     * complaint_detail
     * 类型 string
     * 说明 投诉详情
     */
    @SerializedName("complaint_detail")
    private String complaintDetail;

    /**
     * amount
     * 类型 int
     * 说明 订单金额，单位为分
     */
    @SerializedName("amount")
    private int amount;

    /**
     * payer_phone
     * 类型 string
     * 说明 投诉者电话
     */
    @SerializedName("payer_phone")
    private String payerPhone;

}
