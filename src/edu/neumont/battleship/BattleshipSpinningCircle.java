package edu.neumont.battleship;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ProgressBar;

public class BattleshipSpinningCircle extends Activity
{
	
	/**
	 * The name of the app
	 * Used for logging
	 */
	public static String TAG;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		//get the name of the app
		TAG = getText(R.string.app_name).toString();
		Log.v(TAG, "in OnCreated!");
		
		setContentView(R.layout.home);
		
		//run the progress bar
		final ProgressBar bar = (ProgressBar)findViewById(R.id.progress1);
		new Thread(new Runnable() {
			Handler mHandler = new Handler();
            public void run() {
                while (bar.getProgress() < 100) {
                	//work
                	//work
                	//work
                    // Update the progress bar
                    mHandler.post(new Runnable() {
                        public void run() {
                        	int current = bar.getProgress();
                            if(current > 100)
                            	bar.setProgress(0);
                            else
                            	bar.setProgress(current+1);
                            	
                        }
                    });
                }
            }
        }).start();

	}
	
	@Override
	protected void onPause() {
		super.onPause();
		Log.v(TAG, "in onPause");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.v(TAG, "in onDestroy");
	}
}
