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
			if(!isCancelled())
				return HttpHandler.postData(params[0], params[1]);
		} catch (Exception e)
		{
			this.cancel(true);
		}
		return null;
	}
	
	
	@Override
	protected void onPostExecute(String result)
	{
		if(!isCancelled())
			refreshTextView.setText("Server found!");
		else
			refreshTextView.setText("Server not found!");
	}
	
	@Override
	protected void onCancelled()
	{
		refreshTextView.setText("Server not found!");
	}
	
	
}
