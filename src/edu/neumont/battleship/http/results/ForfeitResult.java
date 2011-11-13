package edu.neumont.battleship.http.results;

public class ForfeitResult extends ActionResult 
{
	private final boolean success;

	public ForfeitResult(boolean success)
	{
		super();
		this.success = success;
	}

	public boolean isSuccess()
	{
		return success;
	}
	
}
