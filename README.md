# 面包多支付Java版本SDK

[![](https://www.jitpack.io/v/actar233/mbdpay-java.svg)](https://www.jitpack.io/#actar233/mbdpay-java)

# 如何使用

1. 引入jetpack仓库

maven 
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://www.jitpack.io</url>
    </repository>
</repositories>
```

gradle
```groovy
allprojects {
	repositories {
		...
		maven { url 'https://www.jitpack.io' }
	}
}
```

2. 引入SDK

maven
```xml
<dependency>
    <groupId>com.github.actar233</groupId>
    <artifactId>mbdpay-java</artifactId>
    <version>1.0.1</version>
</dependency>
```

gradle
```groovy
dependencies {
    implementation 'com.github.actar233:mbdpay-java:1.0.1'
}
```

3. 初始化
```java
MbdPayClient client = new MbdPayClient("<app_id>", "<app_key>");
```

4. 获取微信用户openid
```java
private static void openid(MbdPayClient client){
    System.out.println(client.openid("https://www.baidu.com"));
}
```

5. 支付
```java
// 支付宝
private static void aliPay(MbdPayClient client) {
    try {
        AliPayResult result = client.aliPay(AliPayParams.builder()
                .url("<url>")
                .amountTotal(1)
                .callbackUrl("<callbackUrl>")
                .description("<description>")
                .outTradeNo("<可选:outTradeNo>")
                .build());
        if (result.isSuccess()) {
            System.out.println("查询成功");
            System.out.println(result);
        } else {
            System.out.println("查询失败");
            System.out.println(result.getError());
        }
    } catch (MbdPayException e) {
        System.out.println("请求异常");
        e.printStackTrace();
    }
}
// 微信H5
private static void wxH5(MbdPayClient client) {
    try {
        WxH5Result result = client.wxH5(WxH5Params.builder()
                .amountTotal(1)
                .description("<description>")
                .channel("h5")
                .outTradeNo("<可选:outTradeNo>")
                .build());
        if (result.isSuccess()) {
            System.out.println("查询成功");
            System.out.println(result);
        } else {
            System.out.println("查询失败");
            System.out.println(result.getError());
        }
    } catch (MbdPayException e) {
        System.out.println("请求异常");
        e.printStackTrace();
    }
}
// 微信JSAPI
private static void wxJsApi(MbdPayClient client) {
    try {
        WxJsApiResult result = client.wxJsApi(WxJsApiParams.builder()
                .openid("<openid>")
                .amountTotal(1)
                .callbackUrl("<callbackUrl>")
                .description("<description>")
                .outTradeNo("<可选:outTradeNo>")
                .build());
        if (result.isSuccess()) {
            System.out.println("查询成功");
            System.out.println(result);
        } else {
            System.out.println("查询失败");
            System.out.println(result.getError());
        }
    } catch (MbdPayException e) {
        System.out.println("请求异常");
        e.printStackTrace();
    }
}
```

6. 查单
```java
private static void searchOrder(MbdPayClient client) {
    try {
        SearchOrderResult result = client.searchOrder(SearchOrderParams.builder()
                .outTradeNo("<outTradeNo>")
                .build());
        if (result.isSuccess()) {
            System.out.println("查询成功");
            System.out.println(result);
        } else {
            System.out.println("查询失败");
            System.out.println(result.getError());
        }
    } catch (MbdPayException e) {
        System.out.println("请求异常");
        e.printStackTrace();
    }
}
```

7. 退款
```java
private static void refund(MbdPayClient client) {
    try {
        RefundResult result = client.refund(RefundParams.builder()
                .orderId("<orderId>")
                .build());
        if (result.isSuccess()) {
            System.out.println("查询成功");
            System.out.println(result);
        } else {
            System.out.println("查询失败");
            System.out.println(result.getError());
        }
    } catch (MbdPayException e) {
        System.out.println("请求异常");
        e.printStackTrace();
    }
}
```

8. 解析通知
```java
@PostMapping("/callback")
public String callback(@RequestBody String json) throws MbdPayException {
    Notice notice = mbdPayClient.parseNotice(json);
    System.out.println(notice);
    switch (notice.getType()) {
    case charge_succeeded:
        NoticeDataChargeSucceeded data_cs = (NoticeDataChargeSucceeded) notice.getData();
        System.out.println("支付成功");
        System.out.println("订单号:" + data_cs.getOutTradeNo());
        break;
    case complaint:
        NoticeDataComplaint data_c = (NoticeDataComplaint) notice.getData();
        System.out.println("投诉成功");
        System.out.println("订单号:" + data_c.getOutTradeNo());
        break;
    }
    return "success";
}
```

# 链接
[面包多支付](https://mbd.pub/)