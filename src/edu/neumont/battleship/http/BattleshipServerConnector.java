package edu.neumont.battleship.http;

import java.net.URL;
import java.net.URLConnection;

import android.util.Log;
import edu.neumont.battleship.BattleshipActivity;
import edu.neumont.battleship.model.Coordinate;
import edu.neumont.battleship.model.Direction;
import edu.neumont.battleship.model.FireResult;
import edu.neumont.battleship.model.PlayerType;
import edu.neumont.battleship.model.ShipType;
import edu.neumont.battleship.xml.XMLStringBuilder;

public class BattleshipServerConnector {
	public static final String TAG = BattleshipActivity.TAG;
	private final static String contentType = "application/xml";
	private static final String HOST = "http://joe-bass.com:8800/BattleshipServer/";
	
	public static String newGame(String playerID, PlayerType opponent) throws Exception {
			return XMLResponse.getResultType(ServerComm.call(
					HOST + "NewGame",
					XMLStringBuilder.newGame(playerID, opponent), 
					contentType), String.class);
	}

	public static boolean joinGame(String playerID, String gameID) {
		try {
			return XMLResponse.getResultType(ServerComm.call(
					HOST + "Join",
					XMLStringBuilder.joinGame(playerID, gameID), 
					contentType), boolean.class);
		} catch (Exception e) {
			Log.e(TAG, "Exception in BattleshipServerConnector: ", e);
		}
		return false;
	}

	public static boolean placeShip(Coordinate coordinates, 
			Direction direction, ShipType ship) 
	{
		try {
			return XMLResponse.getResultType(ServerComm.call(
					HOST + "PlaceShip", 
					XMLStringBuilder.placeShip(coordinates.toString(), direction, ship), 
					contentType), boolean.class);
		} catch (Exception e) {
			Log.e(TAG, "Exception in BattleshipServerConnector: ", e);
		}
		return false;
	}

	public static FireResult fire(Coordinate coordinates) {
		try {
			return XMLResponse.getResultType(ServerComm.call(
					HOST + "Fire",
					XMLStringBuilder.fire(coordinates.toString()), 
					contentType), FireResult.class);
		} catch (Exception e) {
			Log.e(TAG, "Exception in BattleshipServerConnector: ", e);
		}
		return null;
	}
	
	/**
	 * Pings the server
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
			Log.v(TAG,"Call worked");
			return true;
		}
		catch(Exception e)
		{
			Log.e(TAG,"Call failed", e);
			return false;
		}
	}
	/**
	 * Gets the list of games
	 * @return the body of the HTTP request
	 */
	public static String getGameList()
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Forfeits the match
	 * @return IDK
	 */
	public static String forfeit()
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Gets the last move for the gameId
	 * @return the last move
	 */
	public static String update(String gameId)
	{
		// TODO Auto-generated method stub
		return null;
	}
}
