package edu.neumont.battleship;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import edu.neumont.battleship.http.BattleshipServerConnector;
import edu.neumont.battleship.http.Direction;
import edu.neumont.battleship.http.Opponent;
import edu.neumont.battleship.http.ShipType;
import edu.neumont.battleship.http.XMLResponse;

public class BattleshipActivity extends Activity {
	public static String TAG;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		TAG = getText(R.string.app_name).toString();
		Log.v(TAG, "in oncreate");

		try {
			//String response = HttpHandler.postData(HttpHandler.connectionURL+"NewGame",XMLStringBuilder.newGame("Joe", Opponent.Edison));
			// I want to do this:
			XMLResponse response = BattleshipServerConnector.newGame("Joe",Opponent.Edison);
			Log.i(TAG, "Posted, here's response: "); 
			Log.i(TAG,response.getRawXML()+"");
			response = BattleshipServerConnector.placeShip("A1",Direction.DOWN,ShipType.Battleship);
			Log.i(TAG, "Posted, here's response: "); 
			Log.i(TAG,response.getRawXML()+"");

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
		Log.v(TAG,"I was killed!");
	}
}
