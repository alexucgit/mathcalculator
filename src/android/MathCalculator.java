// package cordova.plugin.mathcalculator;
package com.dylee.plugin.mathcalculator;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import com.imagpay.mpos.MposHandler;
import com.imagpay.Settings;
import android.app.Activity;
import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class echoes a string called from JavaScript.
 */
public class MathCalculator extends CordovaPlugin {
    
       Context context;

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if(action.equals("add")) {
            
            MposHandler handler = MposHandler.getInstance(context);
            Settings setting = Settings.getInstance(handler);
            setting.mPosPowerOn();
            
            setting.prnStr("ciao");
            setting.prnStart();

            this.add(args, callbackContext);
            return true;
        }
        else if(action.equals("substract")) {
            this.substract(args, callbackContext);
            return true;
        }
        return false;
    }

    private void add(JSONArray args, CallbackContext callback) {
        if(args != null) {
            try {
                int p1 = Integer.parseInt(args.getJSONObject(0).getString("param1"));
                int p2 = Integer.parseInt(args.getJSONObject(0).getString("param2"));

                callback.success("ccc"+ (p1+p2) );
            }
            catch(Exception e) {
                callback.error("Something went wrong " + e);
            }
        }
        else {
            callback.error("Something went wrong");
        }
    }

    private void substract(JSONArray args, CallbackContext callback) {
        if(args != null) {
            try {
                int p1 = Integer.parseInt(args.getJSONObject(0).getString("param1"));
                int p2 = Integer.parseInt(args.getJSONObject(0).getString("param2"));

                callback.success("ccc"+ (p1-p2) );
            }
            catch(Exception e) {
                callback.error("Something went wrong " + e);
            }
        }
        else {
            callback.error("Something went wrong");
        }
    }
}
