/**
 * 
 */
package com.fusioncharts.phonegap.plugin;

import org.json.*;

import android.database.*;
import android.util.Log;
 
import com.phonegap.api.Plugin;
import com.phonegap.api.PluginResult;
import com.phonegap.api.PluginResult.Status;

/**
 * @author Sudipto Choudhury
 *
 */
public class CallLog extends Plugin {
	

	/* (non-Javadoc)
	 * @see com.phonegap.api.Plugin#execute(java.lang.String, org.json.JSONArray, java.lang.String)
	 */
	@Override
	public PluginResult execute(String actionName, JSONArray arguments, String callback) 
	{
		
		Log.d("CallLog_Plugin", "Plugin Called");
		JSONObject callLogs = new JSONObject();
		PluginResult result = null;
		
		
		try {
			switch (getActionItem(actionName))
			{
				case 1:
					Log.d("CallLog_Plugin", "ALL: starting ...getAllCallLog() ");
					callLogs = getAllCallLog(arguments);
					Log.d("CallLog_Plugin", "getAllCallLog() returns -> "+ callLogs);
					result = new PluginResult(Status.OK, callLogs);
					break;
				case 2:
					Log.d("CallLog_Plugin", "Returning "+ callLogs);
					callLogs = getLastCallLog(arguments);
					result = new PluginResult(Status.OK, callLogs);
					break;
				case 3:
					Log.d("CallLog_Plugin", "Returning "+ callLogs);
					callLogs = getTimeRangeCallLog(arguments);
					result = new PluginResult(Status.OK, callLogs);
					break;
				
				default:
					result = new PluginResult(Status.INVALID_ACTION);
					Log.d("CallLog_Plugin", "Invalid action : "+actionName+" passed");
			}
		} catch (JSONException jsonEx) {
			Log.d("CallLog_Plugin", "Got JSON Exception "+ jsonEx.getMessage());
			result = new PluginResult(Status.JSON_EXCEPTION);
		}
		
	
		
		return result;
	}

	
	private JSONObject getAllCallLog(JSONArray requirements) throws JSONException
	{
		JSONObject callLog = new JSONObject();
		
		String[] strFields = {
		        android.provider.CallLog.Calls.DATE,
		        android.provider.CallLog.Calls.NUMBER, 
		        android.provider.CallLog.Calls.TYPE,
		        android.provider.CallLog.Calls.DURATION,
		        android.provider.CallLog.Calls.NEW,
		        android.provider.CallLog.Calls.CACHED_NAME,
		        android.provider.CallLog.Calls.CACHED_NUMBER_TYPE,
		        android.provider.CallLog.Calls.CACHED_NUMBER_LABEL//,
		        //android.provider.CallLog.Calls.CONTENT_TYPE,
		        //android.provider.CallLog.Calls.CONTENT_ITEM_TYPE,
		        //android.provider.CallLog.Calls.DEFAULT_SORT_ORDER
		         
		        
		};
		//String strOrder = android.provider.CallLog.Calls.DATE + " DESC"; 
		Log.d("CallLog_Plugin", "Will now call SQL to get cursor");
		
		
		try {
			Cursor callLogCursor = ctx.getContentResolver().query(
			        android.provider.CallLog.Calls.CONTENT_URI,
			        strFields,
			        null,
			        null,
			        android.provider.CallLog.Calls.DEFAULT_SORT_ORDER
			    );
		
		
		
		int callCount = callLogCursor.getCount();
		Log.d("CallLog_Plugin", "Cursor count:"+ callCount);

		if(callCount>0){
			JSONArray callLogItem = new JSONArray();
			JSONArray callLogItems = new JSONArray();
			
			String[] columnNames = callLogCursor.getColumnNames();
			
			callLogCursor.moveToFirst();
			do
			{
				callLogItem.put(callLogCursor.getLong(0));
				callLogItem.put(callLogCursor.getString(1));
				callLogItem.put(callLogCursor.getInt(2));
				callLogItem.put(callLogCursor.getLong(3));
				callLogItem.put(callLogCursor.getInt(4));
				callLogItem.put(callLogCursor.getString(5));
				callLogItem.put(callLogCursor.getInt(6));
				//callLogItem.put(callLogCursor.getString(7));
				//callLogItem.put(callLogCursor.getString(8));
				//callLogItem.put(callLogCursor.getString(9));
				
				callLogItems.put(callLogItem);
				callLogItem = new JSONArray();
				
			}while(callLogCursor.moveToNext());
			
			Log.d("CallLog_Plugin", "Columns:"+ columnNames);//callLog.put("Columns", columnNames);
			callLog.put("Rows", callLogItems);
			
		}
		
		
		callLogCursor.close();
		
		
		}catch(Exception e)
		{
			
			Log.d("CallLog_Plugin", " ERROR : SQL to get cursor: ERROR " + e.getMessage());
		}
		

		
		return callLog;
	}
	private JSONObject getLastCallLog(JSONArray requirements)
	{
		
		return new JSONObject();
	}
	private JSONObject getTimeRangeCallLog(JSONArray requirements)
	{
	
		/*
		 * DATE (long-integer)
		 * NUMBER (text)
		 * TYPE[1- INCOMING_TYPE,3- MISSED_TYPE,2- OUTGOING_TYPE] (int-INTEGER)
		 * DURATION (long-INTEGER)
		 * NEW (boolean-INTEGER)
		 * CACHED_NAME (text)
		 * CACHED_NUMBER_TYPE (integer)
		 * CACHED_NUMBER_LABEL(text)
		 * CONTENT_TYPE (uri)
		 * CONTENT_ITEM_TYPE (uri)
		 * DEFAULT_SORT_ORDER (text)
		 * 
		 * [CONTENT_FILTER_URI,CONTENT_URI]
		 * getLastOutgoingCall(Context context)
		 */
		
		/*
		String[] strFields = {
		        android.provider.CallLog.Calls.NUMBER, 
		        android.provider.CallLog.Calls.TYPE,
		        android.provider.CallLog.Calls.CACHED_NAME,
		        android.provider.CallLog.Calls.CACHED_NUMBER_TYPE,
		        android.provider.CallLog.Calls.CACHED_NUMBER_LABEL,
		        android.provider.CallLog.Calls.CONTENT_ITEM_TYPE,
		        android.provider.CallLog.Calls.CONTENT_TYPE,
		        android.provider.CallLog.Calls.DATE,
		        android.provider.CallLog.Calls.DEFAULT_SORT_ORDER,
		        android.provider.CallLog.Calls.DURATION,
		        android.provider.CallLog.Calls.NEW
		        
		};
		
		
		
		String strOrder = android.provider.CallLog.Calls.DATE + " DESC"; 
		 
		Cursor mCallCursor = ctx.getContentResolver().query(
		        android.provider.CallLog.Calls.CONTENT_URI,
		        strFields,
		        null,
		        null,
		        strOrder
		        );
		
		*/
		return new JSONObject();
	}
	
	private int getActionItem(String actionName) throws JSONException 
	{
		JSONObject actions = new JSONObject("{'all':1,'last':2,'time':3}");
		if (actions.has(actionName))
			return actions.getInt(actionName);

		return 0;
	}
}
