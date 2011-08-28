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
	//no cookie, except for newGame
	//bad gameId
	//game is over
	
	public abstract int newGame(String playerName, PlayerType robot) throws IOException;
	
	public abstract int[] getGameList() throws IOException;
	
	// successful
	// unsuccessful
	//  off board
	//  overlaps
	//  already placed that ship
	public abstract String placeShip(int gameId, String playerName, Coordinate coords, Direction direction, ShipType ship) throws Exception;
	
	//miss
	//hit
	//not your turn
	public abstract String fire(int gameId, String playerName, Coordinate coords) throws IOException;
	
	// TODO: complicated
	public abstract String update(int gameId, String playerName) throws IOException;
	
	//successful
	//unsuccessful
	public abstract String join(int gameId, String playerName) throws IOException;
	
	public abstract String forfeit(int gameId, String playerName) throws IOException;
	
}