package edu.neumont.battleship.testharness;

import java.io.IOException;
import java.util.Random;

public class HardCodedXML implements GameLogic
{
	
	public HardCodedXML()
	{
		
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.neumont.battleship.testers.IGameLogic#newGame(java.lang.String,
	 * java.lang.String)
	 */
	public String newGame(String playerName, String robot) throws IOException
	{
		String result = ServerComm.call("NewGame",
				"<request><type>New Game</type><playerID>" + playerName +
						"</playerID><robot>" + robot + "</robot></request>");
		System.out.println(result);
		int start = result.indexOf("<gameID>") + 8;
		
		int end = result.indexOf("</gameID>");
		return result.substring(start, end);
		
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.neumont.battleship.testers.IGameLogic#gameList()
	 */
	public void gameList() throws IOException
	{
		String result = ServerComm.call("GameList",
				"<request><type>game list</type></request>");
		System.out.println(result);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * edu.neumont.battleship.testers.IGameLogic#placeShip(java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public void placeShip(String game_Id, String playerName, String coords,
			String direction, String ship) throws Exception
	{
		String result = ServerComm.call("PlaceShip",
				"<request><type>Place</type><gameID>" + game_Id
						+ "</gameID><playerID>" + playerName
						+ "</playerID><coordinates>" + coords
						+ "</coordinates><direction>" + direction
						+ "</direction><ship>" + ship + "</ship></request>");
		System.out.println(result);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.neumont.battleship.testers.IGameLogic#fire()
	 */
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
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.neumont.battleship.testers.IGameLogic#update(java.lang.String,
	 * java.lang.String)
	 */
	public void update(String game_Id, String playerName) throws IOException
	{
		String result = ServerComm.call("Update",
				"<request><type>Update</type><gameID>" + game_Id
						+ "</gameID><playerID>" + playerName
						+ "</playerID></request>");
		System.out.println(result);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.neumont.battleship.testers.IGameLogic#join(java.lang.String,
	 * java.lang.String)
	 */
	public void join(String game_Id, String playerName) throws IOException
	{
		String result = ServerComm.call("Join",
				"<request><type>Join</type><playerID>" + playerName
						+ "</playerID><gameID>" + game_Id
						+ "</gameID></request>");
		System.out.println(result);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.neumont.battleship.testers.IGameLogic#forfeit(java.lang.String,
	 * java.lang.String)
	 */
	public void forfeit(String game_Id, String playerName) throws IOException
	{
		String result = ServerComm.call("Forfeit",
				"<request><type>Forfeit</type><gameID>" + game_Id
						+ "</gameID><playerID>" + playerName
						+ "</playerID></request>");
		System.out.println(result);
	}
}