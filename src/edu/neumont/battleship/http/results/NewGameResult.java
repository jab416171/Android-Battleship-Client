package edu.neumont.battleship.http.results;

public class NewGameResult extends ActionResult
{
	private final int gameId;

	public NewGameResult(int gameId)
	{
		super();
		this.gameId = gameId;
	}

	public int getGameId()
	{
		return gameId;
	}
	
}
