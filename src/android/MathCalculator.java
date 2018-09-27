// package cordova.plugin.mathcalculator;
package com.dylee.plugin.mathcalculator;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import com.imagpay.mpos.MposHandler;
import com.imagpay.Settings;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class echoes a string called from JavaScript.
 */
public class MathCalculator extends CordovaPlugin {
    
    private Context context;
    private Settings setting;
    private MposHandler handler;
    private static String TAG = "PosDemo";
    

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if(action.equals("print")) {
           print(args, callbackContext);
            return true;
        }
        return false;
    }
    
    private void print(JSONArray args, CallbackContext callback) {
        /*if(handler == null || setting == null) {
                context = this.cordova.getActivity();
                handler = MposHandler.getInstance(context);
                setting = Settings.getInstance(handler);
           } 
            setting.mPosPowerOn();
            setting.prnStr("This class echoes a string called from JavaScript.");
            setting.prnStart();
            callback.success();*/
        callback.success(handler.isConnected());
    }
}
