package edu.neumont.battleship.http;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.AsyncTask;
import edu.neumont.battleship.R;
import edu.neumont.battleship.testharness.ServerComm;

public class HttpPostTask extends AsyncTask<String, Void, String>
{
	ProgressDialog pd;
	Context ctx;
	
	public HttpPostTask(Context ctx, String title)
	{
		this.ctx = ctx;
		pd = new ProgressDialog(ctx);
		pd.setTitle(title);
	}
	
	@Override
	protected String doInBackground(String... params)
	{
		this.publishProgress();
		try
		{
			//TODO: can we make this use BattleshipServerConnector?
			return ServerComm.call(params[0], params[1], "application/xml");
		} catch (Exception e)
		{
			this.cancel(true);
		}
		return null;
	}
	
	@Override
	protected void onPostExecute(String result)
	{
		pd.dismiss();
	}
	
	@Override
	protected void onCancelled()
	{
		pd.dismiss();
		new AlertDialog.Builder(ctx).setTitle(R.string.servernotfound)
				.setPositiveButton("OK", new OnClickListener() {
					public void onClick(DialogInterface dialog, int which)
					{
						dialog.dismiss();
					}
				}).show();
	}
}
