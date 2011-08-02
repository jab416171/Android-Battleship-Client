package edu.neumont.battleship.model;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class XMLDOMHelper
{
	public static Document getDocument(InputStream is) throws ParserConfigurationException, SAXException, IOException
	{
		
			DocumentBuilderFactory domfactory = DocumentBuilderFactory.newInstance();
			domfactory.setNamespaceAware(true); // never forget this!
			DocumentBuilder builder = domfactory.newDocumentBuilder();
			Document doc = builder.parse(is);
			return doc;
		
	}

	public static XPathExpression getXpathExpression(String s) throws XPathExpressionException
	{
		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		return xpath.compile(s);
	}
}
