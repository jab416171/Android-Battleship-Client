package edu.neumont.battleship;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;
import edu.neumont.battleship.model.PlayerType;

public class BattleshipNewGame extends Activity
{
	public static final String TAG = BattleshipActivity.TAG;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.newgame);
	}

	public void submit(View view)
	{
		Log.v(TAG,"Submit button in NewGame was clicked!");
		RadioGroup rg = (RadioGroup) findViewById(R.id.rgplayers);
		int selectedPlayer = rg.getCheckedRadioButtonId();
		PlayerType opponent = null;
		switch (selectedPlayer)
		{
		case R.id.radioH:
			opponent = PlayerType.Human;
			break;
		case R.id.radioG:
			opponent = PlayerType.Geeves;
			break;
		case R.id.radioE:
			opponent = PlayerType.Edison;
			break;
		case R.id.radioR:
			opponent = PlayerType.Robby;
			break;
		default:
			opponent = null;
		}
		if (opponent != null)
		{
			Intent intent = new Intent(BattleshipNewGame.this, BattleshipGameBoard.class);
			startActivity(intent);
		}
		// TODO finish this method
	}

}
