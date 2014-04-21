var CallLog = function() {
};

CallLog.prototype.list = function(params, successCallback, failureCallback) {
	return Cordova.exec(successCallback, failureCallback, 'CallListPlugin', 'list',
			[ params ]);
};

CallLog.prototype.contact = function(params, successCallback, failureCallback) {
	return Cordova.exec(successCallback, failureCallback, 'CallListPlugin', 'contact',
			[ params ]);
};

CallLog.prototype.show = function(params, successCallback, failureCallback) {
	return Cordova.exec(successCallback, failureCallback, 'CallListPlugin', 'show',
			[ params ]);
};

        if (!window.plugins) {
            window.plugins = {};
        }
        window.plugins.CallLog = new CallLog();