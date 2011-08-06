package edu.neumont.battleship;

import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;
import edu.neumont.battleship.model.PlayerType;
import edu.neumont.battleship.testharness.GameLogic;
import edu.neumont.battleship.testharness.HardCodedXML;

public class BattleshipGameBoard extends Activity
{
	public static final String TAG = BattleshipActivity.TAG;
	private static final boolean LOCAL_LOGD = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gameboard);
		
		setupUI();
		
		joingame();
	}
	
	private void setupUI()
	{
		GridView gridview = (GridView) findViewById(R.id.gvboard);
		gridview.setAdapter(new BoardImageAdapter(this));
		
		gridview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v, int position, long id)
			{
				Toast.makeText(BattleshipGameBoard.this, "" + position, Toast.LENGTH_SHORT).show();
			}
		});
	}
	
	private void joingame()
	{
		GameLogic logic = new HardCodedXML();
		Intent starter = getIntent();
		Bundle extras = starter.getExtras();
		String playerName = extras.getString("playerName");
		if (playerName == null)
		{
			playerName = "Joe";
		}
		String selectedGame = null;
		if (extras.getString("selectedGame") != null)
		{
			selectedGame = extras.getString("selectedGame");
			if (LOCAL_LOGD) {
				Log.d(TAG, selectedGame);
			}
		} else
		{
			if (LOCAL_LOGD) {
				Log.d(TAG, "selected game was null");
			}
		}
		
		if (selectedGame != null) // we're joining a game
		{
			try
			{
				logic.join(selectedGame, playerName);
			} catch (IOException e)
			{
				Log.e(TAG, "Exception in GameBoard", e);
			}
		} else
			// we're making a new game
		{
			PlayerType opponent = (PlayerType) extras.getSerializable("opponent");
			try
			{
				selectedGame = logic.newGame(playerName, opponent.toString());
			} catch (IOException e)
			{
				Log.e(TAG, "Exception in GameBoard", e);
			}
		}
		
		try
		{
			if (LOCAL_LOGD) {
				Log.d(TAG, logic.update(selectedGame, playerName));
			}
		} catch (IOException e)
		{
			
			Log.e(TAG, "Exception in GameBoard", e);
		}
	}
}
