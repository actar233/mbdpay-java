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
public class AliPayResult extends Result {

    /**
     * body
     */
    @SerializedName("body")
    private String body;

}
