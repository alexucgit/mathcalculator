// package cordova.plugin.mathcalculator;
package com.dylee.plugin.mathcalculator;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.imagpay.Settings;
import com.imagpay.SwipeEvent;
import com.imagpay.SwipeListener;
import com.imagpay.enums.CardDetected;
import com.imagpay.enums.EmvStatus;
import com.imagpay.enums.PosModel;
import com.imagpay.enums.PrintStatus;
import com.imagpay.mpos.MposHandler;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.UUID;
import java.util.concurrent.TimeUnit;
 


/**
 * This class echoes a string called from JavaScript.
 */
@SuppressLint("HandlerLeak")
public class MathCalculator extends CordovaPlugin {
    
    private MposHandler handler;
    private Settings setting;
    private static Context context;
    protected boolean ioutP = false;
    

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if(action.equals("add")) {
         
        // Init SDK,call singleton function,so that you can keeping on the
        // connect in the whole life cycle
        // handler = MposHandler.getInstance(this);
        handler = MposHandler.getInstance(context.getApplicationContext(), PosModel.Z91);

        setting = Settings.getInstance(handler);
        // power on the device when you need to read card or print
        setting.mPosPowerOn();
        try {
            // for Z90,delay 1S and then connect
            // Thread.sleep(1000);
            // connect device via serial port
            if (!handler.isConnected()) {
                sendMessage("Connect Res:" + handler.connect());
            } else {
                handler.close();
                sendMessage("ReConnect Res:" + handler.connect());
            }
        } catch (Exception e) {
            sendMessage(e.getMessage());

        }
        // add linstener for connection
        handler.addSwipeListener(this);
        // add linstener for read IC chip card
        // handler.addEMVListener(this);
         
            
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
 
    protected void sendMessage(String string) {
        Log.i("xtztt", "==>:" + string);
    }
 
}
