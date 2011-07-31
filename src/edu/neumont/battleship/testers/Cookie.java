package edu.neumont.battleship.testers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

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
	static DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
	
	private String name;
	private String value;
	private Date expiration;
	private String path;
//	private String domain;
	
	public Cookie(String headerLine)
	{
		// get ready to parse the cookie
		StringTokenizer st = new StringTokenizer(headerLine, COOKIE_VALUE_DELIMITER);
		
		// the specification dictates that the first name/value pair
		// in the string is the cookie name and value, so let's handle
		// them as a special case:
		
		if (st.hasMoreTokens())
		{
			// get the next key/value pair
			String token = st.nextToken();
			// get the index of the separator
			int seperatorIdx = token.indexOf(NAME_VALUE_SEPARATOR);
			// retrieve the key and value from the token
			name = token.substring(0, seperatorIdx);
			value = token.substring(seperatorIdx + 1, token.length());
			
		}
		// same as in above 'if' statement
		while (st.hasMoreTokens())
		{
			String token = st.nextToken();
			if (token.length() > 0)
			{
				int separatorIdx = token.indexOf(NAME_VALUE_SEPARATOR);
				if (separatorIdx >= 0)
				{
					String attrName = token.substring(
							0, separatorIdx).toLowerCase();
					String attrValue = token.substring(
							separatorIdx + 1, token.length());
					if(attrName.equals(EXPIRES))
						try {
							expiration = dateFormat.parse(attrValue);
						} catch (ParseException e){/*oh well*/}
					else if(attrName.equals(PATH))
						path = attrValue;
				}
			}
		}
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
//		this.domain = domain;
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
	
//	public String getDomain()
//	{
//		return domain;
//	}
	
	public String toHttpHeader()
	{
		if(isExpired())
			return "";
		return name+NAME_VALUE_SEPARATOR+value;
	}
	
	@Override
	public String toString()
	{
		return name+NAME_VALUE_SEPARATOR+value+COOKIE_VALUE_DELIMITER+
				"path"+NAME_VALUE_SEPARATOR+path+COOKIE_VALUE_DELIMITER+
				"expiration"+NAME_VALUE_SEPARATOR+expiration+COOKIE_VALUE_DELIMITER;
	}
}
