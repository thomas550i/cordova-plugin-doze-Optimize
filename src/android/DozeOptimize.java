package cordova.plugins.DozeOptimize;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;




import android.os.Build;
import android.os.Environment;
import android.util.Base64;
import android.net.Uri;
import android.net.ConnectivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.provider.Settings;
import android.provider.Settings.SettingNotFoundException;

import static android.net.ConnectivityManager.RESTRICT_BACKGROUND_STATUS_DISABLED;
import static android.net.ConnectivityManager.RESTRICT_BACKGROUND_STATUS_ENABLED;
import static android.net.ConnectivityManager.RESTRICT_BACKGROUND_STATUS_WHITELISTED;

/**
 * This class echoes a string called from JavaScript.
 */
public class DozeOptimize extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

        Context context = cordova.getActivity().getApplicationContext();
        String packageName = context.getPackageName();

        if (action.equals("IsIgnoringBatteryOptimizations")) {
            try {

                if (Build.VERSION.SDK_INT>Build.VERSION_CODES.LOLLIPOP_MR1) {

                    String message ="";
                    PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
                    if (pm.isIgnoringBatteryOptimizations(packageName)) {
                        message ="true";
                    }
                    else
                    {
                        message ="false";
                    }
                    callbackContext.success(message);
                    return true;
                }
                else
                {
                    callbackContext.error("BATTERY_OPTIMIZATIONS Not available.");
                    return false;
                }
            } catch (Exception e) {
                callbackContext.error("IsIgnoringBatteryOptimizations: failed N/A");
                return false;
            }
        }else if (action.equals("RequestOptimizations")) {
            try {

                if (Build.VERSION.SDK_INT>Build.VERSION_CODES.LOLLIPOP_MR1) {

                    String message ="Optimizations Requested Successfully";

                    Intent intent = new Intent();
                    intent.setAction(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setData(Uri.parse("package:" + packageName));
                        context.startActivity(intent);

                    callbackContext.success(message);
                    return true;
                }
                else
                {
                    callbackContext.error("BATTERY_OPTIMIZATIONS Not available.");
                    return false;
                }
            } catch (Exception e) {
                callbackContext.error("N/A");
                return false;
            }
        } else if (action.equals("RequestOptimizationsMenu")) {
            try {

                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {

                    Intent intent = new Intent();
                    PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
                    if (pm.isIgnoringBatteryOptimizations(packageName)){
                        intent.setAction(Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }

                    callbackContext.success("requested");
                    return true;
                }
                else
                {
                    callbackContext.error("BATTERY_OPTIMIZATIONS Not available.");
                    return false;
                }
            } catch (Exception e) {
                callbackContext.error("RequestOptimizationsMenu: failed N/A");
                return false;
            }
        }else if(action.equals("IsIgnoringDataSaver")) {
             try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

                    String message ="";
                    ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

                    switch (connMgr.getRestrictBackgroundStatus())
                    {
                        case RESTRICT_BACKGROUND_STATUS_ENABLED:
                            // The app is whitelisted. Wherever possible,
                            // the app should use less data in the foreground and background.
                            message = "false";
                            break;

                        case RESTRICT_BACKGROUND_STATUS_WHITELISTED:
                            // Background data usage is blocked for this app. Wherever possible,
                            // the app should also use less data in the foreground.
                        case RESTRICT_BACKGROUND_STATUS_DISABLED:
                            // Data Saver is disabled. Since the device is connected to a
                            // metered network, the app should use less data wherever possible.
                             message = "true";
                            break;
                    }
                    callbackContext.success(message);
                    return true;

                }
                else
                {
                    callbackContext.error("DATA_SAVER Not available.");
                    return false;
                }
            } catch (Exception e) {
                callbackContext.error("IsIgnoringDataSaver: failed N/A");
                return false;
            }
        }else  if (action.equals("RequestDataSaverMenu")) {
            try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    Intent intent = new Intent();
                    ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

                    intent.setAction(Settings.ACTION_IGNORE_BACKGROUND_DATA_RESTRICTIONS_SETTINGS);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setData(Uri.parse("package:" + packageName));
                    context.startActivity(intent);

                    callbackContext.success("requested");
                    return true;
                }else{
                    callbackContext.error("DATA_SAVER Not available.");
                    return false;
                }
            } catch (Exception e) {
                callbackContext.error("RequestDataSaverMenu failed: N/A");
                return false;
            }
        }
        return false;
    }
}
