package edu.neumont.battleship.http;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

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
	public static <T> T getResultType(String xml, Class<T> cls) {
		T returnedResult = null;
		Object result = parseXML(xml);
		
		returnedResult = (T) result;
		return returnedResult;
		
		
	}

	private static Object parseXML(String xml)
	{
		try
		{
			InputStream is = new ByteArrayInputStream(xml.getBytes());
			
			Node responseBody = (Node) xpath.evaluate("//response",is,XPathConstants.NODE);
			Node fireResultNode = (Node) xpath.evaluate("//FireResult", responseBody, XPathConstants.NODE);
			if(fireResultNode != null) {
				return parseFireResult(is);
			} else if(is.equals(null)) {
				return 0;
			}
			else {
				return null;
			}
			
		} catch (XPathExpressionException e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
	
	private static FireResult parseFireResult(InputStream is)
	{
		// get an input stream from the xml
		try
		{
			String strGameId = xpath.evaluate("gameID", is);
			String strStatus = xpath.evaluate("result", is);
			String strShipType = xpath.evaluate("ship", is);

			// evaluate
			int gameId = Integer.parseInt(strGameId);
			FireStatus status = getFireStatus(strStatus);
			ShipType shipType = getShipType(strShipType);
			
			return new FireResult(gameId, status, shipType);
		} catch (Exception e)
		{
			Log.e(TAG, "Exception in FireResult", e);
		}
		return null;
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