package edu.neumont.battleship;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import edu.neumont.battleship.model.PlayerType;

public class BattleshipNewGame extends Activity
{
	public static final String TAG = BattleshipActivity.TAG;
	private static final boolean LOCAL_LOGV = false;
	private static final boolean LOCAL_LOGD = false;
	private int selectedRadioButton;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.newgame);
		//the default radio button selected
		selectedRadioButton = R.id.radioH;
		
	}
	

	public void submit(View view)
	{
		if (LOCAL_LOGV) {
			Log.v(TAG,"Submit button in NewGame was clicked!");
		}
//		View v = findViewById(R.id.rgplayers);
//		RadioGroup rg = (RadioGroup) v;
//		int selectedPlayer = rg.getCheckedRadioButtonId();
		PlayerType opponent = null;
		switch (selectedRadioButton)
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
			Log.e(TAG, "oppenent is null");
		}
		if (opponent != null)
		{
			Intent intent = new Intent(BattleshipNewGame.this, BattleshipGameBoard.class);
			intent.putExtra("opponent", opponent);
			startActivity(intent);
			
		}
		// TODO finish this method
	}

	public void RGClicked(View v)
	{
		selectedRadioButton = ((RadioButton) v).getId();
		if (LOCAL_LOGD) {
			Log.d(TAG,"selectedRadioButton: "+selectedRadioButton);
		}
	}

}
