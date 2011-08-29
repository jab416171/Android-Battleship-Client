package edu.neumont.battleship.http;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.Node;

import android.util.Log;
import edu.neumont.battleship.BattleshipActivity;
import edu.neumont.battleship.model.FireResult;
import edu.neumont.battleship.model.FireStatus;
import edu.neumont.battleship.model.ShipType;
import edu.neumont.battleship.xml.XMLDOMHelper;

public class XMLResponse
{
	private static final String TAG = BattleshipActivity.TAG;
	private static final XPath xpath = XMLDOMHelper.getXPath();
	
	// Do we want to make a game object?
	// What's the best way to represent the different types of responses?
	
	private XMLResponse()
	{
		
	}
	
	@SuppressWarnings("unchecked")
	public static <T extends ActionResult> T getResultType(String xml, Class<T> cls)
	{
		T returnedResult = null;
		Object result = parseXML(xml);
		
		returnedResult = (T) result;
		return returnedResult;
	}
	
	private static ActionResult parseXML(String xml)
	{
		try
		{
			InputStream is = new ByteArrayInputStream(xml.getBytes());
			
			Node responseBody = (Node) xpath.evaluate("//response", is, XPathConstants.NODE);
			// New game
			if (isNewGameResult(responseBody))
			{
				return parseNewGameResponse(responseBody);
			} else if (isGameListResult(responseBody))
			{
				return parseGameListResponse(responseBody);
			} else if (isGameStateResult(responseBody))
			{
				return parseGameStateResponse(responseBody);
			}
			// forfeit
			// join
			// place ship
			// fire
			Node resultNode = (Node) xpath.evaluate("//result", responseBody, XPathConstants.NODE);
			if (resultNode != null)
			{
				String nodeValue = resultNode.getNodeValue();
				if (isFireResult(nodeValue))
				{
					return parseFireResponse(resultNode);
				} else if (isForfeitResult(nodeValue))
				{
					return parseForfeitResponse(resultNode);
				} else if (isJoinResult(nodeValue))
				{
					return parseJoinResponse(resultNode);
				} else if (isPlaceShipResult(nodeValue))
				{
					return parsePlaceShipResponse(resultNode);
				} else
					throw new RuntimeException("could not parse response " + xml);
			}
			
		} catch (XPathExpressionException e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
	
	private static NewGameResult parseNewGameResponse(Node responseBody)
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	private static GameListResult parseGameListResponse(Node responseBody)
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	private static GameStateResult parseGameStateResponse(Node responseBody)
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	private static boolean isGameListResult(Node responseBody)
	{
		return responseBody.hasChildNodes() && responseBody.getChildNodes().getLength() >= 1
				&& responseBody.getChildNodes().item(0).getChildNodes().getLength() == 3
				&& isWaitingFor2nd(responseBody.getChildNodes().item(0).getChildNodes().item(2));
	}
	
	private static boolean isGameStateResult(Node responseBody)
	{
		return responseBody.hasChildNodes() && responseBody.getChildNodes().getLength() >= 1
				&& responseBody.getChildNodes().item(0).getChildNodes().getLength() == 3
				&& !isWaitingFor2nd(responseBody.getChildNodes().item(0).getChildNodes().item(2));
	}
	
	private static boolean isWaitingFor2nd(Node item)
	{
		return item.getNodeValue().equalsIgnoreCase("WaitingFor2nd");
	}
	
	private static boolean isNewGameResult(Node responseBody)
	{
		return responseBody.hasChildNodes() && responseBody.getChildNodes().getLength() == 1
				&& responseBody.getChildNodes().item(0).getNodeName().equals("gameID");
	}
	
	private static PlaceShipResult parsePlaceShipResponse(Node node)
	{
		// TODO Auto-generated method stub
		// TODO handle errors
		return null;
	}
	
	private static boolean isPlaceShipResult(String nodeValue)
	{
		return nodeValue.toLowerCase().contains("Successfully placed ship".toLowerCase());
	}
	
	private static JoinResult parseJoinResponse(Node node)
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	private static boolean isJoinResult(String nodeValue)
	{
		return nodeValue.toLowerCase().contains("joined".toLowerCase());
	}
	
	private static ForfeitResult parseForfeitResponse(Node node)
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	private static boolean isForfeitResult(String nodeValue)
	{
		return nodeValue.toLowerCase().contains("forfeit".toLowerCase());
	}
	
	private static FireResult parseFireResponse(Node node)
	{
		// get an input stream from the xml
		try
		{
			String strGameId = xpath.evaluate("gameID", node);
			String strStatus = xpath.evaluate("result", node);
			String strShipType = xpath.evaluate("ship", node);
			
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
	
}

// incomplete...
enum ResponseType
{
	error, simpleResult, fireResult,
	
}