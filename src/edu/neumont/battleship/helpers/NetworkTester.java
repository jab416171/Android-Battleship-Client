package edu.neumont.battleship.helpers;

import java.io.IOException;
import java.util.*;

public class NetworkTester
{

	private String game_Id;
	private String playerName;
	Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args)
	{
		new NetworkTester().RunGame();
	}
	
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
				System.out.println("2 - Game List");
				System.out.println("3 - Fire");
				System.out.println("4 - Place Ship");
				System.out.println("5 - Update");
				System.out.println("6 - Join");
				System.out.println("7 - Forfeit");
				System.out.println("8 - Quit");
				String input = scan.nextLine();

				if (input.equals("1"))
				{
					newGame();
				} else if (input.equals("2"))
				{
					gameList();
				} else if (input.equals("3"))
				{
					fire();
				} else if (input.equals("4"))
				{
					placeShip();
				} else if (input.equals("5"))
				{
					update();
				} else if (input.equals("6"))
				{
					join();
				} else if (input.equals("7"))
				{
					forfeit();
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

	public void newGame() throws IOException
	{
		String result = BattleshipPhone.call("NewGame", "<request><type>New Game</type><playerID>"+playerName+"</playerID><robot>Edison</robot></request>");
		System.out.println(result);
		int start = result.indexOf("<gameID>") + 8;

		int end = result.indexOf("</gameID>");
		game_Id = result.substring(start, end);

	}

	public void gameList() throws IOException
	{
		String result = BattleshipPhone.call("GameList", "<request><type>game list</type></request>");
		System.out.println(result);
	}

	public void placeShip() throws Exception
	{
		String result = BattleshipPhone.call("PlaceShip", "<request><type>Place</type><gameID>" + game_Id + "</gameID><playerID>"+playerName+"</playerID><coordinates>"+getCoords()+"</coordinates><direction>"+getDirection()+"</direction><ship>"+getShip()+"</ship></request>");
		System.out.println(result);
	}


	public void fire() throws IOException
	{
		String result = BattleshipPhone.call("Fire", "<request><type>Fire</type><gameID>" + game_Id + "</gameID><playerID>"+playerName+"</playerID><coordinates>"+getCoords()+"</coordinates></request>");
		System.out.println(result);
	}

	public void update() throws IOException
	{
		String result = BattleshipPhone.call("Update", "<request><type>Update</type><gameID>" + game_Id + "</gameID><playerID>"+playerName+"</playerID></request>");
		System.out.println(result);
	}

	public void join() throws IOException
	{
		String result = BattleshipPhone.call("Join", "<request><type>Join</type><playerID>"+playerName+"</playerID><gameID>" + game_Id + "</gameID></request>");
		System.out.println(result);
	}

	public void forfeit() throws IOException
	{
		String result = BattleshipPhone.call("Forfeit", "<request><type>Forfeit</type><gameID>" + game_Id + "</gameID><playerID>"+playerName+"</playerID></request>");
		System.out.println(result);
	}
	private String getCoords()
	{
		String ans;
		do
		{
			System.out.print("Enter coordinates: ");
			ans = scan.nextLine();
			//System.out.println();
		} while(ans.length() < 2 || ans.toCharArray()[0] < 97 || ans.toCharArray()[0] > 106 || ans.toCharArray()[1] < 49 || ans.toCharArray()[1] > 57);
		return ans;
	}
	private String getDirection() throws Exception
	{
		String ans;
		do
		{
			System.out.print("Enter direction: ");
			ans = scan.nextLine();
			//System.out.println();
		} while(!ans.equalsIgnoreCase("d") && !ans.equalsIgnoreCase("u") && !ans.equalsIgnoreCase("r") && !ans.equalsIgnoreCase("l") );
		if(ans.equalsIgnoreCase("d"))
			return "DOWN";
		else if(ans.equalsIgnoreCase("u"))
			return "UP";
		else if(ans.equalsIgnoreCase("r"))
			return "RIGHT";
		else if(ans.equalsIgnoreCase("l"))
			return "LEFT";
		throw new Exception("All directions should be accounted for");
	}
	private String getShip() throws Exception
	{
		String ans;
		do
		{
			System.out.print("Enter ship: ");
			ans = scan.nextLine();
			//System.out.println();
		} while(!ans.equalsIgnoreCase("c") && !ans.equalsIgnoreCase("b") && !ans.equalsIgnoreCase("s") && !ans.equalsIgnoreCase("r") && !ans.equalsIgnoreCase("p"));
		if(ans.equalsIgnoreCase("c"))
			return "Carrier";
		else if(ans.equalsIgnoreCase("b"))
			return "Battleship";
		else if(ans.equalsIgnoreCase("s"))
			return "Submarine";
		else if(ans.equalsIgnoreCase("r"))
			return "Cruiser";
		else if(ans.equalsIgnoreCase("p"))
			return "PatrolBoat";
		throw new Exception("All ships should be accounted for");
	}
}
