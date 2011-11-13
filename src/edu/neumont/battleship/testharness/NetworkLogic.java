package edu.neumont.battleship.testharness;

import java.io.IOException;

import edu.neumont.battleship.exceptions.BattleshipException;
import edu.neumont.battleship.exceptions.BattleshipIOException;
import edu.neumont.battleship.exceptions.InvalidXMLException;
import edu.neumont.battleship.http.BattleshipServerConnector;
import edu.neumont.battleship.http.results.FireResult;
import edu.neumont.battleship.model.Coordinate;
import edu.neumont.battleship.model.Direction;
import edu.neumont.battleship.model.PlayerType;
import edu.neumont.battleship.model.ShipType;

/**
 * @author gwatson
 *
 */
public class NetworkLogic implements GameLogic
{

	public int newGame(String playerName, PlayerType robot) throws BattleshipException
	{
		try
		{
			BattleshipServerConnector.newGame(playerName, robot);
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

	public int[] getGameList()
	{
		//BattleshipServerConnector.getGameList();
		return new int[] {-1};
	}

	public boolean placeShip(int gameId, String playerName, Coordinate coords, Direction direction,
			ShipType ship) throws BattleshipException
	{
		BattleshipServerConnector.placeShip(coords, direction, ship);
		return false;
	}

	public FireResult fire(int gameId, String playerName, Coordinate coords) throws BattleshipException
	{
		BattleshipServerConnector.fire(coords);
		return null;
	}

	public String update(int gameId, String playerName) throws BattleshipException
	{
		BattleshipServerConnector.update(Integer.toString(gameId));
		return "";
	}

	public boolean join(int gameId, String playerName) throws BattleshipException
	{
		BattleshipServerConnector.joinGame(playerName, Integer.toString(gameId));
		return false;
	}

	public void forfeit(int gameId, String playerName) throws BattleshipException
	{
		BattleshipServerConnector.forfeit();
	}
	
}
