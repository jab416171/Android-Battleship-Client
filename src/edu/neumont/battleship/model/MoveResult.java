package edu.neumont.battleship.model;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class MoveResult {
	private Coordinate coordinate;
	private FireStatus status;
	private int playerId;

	public MoveResult() {
		// TODO Auto-generated constructor stub
	}
	public MoveResult(String xml)
	{
		coordinate = new Coordinate(xml);
		status = FireStatus.fromXml(xml);
//		playerId = ;
		throw new NotImplementedException();
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
