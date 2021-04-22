package com.github.actar233.mbdpay.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@NoArgsConstructor
public class WxJsApiResult extends Result {

    /**
     * appId
     */
    @SerializedName("appId")
    private String appId;

    /**
     * timeStamp
     */
    @SerializedName("timeStamp")
    private String timeStamp;

    /**
     * nonceStr
     */
    @SerializedName("nonceStr")
    private String nonceStr;

    /**
     * package
     */
    @SerializedName("package")
    private String packageName;

    /**
     * signType
     */
    @SerializedName("signType")
    private String signType;

    /**
     * paySign
     */
    @SerializedName("paySign")
    private String paySign;

    public Map<String, String> getPayParams() {
        HashMap<String, String> map = new HashMap<>();
        map.put("appId", this.appId);
        map.put("timeStamp", this.timeStamp);
        map.put("nonceStr", this.nonceStr);
        map.put("package", this.packageName);
        map.put("signType", this.signType);
        map.put("paySign", this.paySign);
        return map;
    }

}
