package edu.neumont.battleship.model;

public enum FireStatus {
	HIT, MISS, SUNK, UNKNOWN;

	private FireStatus() {
	}

	public static FireStatus fromXml(String xml) {
		return Enum.valueOf(FireStatus.class, xml);
	}
}
