package model;

public enum Direction implements XmlWritable{
	DOWN, UP, LEFT, RIGHT;

	public String toXML()
	{
		return toString().toUpperCase();
	}
}
