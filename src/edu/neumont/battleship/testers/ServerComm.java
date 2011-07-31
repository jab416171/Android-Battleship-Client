package edu.neumont.battleship.testers;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * The ServerComm class is a helper class for communicating with the
 * battleship server. The only method you need to worry about is
 * <code>call</code>. As an example, you could write the following code: String
 * result = ServerComm.call( "NewGame",
 * "<request><playerID>[[Your Name]]</playerID><robot>Edison</robot></request>"
 * ); // process the xml result here
 */
/**
 * @author gwatson
 * 
 */
public class ServerComm
{
	private static final String HOST = "http://joe-bass.com:8800/BattleshipServer";
//	private boolean save = true;
	
	private ServerComm()
	{
		this(false);
	}
	
	private ServerComm(boolean save)
	{
//		this.save = save;
//		restore();
	}
	
	public static String call(String requestType) throws IOException
	{
		return call(requestType, null);
	}
	
	/**
	 * @param requestType
	 *            the URL to send to
	 * @param requestBody
	 *            the message to put in the HTTP body
	 * @return the response body
	 * @throws IOException
	 */
	public static String call(String requestType, String requestBody) throws IOException
	{
		URLConnection conn = getConnection(requestType);
		instance.setCookies(conn);
		
		// write the body
		writeToConn(requestBody, conn);
		
		// read the response
		StringBuilder sb = readFromConn(conn);
		
		instance.storeCookies(conn);
		
		return sb.toString();
	}
	
	/**
	 * @param conn
	 *            URLConnection to read form
	 * @return a StringBuilder with the response
	 * @throws IOException
	 */
	private static StringBuilder readFromConn(URLConnection conn) throws IOException
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		StringBuilder sb = new StringBuilder(conn.getContentLength());
		String inputLine;
		while ((inputLine = in.readLine()) != null)
		{
			sb.append(inputLine);
			sb.append("\r\n");
		}
		in.close();
		return sb;
	}
	
	/**
	 * @param requestBody
	 *            what to write in the HTTP body
	 * @param conn
	 *            the URLConnection to write to
	 * @throws IOException
	 */
	private static void writeToConn(String requestBody, URLConnection conn) throws IOException
	{
		if (requestBody != null && requestBody.length() > 0)
		{
			PrintWriter request = new PrintWriter(conn.getOutputStream());
			request.print(requestBody);
			request.flush();
			request.close();
		}
	}
	
	/**
	 * @param requestType
	 *            the path to append to the URL.<br/>
	 *            prepended with the 'HOST' and '/GameRequest/'
	 * @return
	 * @throws IOException
	 */
	private static URLConnection getConnection(String requestType) throws IOException
	{
		URL url = new URL(HOST + "/GameRequest/" + requestType);
		URLConnection conn = url.openConnection();
		conn.setDoOutput(true);
		return conn;
	}
	
	private Map<String, Map<String, Map<String, String>>> cookies = new HashMap<String, Map<String, Map<String, String>>>();
	
	private static final char DOT = '.';
	
	private static ServerComm instance = new ServerComm();
	
//	/**
//	 * @return where to save cookies to;
//	 */
//	private String file()
//	{
//		return "cookies.out";
//	}
//	
//	/**
//	 * writes the cookies as XML to file()
//	 */
//	private void save()
//	{
//		if (save)
//		{
//			try
//			{
//				XMLEncoder encoder = new XMLEncoder(new FileOutputStream(file()));
//				encoder.writeObject(cookies);
//				encoder.flush();
//				encoder.close();
//			} catch (FileNotFoundException e)
//			{
//				e.printStackTrace();
//			}
//		}
//	}
//	
//	/**
//	 * reads the cookies from file()
//	 */
//	@SuppressWarnings("unchecked")
//	private void restore()
//	{
//		File f = new File(file());
//		if (f.exists() && save)
//		{
//			try
//			{
//				XMLDecoder decoder = new XMLDecoder(new FileInputStream(f));
//				cookies = (Map<String, Map<String, Map<String, String>>>) decoder.readObject();
//			} catch (Exception e)
//			{
//				e.printStackTrace();
//			}
//		}
//		
//		if (cookies == null)
//			cookies = new HashMap<String, Map<String, Map<String, String>>>();
//	}
	
	private void storeCookies(URLConnection conn) throws IOException
	{
		
		// let's determine the domain from where these cookies are being sent
		String domain = getDomainFromHost(conn.getURL().getHost());
		
		Map<String, Map<String, String>> domainStore; // this is where we will
														// store cookies for
														// this domain
		
		// now let's check the store to see if we have an entry for this domain
		if (cookies.containsKey(domain))
		{
			// we do, so lets retrieve it from the store
			domainStore = cookies.get(domain);
		} else
		{
			// we don't, so let's create it and put it in the store
			domainStore = new HashMap<String, Map<String, String>>();
			cookies.put(domain, domainStore);
		}
		
		// OK, now we are ready to get the cookies out of the URLConnection
		
		String headerName = null;
		// loop through each header
		for (int i = 1; (headerName = conn.getHeaderFieldKey(i)) != null; i++)
		{
			// if this header is a cookie...
			if (headerName.equalsIgnoreCase(Cookie.SET_COOKIE))
			{
				// pull the cookie form the header
				Map<String, String> cookie = new HashMap<String, String>();
				// get ready to parse the cookie
				StringTokenizer st = new StringTokenizer(conn.getHeaderField(i),
						Cookie.COOKIE_VALUE_DELIMITER);
				
				// the specification dictates that the first name/value pair
				// in the string is the cookie name and value, so let's handle
				// them as a special case:
				
				if (st.hasMoreTokens())
				{
					// get the next key/value pair
					String token = st.nextToken();
					// get the index of the separator
					int seperatorIdx = token.indexOf(Cookie.NAME_VALUE_SEPARATOR);
					// retrieve the key and value from the token
					String name = token.substring(0, seperatorIdx);
					String value = token.substring(seperatorIdx + 1, token.length());
					
					// store the in the 'cookie' dictionary
					cookie.put(name, value);
					// store the cookie in the domainStore
					domainStore.put(name, cookie);
				}
				// same as in above 'if' statement
				while (st.hasMoreTokens())
				{
					String token = st.nextToken();
					if (token.length() > 0)
					{
						if (token.indexOf(Cookie.NAME_VALUE_SEPARATOR) >= 0)
						{
							cookie.put(token.substring(0, token.indexOf(Cookie.NAME_VALUE_SEPARATOR))
									.toLowerCase(), token.substring(
									token.indexOf(Cookie.NAME_VALUE_SEPARATOR) + 1, token.length()));
						}
					}
				}
			}
		}
		
//		save();
	}
	
	private void setCookies(URLConnection conn) throws IOException
	{
		// let's determine the domain and path to retrieve the appropriate
		// cookies
		URL url = conn.getURL();
		String domain = getDomainFromHost(url.getHost());
		String path = url.getPath();
		
		Map<String, Map<String, String>> domainStore = cookies.get(domain);
		if (domainStore == null)
			return;
		StringBuffer cookieStringBuffer = new StringBuffer();
		
		Iterator<String> cookieNames = domainStore.keySet().iterator();
		while (cookieNames.hasNext())
		{
			String cookieName = (String) cookieNames.next();
			Map<String, String> cookie = domainStore.get(cookieName);
			// check cookie to ensure path matches and cookie is not expired
			// if all is cool, add cookie to header string
			if (arePathsEqual((String) cookie.get(Cookie.PATH), path)
					&& !isExpired((String) cookie.get(Cookie.EXPIRES)))
			{
				cookieStringBuffer.append(cookieName);
				cookieStringBuffer.append("=");
				cookieStringBuffer.append((String) cookie.get(cookieName));
				if (cookieNames.hasNext())
					cookieStringBuffer.append(Cookie.SET_COOKIE_SEPARATOR);
			}
		}
		try
		{
			conn.setRequestProperty(Cookie.COOKIE, cookieStringBuffer.toString());
		} catch (java.lang.IllegalStateException ise)
		{
			IOException ioe = new IOException(
					"Illegal State! Cookies cannot be set on a URLConnection that is already connected. Only call setCookies(java.net.URLConnection) AFTER calling java.net.URLConnection.connect().");
			throw ioe;
		}
	}
	
	private String getDomainFromHost(String host)
	{
		if (host.indexOf(DOT) != host.lastIndexOf(DOT))
		{
			return host.substring(host.indexOf(DOT) + 1);
		} else
		{
			return host;
		}
	}
	
	/**
	 * parses the parameter according to 'dateFormat'
	 * @param cookieExpires A date string 
	 * @return if cookieExpires is before today
	 */
	private boolean isExpired(String cookieExpires)
	{
		//no expire data
		if (cookieExpires == null)
			return false;
		// get current date
		Date now = new Date();
		try
		{
			Date cookieExpireDate = Cookie.dateFormat.parse(cookieExpires);
			return cookieExpireDate.before(now);
		} catch (java.text.ParseException pe)
		{
			pe.printStackTrace();
			return true;
		}
	}
	
	private boolean arePathsEqual(String cookiePath, String targetPath)
	{
		if (cookiePath == null)
		{
			return true;
		} else if (cookiePath.equals("/"))
		{
			return true;
		} else if (targetPath.regionMatches(0, cookiePath, 0, cookiePath.length()))
		{
			return true;
		} else
		{
			return false;
		}
		
	}
	
	/**
	 * Returns a string representation of stored cookies organized by domain.
	 */
	@Override
	public String toString()
	{
		return cookies.toString();
	}
}
