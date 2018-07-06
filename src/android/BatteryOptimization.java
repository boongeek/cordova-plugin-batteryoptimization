package org.apache.cordova.batteryoptimization;

import at.moop.util.BatteryOptimizationUtil;
import android.support.v7.app.AlertDialog;
import android.content.Context;
import android.util.Log;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import com.google.firebase.analytics.FirebaseAnalytics;



import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by olegkunytskyi on 7/5/18.
 */

public class BatteryOptimization  extends CordovaPlugin {

  //  private BatteryOptimization batteryOptimization = null;

  //  private static final String DURATION_LONG = "long";

    private static final String LOG_TAG = "Battery Optimization";
  
  
  public void initialize (CordovaInterface cordova, CordovaWebView webView) {
    super.initialize(cordova, webView);
  }

    @Override
    public boolean execute(String action, JSONArray args,
                           final CallbackContext callbackContext) {
        // Verify that the user sent a 'show' action
        if (!action.equals("echo")) {
            callbackContext.error("\"" + action + "\" is not a recognized action.");
            return false;
        } else {

        /*
        String message;
        String duration;
        try {
            JSONObject options = args.getJSONObject(0);
            message = options.getString("message");
            duration = options.getString("duration");
        } catch (JSONException e) {
            callbackContext.error("Error encountered: " + e.getMessage());
            return false;
        }
*/

            final AlertDialog dialog = BatteryOptimizationUtil.getBatteryOptimizationDialog(
                    getApplicationContext(),
                    new BatteryOptimizationUtil.OnBatteryOptimizationAccepted() {
                        @Override
                        public void onBatteryOptimizationAccepted() {
                            FirebaseAnalytics.getInstance(getApplicationContext()).logEvent("battery_optimizations_accepted", null);

                            Log.d(LOG_TAG, "battery_optimizations_accepted");
                        }
                    },
                    new BatteryOptimizationUtil.OnBatteryOptimizationCanceled() {
                        @Override
                        public void onBatteryOptimizationCanceled() {
                            FirebaseAnalytics.getInstance(getApplicationContext()).logEvent("battery_optimizations_canceled", null);
                            Log.d(LOG_TAG, "battery_optimizations_canceled");
                        }
                    });
            if (dialog != null) dialog.show();




        /*
        // Create the toast
        Toast toast = Toast.makeText(cordova.getActivity(), message,
                DURATION_LONG.equals(duration) ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT);
        // Display toast
        toast.show();
       */


            // Send a positive result to the callbackContext
            PluginResult pluginResult = new PluginResult(PluginResult.Status.OK);
            callbackContext.sendPluginResult(pluginResult);
            return true;
        }


    }


}
