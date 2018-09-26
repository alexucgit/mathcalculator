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

import android.bluetooth.BluetoothDevice;
import android.content.Intent;

import java.util.UUID;
import java.util.concurrent.TimeUnit;
 
import static android.app.Activity.RESULT_OK;

/**
 * This class echoes a string called from JavaScript.
 */
public class MathCalculator extends CordovaPlugin {
    
    private CallbackContext callbackContext = null;
    private static String TAG = "PosDemo";
    private Settings setting;
 private Context context;

    

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if(action.equals("add")) {
            
            this.callbackContext = callbackContext;
            
            final Activity activity = this.cordova.getActivity();
            final MposHandler mPOSHandler = MposHandler.getInstance(context);
 
            cordova.setActivityResultCallback(MathCalculator.this);
            
            setting = Settings.getInstance(mPOSHandler);
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
