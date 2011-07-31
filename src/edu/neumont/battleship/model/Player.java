package edu.neumont.battleship.model;

public class Player
{
	private int id;
	private PlayerType type;
	
	public int getId()
	{
		return id;
	}
	
	public void setId(int id)
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
}
