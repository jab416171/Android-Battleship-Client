package edu.neumont.battleship;


public enum ShipType{
	Carrier(5),Battleship(4),Submarine(3),Cruiser(3),PatrolBoat(2);

	private int	size;

	/**
	 * @return the size
	 */
	public int getSize(){
		return this.size;
	}

	/**
	 * @param size
	 *            the size to set
	 */
	public void setSize(int size){
		this.size=size;
	}

	
	private ShipType(int size){
		this.size=size;
	}
}
