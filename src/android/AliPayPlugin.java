/*
 * Created by IntelliJ IDEA.
 * User: GuoDapeng
 * Date: 16/9/1
 * Time: 上午9:56
 */

package org.easycloud.alipay;

import android.text.TextUtils;
import com.alipay.sdk.app.PayTask;
import org.apache.cordova.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class AliPayPlugin extends CordovaPlugin {
    private static String TAG = "AliPayPlugin";

    private static final String PAY = "pay";

    /**
     * @param action          The action to execute.
     * @param args            The exec() arguments.
     * @param callbackContext The callback context used when calling back into JavaScript.
     * @return bool
     */
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        LOG.d(TAG, "AliPayPlugin action:" + action);
        if (action.equals(PAY)) {
            JSONObject arguments = args.getJSONObject(0);
            String orderString = arguments.getString("order");
            this.pay(orderString, callbackContext);
            return true;
        }
        return false;  // Returning false results in a "MethodNotFound" error.
    }

    private void pay(String order, final CallbackContext callbackContext) {
        LOG.d(TAG, "AliPayPlugin pay");

        final String orderString = order;
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(cordova.getActivity());
                Map<String, String> result = alipay.payV2(orderString, true);

                LOG.d(TAG, "result:" + result.toString());

                JSONObject json = new JSONObject(result);

                if (TextUtils.equals(result.get("resultStatus"), "9000")) {
                    callbackContext.success(json);
                } else {
                    // 判断resultStatus 为非“9000”则代表可能支付失败
                    // “8000”代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                    if (TextUtils.equals(result.get("resultStatus"), "8000")) {
                        callbackContext.success(json);
                    } else {
                        callbackContext.error(json);
                    }
                }
            }
        };

        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }
}
