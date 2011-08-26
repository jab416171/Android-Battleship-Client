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
import android.view.Window;
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
		if (LOCAL_LOGV)
		{
			Log.v(TAG, "in oncreate");
		}
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.main);
		TAG = getText(R.string.app_name).toString();
		new SharedPrefsManager(getApplicationContext());
		EditText ed = (EditText) findViewById(R.id.edentername);
		ed.setText(SharedPrefsManager.getString(R.string.username, ""));
		
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
		tv.setText("Pinging server...");
		task.execute(HttpHandler.connectionURL,"");
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
		}
	}
	
	@Override
	protected void onPause()
	{
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
