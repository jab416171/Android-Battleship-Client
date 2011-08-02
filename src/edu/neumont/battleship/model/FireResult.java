package edu.neumont.battleship.model;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;

import org.w3c.dom.Document;

import android.util.Log;
import edu.neumont.battleship.BattleshipActivity;

public class FireResult
{
	public static final String TAG = BattleshipActivity.TAG;
	private int gameId;
	private FireStatus status;
	private ShipType shipType;

	public FireResult()
	{
		// TODO Auto-generated constructor stub
	}

	public FireResult(String xml)
	{
		// This is ridiculous
		// but it's the only way I could get XPath to work--v

		// get an input stream from the xml
		InputStream is = new ByteArrayInputStream(xml.getBytes());
		try
		{
			// load up the DOM
			Document doc = XMLDOMHelper.getDocument(is);

			// create the XPath
			XPathExpression gameIdExpr = XMLDOMHelper.getXpathExpression("//gameID/text()");
			XPathExpression statusExpr = XMLDOMHelper.getXpathExpression("//result/text()");
			XPathExpression shipTypeExpr = XMLDOMHelper.getXpathExpression("//ship/text()");

			// evaluate
			gameId = Integer.parseInt((String) gameIdExpr.evaluate(doc, XPathConstants.STRING));
			status = FireStatus.fromXml((String) statusExpr.evaluate(doc));
			shipType = ShipType.fromXML((String) shipTypeExpr.evaluate(doc));
			// end ridiculous-ness--^
		} catch (Exception e)
		{
			Log.e(TAG, "Exception in FireResult", e);
		}
	}

	public int getGameId()
	{
		return gameId;
	}

	public void setGameId(int gameId)
	{
		this.gameId = gameId;
	}

	public FireStatus getStatus()
	{
		return status;
	}

	public void setStatus(FireStatus status)
	{
		this.status = status;
	}

	public ShipType getShipType()
	{
		return shipType;
	}

	public void setShipType(ShipType shipType)
	{
		this.shipType = shipType;
	}

}
