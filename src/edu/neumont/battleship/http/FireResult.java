package edu.neumont.battleship.http;

public class FireResult {
	private int gameId;
	private FireStatus status;
	private ShipType shipType;

	public FireResult() {
		// TODO Auto-generated constructor stub
	}

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public FireStatus getStatus() {
		return status;
	}

	public void setStatus(FireStatus status) {
		this.status = status;
	}

	public ShipType getShipType() {
		return shipType;
	}

	public void setShipType(ShipType shipType) {
		this.shipType = shipType;
	}

}
