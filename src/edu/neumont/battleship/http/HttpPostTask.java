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
		super.onPostExecute(result);
		// things to do after executing, ex, hiding/killing the progress dialog, and doing whatever you need to with the string that's passed in (the same string that doInBackground returns)
	}
	
}
