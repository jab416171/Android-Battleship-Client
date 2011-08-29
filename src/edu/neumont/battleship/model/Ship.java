package edu.neumont.battleship.model;

public class Ship
{
	private ShipType shipType;
	private Coordinate coordinate;
	private Direction direction;

	public Ship() {
		// TODO Auto-generated constructor stub
	}
	
	public ShipType getShipType() {
		return shipType;
	}

	public void setShipType(ShipType shipType) {
		this.shipType = shipType;
	}

	public Coordinate getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

}
