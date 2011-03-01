package ca.ubc.magic.icd.web.oauth;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import ca.ubc.magic.icd.web.oauth.OAuth.Encoding;

public class OAuthRequest {
	private LinkedHashMap<String, String> parameters;
	private String url;
	private String consumerSecret;
	private String tokenSecret = "";
	private String httpMethod = "POST";
	private Encoding encoding = Encoding.HMAC_SHA1;
	
	public OAuthRequest(String url, String consumerKey, 
			String consumerSecret, String tokenSecret) {
		this.url = url;
		this.consumerSecret = consumerSecret;
		this.tokenSecret = tokenSecret;
		this.parameters = new LinkedHashMap<String, String>();
		parameters.put(OAuth.OAUTH_CONSUMER_KEY, consumerKey);
		parameters.put(OAuth.OAUTH_VERSION, "1.0");
		parameters.put(OAuth.OAUTH_SIGNATURE_METHOD, encoding.toString().replace("_", "-"));
	}
	
	public OAuthRequest(String url, String consumerKey, String consumerSecret) {
		this(url, consumerKey, consumerSecret, "");
	}
	
	public String setup(String callbackURL) throws MalformedURLException, IOException {
		parameters.put(OAuth.OAUTH_CALLBACK, callbackURL);
		this.send();
		DataInputStream reader = new DataInputStream((new URL(callbackURL)).openConnection().getInputStream());
		String response = "";
		while((response += reader.readLine()) != null);
		return response;
	}
	
	public void send() throws IOException {
		System.out.println("Sending...\n" + toString()); // debug
		URLConnection connection = (new URL(url + "?" + OAuth.normalize(parameters)).openConnection());
		connection.setDoOutput(true);
		connection.setDoInput(true);
		DataOutputStream writer = new DataOutputStream(connection.getOutputStream());
		writer.write(0);
		writer.flush();
		DataInputStream reader = new DataInputStream(connection.getInputStream());
		while(reader.available() > 0) {
			System.out.println(reader.read());
		}
		
		/*ByteArrayOutputStream byteOutputStream = null;
        InputStream inputStream = null;
        String stream = null;
        try {
            inputStream = (new URL(url + "?" + OAuth.normalize(parameters))).openStream();
            byteOutputStream = new ByteArrayOutputStream();
            int i;
            while ((i = inputStream.read()) != -1)
                byteOutputStream.write(i);
            stream = new String(byteOutputStream.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (null != byteOutputStream) {
                try {
                    byteOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
 
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println(stream); */
	}
	
	private URLConnection initConnection(String url) throws MalformedURLException, IOException {
		URLConnection connection = (new URL(url)).openConnection();
		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setAllowUserInteraction(false);
		((HttpURLConnection)connection).setRequestMethod(httpMethod);
//		connection.addRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		return connection;
	}
	
	protected void setNonce(String nonce) {
		parameters.put(OAuth.OAUTH_NONCE, nonce);
	}
	
	protected void setTimestamp(String seconds) {
		parameters.put(OAuth.OAUTH_TIMESTAMP, seconds);
	}
	
	protected void setSignature(String signature) {
		parameters.put(OAuth.OAUTH_SIGNATURE, signature);
	}
	
	public void setTokenSecret(String secret) {
		this.tokenSecret = secret;
	}

	public String getUrl() {
		return url;
	}

	public Encoding getEncoding() {
		return encoding;
	}

	public void setEncoding(Encoding encoding) {
		this.encoding = encoding;
	}

	public String getHttpMethod() {
		return httpMethod;
	}

	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}

	public byte[] getEncryptionKey() {
		return (consumerSecret + "&" + tokenSecret).getBytes();
	}
	
	public Iterator<Map.Entry<String, String>> iterator() {
		return parameters.entrySet().iterator();
	}
	
	public String getParameterValue(String key) {
		return parameters.get(key);
	}
	
	public Collection<String> getParameterKeys() {
		return parameters.keySet();
	}
	
	public String toString() {
		return("request url: " + url + "?" + OAuth.normalize(parameters)
				+ "\n\t" + OAuth.normalize(parameters, ",\n\t", true));
	}
	
}
