package fusioncharts.android.app.callchart;

import com.phonegap.*;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.WindowManager;
import android.util.Log;


public class Application extends DroidGap {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
       	super.onCreate(savedInstanceState);
    	super.init();
    	this.appView.getSettings().setPluginsEnabled(true);
    	getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN | WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
    	super.loadUrl("file:///android_asset/www/index2.html"); 
    }
    /*
    @Override 
    protected void onActivityResult(int requestCode, int resultCode, Intent data) { 
    	super.onActivityResult(requestCode, resultCode, data); 
    	if (requestCode == 123) { 
    		this.finish(); 
    	} 

    } 
    */
    @Override
    protected void onStart() {
        super.onStart();
        Log.d("CallLog_Application", "Application started in onStart event");
        // The activity is about to become visible.
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("CallLog_Application", "Application resumed onResume called");
        // The activity has become visible (it is now "resumed").
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.d("CallLog_Application", "Application pause, event onPause called");
        //System.exit(0);
        // Another activity is taking focus (this activity is about to be "paused").
    }
    @Override
    protected void onStop() {
        super.onStop();
        /* Way one */
        //android.os.Process.killProcess(android.os.Process.myPid());

        //SharedPreferences settings = getSharedPreferences(PREF_FILE_NAME, 0);
        //Editor e = settings.edit();
        //e.clear(); 
        //e.commit();
        
        /* Way Two */
        //System.exit(0);
        Log.d("CallLog_Application", "Application stopped, event onStop called");
        //System.exit(0);
        
        // The activity is no longer visible (it is now "stopped")
    }
    @Override
	public void onDestroy() {
        super.onDestroy();
        Log.d("CallLog_Application", "Application destoryed, event onDestory called");
        //System.exit(0);
        // The activity is about to be destroyed.
    }
}
