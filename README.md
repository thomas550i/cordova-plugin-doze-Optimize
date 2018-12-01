# cordova-plugin-doze-Optimize

# Whitelisting an Android application programmatically 

If you want to run your application in background mode and it should standby even battery optimization enabled, then this Cordova plugin is used to check the doze or battery optimization status and also it help to request whitelist popup for battery optimization.

This will also help check if your app is whitelisted from the Data Saver options in Android 7+ 



## Getting Started

You have to install this plugin in to your cordova project

### Installing

What things you need to install the software and how to install them

```
cordova plugin add https://github.com/thomas550i/cordova-plugin-doze-Optimize
```

### Usage 

Sample Code to get the status of your app 

```
cordova.plugins.DozeOptimize.IsIgnoringBatteryOptimizations(function (responce){
      console.log("IsIgnoringBatteryOptimizations: "+responce);
          if(responce=="false")
          {
            console.log("Application not Ignoring Battery Optimizations");
          }
          else
          {
            console.log("Application already Ignoring Battery Optimizations");
          }		
    }, function (error){
    console.error("IsIgnoringBatteryOptimizations Error"+error);
    
    });
```

```
   cordova.plugins.DozeOptimize.IsIgnoringDataSaver(function (response){
        if(responce=="false")
        {
            console.log("Application not Ignoring data saver");
        }
        else
        {
            console.log("Application already Ignoring data saver and is probably whitelisted.");
        }		
   }, function (error){
       console.log(error);
   }); 
```

Sample Code to Popup ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS to whitelist your app.

```
    cordova.plugins.DozeOptimize.RequestOptimizations(function (responce){
        console.log(responce); // Will give "Optimizations Requested Successfully"
    }, function (error){
        console.error("BatteryOptimizations Request Error"+error);			
    });
```

Full Code of usage with step one and two

```
cordova.plugins.DozeOptimize.IsIgnoringBatteryOptimizations(function (responce){
      console.log("IsIgnoringBatteryOptimizations: "+responce);
          if(responce=="false")
          {
            cordova.plugins.DozeOptimize.RequestOptimizations(function (responce){
              console.log(responce);
            }, function (error){
            console.error("BatteryOptimizations Request Error"+error);			
            });
          }
          else
          {
            console.log("Application already Ignoring Battery Optimizations");
          }		
    }, function (error){
    console.error("IsIgnoringBatteryOptimizations Error"+error);    
    });
```
### Important notes

Battery Optimization will work only Android 6.0 and higher. if you using this plugin below 6.0 you will get "BATTERY_OPTIMIZATIONS Not available" as a result. 

# Contributors are welcome! send Request to thomas550i@gmail.com
