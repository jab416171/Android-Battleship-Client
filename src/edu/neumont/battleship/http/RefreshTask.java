package edu.neumont.battleship.http;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.TextView;
import edu.neumont.battleship.R;

public class RefreshTask extends AsyncTask<String, Void, Void>
{
	private final TextView refreshTextView;
	private final Activity activity;
	public RefreshTask(Activity activity)
	{
		this.activity = activity;
		refreshTextView = (TextView) activity.findViewById(R.id.tvPing);
	}
	@Override
	protected Void doInBackground(String... params)
	{
			this.publishProgress();
			this.cancel(!isCancelled() && BattleshipServerConnector.ping());
			return null;
	}

	@Override
	protected void onPreExecute()
	{
		activity.setProgressBarIndeterminateVisibility(true);
	}
	
	@Override
	protected void onPostExecute(Void result)
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
