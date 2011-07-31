package model;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Coordinate implements XmlWritable, XmlReadable<Coordinate>
{
	private int row;
	private int col;

	public Coordinate(int row, int col) 
	{
		if(row > Board.NUM_ROWS)
			throw new IllegalArgumentException("row cannont be over "+Board.NUM_ROWS);
		if(row < 0)
			throw new IllegalArgumentException("row must be greater that 0");
		this.row = row;
		
		if(col > Board.NUM_COLS)
			throw new IllegalArgumentException("col cannont be over "+Board.NUM_COLS);
		if(col < 0)
			throw new IllegalArgumentException("col must be greater that 0");
		this.col = col;
	}
	
	public String toXML()
	{
		return "<coordinates>"+getLetter()+row+"</coordinates>";
	}
	public Coordinate fromXML(String xml)
	{
		throw new NotImplementedException();
	}
	
	public char getLetter()
	{
		return (char)(col + 97);
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

}
