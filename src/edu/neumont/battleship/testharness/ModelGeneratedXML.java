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
		StringBuilder request = new StringBuilder();
		request.append("<request><type>New Game</type><playerID>");
		request.append(playerName);
		request.append("</playerID><robot>");
		request.append(robot);
		request.append("</robot></request>");
		String response = ServerComm.call(RequestType.NewGame,request.toString());
		// System.out.println(result);
		
		int start = response.indexOf("<gameID>") + "<gameID>".length();
		int end = response.indexOf("</gameID>");
		return response.substring(start, end);
	}
	
	public String gameList() throws IOException
	{
		String response = ServerComm.call(RequestType.GameList,"<request><type>game list</type></request>");
		return response;
		
	}
	
	public String placeShip(String game_Id, String playerName, String coords,
			String direction, String ship) throws Exception
	{
		Ship s = new Ship();
		s.setCoordinate(new Coordinate(coords));
		s.setDirection(Direction.fromXML(direction));
		s.setShipType(ShipType.fromXML(ship));
		
		game.getLocalPlayer().setShip(s);
		StringBuilder request = new StringBuilder();
		request.append("<request><type>Place</type>");
		request.append("<gameID>");
		request.append(game.getGameId());
		request.append("</gameID>");
		request.append("<playerID>");
		request.append(game.getLocalPlayer().getId());
		request.append("</playerID>");
		request.append(s.toXML());
		request.append("</request>");
		
		String response = ServerComm.call(RequestType.PlaceShip,request.toString());
		
		return response;
	}
	
	public String fire(String game_Id, String playerName, String coords)
			throws IOException
	{
		StringBuilder request = new StringBuilder();
		request.append("<request><type>Fire</type><gameID>");
		request.append(game_Id);
		request.append("</gameID><playerID>");
		request.append(playerName);
		request.append("</playerID><coordinates>");
		request.append(coords);
		request.append("</coordinates></request>");
		
		String response = ServerComm.call(RequestType.Fire,request.toString());
		return response;
		
	}
	
	public String update(String game_Id, String playerName) throws IOException
	{
		String response = "";
		// TODO: Auto-generated method stub
		return response;
	}
	
	public String join(String game_Id, String playerName) throws IOException
	{
		String response = "";
		// TODO: Auto-generated method stub
		return response;
	}
	
	public String forfeit(String game_Id, String playerName) throws IOException
	{
		String response = "";
		// TODO: Auto-generated method stub
		return response;
	}
	
}
