package edu.neumont.battleship;

import edu.neumont.battleship.R;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class BattleshipActivity extends Activity {
	public static final String TAG = "Battleship"; 
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        try
        {
        	Log.i(TAG, "message");
        } catch(Exception e)
        {
        	Log.e(TAG, "Exception", e);
        }
    }
}
