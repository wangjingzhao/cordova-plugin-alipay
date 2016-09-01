/*
 * Created by IntelliJ IDEA.
 * User: GuoDapeng
 * Date: 16/9/1
 * Time: 上午9:56
 */

package org.easycloud.alipay;

import com.alipay.sdk.app.PayTask;
import org.apache.cordova.*;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.Map;

public class AliPayPlugin extends CordovaPlugin {
    private static String TAG = "AliPayPlugin";

    private static final String PAY = "pay";

    //商户PID
    private String partner = "";
    //商户收款账号
    private String seller = "";
    //商户私钥，pkcs8格式
    private String privateKey = "";

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        // your init code here
        LOG.d(TAG, "AliPayPlugin initialize");
        System.out.println("AliPayPlugin initialize");
    }

    /**
     * @param action          The action to execute.
     * @param args            The exec() arguments.
     * @param callbackContext The callback context used when calling back into JavaScript.
     * @return bool
     */
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        LOG.d(TAG, "AliPayPlugin action:" + action);
        System.out.println("AliPayPlugin action:" + action);
        if (action.equals(PAY)) {
            pay();
            return true;
        }
        return false;  // Returning false results in a "MethodNotFound" error.
    }

    private void pay() {
        String sign = "aa";
        final String orderInfo = "orderParam" + "&" + sign;

        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(cordova.getActivity());
                Map<String, String> result = alipay.payV2(orderInfo, true);
                LOG.d(TAG, "AliPayPlugin result:" + result);
                System.out.println("AliPayPlugin result:" + result);
            }
        };

        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }
}
