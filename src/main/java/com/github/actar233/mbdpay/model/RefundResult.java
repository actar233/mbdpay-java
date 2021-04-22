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
public class RefundResult extends Result {

    /**
     * code
     */
    @SerializedName("code")
    private int code;

    /**
     * info
     */
    @SerializedName("info")
    private String info;

}
