package edu.neumont.battleship.http;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Log;
import android.util.Xml;
import edu.neumont.battleship.BattleshipActivity;
import edu.neumont.battleship.exceptions.InvalidXMLException;
import edu.neumont.battleship.http.results.ActionResult;
import edu.neumont.battleship.http.results.FireResult;
import edu.neumont.battleship.http.results.ForfeitResult;
import edu.neumont.battleship.http.results.GameListResult;
import edu.neumont.battleship.http.results.GameStateResult;
import edu.neumont.battleship.http.results.JoinResult;
import edu.neumont.battleship.http.results.NewGameResult;
import edu.neumont.battleship.http.results.PlaceShipResult;
import edu.neumont.battleship.model.FireStatus;
import edu.neumont.battleship.model.GameState;
import edu.neumont.battleship.model.ShipType;
import edu.neumont.battleship.xml.XMLGame;

public class XMLResponse
{
	private static final String TAG = BattleshipActivity.TAG;
	
	// Do we want to make a game object?
	// What's the best way to represent the different types of responses?
	
	@SuppressWarnings("unchecked")
	public static <T extends ActionResult> T getResultType(String xml, Class<T> cls)
			throws InvalidXMLException
	{
		T returnedResult = null;
		ActionResult result = parseXML(xml);
		
		returnedResult = (T) result;
		return returnedResult;
	}
	
	private static ActionResult parseXML(String xml) throws InvalidXMLException
	{
		return parseGameListResponse(xml);
		
//		if (xml != null)
//		{
//			if (isNewGameResult(xml))
//			{
//				return parseNewGameResponse(xml);
//			} else if (isGameListResult(xml))
//			{
//				return parseGameListResponse(xml);
//			} else if (isGameStateResult(xml))
//			{
//				return parseGameStateResponse(xml);
//			} else if (isFireResult(xml))
//			{
//				return parseFireResponse(xml);
//			} else if (isForfeitResult(xml))
//			{
//				return parseForfeitResponse(xml);
//			} else if (isJoinResult(xml))
//			{
//				return parseJoinResponse(xml);
//			} else if (isPlaceShipResult(xml))
//			{
//				return parsePlaceShipResponse(xml);
//			} else
//				throw new InvalidXMLException("Could not parse response " + xml);
//		}
//			
//		
//		return null;
	}
	
	private static NewGameResult parseNewGameResponse(String xml)
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	private static GameListResult parseGameListResponse(String xml) throws InvalidXMLException
	{
		class GameListContentHandler extends DefaultHandler
		{
			private Stack<String> stack = new Stack<String>();
			private XMLGame game;
			private List<XMLGame> gameList = new ArrayList<XMLGame>();
			
			/**
			 * Called when tag starts ( ex:- <name>AndroidPeople</name> --
			 * <name> )
			 */
			@Override
			public void startElement(String uri, String localName, String qName,
					Attributes attributes) throws SAXException
			{
				stack.push(localName);
				if (localName.equals("game"))
				{
					game = new XMLGame();
				}
			}
			
			/**
			 * Called when tag closing ( ex:- <name>AndroidPeople</name> --
			 * </name> )
			 */
			@Override
			public void endElement(String uri, String localName, String qName) throws SAXException
			{
				if (!stack.pop().equals(localName))
				{
					throw new SAXException("Ended on an element we didn't know we started");
				}
				if (localName.equals("game"))
				{
					gameList.add(game);
					game = null;
				}
//				stack.notify();
			}
			
			/**
			 * Called to get tag characters ( ex:- <name>AndroidPeople</name> --
			 * to get AndroidPeople Character )
			 */
			@Override
			public void characters(char[] ch, int start, int length) throws SAXException
			{
				String topOfStack = stack.peek();
				if (topOfStack.equals("response"))
				{
					//throw new SAXException("There should be no characters in <response>");
					Log.w(TAG, "characters in <response>: "+new String(ch, start, length));
				} else if (topOfStack.equals("game"))
				{
//					throw new SAXException("There should be no characters in <game>");
					Log.w(TAG, "characters in <game>: "+new String(ch, start, length));
				} else if (topOfStack.equals("gameID"))
				{
					game.setGameId(Integer.parseInt(new String(ch, start, length)));
				} else if (topOfStack.equals("turn"))
				{
					game.setTurn(new String(ch, start, length));
				} else if (topOfStack.equals("state"))
				{
					game.setState(XMLResponse.parseGameState(new String(ch, start, length)));
				}
			}
			
			public List<XMLGame> getGameList()
			{
//				try
//				{
//					stack.wait();
					return gameList;
//				} catch (InterruptedException e)
//				{
//					e.printStackTrace();
//				}
//				return null;
			}
		}
		
		try
		{
			GameListContentHandler handler = new GameListContentHandler();
			Xml.parse(xml, handler);
			return new GameListResult(handler.getGameList());
		} catch (SAXException e)
		{
			Log.e(TAG, "Error parsing server repsonse", e);
			throw new InvalidXMLException("Error parsing server repsonse");
		}
	}
	
	private static GameStateResult parseGameStateResponse(String xml)
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	private static boolean isGameListResult(String xml)
	{
//		return responseBody.hasChildNodes() && responseBody.getChildNodes().getLength() >= 1
//				&& responseBody.getChildNodes().item(0).getChildNodes().getLength() == 3
//				&& isWaitingFor2nd(responseBody.getChildNodes().item(0).getChildNodes().item(2));
		return false;
	}
	
	private static boolean isGameStateResult(String xml)
	{
//		return responseBody.hasChildNodes() && responseBody.getChildNodes().getLength() >= 1
//				&& responseBody.getChildNodes().item(0).getChildNodes().getLength() == 3
//				&& !isWaitingFor2nd(responseBody.getChildNodes().item(0).getChildNodes().item(2));
		return false;
	}
	
	private static boolean isWaitingFor2nd(String xml)
	{
//		return item.getNodeValue().equalsIgnoreCase("WaitingFor2nd");
		return false;
	}
	
	private static boolean isNewGameResult(String xml)
	{
//		return responseBody.hasChildNodes() && responseBody.getChildNodes().getLength() == 1
//				&& responseBody.getChildNodes().item(0).getNodeName().equals("gameID");
		return false;
	}
	
	private static PlaceShipResult parsePlaceShipResponse(String xml)
	{
		// TODO Auto-generated method stub
		// TODO handle errors
		return null;
	}
	
	private static boolean isPlaceShipResult(String xml)
	{
//		return nodeValue.toLowerCase().contains("Successfully placed ship".toLowerCase());
		return false;
	}
	
	private static JoinResult parseJoinResponse(String xml)
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	private static boolean isJoinResult(String xml)
	{
//		return nodeValue.toLowerCase().contains("joined".toLowerCase());
		return false;
	}
	
	private static ForfeitResult parseForfeitResponse(String xml)
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	private static boolean isForfeitResult(String xml)
	{
//		return nodeValue.toLowerCase().contains("forfeit".toLowerCase());
		return false;
	}
	
	private static FireResult parseFireResponse(String xml)
	{
		// get an input stream from the xml
		try
		{
			String strGameId = "gameID";
			String strStatus = "result";
			String strShipType = "ship";
			
			// evaluate
			int gameId = Integer.parseInt(strGameId);
			FireStatus status = getFireStatus(strStatus);
			ShipType shipType = getShipType(strShipType);
			
			return new FireResult(gameId, status, shipType);
		} catch (Exception e)
		{
			Log.e(TAG, "Exception in FireResult", e);
			throw new IllegalArgumentException("Invalid Fire Response");
		}
	}
	
	private static boolean isFireResult(String nodeValue)
	{
		FireStatus[] fireStatuses = FireStatus.values();
		List<String> fireStatusStrings = new ArrayList<String>();
		for (FireStatus fireStatus : fireStatuses)
		{
			fireStatusStrings.add(fireStatus.toString());
		}
		return fireStatusStrings.contains(nodeValue);
	}
	
	private static ShipType getShipType(String shipType)
	{
		return Enum.valueOf(ShipType.class, shipType);
	}
	
	private static FireStatus getFireStatus(String fireStatus)
	{
		return Enum.valueOf(FireStatus.class, fireStatus);
	}
	
	public static GameState parseGameState(String str)
	{
		if (str.equals("WaitingFor2nd"))
		{
			return GameState.WaitingFor2nd;
		} else if (str.equals("WaitingForShips"))
		{
			return GameState.WaitingForShips;
		} else if (str.equals("InProgress"))
		{
			return GameState.InProgress;
		} else if (str.equals("Finished"))
		{
			return GameState.Finished;
		} else if (str.equals("Forfeited"))
		{
			return GameState.Forfeited;
		} else
		{
			return GameState.TimedOut;
		}
		
	}
}

// incomplete...
enum ResponseType
{
	error, simpleResult, fireResult, gameListResult,
	
}