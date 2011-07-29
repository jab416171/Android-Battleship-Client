package edu.neumont.battleship.http;

public class XMLStringBuilder {
	// public static String methodName() {
	// StringBuilder xml = new StringBuilder();
	//
	// return xml.toString();
	// }

	public static String newGame(String playerID, Opponent opponent) {
		StringBuilder xml = new StringBuilder();
		xml.append("<request>");
		xml.append("<playerID>");
		xml.append(playerID);
		xml.append("</playerID>");
		if (opponent != Opponent.Human) {
			xml.append("<robot>");
			xml.append(opponent.toString());
			xml.append("</robot>");
		}
		xml.append("</request>");
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
			ShipType ship) {
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
