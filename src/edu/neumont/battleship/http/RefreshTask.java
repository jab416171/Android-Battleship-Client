package edu.neumont.battleship.http;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.TextView;
import edu.neumont.battleship.R;

public class RefreshTask extends AsyncTask<String, Void, String>
{
	private final TextView refreshTextView;
	private final Activity activity;
	public RefreshTask(Activity activity)
	{
		this.activity = activity;
		refreshTextView = (TextView) activity.findViewById(R.id.tvPing);
	}
	@Override
	protected String doInBackground(String... params)
	{
		try
		{
			this.publishProgress();
			if(!isCancelled())
				return BattleshipServerConnector.getGameList();
		} catch (Exception e)
		{
			this.cancel(true);
		}
		return null;
	}

	@Override
	protected void onPreExecute()
	{
		activity.setProgressBarIndeterminateVisibility(true);
	}
	
	@Override
	protected void onPostExecute(String result)
	{
		if(!isCancelled())
			refreshTextView.setText("Server found!");
		else
			refreshTextView.setText("Server not found!");
		activity.setProgressBarIndeterminateVisibility(false);
	}
	
	@Override
	protected void onCancelled()
	{
		refreshTextView.setText("Server not found!");
	}
	
	
}
