package edu.neumont.battleship.http.results;

public class JoinResult extends ActionResult 
{
	private final int gameId;
	private final boolean success;
	public JoinResult(int gameId, boolean success)
	{
		super();
		this.gameId = gameId;
		this.success = success;
	}
	public int getGameId()
	{
		return gameId;
	}
	public boolean isSuccess()
	{
		return success;
	}
	
}
