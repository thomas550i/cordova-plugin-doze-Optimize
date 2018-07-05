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
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.provider.Settings;
import android.provider.Settings.SettingNotFoundException;

/**
 * This class echoes a string called from JavaScript.
 */
public class DozeOptimize extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		
		if (action.equals("IsIgnoringBatteryOptimizations")) {
            try {
				
				if (Build.VERSION.SDK_INT>Build.VERSION_CODES.LOLLIPOP_MR1) {
					
					String message ="";
					Context context = cordova.getActivity().getApplicationContext();
					String packageName = context.getPackageName();
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
					callbackContext.error("BATTERY_OPTIMIZATIONS Not available");
					return false;
				}
            } catch (Exception e) {
                callbackContext.error("N/A");
                return false;
            }
        }
		
		
		if (action.equals("RequestOptimizations")) {
            try {
				
				if (Build.VERSION.SDK_INT>Build.VERSION_CODES.LOLLIPOP_MR1) {
					
					String message ="Optimizations Requested Successfully";
					Context context = cordova.getActivity().getApplicationContext();
					String packageName = context.getPackageName();
					
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
					callbackContext.error("BATTERY_OPTIMIZATIONS Not available");
					return false;
				}
            } catch (Exception e) {
                callbackContext.error("N/A");
                return false;
            }
        }
		
        return false;
    }

   
}
