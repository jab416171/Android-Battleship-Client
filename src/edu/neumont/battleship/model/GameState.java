package edu.neumont.battleship.model;

public class GameState {
	private boolean turn;
	private GameStatus state;
	private MoveResult move;
	
	public GameState() {
		// TODO Auto-generated constructor stub
	}

	public boolean isTurn() {
		return turn;
	}

	public void setTurn(boolean turn) {
		this.turn = turn;
	}

	public GameStatus getState() {
		return state;
	}

	public void setState(GameStatus state) {
		this.state = state;
	}

	public MoveResult getMove() {
		return move;
	}

	public void setMove(MoveResult move) {
		this.move = move;
	}

}
