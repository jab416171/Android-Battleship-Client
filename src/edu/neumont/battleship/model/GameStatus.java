package edu.neumont.battleship.model;

import edu.neumont.battleship.http.results.MoveResult;

public class GameStatus {
	private boolean turn;
	private GameState state;
	private MoveResult move;

	public boolean isTurn() {
		return turn;
	}

	public void setTurn(boolean turn) {
		this.turn = turn;
	}

	public GameState getState() {
		return state;
	}

	public void setState(GameState state) {
		this.state = state;
	}

	public MoveResult getMove() {
		return move;
	}

	public void setMove(MoveResult move) {
		this.move = move;
	}

}
