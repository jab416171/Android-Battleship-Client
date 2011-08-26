package edu.neumont.battleship.testharness;

import java.io.IOException;

public class HardCodedXML implements GameLogic
{
	private final static String contentType = "application/xml";
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
		StringBuilder request = new StringBuilder();
		String robotString = "";
		if(!"Human".equals(robot)) {
			robotString = "<robot>" + robot + "</robot>";
		}
		
		request.append("<request><type>New Game</type><playerID>");
		request.append(playerName);
		request.append("</playerID>");
		request.append(robotString);
		request.append("</request>");
		String response = ServerComm.call(RequestType.NewGame.toString(),request.toString(),contentType);
		// System.out.println(result);
		
		int start = response.indexOf("<gameID>") + "<gameID>".length();
		int end = response.indexOf("</gameID>");
		return response.substring(start, end);
		
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.neumont.battleship.testers.IGameLogic#gameList()
	 */
	public String gameList() throws IOException
	{
		String response = ServerComm.call(RequestType.GameList.toString(),
				"<request><type>game list</type></request>",contentType);
		return response;
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
		StringBuilder request = new StringBuilder();
		request.append("<request><type>Place</type><gameID>");
		request.append(game_Id);
		request.append("</gameID><playerID>");
		request.append(playerName);
		request.append("</playerID><coordinates>");
		request.append(coords);
		request.append("</coordinates><direction>");
		request.append(direction);
		request.append("</direction><ship>");
		request.append(ship);
		request.append("</ship></request>");
		
		String response = ServerComm.call(RequestType.PlaceShip.toString(),request.toString(),contentType);
		return response;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.neumont.battleship.testers.IGameLogic#fire()
	 */
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
		String response = ServerComm.call(RequestType.Fire.toString(),request.toString(),contentType);
		return response;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.neumont.battleship.testers.IGameLogic#update(java.lang.String,
	 * java.lang.String)
	 */
	public String update(String game_Id, String playerName) throws IOException
	{
		StringBuilder request = new StringBuilder();
		request.append("<request><type>Update</type><gameID>");
		request.append(game_Id);
		request.append("</gameID><playerID>");
		request.append(playerName);
		request.append("</playerID></request>");
		String response = ServerComm.call(RequestType.Update.toString(),request.toString(),contentType);
		return response;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.neumont.battleship.testers.IGameLogic#join(java.lang.String,
	 * java.lang.String)
	 */
	public String join(String game_Id, String playerName) throws IOException
	{
		StringBuilder request = new StringBuilder();
		request.append("<request><type>Join</type><playerID>");
		request.append(playerName);
		request.append("</playerID><gameID>");
		request.append(game_Id);
		request.append("</gameID></request>");
		String response = ServerComm.call(RequestType.Join.toString(),request.toString(),contentType);
		return response;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.neumont.battleship.testers.IGameLogic#forfeit(java.lang.String,
	 * java.lang.String)
	 */
	public String forfeit(String game_Id, String playerName) throws IOException
	{
		StringBuilder request = new StringBuilder();
		request.append("<request><type>Forfeit</type><gameID>");
		request.append(game_Id);
		request.append("</gameID><playerID>");
		request.append(playerName);
		request.append("</playerID></request>");
		String response = ServerComm.call(RequestType.Forfeit.toString(),request.toString(),contentType);
		return response;
	}
}