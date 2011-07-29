import java.io.IOException;
import java.util.*;

/**
 * Scout Bogar Neumont University CSC 150 Winter 2011 Section: A Date: Lab:
 */
public class NetworkTester {

	private String ID;
	private boolean gamePlayed = true;

	/**
	 * Constructor for objects of class NetworkTester
	 */
	public NetworkTester() {
		Game();
	}

	public void Game() {
		while (gamePlayed == true) {
			try {
				System.out
						.println("Battleship: Type the number of the item you would like.");
				System.out.println("1 - New Game");
				System.out.println("2 - Game List");
				System.out.println("3 - Fire");
				System.out.println("4 - Place Ship");
				System.out.println("5 - Update");
				System.out.println("6 - Join");
				System.out.println("7 - Forfeit");
				System.out.println("8 - Quit");
				Scanner scan = new Scanner(System.in);
				String input1 = scan.nextLine();

				if (input1.equals("1")) {
					newGame();
				}
				if (input1.equals("2")) {
					gameList();
				}
				if (input1.equals("3")) {
					fire();
				}
				if (input1.equals("4")) {
					placeShip();
				}
				if (input1.equals("5")) {
					update();
				}
				if (input1.equals("6")) {
					join();
				}
				if (input1.equals("7")) {
					forfeit();
				}
				if (input1.equals("8")) {
					System.exit(0);
				}
			}

			catch (IOException e) {
				System.out.println("There was an error.");
			}

		}
	}

	public void newGame() throws IOException {
		String result = BattleshipPhone
				.call("NewGame",
						"<request><type>New Game</type><playerID>Scout</playerID><robot>Edison</robot></request>");
		System.out.println(result);
		int start = result.indexOf("<gameID>") + 8;

		int end = result.indexOf("</gameID>");
		ID = result.substring(start, end);

	}

	public void gameList() throws IOException {
		String result = BattleshipPhone.call("GameList",
				"<request><type>game list</type></request>");
		System.out.println(result);
	}

	public void placeShip() throws IOException {
		String result = BattleshipPhone
				.call("PlaceShip",
						"<request><type>Place</type><gameID>"
								+ ID
								+ "</gameID><playerID>Scout</playerID><coordinates>B1</coordinates><direction>DOWN</direction><ship>Carrier</ship></request>");
		System.out.println(result);
	}

	public void fire() throws IOException {
		String result = BattleshipPhone
				.call("Fire",
						"<request><type>Fire</type><gameID>"
								+ ID
								+ "</gameID><playerID>Scout</playerID><coordinates>A1</coordinates></request>");
		System.out.println(result);
	}

	public void update() throws IOException {
		String result = BattleshipPhone.call("Update",
				"<request><type>Update</type><gameID>" + ID
						+ "</gameID><playerID>Scout</playerID></request>");
		System.out.println(result);
	}

	public void join() throws IOException {
		String result = BattleshipPhone.call("Join",
				"<request><type>Join</type><playerID>Scout</playerID><gameID>"
						+ ID + "</gameID></request>");
		System.out.println(result);
	}

	public void forfeit() throws IOException {
		String result = BattleshipPhone.call("Forfeit",
				"<request><type>Forfeit</type><gameID>" + ID
						+ "</gameID><playerID>Scout</playerID></request>");
		System.out.println(result);
	}

}
