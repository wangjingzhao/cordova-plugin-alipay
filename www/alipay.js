/**
 * Created by IntelliJ IDEA.
 * User: GuoDapeng
 * Date: 16/8/31
 * Time: 下午8:56
 */

module.exports = {
    pay: function (paymentInfo, successCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, "AliPay", "pay", [paymentInfo]);
    }
};