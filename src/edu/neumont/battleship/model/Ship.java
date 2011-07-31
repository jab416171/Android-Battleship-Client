package edu.neumont.battleship.model;

public class Ship implements XmlWritable 
{
	private ShipType shipType;
	private Coordinate coordinate;
	private Direction direction;

	public Ship() {
		// TODO Auto-generated constructor stub
	}
	
	public String toXML()
	{
		/*
		 	<coordinates>B1</coordinates>
			<direction>DOWN</direction>
			<ship>Carrier</ship>
		 */
		return coordinate.toXML() +"\r\n" + 
			direction.toXML() +"\r\n" +
			shipType.toXML();
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
