package model;

public interface XmlReadable<T>
{
	public T fromXML(String xml);
}
