/**
 * From: http://www.aviransplace.com/2008/01/08/make-http-post-or-get-request-from-java/
 */

package edu.neumont.battleship.http;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import android.util.Log;
import edu.neumont.battleship.BattleshipActivity;

public class HttpHandler {
	public static final String connectionURL = "http://joe-bass.com:8800/BattleshipServer/GameRequest/";
	public static final String TAG = BattleshipActivity.TAG;
	private static final boolean LOCAL_LOGV = false;
	private static final boolean LOCAL_LOGD = false;
	private static final int TIMEOUT = 5*1000;

	/**
	 * Sends an HTTP GET request to a url
	 * 
	 * @param endpoint
	 *            - The URL of the server. (Example:
	 *            " http://www.yahoo.com/search")
	 * @param requestParameters
	 *            - all the request parameters (Example:
	 *            "param1=val1&param2=val2"). Note: This method will add the
	 *            question mark (?) to the request - DO NOT add it yourself
	 * @return - The response from the end point
	 */
	public static String sendGetRequest(String endpoint,
			String requestParameters) {
		if (LOCAL_LOGD) {
			Log.d(TAG, "in get request, endpoint is " + endpoint + " params: "
				+ requestParameters);
		}
		String result = null;
		if (endpoint != null) {
			if (!endpoint.startsWith("http://"))
				endpoint = "http://" + endpoint;
			// Send a GET request to the servlet
			try {
				// Send data
				String urlStr = endpoint;
				if (requestParameters != null && requestParameters.length() > 0) {
					urlStr += URLEncoder.encode("?" + requestParameters);
				}
				URL url = new URL(urlStr);
				if (LOCAL_LOGD) {
					Log.d(TAG, url.toString());
				}
				URLConnection conn = url.openConnection();
				InputStream is = conn.getInputStream();
				// Get the response
				BufferedReader rd = new BufferedReader(
						new InputStreamReader(is));
				StringBuffer sb = new StringBuffer();
				String line;
				while ((line = rd.readLine()) != null) {
					sb.append(line);
				}
				rd.close();
				result = sb.toString();
			} catch (Exception e) {
				Log.e(TAG, "Exception in HttpHandler: ", e);
			}
		}
		return result;
	}

	/**
	 * Reads data from the data reader and posts it to a server via POST
	 * request.
	 * 
	 * data - The data you want to send endpoint - The server's address
	 * 
	 * output - writes the server's response to output
	 * 
	 * @throws Exception
	 */
//	public static void postData(Reader data, URL endpoint, Writer output)
//			throws Exception {
//		HttpURLConnection urlc = null;
//		try {
//			urlc = (HttpURLConnection) endpoint.openConnection();
//			try {
//				urlc.setRequestMethod("POST");
//			} catch (ProtocolException e) {
//				throw new Exception(
//						"Shouldn't happen: HttpURLConnection doesn't support POST??",
//						e);
//			}
//			urlc.setDoOutput(true);
//			urlc.setDoInput(true);
//			urlc.setUseCaches(false);
//			urlc.setAllowUserInteraction(false);
//			urlc.setRequestProperty("Content-type", "text/xml; charset="
//					+ "UTF-8");
//			OutputStream out = urlc.getOutputStream();
//			try {
//				Writer writer = new OutputStreamWriter(out, "UTF-8");
//				pipe(data, writer);
//				writer.close();
//			} catch (IOException e) {
//				throw new Exception("IOException while posting data", e);
//			} finally {
//				if (out != null)
//					out.close();
//			}
//			InputStream in = urlc.getInputStream();
//			try {
//				Reader reader = new InputStreamReader(in);
//				pipe(reader, output);
//				reader.close();
//			} catch (IOException e) {
//				throw new Exception("IOException while reading response", e);
//			} finally {
//				if (in != null)
//					in.close();
//			}
//		} catch (IOException e) {
//			throw new Exception("Connection error (is server running at "
//					+ endpoint + " ?): " + e);
//		} finally {
//			if (urlc != null)
//				urlc.disconnect();
//		}
//	}

	/**
	 * Pipes everything from the reader to the writer via a buffer
	 */
	private static void pipe(Reader reader, Writer writer) throws IOException {
		char[] buf = new char[1024];
		int read = 0;
		while ((read = reader.read(buf)) >= 0) {
			writer.write(buf, 0, read);
		}
		writer.flush();
	}

//	public static void postData(Reader reader, Writer writer)
//			throws MalformedURLException, Exception {
//		postData(reader, new URL(HttpHandler.connectionURL), writer);
//	}

	public static String sendGetRequest(String requestParameters) {
		return sendGetRequest(HttpHandler.connectionURL, requestParameters);
	}

	public static String postData(String target, String content)
			throws Exception {
		if (LOCAL_LOGV) {
			Log.v(TAG, "About to post");
			Log.v(TAG, "URL: " + target + "content: " + content);
		}
		String response = "";
		URL url = new URL(target);
		URLConnection conn = url.openConnection();
		// 	Map<String, List<String>> m = conn.getHeaderFields();
		// 	for (Entry<String,List<String>> e : m.entrySet()) {
		//	 	if (LOCAL_LOGD) {
		//	 		Log.d(TAG,"Key: "+e.getKey());
		//	 		Log.d(TAG,"Value: "+Arrays.toString(e.getValue().toArray()));
		//	 	}
		//
		// 	}
		// Set connection parameters.
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setUseCaches(false);
		conn.setConnectTimeout(HttpHandler.TIMEOUT);
		// Make server believe we are form data...
		conn.setRequestProperty("Content-Type", "application/xml");
		DataOutputStream out = new DataOutputStream(conn.getOutputStream());
		// Write out the bytes of the content string to the stream.
		out.writeBytes(content);
		out.flush();
		out.close();
		// Read response from the input stream.
		BufferedReader in = new BufferedReader(new InputStreamReader(
				conn.getInputStream()));
		String temp;
		while ((temp = in.readLine()) != null) {
			response += temp + "\n";
		}
		temp = null;
		in.close();
		// System.out.println("Server response:\n'" + response + "'");
		return response;
	}
}
