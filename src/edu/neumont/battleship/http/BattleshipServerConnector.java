package edu.neumont.battleship.http;

import edu.neumont.battleship.BattleshipActivity;
import android.util.Log;

public class BattleshipServerConnector {
	public static final String TAG = BattleshipActivity.TAG;

	public static XMLResponse newGame(String playerID, Opponent opponent) {
		try {
			return new XMLResponse(HttpHandler.postData(
					HttpHandler.connectionURL + "NewGame",
					XMLStringBuilder.newGame(playerID, opponent)));
		} catch (Exception e) {
			Log.e(TAG, "Exception in BattleshipServerConnector: ", e);
		}
		return null;
	}

	public static XMLResponse joinGame(String playerID, String gameID) {
		try {
			return new XMLResponse(HttpHandler.postData(
					HttpHandler.connectionURL + "Join",
					XMLStringBuilder.joinGame(playerID, gameID)));
		} catch (Exception e) {
			Log.e(TAG, "Exception in BattleshipServerConnector: ", e);
		}
		return null;
	}

	public static XMLResponse placeShip(String coordinates,
			Direction direction, ShipType ship) {
		try {
			return new XMLResponse(HttpHandler.postData(
					HttpHandler.connectionURL + "PlaceShip",
					XMLStringBuilder.placeShip(coordinates, direction, ship)));
		} catch (Exception e) {
			Log.e(TAG, "Exception in BattleshipServerConnector: ", e);
		}
		return null;
	}

	public static XMLResponse fire(String coordinates) {
		try {
			return new XMLResponse(HttpHandler.postData(
					HttpHandler.connectionURL + "Fire",
					XMLStringBuilder.fire(coordinates)));
		} catch (Exception e) {
			Log.e(TAG, "Exception in BattleshipServerConnector: ", e);
		}
		return null;
	}
}
