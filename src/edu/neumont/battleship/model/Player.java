package edu.neumont.battleship.model;

public class Player
{
	private String id;
	private PlayerType type;
	private Board board;
	
	public Player(){}
	
	public Player(String id, PlayerType type)
	{
		this.id = id;
		this.type = type;
		board = new Board();
	}
	
	public void setShip(Ship ship)
	{
		board.setShip(ship);
	}
	
	public String getId()
	{
		return id;
	}
	
	public void setId(String id)
	{
		this.id = id;
	}
	
	public PlayerType getType()
	{
		return type;
	}
	
	public void setType(PlayerType type)
	{
		this.type = type;
	}

	public Board getBoard()
	{
		return board;
	}

	public void setBoard(Board board)
	{
		this.board = board;
	}
}
