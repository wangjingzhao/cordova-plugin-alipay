/**
 * Created by IntelliJ IDEA.
 * User: GuoDapeng
 * Date: 16/8/31
 * Time: 下午8:56
 */

var exec = require("cordova/exec");

/**
 * Constructor.
 *
 * @returns {aliPay}
 */
function aliPay() {
}

/**
 *
 *@param {object} paymentInfo
 * @param {Function} successCallback
 * @param {Function} errorCallback
 */
aliPay.prototype.pay = function (paymentInfo, successCallback, errorCallback) {
    exec(successCallback, errorCallback, "AliPay", "pay", [paymentInfo]);
};

module.exports = new aliPay();