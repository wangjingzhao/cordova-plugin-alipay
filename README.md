# cordova-plugin-alipay

支付宝 APP 支付插件

cordova plugin add

cordova plugin rm cordova-plugin-alipay

## iOS 使用前
修改 ``` src/ios/AliPayPlugin.m ``` 文件的 ``` - (void)pay:(CDVInvokedUrlCommand*)command ``` 的 ``` NSString *appScheme = @"com.your.package"; ``` 的值。修改为你自己的 URL Types 设置的值。

## 调用方法

```
function test() {
    console.log('test');
    window.aliPay.pay(
        {
            order  : "签名好的，准备发送给 SDK 的订单信息",
        },
        function (successResults) {
            alert(successResults);
            console.log(successResults);
        }, function (errorResults) {
            alert(errorResults);
            console.log(errorResults);
        }
    );
}
```
