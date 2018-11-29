var exec = require('cordova/exec');

exports.RequestOptimizations = function(success, error) {
    exec(success, error, "DozeOptimize", "RequestOptimizations", []);
};

exports.IsIgnoringBatteryOptimizations = function(success, error) {
    exec(success, error, "DozeOptimize", "IsIgnoringBatteryOptimizations", []);
};

exports.IsIgnoringDataSaver = function(success, error) {
    exec(success, error, "DozeOptimize", "IsIgnoringDataSaver", []);
};

exports.RequestOptimizationsMenu = function(success, error) {
    exec(success, error, "DozeOptimize", "RequestOptimizationsMenu", []);
};

exports.RequestDataSaverMenu = function(success, error) {
    exec(success, error, "DozeOptimize", "RequestDataSaverMenu", []);
};
