package edu.neumont.battleship.testharness;

import java.io.IOException;

import edu.neumont.battleship.exceptions.BattleshipException;
import edu.neumont.battleship.exceptions.BattleshipIOException;
import edu.neumont.battleship.http.results.FireResult;
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
	
	public abstract int newGame(String playerName, PlayerType robot) throws BattleshipException;
	
	public abstract int[] getGameList() throws IOException, BattleshipIOException;
	
	// successful
	// unsuccessful
	//  Exception:off board
	//  Exception:overlaps
	//  Exception:already placed that ship
	public abstract boolean placeShip(int gameId, String playerName, Coordinate coords, Direction direction, ShipType ship) throws BattleshipException;
	
	//miss
	//hit
	//sunk
	//unknown
	//Exception:not your turn
	public abstract FireResult fire(int gameId, String playerName, Coordinate coords) throws BattleshipException;
	
	// TODO: complicated
	public abstract String update(int gameId, String playerName) throws BattleshipException;
	
	//successful
	//unsuccessful
	public abstract boolean join(int gameId, String playerName) throws BattleshipException;
	
	public abstract void forfeit(int gameId, String playerName) throws BattleshipException;
	
}