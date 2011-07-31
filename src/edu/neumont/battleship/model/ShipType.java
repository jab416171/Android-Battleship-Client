package edu.neumont.battleship.model;

import java.io.Serializable;

public enum ShipType implements Serializable, XmlWritable
{
	Carrier(5), Battleship(4), Submarine(3), Cruiser(3), PatrolBoat(2);

	private int size;


	private ShipType(int size)
	{
		this.size = size;
	}
	
	public String toXML()
	{
		return toString().toUpperCase();
	}
	public static ShipType fromXML(String xml)
	{
		return Enum.valueOf(ShipType.class, xml);
	}
	
	/**
	 * @return the size
	 */
	public int getSize() {
		return this.size;
	}

}
