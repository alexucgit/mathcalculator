var exec = require('cordova/exec');

module.exports.print = function (arg0, success, error) {
    exec(success, error, 'MathCalculator', 'print', [arg0]);
};
