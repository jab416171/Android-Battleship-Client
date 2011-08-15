package edu.neumont.battleship.http;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.TextView;
import edu.neumont.battleship.R;

public class RefreshTask extends AsyncTask<String, Void, String>
{
	TextView refreshTextView;
	public RefreshTask(Activity activity)
	{
		refreshTextView = (TextView) activity.findViewById(R.id.tvPing);
	}
	@Override
	protected String doInBackground(String... params)
	{
		try
		{
			return HttpHandler.postData(params[0], params[1]);
		} catch (Exception e)
		{
			refreshTextView.setText("Server not found!");
		}
		// call publishProgress() to make onProgressUpdate(Void... values) be called
		return null;
	}
	
	@Override
	protected void onPreExecute()
	{
		super.onPreExecute();
		// things to do before the task executes, ex, showing progress dialog
	}
	
	@Override
	protected void onProgressUpdate(Void... values)
	{
		super.onProgressUpdate(values);
		// things to do while executing, ex, updating progress dialog
	}
	
	@Override
	protected void onPostExecute(String result)
	{
//		super.onPostExecute(result);
		refreshTextView.setText("Server found!");
		// things to do after executing, ex, hiding/killing the progress dialog, and doing whatever you need to with the string that's passed in (the same string that doInBackground returns)
	}
	
}
