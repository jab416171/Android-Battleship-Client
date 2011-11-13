package edu.neumont.battleship.http;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

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
	private final static String DOMAIN = "joe-bass.com";
	private final static int PORT = 8800;
	private final static String PROTOCOL = "http";
	private final static String PATH = "BattleshipServer";
	private static final String URL = PROTOCOL+"://"+DOMAIN+":"+PORT+"/"+PATH+"/";
	
	public static NewGameResult newGame(String playerID, PlayerType opponent)
			throws BattleshipException
	{
		try
		{
			String response = ServerComm.call(URL + "NewGame",
					XMLStringBuilder.newGame(playerID, opponent), contentType);
			return XMLResponse.getResultType(response, NewGameResult.class);
		} catch (IOException e)
		{
			Log.e(TAG, "Exception in BattleshipServerConnector: ", e);
			throw new BattleshipIOException(e.getMessage());
		}
	}
	
	public static JoinResult joinGame(String playerID, String gameID) throws BattleshipException
	{
		try
		{
			String response = ServerComm.call(URL + "Join",
					XMLStringBuilder.joinGame(playerID, gameID), contentType);
			return XMLResponse.getResultType(response, JoinResult.class);
		} catch (IOException e)
		{
			Log.e(TAG, "Exception in BattleshipServerConnector: ", e);
			throw new BattleshipIOException(e.getMessage());
		}
	}
	
	public static PlaceShipResult placeShip(Coordinate coordinates, Direction direction,
			ShipType ship) throws BattleshipException
	{
		try
		{
			String response = ServerComm.call(URL + "PlaceShip",
					XMLStringBuilder.placeShip(coordinates.toString(), direction, ship),
					contentType);
			return XMLResponse.getResultType(response, PlaceShipResult.class);
		} catch (IOException e)
		{
			Log.e(TAG, "Exception in BattleshipServerConnector: ", e);
			throw new BattleshipIOException(e.getMessage());
		}
	}
	
	public static FireResult fire(Coordinate coordinates) throws BattleshipException
	{
		try
		{
			return XMLResponse.getResultType(ServerComm.call(URL + "Fire",
					XMLStringBuilder.fire(coordinates.toString()), contentType), FireResult.class);
		} catch (IOException e)
		{
			Log.e(TAG, "Exception in BattleshipServerConnector: ", e);
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
            InetAddress.getByName(DOMAIN).isReachable(2000);
            return true;
        } catch (UnknownHostException e)
        {
            return false;
        } catch (IOException e)
        {
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
