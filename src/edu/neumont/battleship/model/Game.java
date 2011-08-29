package edu.neumont.battleship.model;

import edu.neumont.battleship.http.BattleshipCookie;

public class Game {
	private int gameId;
	//Game shouldn't worry about the cookie, only the game state
	private BattleshipCookie cookie;
	private Player localPlayer;
	private Player remotePlayer;
	private GameState state;
	private Player winner;

	public Game() {
		// TODO Auto-generated constructor stub
	}

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public BattleshipCookie getCookie() {
		return cookie;
	}

	public void setCookie(BattleshipCookie cookie) {
		this.cookie = cookie;
	}

	public Player getLocalPlayer() {
		return localPlayer;
	}

	public void setLocalPlayer(Player localPlayer) {
		this.localPlayer = localPlayer;
	}

	public Player getRemotePlayer() {
		return remotePlayer;
	}

	public void setRemotePlayer(Player remotePlayer) {
		this.remotePlayer = remotePlayer;
	}

	public GameState getState() {
		return state;
	}

	public void setState(GameState state) {
		this.state = state;
	}

	public Player getWinner() {
		return winner;
	}

	public void setWinner(Player winner) {
		this.winner = winner;
	}

}
