package com.github.actar233.mbdpay;

public class Constant {
    /**
     * 基础地址
     */
    public static final String BASE_URL = "https://api.mianbaoduo.com";
    /**
     * openid
     */
    public static final String OPENID = "https://mbd.pub/openid";

    /**
     * 微信JSAPI
     */
    public static final String WX_JSAPI = BASE_URL + "/release/wx/prepay";

    /**
     * 微信H5
     */
    public static final String WX_H5 = BASE_URL + "/release/wx/prepay";

    /**
     * 支付宝pay
     */
    public static final String ALI_PAY = BASE_URL + "/release/alipay/pay";

    /**
     * 退款
     */
    public static final String REFUND = BASE_URL + "/release/main/refund";

    /**
     * 订单查询
     */
    public static final String SEARCH_ORDER = BASE_URL + "/release/main/search_order";

}
