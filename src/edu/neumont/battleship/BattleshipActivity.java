package edu.neumont.battleship;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class BattleshipActivity extends Activity
{
	private static final boolean LOCAL_LOGV = false;
	public static String TAG;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		TAG = getText(R.string.app_name).toString();
		if (LOCAL_LOGV) {
			Log.v(TAG, "in oncreate");
		}
		
	}
	
	public void newgame(View view)
	{
		nextView(view, BattleshipNewGame.class);
	}
	
	public void joingame(View view)
	{
		nextView(view, BattleshipJoinGame.class);
	}
	
	public void nextView(View view, Class cls)
	{
		EditText tv = (EditText) findViewById(R.id.edentername);
		if (tv.getText() != null && tv.getText().length() > 0)
		{
			SharedPrefsManager.setString(getString(R.string.username), tv.getText().toString());
			Intent intent = new Intent(BattleshipActivity.this, cls);
			startActivity(intent);
		} else
		{
			Toast.makeText(BattleshipActivity.this, R.string.entername, Toast.LENGTH_SHORT).show();
		}
	}
	
	@Override
	protected void onPause()
	{
		// TODO Auto-generated method stub
		super.onPause();
		if (LOCAL_LOGV) {
			Log.v(TAG, "I was paused!");
		}
	}
	
	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		if (LOCAL_LOGV) {
			Log.v(TAG, "I was killed!");
		}
	}
}
