package com.github.actar233.mbdpay.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@NoArgsConstructor
public class SearchOrderResult extends Result {


    /**
     * order_id
     */
    @SerializedName("order_id")
    private String orderId;

    /**
     * charge_id
     */
    @SerializedName("charge_id")
    private String chargeId;

    /**
     * description
     */
    @SerializedName("description")
    private String description;

    /**
     * share_id
     */
    @SerializedName("share_id")
    private String shareId;

    /**
     * share_state
     */
    @SerializedName("share_state")
    private String shareState;

    /**
     * amount
     */
    @SerializedName("amount")
    private String amount;

    /**
     * state
     */
    @SerializedName("state")
    private String state;

    /**
     * create_time
     */
    @SerializedName("create_time")
    private String createTime;

    /**
     * payway
     */
    @SerializedName("payway")
    private int payway;

    /**
     * refund_state
     */
    @SerializedName("refund_state")
    private String refundState;

    /**
     * refund_amount
     */
    @SerializedName("refund_amount")
    private String refundAmount;


}
