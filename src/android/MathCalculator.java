// package cordova.plugin.mathcalculator;
package com.dylee.plugin.mathcalculator;

/*

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

import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
*/

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.imagpay.Constants;
import com.imagpay.PrnStrFormat;
import com.imagpay.Settings;
import com.imagpay.SwipeEvent;
import com.imagpay.SwipeListener;
import com.imagpay.enums.CardDetected;
import com.imagpay.enums.EmvStatus;
import com.imagpay.enums.PrintStatus;
import com.imagpay.enums.PrnTextFont;
import com.imagpay.enums.PrnTextStyle;
import com.imagpay.mpos.MposHandler;
import com.imagpay.utils.HCBoolean;
import com.imagpay.utils.MessageDigestUtils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.Layout.Alignment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
	    
	PrnStrFormat psf = new PrnStrFormat();
	psf.setFont(PrnTextFont.MONOSPACE);
	psf.setStyle(PrnTextStyle.BOLD);
	setting.prnStr("HELLOBEACH", psf);
        
	    /*
    	setting.mPosPrintFontSwitch(Settings.MPOS_PRINT_FONT_NEW);
	// show print receipts
	setting.mPosPrintAlign(Settings.MPOS_PRINT_ALIGN_CENTER);
	setting.mPosPrintTextSize(Settings.MPOS_PRINT_TEXT_DOUBLE_HEIGHT);
	setting.mPosPrnStr("POS Signed Order");
	setting.mPosPrintLn();*/
        
        setting.mPosPrintAlign(Settings.MPOS_PRINT_ALIGN_LEFT);
        setting.mPosPrintTextSize(Settings.MPOS_PRINT_TEXT_NORMAL);
            
            //setting.mPosPrintAlign(Settings.MPOS_PRINT_ALIGN_CENTER);
            //setting.mPosPrintTextSize(Settings.MPOS_PRINT_TEXT_DOUBLE_SIZE);
            //setting.mPosPrnStr(split[0]+"\n");

            //setting.mPosPrintAlign(Settings.MPOS_PRINT_ALIGN_CENTER);
            //setting.mPosPrintTextSize(Settings.MPOS_PRINT_TEXT_NORMAL);
            //setting.mPosPrnStr(split[1]+"\n");
            //setting.mPosPrnStr(split[2]+"\n");

            //setting.mPosPrintAlign(Settings.MPOS_PRINT_ALIGN_LEFT);

            /*for (int i=0; i<split.length-1; i++) {
                testo.append(split[i]+"\n");
                System.out.println(split[i]+"\n");
            }*/

            //setting.prnStr(testo.toString());
            //setting.mPosPrintAlign(Settings.MPOS_PRINT_ALIGN_RIGHT);
            //setting.mPosPrintTextSize(Settings.MPOS_PRINT_TEXT_DOUBLE_HEIGHT);
            //setting.mPosPrnStr(split[split.length-1]+"\n");

            setting.prnStart();
         
        
        callback.success(connect);
    }
}
