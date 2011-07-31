package edu.neumont.battleship.http;

import edu.neumont.battleship.model.Direction;
import edu.neumont.battleship.model.PlayerType;
import edu.neumont.battleship.model.ShipType;
import android.widget.TextView;

public class XMLStringBuilder {
	// public static String methodName() {
	// StringBuilder xml = new StringBuilder();
	//
	// return xml.toString();
	// }

	public static String newGame(String playerID, PlayerType opponent,
			TextView tv) {
		StringBuilder xml = new StringBuilder();
		xml.append("<request>");
		xml.append("<playerID>");
		xml.append(playerID);
		xml.append("</playerID>");
		if (opponent != PlayerType.Human) {
			xml.append("<robot>");
			xml.append(opponent.toString());
			xml.append("</robot>");
		}
		xml.append("</request>");
		tv.setText(tv.getText() + xml.toString());
		return xml.toString();
	}

	private static String emptyRequest() {
		StringBuilder xml = new StringBuilder();
		xml.append("<request>");
		xml.append("</request>");
		return xml.toString();
	}

	public static String forfeit() {
		return emptyRequest();
	}

	public static String requestGameState() {
		return emptyRequest();
	}

	public static String joinGame(String playerID, String gameID) {
		StringBuilder xml = new StringBuilder();
		xml.append("<request>");
		xml.append("<playerID>");
		xml.append(playerID);
		xml.append("</playerID>");
		xml.append("<gameID>");
		xml.append(gameID);
		xml.append("</gameID>");
		xml.append("</request>");
		return xml.toString();
	}

	public static String placeShip(String coordinates, Direction direction,
			ShipType ship, TextView tv) {
		StringBuilder xml = new StringBuilder();
		xml.append("<request>");
		xml.append("<coordinates>");
		xml.append(coordinates);
		xml.append("</coordinates>");
		xml.append("<direction>");
		xml.append(direction.toString());
		xml.append("</direction>");
		xml.append("<ship>");
		xml.append(ship.toString());
		xml.append("</ship>");
		xml.append("</request>");
		tv.setText(tv.getText() + xml.toString());
		return xml.toString();
	}

	public static String fire(String coordinates) {
		StringBuilder xml = new StringBuilder();
		xml.append("<request>");
		xml.append("<coordinates>");
		xml.append(coordinates);
		xml.append("</coordinates>");
		xml.append("</request>");
		return xml.toString();
	}

}
