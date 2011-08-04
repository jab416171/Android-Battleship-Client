package edu.neumont.battleship;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

public class BattleshipGameBoard extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
	    setContentView(R.layout.gameboard);

	    GridView gridview = (GridView) findViewById(R.id.gvboard);
	    gridview.setAdapter(new BoardImageAdapter(this));

	    gridview.setOnItemClickListener(new OnItemClickListener() {
	        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
	            Toast.makeText(BattleshipGameBoard.this, "" + position, Toast.LENGTH_SHORT).show();
	        }
	    });

	}
}
