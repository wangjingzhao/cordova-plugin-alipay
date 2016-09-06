# cordova-plugin-alipay

支付宝 APP 支付插件

cordova plugin add 

cordova plugin rm cordova-plugin-alipay

调用方法

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