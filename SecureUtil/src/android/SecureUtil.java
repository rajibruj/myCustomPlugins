package com.secureYou.dev;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaWebView;

import android.telephony.TelephonyManager;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class echoes a string called from JavaScript.
 */
public class SecureUtil extends CordovaPlugin {

    CordovaInterface cordova;

    @Override
public void initialize(CordovaInterface cordova, CordovaWebView webView) {
    super.initialize(cordova, webView);
    this.cordova=cordova;
}

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("coolMethod")) {
            String message = args.getString(0);
            this.coolMethod(message, callbackContext);
            return true;
        }
        else if(action.equals("getPhoneringing")){
            getPhoneringing();

        }
        return false;
    }

    private void coolMethod(String message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) {
            callbackContext.success(message);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }

    private void getPhoneringing(){

        TelephonyManager telephonyMgr = (TelephonyManager) cordova.getActivity().getSystemService(cordova.getContext().TELEPHONY_SERVICE);

        String text = (String)telephonyMgr.getCallState();

        Toast toast = Toast.makeText(cordova.getContext(), text, 3000);
        toast.show();
    }






}//end
