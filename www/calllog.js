var CallLog = function() {
};

CallLog.prototype.list = function(params, successCallback, failureCallback) {
	return PhoneGap.exec(successCallback, failureCallback, 'CallListPlugin', 'list',
			[ params ]);
};

CallLog.prototype.contact = function(params, successCallback, failureCallback) {
	return PhoneGap.exec(successCallback, failureCallback, 'CallListPlugin', 'contact',
			[ params ]);
};

CallLog.prototype.show = function(params, successCallback, failureCallback) {
	return PhoneGap.exec(successCallback, failureCallback, 'CallListPlugin', 'show',
			[ params ]);
};
CallLog.prototype.all = function(params, successCallback, failureCallback) 
{
    /* @param   successCallback
     * @param   failureCallback
     * @param   plugin name
     * @param   action
     * @param   JSONArray of parameters
     */ 
    return PhoneGap.exec(successCallback, failureCallback, 'CallListPlugin', 'all', [params]);
};
/*PhoneGap.addConstructor(function() {
	PhoneGap.addPlugin('CallLog', new CallLog());
	PluginManager.addService("CallListPlugin", "com.leafcut.ctrac.CallListPlugin");
});*/
//window.CallLog = new CallLog();

if (!window.plugins) {
window.plugins = {};
}
window.plugins.CallLog = new CallLog();
