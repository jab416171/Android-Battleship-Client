package edu.neumont.battleship.testharness;

import java.io.IOException;

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
		String r = robot.equals("Human")?"":"<robot>" + robot + "</robot>";
		
		String result = ServerComm.call(RequestType.NewGame,
				"<request><type>New Game</type><playerID>" + playerName +
						"</playerID>"+r+"</request>");
		System.out.println(result);
		int start = result.indexOf("<gameID>") + "<gameID>".length();
		
		int end = result.indexOf("</gameID>");
		return result.substring(start, end);
		
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.neumont.battleship.testers.IGameLogic#gameList()
	 */
	public String gameList() throws IOException
	{
		String result = ServerComm.call(RequestType.GameList,
				"<request><type>game list</type></request>");
		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * edu.neumont.battleship.testers.IGameLogic#placeShip(java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String placeShip(String game_Id, String playerName, String coords,
			String direction, String ship) throws Exception
	{
		String result = ServerComm.call(RequestType.PlaceShip,
				"<request><type>Place</type><gameID>" + game_Id
						+ "</gameID><playerID>" + playerName
						+ "</playerID><coordinates>" + coords
						+ "</coordinates><direction>" + direction
						+ "</direction><ship>" + ship + "</ship></request>");
		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.neumont.battleship.testers.IGameLogic#fire()
	 */
	public String fire(String game_Id, String playerName, String coords)
			throws IOException
	{
		String result = ServerComm.call(RequestType.Fire,
				"<request><type>Fire</type><gameID>" + game_Id
						+ "</gameID><playerID>" + playerName
						+ "</playerID><coordinates>" + coords
						+ "</coordinates></request>");
		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.neumont.battleship.testers.IGameLogic#update(java.lang.String,
	 * java.lang.String)
	 */
	public String update(String game_Id, String playerName) throws IOException
	{
		String result = ServerComm.call(RequestType.Update,
				"<request><type>Update</type><gameID>" + game_Id
						+ "</gameID><playerID>" + playerName
						+ "</playerID></request>");
		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.neumont.battleship.testers.IGameLogic#join(java.lang.String,
	 * java.lang.String)
	 */
	public String join(String game_Id, String playerName) throws IOException
	{
		String result = ServerComm.call(RequestType.Join,
				"<request><type>Join</type><playerID>" + playerName
						+ "</playerID><gameID>" + game_Id
						+ "</gameID></request>");
		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.neumont.battleship.testers.IGameLogic#forfeit(java.lang.String,
	 * java.lang.String)
	 */
	public String forfeit(String game_Id, String playerName) throws IOException
	{
		String result = ServerComm.call(RequestType.Forfeit,
				"<request><type>Forfeit</type><gameID>" + game_Id
						+ "</gameID><playerID>" + playerName
						+ "</playerID></request>");
		return result;
	}
}