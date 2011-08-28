package edu.neumont.battleship.testharness;

import java.io.IOException;

import edu.neumont.battleship.http.BattleshipServerConnector;
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

	public int newGame(String playerName, PlayerType robot)
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

	public int[] getGameList() throws IOException
	{
		BattleshipServerConnector.getGameList();
		return new int[] {-1};
	}

	public String placeShip(int gameId, String playerName, Coordinate coords, Direction direction,
			ShipType ship) throws Exception
	{
		BattleshipServerConnector.placeShip(coords, direction, ship);
		return "";
	}

	public String fire(int gameId, String playerName, Coordinate coords) throws IOException
	{
		BattleshipServerConnector.fire(coords);
		return "";
	}

	public String update(int gameId, String playerName) throws IOException
	{
		BattleshipServerConnector.update(Integer.toString(gameId));
		return "";
	}

	public String join(int gameId, String playerName) throws IOException
	{
		BattleshipServerConnector.joinGame(playerName, Integer.toString(gameId));
		return "";
	}

	public String forfeit(int gameId, String playerName) throws IOException
	{
		BattleshipServerConnector.forfeit();
		return "";
	}
	
}
