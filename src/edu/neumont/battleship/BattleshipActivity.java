package edu.neumont.battleship;

import java.io.StringReader;
import java.io.StringWriter;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import edu.neumont.battleship.http.HttpHandler;
import edu.neumont.battleship.http.Opponent;
import edu.neumont.battleship.http.XMLStringBuilder;

public class BattleshipActivity extends Activity {
	public static final String TAG = "Battleship";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Log.v(TAG, "in oncreate");

		try {
			String response = HttpHandler.postData(HttpHandler.connectionURL,XMLStringBuilder.newGame("Joe", Opponent.Edison));
			Log.i(TAG, "Posted, here's response: "); 
			Log.i(TAG,response);

			Log.i(TAG, "this is useless code");
		} catch (Exception e) {
			Log.e(TAG, "Exception", e);
		}
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.v(TAG,"I was paused!");
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.i(TAG,"I was killed!");
	}
}
