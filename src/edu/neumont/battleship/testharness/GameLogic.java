package edu.neumont.battleship.testharness;

import java.io.IOException;

import edu.neumont.battleship.model.Coordinate;
import edu.neumont.battleship.model.Direction;
import edu.neumont.battleship.model.PlayerType;
import edu.neumont.battleship.model.ShipType;

/**
 * Everything you can do in a battleship match
 * @author gwatson
 *
 */
public interface GameLogic
{
	public abstract int newGame(String playerName, PlayerType robot) throws IOException;
	
	public abstract int[] getGameList() throws IOException;
	
	public abstract String placeShip(int gameId, String playerName, Coordinate coords, Direction direction, ShipType ship) throws Exception;
	
	public abstract String fire(int gameId, String playerName, Coordinate coords) throws IOException;
	
	public abstract String update(int gameId, String playerName) throws IOException;
	
	public abstract String join(int gameId, String playerName) throws IOException;
	
	public abstract String forfeit(int gameId, String playerName) throws IOException;
	
}