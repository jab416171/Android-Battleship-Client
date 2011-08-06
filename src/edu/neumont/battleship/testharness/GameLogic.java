package edu.neumont.battleship.testharness;

import java.io.IOException;

public interface GameLogic
{
	
	
	public abstract String newGame(String playerName, String robot) throws IOException;
	
	public abstract String gameList() throws IOException;
	
	public abstract String placeShip(String game_Id, String playerName, String coords, String direction, String ship) throws Exception;
	
	public abstract String fire(String game_Id, String playerName, String coords) throws IOException;
	
	public abstract String update(String game_Id, String playerName) throws IOException;
	
	public abstract String join(String game_Id, String playerName) throws IOException;
	
	public abstract String forfeit(String game_Id, String playerName) throws IOException;
	
}