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
public class RefundParams {

    /**
     * 必选 是
     * 类型 string
     * 说明 订单号
     */
    @SerializedName("order_id")
    private String orderId;

}
