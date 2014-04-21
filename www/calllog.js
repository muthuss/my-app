var CallListPlugin = function() {
};

CallListPlugin.prototype.list = function(params, successCallback, failureCallback) {
	return cordova.exec(successCallback, failureCallback, 'CallListPlugin', 'list',
			[ params ]);
};

CallListPlugin.prototype.contact = function(params, successCallback, failureCallback) {
	return Cordova.exec(successCallback, failureCallback, 'CallListPlugin', 'contact',
			[ params ]);
};

CallListPlugin.prototype.show = function(params, successCallback, failureCallback) {
	return Cordova.exec(successCallback, failureCallback, 'CallListPlugin', 'show',
			[ params ]);
};

        if (!window.plugins) {
            window.plugins = {};
        }
        //window.plugins.CallLog = new CallLog();
		window.plugins.CallListPlugin = new CallListPlugin();