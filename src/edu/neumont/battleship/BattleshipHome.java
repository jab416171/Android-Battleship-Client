package edu.neumont.battleship;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.IntentSender.SendIntentException;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class BattleshipHome extends Activity
{
	
	/**
	 * The name of the app
	 * Used for logging
	 */
	public static String TAG;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		//get the name of the app
		TAG = getText(R.string.app_name).toString();
		Log.v(TAG, "in OnCreated!");
		TextView tv = (TextView) findViewById(R.id.textview);
		tv.setText("Welcome Home");
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		Log.v(TAG, "in onPause");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.v(TAG, "in onDestroy");
	}
	
	
	@Override
	public boolean onTrackballEvent(MotionEvent e)
	{
		Intent intent = new Intent(new Context() {
			
			@Override
			public void unregisterReceiver(BroadcastReceiver receiver)
			{
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void unbindService(ServiceConnection conn)
			{
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public boolean stopService(Intent service)
			{
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public ComponentName startService(Intent service)
			{
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public void startIntentSender(IntentSender intent, Intent fillInIntent, int flagsMask, int flagsValues, int extraFlags) throws SendIntentException
			{
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public boolean startInstrumentation(ComponentName className, String profileFile, Bundle arguments)
			{
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public void startActivity(Intent intent)
			{
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void setWallpaper(InputStream data) throws IOException
			{
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void setWallpaper(Bitmap bitmap) throws IOException
			{
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void setTheme(int resid)
			{
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void sendStickyOrderedBroadcast(Intent intent, BroadcastReceiver resultReceiver, Handler scheduler, int initialCode, String initialData, Bundle initialExtras)
			{
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void sendStickyBroadcast(Intent intent)
			{
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void sendOrderedBroadcast(Intent intent, String receiverPermission, BroadcastReceiver resultReceiver, Handler scheduler, int initialCode, String initialData, Bundle initialExtras)
			{
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void sendOrderedBroadcast(Intent intent, String receiverPermission)
			{
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void sendBroadcast(Intent intent, String receiverPermission)
			{
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void sendBroadcast(Intent intent)
			{
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void revokeUriPermission(Uri uri, int modeFlags)
			{
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void removeStickyBroadcast(Intent intent)
			{
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public Intent registerReceiver(BroadcastReceiver receiver, IntentFilter filter, String broadcastPermission, Handler scheduler)
			{
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Intent registerReceiver(BroadcastReceiver receiver, IntentFilter filter)
			{
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Drawable peekWallpaper()
			{
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public SQLiteDatabase openOrCreateDatabase(String name, int mode, CursorFactory factory)
			{
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public FileOutputStream openFileOutput(String name, int mode) throws FileNotFoundException
			{
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public FileInputStream openFileInput(String name) throws FileNotFoundException
			{
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public void grantUriPermission(String toPackage, Uri uri, int modeFlags)
			{
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public int getWallpaperDesiredMinimumWidth()
			{
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public int getWallpaperDesiredMinimumHeight()
			{
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public Drawable getWallpaper()
			{
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Theme getTheme()
			{
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Object getSystemService(String name)
			{
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public SharedPreferences getSharedPreferences(String name, int mode)
			{
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Resources getResources()
			{
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String getPackageName()
			{
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public PackageManager getPackageManager()
			{
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Looper getMainLooper()
			{
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public File getFilesDir()
			{
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public File getFileStreamPath(String name)
			{
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public File getDir(String name, int mode)
			{
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public File getDatabasePath(String name)
			{
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public ContentResolver getContentResolver()
			{
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public ClassLoader getClassLoader()
			{
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public File getCacheDir()
			{
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public AssetManager getAssets()
			{
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public ApplicationInfo getApplicationInfo()
			{
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Context getApplicationContext()
			{
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String[] fileList()
			{
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public void enforceUriPermission(Uri uri, String readPermission, String writePermission, int pid, int uid, int modeFlags, String message)
			{
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void enforceUriPermission(Uri uri, int pid, int uid, int modeFlags, String message)
			{
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void enforcePermission(String permission, int pid, int uid, String message)
			{
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void enforceCallingUriPermission(Uri uri, int modeFlags, String message)
			{
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void enforceCallingPermission(String permission, String message)
			{
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void enforceCallingOrSelfUriPermission(Uri uri, int modeFlags, String message)
			{
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void enforceCallingOrSelfPermission(String permission, String message)
			{
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public boolean deleteFile(String name)
			{
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean deleteDatabase(String name)
			{
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public String[] databaseList()
			{
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Context createPackageContext(String packageName, int flags) throws NameNotFoundException
			{
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public void clearWallpaper() throws IOException
			{
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public int checkUriPermission(Uri uri, String readPermission, String writePermission, int pid, int uid, int modeFlags)
			{
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public int checkUriPermission(Uri uri, int pid, int uid, int modeFlags)
			{
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public int checkPermission(String permission, int pid, int uid)
			{
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public int checkCallingUriPermission(Uri uri, int modeFlags)
			{
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public int checkCallingPermission(String permission)
			{
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public int checkCallingOrSelfUriPermission(Uri uri, int modeFlags)
			{
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public int checkCallingOrSelfPermission(String permission)
			{
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public boolean bindService(Intent service, ServiceConnection conn, int flags)
			{
				// TODO Auto-generated method stub
				return false;
			}
		}, BattleshipActivity.class);
		startActivity(intent);
		return true;
	}
}
