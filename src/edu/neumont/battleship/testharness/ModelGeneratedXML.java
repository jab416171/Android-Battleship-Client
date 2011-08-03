package edu.neumont.battleship.testharness;

import java.io.IOException;

import edu.neumont.battleship.model.Coordinate;
import edu.neumont.battleship.model.Direction;
import edu.neumont.battleship.model.Game;
import edu.neumont.battleship.model.Player;
import edu.neumont.battleship.model.PlayerType;
import edu.neumont.battleship.model.Ship;
import edu.neumont.battleship.model.ShipType;

public class ModelGeneratedXML implements GameLogic
{
	private Game game;
	public ModelGeneratedXML()
	{
		game = new Game();
		Player p1 = new Player();
		p1.setType(PlayerType.Human);
		Player p2 = new Player();
		p2.setType(PlayerType.Edison);
		game.setLocalPlayer(p1);
		game.setRemotePlayer(p2);
	}
	
	public String newGame(String playerName, String robot) throws IOException
	{
		//first chance to set the players name
		game.getLocalPlayer().setId(playerName);
		
		String result = ServerComm.call("NewGame",
				"<request><type>New Game</type><playerID>" + playerName
						+ "</playerID><robot>" + robot + "</robot></request>");
		System.out.println(result);
		int start = result.indexOf("<gameID>") + 8;
		
		int end = result.indexOf("</gameID>");
		return result.substring(start, end);
	}
	
	public void gameList() throws IOException
	{
		String result = ServerComm.call("GameList",
				"<request><type>game list</type></request>");
		System.out.println(result);
		
	}
	
	public void placeShip(String game_Id, String playerName, String coords,
			String direction, String ship) throws Exception
	{
		Ship s = new Ship();
		s.setCoordinate(new Coordinate(coords));
		s.setDirection(Direction.fromXML(direction));
		s.setShipType(ShipType.fromXML(ship));
		
		game.getLocalPlayer().setShip(s);
		
		String result = ServerComm.call("PlaceShip",
			"<request><type>Place</type>"+
				"<gameID>"+game.getGameId()+"</gameID>"+
				"<playerID>"+game.getLocalPlayer().getId()+"</playerID>"+
				s.toXML()+
			"</request>");
		
		System.out.println(result);
		
	}
	
	public void fire(String game_Id, String playerName, String coords)
			throws IOException
	{
		
		String result = ServerComm.call("Fire",
				"<request><type>Fire</type><gameID>" + game_Id
				+ "</gameID><playerID>" + playerName
				+ "</playerID><coordinates>" + coords
				+ "</coordinates></request>");
		System.out.println(result);
		
	}
	
	public void update(String game_Id, String playerName) throws IOException
	{
		// TODO Auto-generated method stub
		
	}
	
	public void join(String game_ID, String playerName) throws IOException
	{
		// TODO Auto-generated method stub
		
	}
	
	public void forfeit(String game_Id, String playerName) throws IOException
	{
		// TODO Auto-generated method stub
		
	}
	
}
