package edu.neumont.battleship.http;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import android.util.Log;
import edu.neumont.battleship.BattleshipActivity;
import edu.neumont.battleship.exceptions.BattleshipException;
import edu.neumont.battleship.exceptions.BattleshipIOException;
import edu.neumont.battleship.http.results.ActionResult;
import edu.neumont.battleship.http.results.FireResult;
import edu.neumont.battleship.http.results.JoinResult;
import edu.neumont.battleship.http.results.NewGameResult;
import edu.neumont.battleship.http.results.PlaceShipResult;
import edu.neumont.battleship.model.Coordinate;
import edu.neumont.battleship.model.Direction;
import edu.neumont.battleship.model.PlayerType;
import edu.neumont.battleship.model.ShipType;
import edu.neumont.battleship.xml.XMLStringBuilder;

public class BattleshipServerConnector
{
	public static final String TAG = BattleshipActivity.TAG;
	private final static String contentType = "application/xml";
	private static final String HOST = "http://joe-bass.com:8800/BattleshipServer/";
	
	public static NewGameResult newGame(String playerID, PlayerType opponent)
			throws BattleshipException
	{
		try
		{
			String response = ServerComm.call(HOST + "NewGame",
					XMLStringBuilder.newGame(playerID, opponent), contentType);
			return XMLResponse.getResultType(response, NewGameResult.class);
		} catch (IOException e)
		{
			// TODO log
			throw new BattleshipIOException(e.getMessage());
		}
	}
	
	public static JoinResult joinGame(String playerID, String gameID) throws BattleshipException
	{
		try
		{
			String response = ServerComm.call(HOST + "Join",
					XMLStringBuilder.joinGame(playerID, gameID), contentType);
			return XMLResponse.getResultType(response, JoinResult.class);
		} catch (IOException e)
		{
			// TODO log
			throw new BattleshipIOException(e.getMessage());
		}
	}
	
	public static PlaceShipResult placeShip(Coordinate coordinates, Direction direction,
			ShipType ship) throws BattleshipException
	{
		try
		{
			String response = ServerComm.call(HOST + "PlaceShip",
					XMLStringBuilder.placeShip(coordinates.toString(), direction, ship),
					contentType);
			return XMLResponse.getResultType(response, PlaceShipResult.class);
		} catch (IOException e)
		{
			// TODO log
			throw new BattleshipIOException(e.getMessage());
		}
	}
	
	public static FireResult fire(Coordinate coordinates) throws BattleshipException
	{
		try
		{
			return XMLResponse.getResultType(ServerComm.call(HOST + "Fire",
					XMLStringBuilder.fire(coordinates.toString()), contentType), FireResult.class);
		} catch (IOException e)
		{
			// TODO log
			throw new BattleshipIOException(e.getMessage());
		}
	}
	
	/**
	 * Pings the server
	 * 
	 * @return if there was a response from the <code>HOST</code>
	 */
	public static boolean ping()
	{
		try
		{
			URL url = new URL(HOST);
			URLConnection conn = url.openConnection();
			conn.setConnectTimeout(5000);
			conn.connect();
			Log.v(TAG, "Call worked");
			return true;
		} catch (Exception e)
		{
			Log.e(TAG, "Call failed", e);
			return false;
		}
	}
	
	/**
	 * Gets the list of games
	 * 
	 * @return the body of the HTTP request
	 */
	public static ActionResult getGameList()
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Forfeits the match
	 * 
	 * @return IDK
	 */
	public static ActionResult forfeit()
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Gets the last move for the gameId
	 * 
	 * @return the last move
	 */
	public static ActionResult update(String gameId)
	{
		// TODO Auto-generated method stub
		return null;
	}
}
