package edu.neumont.battleship.model;

public class Board
{
	public static final int NUM_ROWS = 10;
	public static final int NUM_COLS = 10;
	
	private FireStatus[][] board;
	public Board()
	{
		board = new FireStatus[NUM_ROWS][NUM_COLS];
	}
	public FireStatus getCellStatus(int row, int col)
	{
		return board[row][col];
	}
}