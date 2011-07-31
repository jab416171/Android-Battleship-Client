package edu.neumont.battleship.testharness;

import java.io.IOException;

public interface IGameLogic
{
	
	
	public abstract String newGame(String playerName, String robot) throws IOException;
	
	public abstract void gameList() throws IOException;
	
	public abstract void placeShip(String game_Id, String playerName, String coords, String direction, String ship) throws Exception;
	
	public abstract void fire(String game_Id, String playerName, String coords) throws IOException;
	
	public abstract void update(String game_Id, String playerName) throws IOException;
	
	public abstract void join(String game_ID, String playerName) throws IOException;
	
	public abstract void forfeit(String game_Id, String playerName) throws IOException;
	
}