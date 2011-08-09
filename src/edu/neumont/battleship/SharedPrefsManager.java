package edu.neumont.battleship;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.util.Log;

public class SharedPrefsManager
{
	private static SharedPreferences preferences;
	private static final Editor editor = preferences.edit();
	private static final String TAG = BattleshipActivity.TAG;
	private static Context context;
	
	public SharedPrefsManager(final Context context)
	{
		preferences = PreferenceManager.getDefaultSharedPreferences(context);
		this.context=context;
	}
	
	public static String getString(final int id, final String defValue) {
		return getString(context.getString(id), defValue);
	}
	
	public static String getString(final String key, final String defValue)
	{
		return preferences.getString(key, defValue);
	}
	
	public static void setString(final String key, final String value)
	{
		editor.putString(key, value);
		if(!editor.commit()){
			Log.e(TAG,"Error: Editor failed to commit!");
		}
	}
}
