package edu.neumont.battleship.http.results;

import edu.neumont.battleship.model.GameState;

public class GameStateResult extends ActionResult
{
	private final String turn;
	private final GameState state;
	private final String winner;
	private final MoveResult last;
	public GameStateResult(String turn, GameState state, String winner, MoveResult last)
	{
		super();
		this.turn = turn;
		this.state = state;
		this.winner = winner;
		this.last = last;
	}
	public String getTurn()
	{
		return turn;
	}
	public GameState getState()
	{
		return state;
	}
	public String getWinner()
	{
		return winner;
	}
	public MoveResult getLast()
	{
		return last;
	}
}
