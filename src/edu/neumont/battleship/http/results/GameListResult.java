package edu.neumont.battleship.http.results;

import java.util.List;

import edu.neumont.battleship.xml.XMLGame;

public class GameListResult extends ActionResult
{
	List<XMLGame> gameList;
	public GameListResult(List<XMLGame> gameList)
	{
		this.gameList = gameList;
	}
}
