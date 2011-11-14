package edu.neumont.battleship;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;
import android.widget.ZoomControls;
import edu.neumont.battleship.exceptions.BattleshipException;
import edu.neumont.battleship.model.PlayerType;
import edu.neumont.battleship.testharness.GameLogic;
import edu.neumont.battleship.testharness.NetworkLogic;

public class BattleshipGameBoard extends Activity implements OnTouchListener
{
	public static final String TAG = BattleshipActivity.TAG;
	private static final boolean LOCAL_LOGD = false;
	private static final float SCROLL_SCALE = 5f;//2.5f;
	private TouchMode mode = TouchMode.NONE;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		Log.v(TAG, "in BattleshipGameBoard onCreate()");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gameboard);
		
		// This doesn't work for some reason, so
		// I add the listener to every ImageView
		View view = findViewById(R.id.tblBoard);
		view.setOnTouchListener(this);
		
		setupUI();
		
		joingame();
		
	}
	
	private void setupUI()
	{
		Log.v(TAG, "in setupUI()");
		final TableLayout table = (TableLayout) findViewById(R.id.tblBoard);
		
		for (int row = 0; row < table.getChildCount(); row++)
		{
			View view = table.getChildAt(row);
			if (view instanceof TableRow)
			{
				TableRow tableRow = (TableRow) table.getChildAt(row);
				for (int col = 0; col < tableRow.getChildCount(); col++)
				{
					if (tableRow.getChildAt(col) instanceof ImageView)
					{
						View cellView = tableRow.getChildAt(col);
						cellView.setOnClickListener(new ImageViewClickListener(row, col));
						cellView.setOnTouchListener(this);
					}
				}
			}
		}
		Log.v(TAG, "after TableRows, before Zoomcontrols");
		final ZoomControls zoomControl = (ZoomControls) findViewById(R.id.zcZoom);
		zoomControl.setOnZoomInClickListener(new OnClickListener() {
			public void onClick(View arg0)
			{
				Log.v(TAG, "Zoom in");
			}
		});
		zoomControl.setOnZoomOutClickListener(new OnClickListener() {
			public void onClick(View arg0)
			{
				Log.v(TAG, "Zoom out");
			}
		});
		zoomControl.show();
		zoomControl.scrollBy(0, 0);
		Log.v(TAG, "after Zoomcontrols");
	}
	
	class ImageViewClickListener implements OnClickListener
	{
		private int row = -1;
		private int col = -1;
		
		public ImageViewClickListener(int row, int column)
		{
			this.row = row;
			this.col = column;
		}
		
		public void onClick(View v)
		{
			Toast.makeText(BattleshipGameBoard.this, row + " " + col, Toast.LENGTH_SHORT).show();
			Log.v(TAG, row + " " + col);
		}
	}
	
	private void joingame()
	{
		GameLogic logic = new NetworkLogic();
		String playerName = SharedPrefsManager.getString(R.string.username, "Player1");
		// The intent for this activity
		Intent i = getIntent();
		// gets the extra data from the bundle, from intent.
		// Gets the R.string.selectedgame value after the selected game has been
		// looked up.
		String strSelectedGame = i.getExtras().getString(getString(R.string.selectedgame));
		int selectedGame;
		if (strSelectedGame != null) // we're joining a game
		{
			selectedGame = Integer.parseInt(strSelectedGame);
			try
			{
				logic.join(selectedGame, playerName);
			} catch (BattleshipException e)
			{
				Log.e(TAG, "Exception in GameBoard", e);
			}
		} else
		// we're making a new game
		{
			PlayerType opponent = PlayerType.valueOf(SharedPrefsManager.getString(
					R.string.opponenttype, "Human"));
			try
			{
				selectedGame = logic.newGame(playerName, opponent);
			} catch (BattleshipException e)
			{
				Log.e(TAG, "Exception in GameBoard", e);
			}
		}
		
		try
		{
			if (LOCAL_LOGD)
			{
				Log.v(TAG, logic.update(selectedGame, playerName));
			}
		} catch (BattleshipException e)
		{
			
			Log.e(TAG, "Exception in GameBoard", e);
		}
	}
	
	@Override
	protected void onDestroy()
	{
		// TODO Save game state
		super.onDestroy();
	}
	
	@Override
	protected void onPause()
	{
		// TODO Auto-generated method stub
		super.onPause();
	}
	
	public boolean onTouch(View view, MotionEvent event)
	{
		// dumpEvent(event);
		switch (event.getAction() & MotionEvent.ACTION_MASK)
		{
		case MotionEvent.ACTION_DOWN:
		case MotionEvent.ACTION_UP:
		case MotionEvent.ACTION_POINTER_UP:
		case MotionEvent.ACTION_POINTER_DOWN:
			TouchAddedRemoved(view, event);
			break;
		case MotionEvent.ACTION_MOVE:
			TouchMove(view, event);
			break;
		default:
			Log.v(TAG, "Unknown Touch action: " + event.getAction());//(event.getAction() & MotionEvent.ACTION_MASK));
		}
		
		return false;
	}
	
	private void TouchAddedRemoved(View view, MotionEvent event)
	{
		if (event.getPointerCount() == 1)
		{
			mode = TouchMode.DRAG;
			Log.v(TAG, "mode=DRAG");
		} else if (event.getPointerCount() == 2)
		{
			mode = TouchMode.PINCH;
			Log.v(TAG, "mode=PINCH");
		}
	}
	
	private void TouchMove(View view, MotionEvent event)
	{
		switch (mode)
		{
		case DRAG:
			Log.v(TAG, "dragging");
			float x = event.getX();
			float y = event.getY();
			int histSize = event.getHistorySize();
			float oldX = event.getHistoricalX(histSize-1);
			float oldY = event.getHistoricalY(histSize-1);
			
			float deltaX = (x-oldX)*SCROLL_SCALE;
			float deltaY = (y-oldY)*SCROLL_SCALE;
			
			View tableLayout = findViewById(R.id.tblBoard);
			tableLayout.layout(
					(int)(tableLayout.getLeft()+deltaX), 
					(int)(tableLayout.getTop()+deltaY), 
					(int)(tableLayout.getRight()-deltaX), 
					(int)(tableLayout.getBottom()-deltaY));
			break;
		case PINCH:
			Log.v(TAG, "pinching");
			break;
		default:
			Log.w(TAG, "We didn't know there was a finger down");
		}
	}
	
	enum TouchMode
	{
		NONE, DRAG, PINCH,
	}
	
	/** Show an event in the LogCat view, for debugging */
	@SuppressWarnings("unused")
	private void dumpEvent(MotionEvent event)
	{
		String names[] = { "DOWN", "UP", "MOVE", "CANCEL", "OUTSIDE", "POINTER_DOWN", "POINTER_UP",
				"7?", "8?", "9?" };
		StringBuilder sb = new StringBuilder();
		int action = event.getAction();
		int actionCode = action & MotionEvent.ACTION_MASK;
		sb.append("event ACTION_").append(names[actionCode]);
		if (actionCode == MotionEvent.ACTION_POINTER_DOWN
				|| actionCode == MotionEvent.ACTION_POINTER_UP)
		{
			sb.append("(pid ").append(action >> MotionEvent.ACTION_POINTER_ID_SHIFT);
			sb.append(")");
		}
		sb.append("[");
		for (int i = 0; i < event.getPointerCount(); i++)
		{
			sb.append("#").append(i);
			sb.append("(pid ").append(event.getPointerId(i));
			sb.append(")=").append((int) event.getX(i));
			sb.append(",").append((int) event.getY(i));
			if (i + 1 < event.getPointerCount())
				sb.append(";");
		}
		sb.append("]");
		Log.v(TAG, sb.toString());
	}
}
