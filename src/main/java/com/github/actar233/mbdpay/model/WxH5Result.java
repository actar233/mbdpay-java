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
public class WxH5Result extends Result {

    /**
     * h5_url
     */
    @SerializedName("h5_url")
    private String h5Url;

}
