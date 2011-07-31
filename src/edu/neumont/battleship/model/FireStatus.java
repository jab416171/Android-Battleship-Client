package edu.neumont.battleship.model;

public enum FireStatus {
	HIT, MISS, SUNK, UNKNOWN;

	FireStatus(){}
	public static FireStatus fromXml(String xml)
	{
		return Enum.valueOf(FireStatus.class, xml);
	}
}
