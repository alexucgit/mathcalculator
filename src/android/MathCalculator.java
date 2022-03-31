/***
 *
 * TEST V91 ALL VERSION

package com.dylee.plugin.mathcalculator;
import android.text.Layout;
import com.zcs.sdk.DriverManager;
import com.zcs.sdk.Printer;
import com.zcs.sdk.SdkResult;
import com.zcs.sdk.Sys;
import com.zcs.sdk.print.PrnStrFormat;
import com.zcs.sdk.print.PrnTextFont;
import com.zcs.sdk.print.PrnTextStyle;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONException;

public class MathCalculator extends CordovaPlugin {
  private static String TAG = "PosDemo";
  private DriverManager mDriverManager;
  private Sys mSys;
  private Printer mPrinter;
  private PrnStrFormat format;
  //private Context context;


  @Override
  public boolean execute(String action, String args, CallbackContext callbackContext) throws JSONException {
    if (action.equals("print")) {
      System.out.println("chiamo print");
      print(args, callbackContext);
      return true;
    }
    return false;
  }

  private void print(String args, CallbackContext callback) {
    //context = this.cordova.getActivity().getApplicationContext();
    new Thread(new Runnable() {
      @Override
      public void run() {
        mDriverManager= DriverManager.getInstance();
        mPrinter = mDriverManager.getPrinter();
        mSys = mDriverManager.getBaseSysDevice();


        callback.success("successoooo");
        System.out.println("mPrinter is " + mPrinter.getPrinterStatus());

        int printStatus = mPrinter.getPrinterStatus();

        StringBuffer testo = new StringBuffer();
        String[] split = args.split("##");
        System.out.println("LUNGHEZZA: " + split.length);
        format = new PrnStrFormat();
        format.setFont(PrnTextFont.MONOSPACE);

        if (printStatus == SdkResult.SDK_PRN_STATUS_PAPEROUT) {
        } else {
          System.out.println("ok, stampo...");


          if("B".equalsIgnoreCase(split[1].charAt(5)+"")){
            // BOOKING
            format.setStyle(PrnTextStyle.BOLD);
            format.setAli(Layout.Alignment.ALIGN_CENTER);
            format.setTextSize(40);

            mPrinter.setPrintAppendString(split[0].replace("\"", ""), format);
            System.out.println("0: " + split[0] + "\n");
            format.setStyle(PrnTextStyle.BOLD);
            format.setAli(Layout.Alignment.ALIGN_CENTER);
            format.setTextSize(30);

            mPrinter.setPrintAppendString(split[1], format);
            System.out.println("1: " + split[1] + "\n");

            format.setStyle(PrnTextStyle.NORMAL);
            format.setAli(Layout.Alignment.ALIGN_CENTER);
            format.setTextSize(25);
            mPrinter.setPrintAppendString(split[2], format);
            System.out.println("2: " + split[2] + "\n");

            format.setStyle(PrnTextStyle.BOLD);
            format.setAli(Layout.Alignment.ALIGN_CENTER);
            format.setTextSize(25);
            mPrinter.setPrintAppendString(split[3], format);
            System.out.println("3: " + split[3] + "\n");

            format.setStyle(PrnTextStyle.BOLD);
            format.setAli(Layout.Alignment.ALIGN_CENTER);
            format.setTextSize(30);
            mPrinter.setPrintAppendString(split[4], format);
            System.out.println("4: " + split[4] + "\n");

            format.setStyle(PrnTextStyle.BOLD);
            format.setAli(Layout.Alignment.ALIGN_CENTER);
            format.setTextSize(25);
            mPrinter.setPrintAppendString(split[5] + "\n", format);
            System.out.println("5: " + split[5] + "\n");

            for (int i = 6; i < split.length - 4; i++) {
              testo.append(split[i] + "\n");
              System.out.println(i + ": " + split[i] + "\n");
            }

            format.setStyle(PrnTextStyle.NORMAL);
            format.setAli(Layout.Alignment.ALIGN_NORMAL);
            format.setTextSize(25);
            mPrinter.setPrintAppendString(testo.toString(), format);

            format.setStyle(PrnTextStyle.BOLD);
            format.setAli(Layout.Alignment.ALIGN_OPPOSITE);
            format.setTextSize(40);
            mPrinter.setPrintAppendString(split[split.length - 4] + " €\n", format);
            System.out.println((split.length - 4) + ": " + split[split.length - 4] + "\n");

            format.setStyle(PrnTextStyle.BOLD);
            format.setAli(Layout.Alignment.ALIGN_CENTER);
            format.setTextSize(25);
            mPrinter.setPrintAppendString("BUON RELAX DA HELLOBEACH\n#PLAYYOURSUMMER\n\n\n", format);
            System.out.println("BUON RELAX");
            printStatus = mPrinter.setPrintStart();
            System.out.println("RISULTATO E :"+printStatus);


          } else {

            // ORDER
            format.setStyle(PrnTextStyle.BOLD);
            format.setAli(Layout.Alignment.ALIGN_CENTER);
            format.setTextSize(40);
            mPrinter.setPrintAppendString(split[0].replace("\"", ""), format);
            System.out.println("0: " + split[0] + "\n");

            format.setStyle(PrnTextStyle.NORMAL);
            format.setAli(Layout.Alignment.ALIGN_CENTER);
            format.setTextSize(20);
            mPrinter.setPrintAppendString(split[1], format);
            System.out.println("1: " + split[1] + "\n");

            format.setStyle(PrnTextStyle.NORMAL);
            format.setAli(Layout.Alignment.ALIGN_CENTER);
            format.setTextSize(20);
            mPrinter.setPrintAppendString(split[2], format);
            System.out.println("2: " + split[2] + "\n");

            format.setStyle(PrnTextStyle.BOLD);
            format.setAli(Layout.Alignment.ALIGN_CENTER);
            format.setTextSize(30);
            mPrinter.setPrintAppendString(split[3] + "\n", format);
            System.out.println("3: " + split[3] + "\n");

            for (int i = 4; i < split.length - 6; i++) {
              if (i == split.length - 7) {
                if (!"".equalsIgnoreCase(split[split.length - 6])) {
                  testo.append("\n************** NOTE **************\n");
                  testo.append(split[split.length - 6] + "\n");
                }
              }

              if (i < split.length - 7) {
                testo.append(split[i] + "\n");
                System.out.println(i + ": " + split[i] + "\n");
              }
            }

            format.setStyle(PrnTextStyle.NORMAL);
            format.setAli(Layout.Alignment.ALIGN_NORMAL);
            format.setTextSize(25);
            mPrinter.setPrintAppendString(testo.toString(), format);

            format.setStyle(PrnTextStyle.BOLD);
            format.setAli(Layout.Alignment.ALIGN_OPPOSITE);
            format.setTextSize(40);
            mPrinter.setPrintAppendString(split[split.length - 4] + " €\n", format);
            System.out.println((split.length - 4) + ": " + split[split.length - 4] + "\n");

            format.setStyle(PrnTextStyle.BOLD);
            format.setAli(Layout.Alignment.ALIGN_CENTER);
            format.setTextSize(25);
            mPrinter.setPrintAppendString("BUON RELAX DA HELLOBEACH\n#PLAYYOURSUMMER\n\n\n", format);
            System.out.println("BUON RELAX");
            printStatus = mPrinter.setPrintStart();
            System.out.println("RISULTATO E :"+printStatus);
          }
        }

        callback.success();
      }
    }).start();
  }

}

*/



/***
 *
package com.dylee.plugin.mathcalculator;

import android.content.Context;
import android.text.Layout;
import android.text.Layout.Alignment;
import android.util.Log;

import com.zcs.sdk.DriverManager;
import com.zcs.sdk.Printer;
import com.zcs.sdk.SdkResult;
import com.zcs.sdk.Sys;
import com.zcs.sdk.print.PrnStrFormat;
import com.zcs.sdk.print.PrnTextFont;
import com.zcs.sdk.print.PrnTextStyle;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONException;


public class MathCalculator extends CordovaPlugin {

  private Context context;

  private static String TAG = "PosDemo";
  private DriverManager mDriverManager;
  private Sys mSys;
  private Printer mPrinter;
  private PrnStrFormat format;


  @Override
  public boolean execute(String action, String args, CallbackContext callbackContext) throws JSONException {
    if (action.equals("print")) {
      System.out.println("chiamo print");
      print(args, callbackContext);
      return true;
    }
    return false;
  }

  private void print(String args, CallbackContext callback) {

    if (mSys == null || mDriverManager == null) {
      context = this.cordova.getActivity().getApplicationContext();
      mDriverManager= DriverManager.getInstance();
      mSys = mDriverManager.getBaseSysDevice();
    }
    callback.success("" + context);

    int statue = mSys.getFirmwareVer(new String[1]);
    if (statue != SdkResult.SDK_OK) {
      int sysPowerOn = mSys.sysPowerOn();
      Log.i(TAG, "sysPowerOn: " + sysPowerOn); try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace(); }
    }
    int init = mSys.sdkInit();

    String connect = "";
    if (init == SdkResult.SDK_OK) {
      connect = "Connect Res: ok";
    } else {
      connect = "Connect Res: ko";
    }


    StringBuffer testo = new StringBuffer();
    String[] split = args.split("##");
    System.out.println("LUNGHEZZA: " + split.length);

    PrnStrFormat psf = new PrnStrFormat();
    //psf.setFont(PrnTextFont.MONOSPACE);

    mPrinter = mDriverManager.getPrinter();
    int printStatus = mPrinter.getPrinterStatus();
    if (printStatus == SdkResult.SDK_PRN_STATUS_PAPEROUT) {
    } else {
      format = new PrnStrFormat();

      if("B".equalsIgnoreCase(split[1].charAt(5)+"")){
        // BOOKING
        psf.setStyle(PrnTextStyle.BOLD);
        psf.setAli(Alignment.ALIGN_CENTER);
        psf.setTextSize(40);
        mPrinter.setPrintAppendString(split[0].replace("\"", ""), psf);
        System.out.println("0: " + split[0] + "\n");

        psf.setStyle(PrnTextStyle.BOLD);
        psf.setAli(Alignment.ALIGN_CENTER);
        psf.setTextSize(30);
        mPrinter.setPrintAppendString(split[1], psf);
        System.out.println("1: " + split[1] + "\n");

        psf.setStyle(PrnTextStyle.NORMAL);
        psf.setAli(Alignment.ALIGN_CENTER);
        psf.setTextSize(25);
        mPrinter.setPrintAppendString(split[2], psf);
        System.out.println("2: " + split[2] + "\n");


        psf.setStyle(PrnTextStyle.BOLD);
        psf.setAli(Alignment.ALIGN_CENTER);
        psf.setTextSize(25);
        mPrinter.setPrintAppendString(split[3], psf);
        System.out.println("3: " + split[3] + "\n");

        psf.setStyle(PrnTextStyle.BOLD);
        psf.setAli(Alignment.ALIGN_CENTER);
        psf.setTextSize(30);
        mPrinter.setPrintAppendString(split[4], psf);
        System.out.println("4: " + split[4] + "\n");

        psf.setStyle(PrnTextStyle.BOLD);
        psf.setAli(Alignment.ALIGN_CENTER);
        psf.setTextSize(25);
        mPrinter.setPrintAppendString(split[5] + "\n", psf);
        System.out.println("5: " + split[5] + "\n");

        for (int i = 6; i < split.length - 4; i++) {

          testo.append(split[i] + "\n");
          System.out.println(i + ": " + split[i] + "\n");


        }

        psf.setStyle(PrnTextStyle.NORMAL);
        psf.setAli(Alignment.ALIGN_NORMAL);
        psf.setTextSize(25);
        mPrinter.setPrintAppendString(testo.toString(), psf);


        psf.setStyle(PrnTextStyle.BOLD);
        psf.setAli(Alignment.ALIGN_OPPOSITE);
        psf.setTextSize(40);
        mPrinter.setPrintAppendString(split[split.length - 4] + " €\n", psf);
        System.out.println((split.length - 4) + ": " + split[split.length - 4] + "\n");


        psf.setStyle(PrnTextStyle.BOLD);
        psf.setAli(Alignment.ALIGN_CENTER);
        psf.setTextSize(25);
        mPrinter.setPrintAppendString("BUON RELAX DA HELLOBEACH\n#PLAYYOURSUMMER\n\n\n", psf);
        System.out.println("BUON RELAX");

        printStatus = mPrinter.setPrintStart();














      } else {
        // ORDER
        psf.setStyle(PrnTextStyle.BOLD);
        psf.setAli(Alignment.ALIGN_CENTER);
        psf.setTextSize(40);
        mPrinter.setPrintAppendString(split[0].replace("\"", ""), psf);
        System.out.println("0: " + split[0] + "\n");

        psf.setStyle(PrnTextStyle.NORMAL);
        psf.setAli(Alignment.ALIGN_CENTER);
        psf.setTextSize(20);
        mPrinter.setPrintAppendString(split[1], psf);
        System.out.println("1: " + split[1] + "\n");

        psf.setStyle(PrnTextStyle.NORMAL);
        psf.setAli(Alignment.ALIGN_CENTER);
        psf.setTextSize(20);
        mPrinter.setPrintAppendString(split[2], psf);
        System.out.println("2: " + split[2] + "\n");


        psf.setStyle(PrnTextStyle.BOLD);
        psf.setAli(Alignment.ALIGN_CENTER);
        psf.setTextSize(30);
        mPrinter.setPrintAppendString(split[3] + "\n", psf);
        System.out.println("3: " + split[3] + "\n");

        for (int i = 4; i < split.length - 6; i++) {
          if (i == split.length - 7) {
            if (!"".equalsIgnoreCase(split[split.length - 6])) {
              testo.append("\n************** NOTE **************\n");
              testo.append(split[split.length - 6] + "\n");

            }
          }
          if (i < split.length - 7) {
            testo.append(split[i] + "\n");
            System.out.println(i + ": " + split[i] + "\n");
          }

        }

        psf.setStyle(PrnTextStyle.NORMAL);
        psf.setAli(Alignment.ALIGN_NORMAL);
        psf.setTextSize(25);
        mPrinter.setPrintAppendString(testo.toString(), psf);


        psf.setStyle(PrnTextStyle.BOLD);
        psf.setAli(Alignment.ALIGN_OPPOSITE);
        psf.setTextSize(40);
        mPrinter.setPrintAppendString(split[split.length - 4] + " €\n", psf);
        System.out.println((split.length - 4) + ": " + split[split.length - 4] + "\n");


        psf.setStyle(PrnTextStyle.BOLD);
        psf.setAli(Alignment.ALIGN_CENTER);
        psf.setTextSize(25);
        mPrinter.setPrintAppendString("BUON RELAX DA HELLOBEACH\n#PLAYYOURSUMMER\n\n\n", psf);
        System.out.println("BUON RELAX");

        printStatus = mPrinter.setPrintStart();
      }

    }

    callback.success(connect);
  }
}

*/


















package com.dylee.plugin.mathcalculator;
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
import android.text.Layout.Alignment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MathCalculator extends CordovaPlugin {
  private Context context;
  private Settings setting;
  private MposHandler handler;
  private static String TAG = "PosDemo";

  @Override
  public boolean execute(String action, String args, CallbackContext callbackContext) throws JSONException {
    if (action.equals("print")) {
      System.out.println("chiamo print");
      print(args, callbackContext);
      return true;
    }
    return false;
  }

  private void print(String args, CallbackContext callback) {
    if (handler == null || setting == null) {
      context = this.cordova.getActivity().getApplicationContext();
      handler = MposHandler.getInstance(context);
      setting = Settings.getInstance(handler);
    }
    callback.success("" + context);
    setting.mPosPowerOn();
    String connect = "";
    try {
      if (!handler.isConnected()) {
        connect = "Connect Res: " + handler.connect();
      } else {
        handler.close();
        connect = "ReConnect Res: " + handler.connect();
      }
      Thread.sleep(1000);
    } catch (Exception e) {
      // error call
    }

    StringBuffer testo = new StringBuffer();
    String[] split = args.split("##");
    System.out.println("LUNGHEZZA: " + split.length);

    PrnStrFormat psf = new PrnStrFormat();
    psf.setFont(PrnTextFont.MONOSPACE);

    if (false) {
    } else {
      psf = new PrnStrFormat();

      if("B".equalsIgnoreCase(split[1].charAt(5)+"")){
        // BOOKING
        psf.setStyle(PrnTextStyle.BOLD);
        psf.setAli(Alignment.ALIGN_CENTER);
        psf.setTextSize(40);
        setting.prnStr(split[0].replace("\"", ""), psf);
        System.out.println("0: " + split[0] + "\n");

        psf.setStyle(PrnTextStyle.BOLD);
        psf.setAli(Alignment.ALIGN_CENTER);
        psf.setTextSize(30);
        setting.prnStr(split[1], psf);
        System.out.println("1: " + split[1] + "\n");

        psf.setStyle(PrnTextStyle.NORMAL);
        psf.setAli(Alignment.ALIGN_CENTER);
        psf.setTextSize(25);
        setting.prnStr(split[2], psf);
        System.out.println("2: " + split[2] + "\n");

        psf.setStyle(PrnTextStyle.BOLD);
        psf.setAli(Alignment.ALIGN_CENTER);
        psf.setTextSize(25);
        setting.prnStr(split[3], psf);
        System.out.println("3: " + split[3] + "\n");

        psf.setStyle(PrnTextStyle.BOLD);
        psf.setAli(Alignment.ALIGN_CENTER);
        psf.setTextSize(30);
        setting.prnStr(split[4], psf);
        System.out.println("4: " + split[4] + "\n");

        psf.setStyle(PrnTextStyle.BOLD);
        psf.setAli(Alignment.ALIGN_CENTER);
        psf.setTextSize(25);
        setting.prnStr(split[5] + "\n", psf);
        System.out.println("5: " + split[5] + "\n");

        for (int i = 6; i < split.length - 4; i++) {
          testo.append(split[i] + "\n");
          System.out.println(i + ": " + split[i] + "\n");
        }

        psf.setStyle(PrnTextStyle.NORMAL);
        psf.setAli(Alignment.ALIGN_NORMAL);
        psf.setTextSize(25);
        setting.prnStr(testo.toString(), psf);

        psf.setStyle(PrnTextStyle.BOLD);
        psf.setAli(Alignment.ALIGN_OPPOSITE);
        psf.setTextSize(40);
        setting.prnStr(split[split.length - 4] + " €\n", psf);
        System.out.println((split.length - 4) + ": " + split[split.length - 4] + "\n");

        psf.setStyle(PrnTextStyle.BOLD);
        psf.setAli(Alignment.ALIGN_CENTER);
        psf.setTextSize(25);
        setting.prnStr("BUON RELAX DA HELLOBEACH\n#PLAYYOURSUMMER\n\n\n", psf);
        System.out.println("BUON RELAX");

        setting.prnStart();

      } else {

        // ORDER
        psf.setStyle(PrnTextStyle.BOLD);
        psf.setAli(Alignment.ALIGN_CENTER);
        psf.setTextSize(40);
        setting.prnStr(split[0].replace("\"", ""), psf);

        System.out.println("0: " + split[0] + "\n");
        psf.setStyle(PrnTextStyle.NORMAL);
        psf.setAli(Alignment.ALIGN_CENTER);
        psf.setTextSize(20);
        setting.prnStr(split[1], psf);
        System.out.println("1: " + split[1] + "\n");

        psf.setStyle(PrnTextStyle.NORMAL);
        psf.setAli(Alignment.ALIGN_CENTER);
        psf.setTextSize(20);
        setting.prnStr(split[2], psf);
        System.out.println("2: " + split[2] + "\n");

        psf.setStyle(PrnTextStyle.BOLD);
        psf.setAli(Alignment.ALIGN_CENTER);
        psf.setTextSize(30);
        setting.prnStr(split[3] + "\n", psf);
        System.out.println("3: " + split[3] + "\n");

        for (int i = 4; i < split.length - 6; i++) {
          if (i == split.length - 7) {
            if (!"".equalsIgnoreCase(split[split.length - 6])) {
              testo.append("\n************** NOTE **************\n");
              testo.append(split[split.length - 6] + "\n");
            }
          }

          if (i < split.length - 7) {
            testo.append(split[i] + "\n");
            System.out.println(i + ": " + split[i] + "\n");
          }
        }

        psf.setStyle(PrnTextStyle.NORMAL);
        psf.setAli(Alignment.ALIGN_NORMAL);
        psf.setTextSize(25);
        setting.prnStr(testo.toString(), psf);

        psf.setStyle(PrnTextStyle.BOLD);
        psf.setAli(Alignment.ALIGN_OPPOSITE);
        psf.setTextSize(40);
        setting.prnStr(split[split.length - 4] + " €\n", psf);
        System.out.println((split.length - 4) + ": " + split[split.length - 4] + "\n");

        psf.setStyle(PrnTextStyle.BOLD);
        psf.setAli(Alignment.ALIGN_CENTER);
        psf.setTextSize(25);
        setting.prnStr("BUON RELAX DA HELLOBEACH\n#PLAYYOURSUMMER\n\n\n", psf);
        System.out.println("BUON RELAX");

        setting.prnStart();
      }
    }

    callback.success(connect);
  }
}
/**
 END USBC
 **/


