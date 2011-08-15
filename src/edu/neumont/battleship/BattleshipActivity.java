package edu.neumont.battleship;

import edu.neumont.battleship.http.HttpHandler;
import edu.neumont.battleship.http.RefreshTask;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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
		new SharedPrefsManager(getApplicationContext());
		EditText ed = (EditText) findViewById(R.id.edentername);
		ed.setText(SharedPrefsManager.getString(R.string.username, ""));
		if (LOCAL_LOGV)
		{
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
	
	public void ping(View view)
	{
		TextView tv = (TextView) findViewById(R.id.tvPing);
		RefreshTask task = new RefreshTask(BattleshipActivity.this);
		// TODO: this will never show up, because its in the same thread. the http request should be in its own thread, so it doesn't hang the UI Thread
		tv.setText("Pinging server...");
		task.execute(HttpHandler.connectionURL,"");
//		try
//		{
//			// this is temporary, and should be moved to the HttpPostTask
//			HttpHandler.sendGetRequest("");
//			// this *should* work, but it doesn't...
////			task.execute(HttpHandler.connectionURL,"");
//			tv.setText("Server found!");
//		} catch (Exception e)
//		{
//			tv.setText("Server not responding!");
//		}
	}
	
	private void nextView(View view, Class<? extends Activity> cls)
	{
		EditText tv = (EditText) findViewById(R.id.edentername);
		if (tv.getText() != null && tv.getText().length() > 0)
		{
			SharedPrefsManager.setString(getString(R.string.username), tv.getText().toString());
			Intent intent = new Intent(BattleshipActivity.this, cls);
			startActivity(intent);
		} else
		{
			new AlertDialog.Builder(BattleshipActivity.this).setTitle(R.string.entername)
					.setPositiveButton("OK", new OnClickListener() {
						public void onClick(DialogInterface dialog, int which)
						{
							dialog.dismiss();
						}
					}).show();
			
			// Toast.makeText(BattleshipActivity.this, R.string.entername,
			// Toast.LENGTH_SHORT).show();
		}
	}
	
	@Override
	protected void onPause()
	{
		// TODO Auto-generated method stub
		super.onPause();
		if (LOCAL_LOGV)
		{
			Log.v(TAG, "I was paused!");
		}
	}
	
	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		if (LOCAL_LOGV)
		{
			Log.v(TAG, "I was killed!");
		}
	}
}
