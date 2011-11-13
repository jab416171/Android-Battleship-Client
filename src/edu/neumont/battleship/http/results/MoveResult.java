package edu.neumont.battleship.http.results;

import edu.neumont.battleship.model.Coordinate;
import edu.neumont.battleship.model.FireStatus;

public class MoveResult {
	private Coordinate coordinate;
	private FireStatus status;
	private int playerId;

	public MoveResult() {
		// TODO Auto-generated constructor stub
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public FireStatus getStatus() {
		return status;
	}

	public void setStatus(FireStatus status) {
		this.status = status;
	}

	public Coordinate getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

}
