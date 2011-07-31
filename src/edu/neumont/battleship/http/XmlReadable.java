package edu.neumont.battleship.http;

public interface XmlReadable<T>
{
	public T fromXML(String xml);
}
