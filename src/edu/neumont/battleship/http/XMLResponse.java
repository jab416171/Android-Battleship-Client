package edu.neumont.battleship.http;

public class XMLResponse {
	private String rawXML;
	private int gameID;
	private String result;
	private String winner;
	private ResponseType type;

	// Do we want to make a game object?
	// What's the best way to represent the different types of responses?

	public XMLResponse(String rawXML) {
		// TODO make this object useful
		this.rawXML = rawXML;
	}

	public String getRawXML() {
		return rawXML;
	}

}

// incomplete...
enum ResponseType {
	error, simpleResult,

}