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
public class SearchOrderParams {

    /**
     * 必选 是
     * 类型 string
     * 说明 订单号（也支持微信/支付宝流水号）
     */
    @SerializedName("out_trade_no")
    private String outTradeNo;

}
