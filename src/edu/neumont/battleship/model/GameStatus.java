package edu.neumont.battleship.model;

public enum GameStatus {
	WaitingFor2nd, WaitingForShips, InProgress, Finished, Forfeited, TimedOut;

	public static GameStatus fromXML(String xml)
	{
		return Enum.valueOf(GameStatus.class, xml);
	}
}
