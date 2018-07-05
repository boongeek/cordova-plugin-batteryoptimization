var BatteryOptimization = function() {};

/**
 * Acquire a new wake-lock (keep device awake)
 *
 * @param successCallback function to be called when the wake-lock was acquired successfully
 * @param errorCallback function to be called when there was a problem with acquiring the wake-lock
 */
BatteryOptimization.prototype.show = function(successCallback,failureCallback) {
  

    cordova.exec(successCallback, failureCallback, 'BatteryOptimization', 'show', []);
}

module.exports = new BatteryOptimization();
