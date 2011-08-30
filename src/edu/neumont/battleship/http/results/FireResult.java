package edu.neumont.battleship.http.results;

import edu.neumont.battleship.BattleshipActivity;
import edu.neumont.battleship.model.FireStatus;
import edu.neumont.battleship.model.ShipType;

public class FireResult extends ActionResult
{
	public static final String TAG = BattleshipActivity.TAG;
	private final int gameId;
	private final FireStatus status;
	private final ShipType shipType;

	public FireResult(int gameId, FireStatus status, ShipType shipType)
	{
		super();
		this.gameId = gameId;
		this.status = status;
		this.shipType = shipType;
	}

	public int getGameId()
	{
		return gameId;
	}

	public FireStatus getStatus()
	{
		return status;
	}

	public ShipType getShipType()
	{
		return shipType;
	}

}
