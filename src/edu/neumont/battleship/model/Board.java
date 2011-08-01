package edu.neumont.battleship.model;

public class Board
{
	public static final int NUM_ROWS = 10;
	public static final int NUM_COLS = 10;
	
	private FireStatus[][] board;
	private Ship[] ships;
	
	public Board()
	{
		board = new FireStatus[NUM_ROWS][NUM_COLS];
		ships = new Ship[ShipType.values().length];
	}
	
	public FireStatus getCellStatus(int row, int col)
	{
		return board[row][col];
	}
	
	public Ship[] getShips()
	{
		return ships;
	}
	
	public void setShip(Ship ship)
	{
		// get the types of ships
		ShipType[] types = ShipType.values();
		
		// use the result of 'ShipType.values()' to determine where to put the
		// ship in 'ships'
		for (int shipsIdx = 0; shipsIdx < types.length; shipsIdx++)
		{
			if (ship.getShipType() == types[shipsIdx])
				ships[shipsIdx] = ship;
		}
	}
}