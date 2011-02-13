package ca.ubc.magic.icd.web.oauth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Provides an abstract interface to OAuth 1.0 protocols as per RFC5489.
 * @author skidson
 *
 */
public class OAuthClient {
	public static enum Encoding { PLAINTEXT, HMAC_SHA1, RSA_SHA1 };
	public static final String OAUTH_CONSUMER_KEY = "oauth_consumer_key";
    public static final String OAUTH_TOKEN = "oauth_token";
    public static final String OAUTH_TOKEN_SECRET = "oauth_token_secret";
    public static final String OAUTH_SIGNATURE_METHOD = "oauth_signature_method";
    public static final String OAUTH_SIGNATURE = "oauth_signature";
    public static final String OAUTH_TIMESTAMP = "oauth_timestamp";
    public static final String OAUTH_NONCE = "oauth_nonce";
    public static final String OAUTH_VERSION = "oauth_version";
    public static final String OAUTH_CALLBACK = "oauth_callback";
    public static final String OAUTH_CALLBACK_CONFIRMED = "oauth_callback_confirmed";
    public static final String OAUTH_VERIFIER = "oauth_verifier";
    
    public static final String VERSION_1_0 = "1.0";
    
	protected String baseURL;
	protected String requestTokenURL;
	protected String userAuthorizationURL;
	protected String accessTokenURL;
	protected String consumerKey;
	protected String consumerSecret;
	protected String tokenSecret = "";
	protected String requestToken;
	protected String nonce;
	protected String timestamp;
	protected Encoding encoding = Encoding.PLAINTEXT;
	
	/**
	 * Provides an abstract interface to OAuth 1.0 protocols.
	 * @param baseURL the base URL for the web service (e.g. http://www.example.com/).
	 * @param requestTokenURL the URL extension to obtain a request token (e.g. http://www.example.com/oauth/request_token/).
	 * @param userAuthorizationURL the URL extension to authorize a user (e.g. http://www.example.com/oauth/authorize/).
	 * @param accessTokenURL the URL extension to obtain an access token (e.g. http://www.example.com/oauth/authorize/).
	 * @param consumerKey the pre-determiend consumer key for the service.
	 * @param consumerSecret pre-determiend consumer secret for the service.
	 */
	public OAuthClient(String baseURL, 
			String requestTokenURL, 
			String userAuthorizationURL,
			String accessTokenURL,
			String consumerKey,
			String consumerSecret) {
		this.baseURL = baseURL;
		this.requestTokenURL = requestTokenURL;
		this.userAuthorizationURL = userAuthorizationURL;
		this.accessTokenURL = accessTokenURL;
		this.consumerKey = consumerKey;
		this.consumerSecret = consumerSecret;
		
		if(!baseURL.endsWith("/"))
			this.baseURL.concat("/");
		
		next();
	}
	
	/**
	 * Fetches an OAuth request token from this client's service.
	 * @return a string containing an OAuth request token
	 * @throws IOException
	 */
	public String getRequestToken() throws IOException {
		next();
		if (this.requestToken == null) {
			URLConnection connection = new URL(baseURL + requestTokenURL).openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setAllowUserInteraction(false);
			System.out.println(parseAuthorizationParameters()); // debug
//			connection.addRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			connection.addRequestProperty("Authorization", "OAuth " + parseAuthorizationParameters());
			connection.connect();
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			while((this.requestToken += reader.readLine()) != null);
		}
		return this.requestToken;
	}
	
	/**
	 * Returns a string containing OAuth authorization parameters intended for use in an http request's Authorization header.
	 * Returned parameters are formatted as name/value pairs seperated with "=" and delimited by "," - following OAuth1.0
	 * specifications outlined in RFC5849.
	 * @return a string containing OAuth authorization parameter name/value pairs seperated with "=" and delimited by ",".
	 */
	private String parseAuthorizationParameters() {
		try {
			String[][] parameters = { 
					{OAUTH_CONSUMER_KEY, encode(consumerKey, "UTF-8")},
					{OAUTH_NONCE, nonce},
					{OAUTH_SIGNATURE_METHOD, encoding.toString()},
					{OAUTH_SIGNATURE, getSignature()},
					{OAUTH_TIMESTAMP, timestamp},
					{OAUTH_VERSION, VERSION_1_0 }};
			return normalize(parameters, ",", true);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace(); // debug
			return null;
		}
	}
	
	/**
	 * Returns a string normalized to RFC5489 specifications.
	 * @param parameters a 2d array of name/value pairs.
	 * @param delim the desired deliminating character between parameters.
	 * @param quoted whether the value portion of the parameter should be in quotations.
	 * @return a string of name/value pairs.
	 */
	private String normalize(String[][] parameters, String delim, boolean quoted){
		// TODO sort parameters
		String quotes = "";
		if (quoted)
			quotes = "\"";
		
		StringBuilder builder = new StringBuilder();
		for (String[] parameter : parameters)
			try {
				builder.append(encode(parameter[0], "UTF-8") + "=" + quotes + encode(parameter[1], "UTF-8") + quotes + delim);
			} catch (UnsupportedEncodingException e) { e.printStackTrace(); }
		
		if (delim.equals(""))
			return builder.toString();
		
		// Remove last instance of delim
		return builder.toString().substring(0, builder.lastIndexOf(delim));
	}
	
	/**
	 * Returns a signature in the format determined by this client's encoding value.
	 * @return a string for this client's signature.
	 */
	private String getSignature() {
		// TODO fix this
		String signature ="";
		switch(encoding) {
		case PLAINTEXT:
			signature = consumerSecret;
			break;
		case HMAC_SHA1:
			break;
		case RSA_SHA1:
			break;
		}
		return signature;
	}
	
	/**
	 * Encodes the string with this client's encoding type.
	 * @param data string to be encoded.
	 * @return an encoded string.
	 * @throws UnsupportedEncodingException
	 */
	private String encode(String data) throws UnsupportedEncodingException {
		switch(encoding) {
		case PLAINTEXT:
			data = URLEncoder.encode(data, "UTF-8").replace("*", "%2A").replace("+", "%20").replace("%7E", "~");
			break;
		case HMAC_SHA1:
			break;
		case RSA_SHA1:
			break;
		}
		return data;
	}
	
	/**
	 * Encodes the string with the specified encoding type.
	 * @param data the string to be encoded.
	 * @param format the desired encoding format.
	 * @return an encoded string.
	 * @throws UnsupportedEncodingException
	 */
	private String encode(String data, String format) throws UnsupportedEncodingException {
		data = URLEncoder.encode(data, format).replace("*", "%2A").replace("+", "%20").replace("%7E", "~");
		return data;
	}
	
	/**
	 * Updates this client's nonce and timestamp for next request.
	 */
	private void next() {
		nonce = nextNonce();
		timestamp = Long.toString(System.currentTimeMillis());
	}
	
	/**
	 * Generates a new nonce.
	 * @return a nonce value as a string.
	 */
	private String nextNonce() {
		// TODO fix this
		SecureRandom random = null;
		byte[] bytes = new byte[1024];
		try {
			random = SecureRandom.getInstance("SHA1PRNG");
			random.nextBytes(bytes);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return("abcdgfda343sd");
	}
	
	/**
	 * Sets the value of this client's consumer key.
	 * @param consumerKey the consumer key.
	 */
	public void setConsumerKey(String consumerKey) {
		this.consumerKey = consumerKey;
	}
	
	/**
	 * Sets the vvalue of this client's consumer secret, corresponding to the consumer key.
	 * @param consumerSecret the consumer secret.
	 */
	public void setConsumerSecret(String consumerSecret) {
		this.consumerSecret	= consumerSecret;
	}
	
	/**
	 * Sets the encoding type of this OAuth client (PLAINTEXT, HMAC_SHA1, RSA_SHA1).
	 * @param encoding the new encoding type.
	 */
	public void setEncoding(Encoding encoding) {
		this.encoding = encoding;
	}
	
	/**
	 * Sets the encoding type of this OAuth client (PLAINTEXT, HMAC_SHA1, RSA_SHA1).
	 * @param encoding the new encoding type.
	 */
	public void setEncoding(String encoding) {
		this.encoding = Encoding.valueOf(encoding);
	}
	
	public String toString() {
		// TODO (for testing!)
		return "";
	}
}
