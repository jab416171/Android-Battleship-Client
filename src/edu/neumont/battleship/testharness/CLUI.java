package edu.neumont.battleship.testharness;

import java.util.Scanner;

import edu.neumont.battleship.model.Coordinate;
import edu.neumont.battleship.model.Direction;
import edu.neumont.battleship.model.PlayerType;
import edu.neumont.battleship.model.ShipType;

public class CLUI
{

	private GameLogic logic;
	private Scanner scan = new Scanner(System.in);
	private int gameId;
	private String playerName;

	public static void main(String[] args)
	{
		//changed this --v to change the logic
		new CLUI(new NetworkLogic()).RunGame();
	}

	public CLUI(GameLogic logic)
	{
		this.logic = logic;
	}

	/*
	 * 
	 * @see edu.neumont.battleship.testers.IGameLogic#RunGame()
	 */
	public void RunGame()
	{
		boolean play = true;
		System.out.print("Name?: ");
		playerName = scan.nextLine();
		System.out.println();
		
		while (play == true)
		{
			try
			{
				System.out.println("1 - New Game");
				System.out.println("2 - Join");
				System.out.println("3 - Fire");
				System.out.println("4 - Place Ship");
				System.out.println("5 - Update");
				System.out.println("6 - Forfeit");
				System.out.println("7 - Game List");
				System.out.println("8 - Quit");
				String input = scan.nextLine();

				if (input.equals("1"))
				{
					gameId = logic.newGame(playerName, PlayerType.Edison);
				} else if (input.equals("2"))
				{
					logic.join(gameId, playerName);
				} else if (input.equals("3"))
				{
					logic.fire(gameId, playerName, getCoords());
				} else if (input.equals("4"))
				{
					logic.placeShip(gameId, playerName, getCoords(), getDirection(), getShip());
				} else if (input.equals("5"))
				{
					logic.update(gameId, playerName);
				} else if (input.equals("6"))
				{
					logic.forfeit(gameId, playerName);
				} else if (input.equals("7"))
				{
					System.out.println(logic.getGameList());
				} else if (input.equals("8"))
				{
					play = false;
				} else
				{
					System.out.println("Didn't understand input");
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}

		}
	}

	private Coordinate getCoords()
	{
		String ans;
		do
		{
			System.out.print("Enter coordinates: ");
			ans = scan.nextLine();
			// System.out.println();
		} while (ans.length() < 2 || ans.toCharArray()[0] < 97 || ans.toCharArray()[0] > 106 || ans.toCharArray()[1] < 49 || ans.toCharArray()[1] > 57);
		return new Coordinate(Integer.parseInt(ans.charAt(0)+""), Integer.parseInt(ans.charAt(1)+""));
	}

	private Direction getDirection() throws Exception
	{
		String ans;
		do
		{
			System.out.print("Enter direction: ");
			ans = scan.nextLine();
			// System.out.println();
		} while (!ans.equalsIgnoreCase("d") && !ans.equalsIgnoreCase("u") && !ans.equalsIgnoreCase("r") && !ans.equalsIgnoreCase("l"));
		if (ans.equalsIgnoreCase("d"))
			return Direction.DOWN;
		else if (ans.equalsIgnoreCase("u"))
			return Direction.UP;
		else if (ans.equalsIgnoreCase("r"))
			return Direction.RIGHT;
		else if (ans.equalsIgnoreCase("l"))
			return Direction.LEFT;
		throw new Exception("All directions should be accounted for");
	}

	private ShipType getShip() throws Exception
	{
		String ans;
		do
		{
			System.out.println("(Carrier, Battleship, Submarie, cRuiser, PatrolBoat)");
			System.out.print("Enter ship: ");
			ans = scan.nextLine();
			// System.out.println();
		} while (!ans.equalsIgnoreCase("c") && !ans.equalsIgnoreCase("b") && !ans.equalsIgnoreCase("s") && !ans.equalsIgnoreCase("r") && !ans.equalsIgnoreCase("p"));
		if (ans.equalsIgnoreCase("c"))
			return ShipType.Carrier;
		else if (ans.equalsIgnoreCase("b"))
			return ShipType.Battleship;
		else if (ans.equalsIgnoreCase("s"))
			return ShipType.Submarine;
		else if (ans.equalsIgnoreCase("r"))
			return ShipType.Cruiser;
		else if (ans.equalsIgnoreCase("p"))
			return ShipType.PatrolBoat;
		throw new Exception("All ships should be accounted for");
	}
}
