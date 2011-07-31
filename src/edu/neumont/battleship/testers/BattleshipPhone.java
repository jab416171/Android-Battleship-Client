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
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;

import edu.neumont.battleship.BattleshipActivity;

import android.util.Log;

/**
 * The BattleshipPhone class is a helper class for communicating with the battleship server.
 * The only method you need to worry about is <code>call</code>.
 * As an example, you could write the following code:
 * String result = BattleshipPhone.call(
 *      "NewGame", "<request><playerID>[[Your Name]]</playerID><robot>Edison</robot></request>");
 * // process the xml result here 
 */
public class BattleshipPhone {
//     private static final String HOST = "http://tics.neumont.edu:8080/BattleshipServer";
    private static final String HOST = "http://joe-bass.com:8800/BattleshipServer";
    private boolean save = true;
    private static final String TAG = BattleshipActivity.TAG;
    
    public static String call( String requestType ) throws IOException {
        return call(requestType, null);
    }
    
    public static String call(String requestType, String requestBody) throws IOException {
        URL url = new URL(HOST+"/GameRequest/"+requestType);
        URLConnection conn = url.openConnection();
        conn.setDoOutput(true);
        instance.setCookies(conn);
        if( requestBody != null && requestBody.length() > 0 ) {
        PrintWriter request = new PrintWriter(conn.getOutputStream());
            request.print(requestBody);
            request.flush();
            request.close();
        }
        
       
        
        BufferedReader in = new BufferedReader(
                                new InputStreamReader(
                                conn.getInputStream()));
        StringWriter inner = new StringWriter();
        PrintWriter result = new PrintWriter(inner);
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            result.println(inputLine);
        }
        in.close();
        instance.storeCookies(conn);
        
        result.flush();
        result.close();
        return inner.toString();
    }

    
    
    private Map<String, Map<String, Map<String, String>>> store = new HashMap<String, Map<String, Map<String, String>>>();

    private static final String SET_COOKIE = "Set-Cookie";
    private static final String COOKIE_VALUE_DELIMITER = ";";
    private static final String PATH = "path";
    private static final String EXPIRES = "expires";
    private static final String DATE_FORMAT = "EEE, dd-MMM-yyyy hh:mm:ss z";
    private static final String SET_COOKIE_SEPARATOR = "; ";
    private static final String COOKIE = "Cookie";

    private static final char NAME_VALUE_SEPARATOR = '=';
    private static final char DOT = '.';

    private DateFormat dateFormat;
    private static BattleshipPhone instance = new BattleshipPhone();
    
    private String file() {
        return "cookies.out";
    }
    
    
    
    private void save() {
        if( save ) {
            try {
                XMLEncoder encoder = new XMLEncoder(new FileOutputStream(file()));
                encoder.writeObject(store);
                encoder.flush();
                encoder.close();
            } catch (FileNotFoundException e) {
                Log.e(TAG,"Exception in BattleshipPhone",e);
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    private void restore() {
        File f = new File(file());
        if( f.exists() && save ) {
            try {
                XMLDecoder decoder = new XMLDecoder(new FileInputStream(f));
                store = (Map<String, Map<String, Map<String, String>>>)decoder.readObject();
            } catch( Exception e ) {
                Log.e(TAG,"Exception in BattleshipPhone",e);
            }
        }
        
        if( store == null )
            store = new HashMap<String, Map<String, Map<String, String>>>();
        
    }

    private BattleshipPhone(boolean save) {
        this.save = save;
        dateFormat = new SimpleDateFormat(DATE_FORMAT);
        restore();
    }
    
    private BattleshipPhone() {
        this(false);
    }
    
    private void storeCookies(URLConnection conn) throws IOException {

        // let's determine the domain from where these cookies are being sent
        String domain = getDomainFromHost(conn.getURL().getHost());

        Map<String, Map<String, String>> domainStore; // this is where we will store cookies for this domain

        // now let's check the store to see if we have an entry for this domain
        if (store.containsKey(domain)) {
            // we do, so lets retrieve it from the store
            domainStore = store.get(domain);
        } else {
            // we don't, so let's create it and put it in the store
            domainStore = new HashMap<String, Map<String, String>>();
            store.put(domain, domainStore);
        }

        // OK, now we are ready to get the cookies out of the URLConnection

        String headerName = null;
        for (int i = 1; (headerName = conn.getHeaderFieldKey(i)) != null; i++) {
            if (headerName.equalsIgnoreCase(SET_COOKIE)) {
                Map<String, String> cookie = new HashMap<String, String>();
                StringTokenizer st = new StringTokenizer(
                        conn.getHeaderField(i), COOKIE_VALUE_DELIMITER);

                // the specification dictates that the first name/value pair
                // in the string is the cookie name and value, so let's handle
                // them as a special case:

                if (st.hasMoreTokens()) {
                    String token = st.nextToken();
                    String name = token.substring(0,
                            token.indexOf(NAME_VALUE_SEPARATOR));
                    String value = token.substring(
                            token.indexOf(NAME_VALUE_SEPARATOR) + 1,
                            token.length());
                    domainStore.put(name, cookie);
                    cookie.put(name, value);
                }

                while (st.hasMoreTokens()) {
                    String token = st.nextToken();
                    if( token.length() > 0 ) {
                        if( token.indexOf(NAME_VALUE_SEPARATOR) >= 0 ) {
                            cookie.put(
                                    token.substring(0,
                                            token.indexOf(NAME_VALUE_SEPARATOR))
                                            .toLowerCase(), token.substring(
                                            token.indexOf(NAME_VALUE_SEPARATOR) + 1,
                                            token.length()));
                        }
                    }
                }
            }
        }
        
        save();
    }

    private void setCookies(URLConnection conn) throws IOException {

        // let's determine the domain and path to retrieve the appropriate
        // cookies
        URL url = conn.getURL();
        String domain = getDomainFromHost(url.getHost());
        String path = url.getPath();

        Map<String, Map<String, String>> domainStore = store.get(domain);
        if (domainStore == null)
            return;
        StringBuffer cookieStringBuffer = new StringBuffer();

        Iterator<String> cookieNames = domainStore.keySet().iterator();
        while (cookieNames.hasNext()) {
            String cookieName = (String) cookieNames.next();
            Map<String, String> cookie = domainStore.get(cookieName);
            // check cookie to ensure path matches and cookie is not expired
            // if all is cool, add cookie to header string
            if (comparePaths((String) cookie.get(PATH), path)
                    && isNotExpired((String) cookie.get(EXPIRES))) {
                cookieStringBuffer.append(cookieName);
                cookieStringBuffer.append("=");
                cookieStringBuffer.append((String) cookie.get(cookieName));
                if (cookieNames.hasNext())
                    cookieStringBuffer.append(SET_COOKIE_SEPARATOR);
            }
        }
        try {
            conn.setRequestProperty(COOKIE, cookieStringBuffer.toString());
        } catch (java.lang.IllegalStateException ise) {
            IOException ioe = new IOException(
                    "Illegal State! Cookies cannot be set on a URLConnection that is already connected. Only call setCookies(java.net.URLConnection) AFTER calling java.net.URLConnection.connect().");
            throw ioe;
        }
    }

    private String getDomainFromHost(String host) {
        if (host.indexOf(DOT) != host.lastIndexOf(DOT)) {
            return host.substring(host.indexOf(DOT) + 1);
        } else {
            return host;
        }
    }

    private boolean isNotExpired(String cookieExpires) {
        if (cookieExpires == null)
            return true;
        Date now = new Date();
        try {
            return (now.compareTo(dateFormat.parse(cookieExpires))) <= 0;
        } catch (java.text.ParseException pe) {
            Log.e(TAG,"Exception in BattleshipPhone",pe);
            return false;
        }
    }

    private boolean comparePaths(String cookiePath, String targetPath) {
        if (cookiePath == null) {
            return true;
        } else if (cookiePath.equals("/")) {
            return true;
        } else if (targetPath.regionMatches(0, cookiePath, 0,
                cookiePath.length())) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * Returns a string representation of stored cookies organized by domain.
     */
    @Override
    public String toString() {
        return store.toString();
    }
}
