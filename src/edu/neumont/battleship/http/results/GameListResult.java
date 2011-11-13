package edu.neumont.battleship.http.results;

import java.util.List;

import edu.neumont.battleship.xml.XMLGame;

public class GameListResult extends ActionResult
{
	private final List<XMLGame> gameList;
	public List<XMLGame> getGameList()
	{
		return gameList;
	}
	public GameListResult(List<XMLGame> gameList)
	{
		this.gameList = gameList;
	}
	public int NumOfGames()
	{
		return gameList.size();
	}
}
