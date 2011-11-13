package edu.neumont.battleship.xml;

import edu.neumont.battleship.model.Coordinate;
import edu.neumont.battleship.model.Direction;
import edu.neumont.battleship.model.PlayerType;
import edu.neumont.battleship.model.Ship;
import edu.neumont.battleship.model.ShipType;

public class XMLStringBuilder {

	public static String newGame(String playerID, PlayerType opponent) {
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

	public static String requestGameState(String gameId, String playerName) {
		StringBuilder xml = new StringBuilder();
		xml.append("<request>");
		xml.append("<type>Update</type>");
		xml.append("<gameID>");
		xml.append(gameId);
		xml.append("</gameID>");
		xml.append("<playerID>");
		xml.append(playerName);
		xml.append("</playerID>");
		xml.append("</request>");
		return xml.toString();
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
	
	public static String gameList() 
	{
		//return "<request><type>game list</type></request>";
		return "<request></request>";
	}
	
	public String shipToXML(Ship ship)
	{
		return coordinateToXml(ship.getCoordinate()) +"\r\n" + 
				directionToXML(ship.getDirection()) +"\r\n" +
				shipTypeToXml(ship.getShipType());
	}
	
	public static String directionToXML(Direction dir)
	{
		return "<direction>"+dir+"</direction>";
	}
	
	public static String coordinateToXml(Coordinate coord)
	{
		return "<coordinates>"+coord.getLetter()+coord.getRow()+"</coordinates>";
	}
	
	public static String shipTypeToXml(ShipType type)
	{
		return "<ship>"+type+"</ship>";
	}

}
