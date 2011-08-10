package edu.neumont.battleship.http;

import android.os.AsyncTask;

public class HttpPostTask extends AsyncTask<String, Void, String>
{
	
	@Override
	protected String doInBackground(String... params)
	{
		try
		{
			return HttpHandler.postData(params[0], params[1]);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
}
