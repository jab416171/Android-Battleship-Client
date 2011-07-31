package edu.neumont.battleship.model;

public enum GameStatus implements XmlReadable<GameStatus>{
	WaitingFor2nd, WaitingForShips, InProgress, Finished, Forfeited, TimedOut;

	public GameStatus fromXML(String xml)
	{
		return Enum.valueOf(GameStatus.class, xml);
	}
}
