package edu.neumont.battleship.http.results;

import edu.neumont.battleship.model.ShipType;

public class PlaceShipResult extends ActionResult 
{
	private final boolean success;
	private final ShipType ship;
	public PlaceShipResult(boolean success, ShipType ship)
	{
		super();
		this.success = success;
		this.ship = ship;
	}
	public boolean isSuccess()
	{
		return success;
	}
	public ShipType getShip()
	{
		return ship;
	}
}
