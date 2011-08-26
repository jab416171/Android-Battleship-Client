package edu.neumont.battleship;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class BattleshipJoinGame extends ListActivity
{
	public static final String TAG = BattleshipActivity.TAG;
	private static final boolean LOCAL_LOGV = false;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		refresh();
	}

	private void refresh()
	{
		if (LOCAL_LOGV) {
			Log.v(TAG,"Menu Item Refresh was clicked!");
		}
		String[] games = getGames();
		this.setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, games));
	}

	private String[] getGames()
	{
		// TODO This should actually get the list of games from the server
		return new String[]{"fake game", "fake game 2", "fake game 3"};
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.joingamemenu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
		case R.id.refresh:
			refresh();
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id)
	{
		Intent intent = new Intent(BattleshipJoinGame.this,BattleshipGameBoard.class);
		intent.putExtra(getString(R.string.selectedgame), l.getItemAtPosition(position).toString());
		startActivity(intent);
	}
}
