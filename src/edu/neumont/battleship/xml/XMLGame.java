package edu.neumont.battleship.xml;

import edu.neumont.battleship.model.GameState;

public class XMLGame
{
	private int gameId;
	private String turn;
	private GameState state;
	
	public int getGameId()
	{
		return gameId;
	}
	public void setGameId(int gameId)
	{
		this.gameId = gameId;
	}
	public String getTurn()
	{
		return turn;
	}
	public void setTurn(String turn)
	{
		this.turn = turn;
	}
	public GameState getState()
	{
		return state;
	}
	public void setState(GameState state)
	{
		this.state = state;
	}
}
