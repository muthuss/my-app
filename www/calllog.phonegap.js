    var CallLog = function() {};
    CallLog.prototype.all = function(params, successCallback, failureCallback) 
    {
        /* @param   successCallback
         * @param   failureCallback
         * @param   plugin name
         * @param   action
         * @param   JSONArray of parameters
         */ 
        return PhoneGap.exec(successCallback, failureCallback, 'CallLog', 'all', [params]);
    };
    
   /**
    * <ul>
    * <li>Register the CallLogPlugin Javascript plugin.</li>
    * <li>Also register native call which will be called when this plugin runs</li>
    * </ul>
    */
    PhoneGap.addConstructor( function() {
          //Register the javascript plugin with PhoneGap
          PhoneGap.addPlugin("calllog", new CallLog());
    
          //Register the native class of plugin with PhoneGap
          PluginManager.addService("CallLog","com.fusioncharts.phonegap.plugin.CallLog");
    });
   
   
