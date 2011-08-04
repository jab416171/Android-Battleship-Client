package edu.neumont.battleship.testharness;

import java.util.Scanner;

public class CLUI
{

	private GameLogic logic;
	private Scanner scan = new Scanner(System.in);
	private String gameId;
	private String playerName;

	public static void main(String[] args)
	{
		//changed this --v to change the logic
		new CLUI(new HardCodedXML()).RunGame();
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
					gameId = logic.newGame(playerName, "Edison");
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
					logic.gameList();
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

	private String getCoords()
	{
		String ans;
		do
		{
			System.out.print("Enter coordinates: ");
			ans = scan.nextLine();
			// System.out.println();
		} while (ans.length() < 2 || ans.toCharArray()[0] < 97 || ans.toCharArray()[0] > 106 || ans.toCharArray()[1] < 49 || ans.toCharArray()[1] > 57);
		return ans;
	}

	private String getDirection() throws Exception
	{
		String ans;
		do
		{
			System.out.print("Enter direction: ");
			ans = scan.nextLine();
			// System.out.println();
		} while (!ans.equalsIgnoreCase("d") && !ans.equalsIgnoreCase("u") && !ans.equalsIgnoreCase("r") && !ans.equalsIgnoreCase("l"));
		if (ans.equalsIgnoreCase("d"))
			return "DOWN";
		else if (ans.equalsIgnoreCase("u"))
			return "UP";
		else if (ans.equalsIgnoreCase("r"))
			return "RIGHT";
		else if (ans.equalsIgnoreCase("l"))
			return "LEFT";
		throw new Exception("All directions should be accounted for");
	}

	private String getShip() throws Exception
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
			return "Carrier";
		else if (ans.equalsIgnoreCase("b"))
			return "Battleship";
		else if (ans.equalsIgnoreCase("s"))
			return "Submarine";
		else if (ans.equalsIgnoreCase("r"))
			return "Cruiser";
		else if (ans.equalsIgnoreCase("p"))
			return "PatrolBoat";
		throw new Exception("All ships should be accounted for");
	}
}
