package edu.neumont.battleship;

public class XMLHelper {
//	public static String methodName() {
//		StringBuilder xml = new StringBuilder();
//		
//		return xml.toString();
//	}

	
	/**
	 * 
	 * @param playerId Your player ID
	 * @param robot The robot you want to play against
	 * @return
	 */
	public static String newGame(String playerId, Opponent robot) {
		StringBuilder xml = new StringBuilder();
		xml.append("<request>");
		xml.append("<playerID>");
		xml.append(playerId);
		xml.append("</playerID>");
		if(robot != Opponent.Human) {
			xml.append("<robot>");
			xml.append(robot.toString());
			xml.append("</robot>");
		}
		xml.append("</request>");
		return xml.toString();
	}
	
	public static String gameList() {
	StringBuilder xml = new StringBuilder();
	xml.append("<request></request>");
	return xml.toString();
}
	
}
