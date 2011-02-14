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
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Provides an abstract interface to OAuth 1.0 protocols as per RFC5849.
 * @author skidson
 *
 */
public class OAuthClient {
	public static final String PLAINTEXT = "PLAINTEXT";
	public static final String HMAC_SHA1 = "HMAC-SHA1";
	public static final String RSA_SHA1 = "RSA-SHA1";
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
	protected String encoding = HMAC_SHA1;
	
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
			connection.addRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			connection.addRequestProperty("Authorization", "OAuth " + normalize(getAuthorizationParameters(true), ",", true));
			connection.connect();
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			while((this.requestToken += reader.readLine()) != null);
		}
		return this.requestToken;
	}
	
	/**
	 * Returns a LinkedHashMap containing OAuth authorization parameters intended for use in an http request's Authorization header.
	 * @return a string containing OAuth authorization parameter name/value pairs seperated with "=" and delimited by ",".
	 */
	private LinkedHashMap<String, String> getAuthorizationParameters() {
		return getAuthorizationParameters(false);
	}
	
	/**
	 * Returns a LinkedHashMap containing OAuth authorization parameters intended for use in an http request's Authorization header.
	 * @param whether to include signature.
	 * @return a string containing OAuth authorization parameter name/value pairs seperated with "=" and delimited by ",".
	 */
	private LinkedHashMap<String, String> getAuthorizationParameters(boolean signature) {
		LinkedHashMap<String, String> parameters = new LinkedHashMap<String, String>();
		try {
			parameters.put(OAUTH_CONSUMER_KEY, encode(consumerKey, "UTF-8"));
			if (!tokenSecret.equals(""))
				parameters.put(OAUTH_TOKEN, encode(tokenSecret, "UTF-8"));
			parameters.put(OAUTH_NONCE, nonce);
			parameters.put(OAUTH_SIGNATURE_METHOD, encoding);
			parameters.put(OAUTH_TIMESTAMP, timestamp);
			parameters.put(OAUTH_VERSION, VERSION_1_0);
			if (signature)
				parameters.put(OAUTH_SIGNATURE, getSignature(parameters));
		} catch (UnsupportedEncodingException e) { e.printStackTrace(); }
		return parameters;
	}
	
	/**
	 * Returns a string normalized to RFC5849 specifications. Note that a backing LinkedHashMap is used here as
	 * it is important we keep the parameters in order.
	 * @param parameters a 2d array of name/value pairs.
	 * @param delim the desired deliminating character between parameters.
	 * @param quoted whether the value portion of the parameter should be in quotations.
	 * @return a string of name/value pairs.
	 */
	private String normalize(Map<String, String> parameters, String delim, boolean quoted){
		// TODO sort parameters
		String quotes = "";
		if (quoted)
			quotes = "\"";
		
		StringBuilder builder = new StringBuilder();
		Iterator<Map.Entry<String, String>> iterator = parameters.entrySet().iterator();
		while(iterator.hasNext()) {
			Map.Entry<String, String> parameter = iterator.next();
			try {
				builder.append(encode(parameter.getKey(), "UTF-8") + "=" + quotes + encode(parameter.getValue(), "UTF-8") + quotes + delim);
			} catch (UnsupportedEncodingException e) { e.printStackTrace(); }
		}
		return builder.toString().substring(0, builder.lastIndexOf(delim));
	}
	
	/**
	 * Returns a signature in the format determined by this client's encoding value.
	 * @return a string for this client's signature.
	 */
	private String getSignature(Map<String, String> parameters) {
		String signature ="";
		try {
			if (encoding.equals(PLAINTEXT)) {
				signature = encode(consumerSecret, "UTF-8") + "&" + encode(tokenSecret, "UTF-8");
			} else if (encoding.equals(HMAC_SHA1)) {
					signature = "POST&" + encode(baseURL, "UTF-8") + "&" + normalize(parameters, "&", false);
			} else if (encoding.equals(RSA_SHA1)) {
				// TODO unsupported at the moment
				throw new UnsupportedEncodingException();
			}
		} catch (UnsupportedEncodingException e) { 
			e.printStackTrace();
			System.exit(1);
		}
		return signature;
	}
	
	/**
	 * Encodes the string with the specified encoding type. Wraps URLEncoder.encode()
	 * @param data the string to be encoded.
	 * @param format the desired encoding format.
	 * @return an encoded string.
	 * @throws UnsupportedEncodingException
	 */
	private String encode(String data, String format) throws UnsupportedEncodingException {
		return URLEncoder.encode(data, format).replace("*", "%2A").replace("+", "%20").replace("%7E", "~");
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
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
	
	public String toString() {
		return normalize(getAuthorizationParameters(true), ",\n", true);
	}
}
