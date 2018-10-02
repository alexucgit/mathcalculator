// package cordova.plugin.mathcalculator;
package com.dylee.plugin.mathcalculator;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import java.io.InputStream;
import android.util.Log;

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
    public boolean execute(String action, String args, CallbackContext callbackContext) throws JSONException {
        if(action.equals("print")) {
           print(args, callbackContext);
            return true;
        }
        return false;
    }
    
    private void print(String args, CallbackContext callback) {
       if(handler == null || setting == null) {
                context = this.cordova.getActivity().getApplicationContext();
                handler = MposHandler.getInstance(context);
                setting = Settings.getInstance(handler);
           } 
       callback.success(""+context);
        
            setting.mPosPowerOn();
        String connect = "";
        try {
            if(!handler.isConnected()){
                connect = "Connect Res: "+handler.connect();
            } else {
                handler.close();
                connect = "ReConnect Res: "+handler.connect();
            }
            Thread.sleep(1000);
        } catch (Exception e) {
            // error call
        }
        
        StringBuffer testo = new StringBuffer();
        String[] split = args.split("##");
        System.out.println("LUNGHEZZA: "+split.length);
        
        for (int y=0; y<3; y++){
            if(y==0){
                    setting.mPosPrintAlign(Settings.MPOS_PRINT_ALIGN_CENTER);
                    setting.mPosPrintTextSize(Settings.MPOS_PRINT_TEXT_DOUBLE_HEIGHT);
                    setting.mPosPrnStr(split[y]+"\n");
            } else {
                    setting.mPosPrintAlign(Settings.MPOS_PRINT_ALIGN_CENTER);
                    setting.mPosPrnStr(split[y]+"\n");
                    
            }
        }
        
        for (int i=3; i<split.length-1; i++) {
            testo.append(split[i]+"\n");
            System.out.println(split[i]+"\n");
        }

        setting.prnStr(testo.toString());
        setting.mPosPrintAlign(Settings.MPOS_PRINT_ALIGN_RIGHT);
        setting.mPosPrintTextSize(Settings.MPOS_PRINT_TEXT_DOUBLE_HEIGHT);
        setting.mPosPrnStr(split[split.length-1]+"\n");
        
        setting.prnStart();

        callback.success(connect);
    }
}
