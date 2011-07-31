package edu.neumont.battleship.testers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Cookie
{
	static final String COOKIE = "Cookie";
	static final String SET_COOKIE = "Set-Cookie";
	static final String COOKIE_VALUE_DELIMITER = ";";
	static final String SET_COOKIE_SEPARATOR = "; ";
	static final String EXPIRES = "expires";
	static final String PATH = "path";
	static final char NAME_VALUE_SEPARATOR = '=';
	
	static final String DATE_FORMAT = "EEE, dd-MMM-yyyy hh:mm:ss z";
	static DateFormat dateFormat= new SimpleDateFormat(DATE_FORMAT);
	
	private String name;
	private String value;
	private Date expiration;
	private String path;
	private String domain;
	
	public Cookie(String headerLine)
	{
		//TODO: parse
	}
	
	public Cookie(String name, String value)
	{
		super();
		this.name = name;
		this.value = value;
	}
	
	public Cookie(String name, String value, Date expiration)
	{
		super();
		this.name = name;
		this.value = value;
		this.expiration = expiration;
	}

	public Cookie(String name, String value, Date expiration, String path, String domain)
	{
		super();
		this.name = name;
		this.value = value;
		this.expiration = expiration;
		this.path = path;
		this.domain = domain;
	}
	
	public boolean isExpired()
	{
		return new Date().before(expiration);
	}
	
	public String getName()
	{
		return name;
	}
	public String getValue()
	{
		return value;
	}
	public Date getExpiration()
	{
		return expiration;
	}
	public String getPath()
	{
		return path;
	}
	public String getDomain()
	{
		return domain;
	}
	
}
