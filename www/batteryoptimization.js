var exec = require ('cordova/exec');
var PLUGIN_NAME = 'BatteryOptimization';

var BatteryOptimization = {
echo: function(phrase, cb) {
exec (cb, null, PLUGIN_NAME, 'echo', [phrase]);
}

};


module.exports = BatteryOptimization;
