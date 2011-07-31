package edu.neumont.battleship.model;

public enum Direction implements XmlWritable{
	DOWN, UP, LEFT, RIGHT;

	public static Direction fromXML(String xml)
	{
		return Enum.valueOf(Direction.class, xml);
	}
	
	public String toXML()
	{
		return toString().toUpperCase();
	}

}
