package ca.ubc.magic.icd.web.oauth;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * @deprecated
 * Provides an abstract interface to OAuth 1.0 protocols as per RFC5849.
 * @author Stephen Kidson
 *
 */
@Deprecated
public class OAuthClient {
	public static final String PLAINTEXT = "PLAINTEXT";
	public static final String HMAC_SHA1 = "HMAC-SHA1";
	public static final String RSA_SHA1 = "RSA-SHA1";
	public static final String ENCODING = "UTF-8";
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
    
    private static final String HTTP_REQUEST_METHOD = "GET";
    private static final int NONCE_LENGTH = 32;
    
	protected String baseURL;
	protected String requestTokenURL;
	protected String userAuthorizationURL;
	protected String accessTokenURL;
	protected String consumerKey;
	protected String consumerSecret;
	protected String tokenSecret = "";
	protected String token = "";
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
		Map<String, String> parameters = getAuthorizationParameters();
		parameters.put(OAUTH_SIGNATURE, getSignature(baseURL + requestTokenURL));
		String parametersURL = "?" + normalize(sort(parameters), "&", false);
		URLConnection connection = setupConnection(new URL(baseURL + requestTokenURL + parametersURL));
		DataOutputStream writer = new DataOutputStream(connection.getOutputStream());
		writer.write(0);
		writer.flush();
		
		DataInputStream reader = new DataInputStream(connection.getInputStream());
		String response = "";
		while((response += reader.readLine()) != null);
		return response;
	}
	
	private URLConnection setupConnection(URL url) throws IOException {
		URLConnection connection = url.openConnection();
		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setAllowUserInteraction(false);
		((HttpURLConnection)connection).setRequestMethod(HTTP_REQUEST_METHOD);
//		connection.addRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		return connection;
	}
	
	/**
	 * Returns a LinkedHashMap containing OAuth authorization parameters intended for use in an http request's Authorization header.
	 * @param whether to include signature.
	 * @return a LinkedHashMap containing OAuth authorization parameter name/value pairs.
	 */
	private LinkedHashMap<String, String> getAuthorizationParameters() {
		LinkedHashMap<String, String> parameters = new LinkedHashMap<String, String>();
		parameters.put(OAUTH_CONSUMER_KEY, consumerKey);
		parameters.put(OAUTH_NONCE, nonce);
		parameters.put(OAUTH_SIGNATURE_METHOD, encoding);
		parameters.put(OAUTH_TIMESTAMP, timestamp);
		if (!token.equals(""))
			parameters.put(OAUTH_TOKEN, token);
		parameters.put(OAUTH_VERSION, VERSION_1_0);
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
				builder.append(encode(parameter.getKey()) + "=" + quotes + encode(parameter.getValue()) + quotes + delim);
			} catch (UnsupportedEncodingException e) { e.printStackTrace(); }
		}
		if (!delim.equals(""))
			return builder.toString().substring(0, builder.lastIndexOf(delim));
		return builder.toString();
	}
	
	/**
	 * Returns a signature in the format determined by this client's encoding value.
	 * @return a string for this client's signature.
	 */
	private String getSignature(String requestURL) {
		String signature = "";
		Map<String, String> parameters = getAuthorizationParameters();
		try {
			if (encoding.equals(PLAINTEXT)) {
				signature = encode(consumerSecret) + "&" + encode(tokenSecret);
			} else if (encoding.equals(HMAC_SHA1)) {
				// Compile signature base string
				signature = HTTP_REQUEST_METHOD + "&" 
						+ encode(requestURL) + "&"
						+ encode(normalize(parameters, "&", false));
				signature = encryptHMAC_SHA1(signature);
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
	
	private String encryptHMAC_SHA1(String data) {
		String result = "";
		try {
			byte[] key = (encode(consumerSecret) + "&" + encode(tokenSecret)).getBytes();
			Mac mac = Mac.getInstance("HmacSHA1");
			mac.init(new SecretKeySpec(key, mac.getAlgorithm()));
			result = new String(Base64.encodeBase64(mac.doFinal(data.getBytes())));
		} catch (NoSuchAlgorithmException e) { 
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) { 
			e.printStackTrace(); 
		}
		return result;
	}
	
	/**
	 * Encodes the string with the specified encoding type. Wraps URLEncoder.encode()
	 * @param data the string to be encoded.
	 * @param format the desired encoding format.
	 * @return an encoded string.
	 * @throws UnsupportedEncodingException
	 */
	public String encode(String data) throws UnsupportedEncodingException {
		return URLEncoder.encode(data, ENCODING).replace("*", "%2A").replace("+", "%20").replace("%7E", "~");
	}
	
	/**
	 * Updates this client's nonce and timestamp for next request.
	 */
	private void next() {
		nonce = nextNonce();
		timestamp = Long.toString(System.currentTimeMillis()/1000);
	}
	
	/**
	 * Generates a new 32-bit "number used once" value.
	 * @return a nonce value as a string.
	 */
	private String nextNonce() {
		return nextNonce(NONCE_LENGTH);
	}
	
	private String nextNonce(int length) {
		String nonce = "";
		String regex = "abcdefghijklmnopqrstuvwxyz" + "1234567890";
		SecureRandom random = null;
		try {
			random = SecureRandom.getInstance("SHA1PRNG");
			for (int i = 0; i < length; i++)
				nonce += regex.charAt(random.nextInt(regex.length()-1));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return(nonce);
	}
	
	public Map<String, String> sort(Map<String, String> unsorted) {
		Map<String, String> sorted = new LinkedHashMap<String, String>();
		sorted.put(OAUTH_VERSION, unsorted.get(OAUTH_VERSION));
		sorted.put(OAUTH_NONCE, unsorted.get(OAUTH_NONCE));
		sorted.put(OAUTH_TIMESTAMP, unsorted.get(OAUTH_TIMESTAMP));
		sorted.put(OAUTH_CONSUMER_KEY, unsorted.get(OAUTH_CONSUMER_KEY));
		sorted.put(OAUTH_SIGNATURE_METHOD, unsorted.get(OAUTH_SIGNATURE_METHOD));
		sorted.put(OAUTH_SIGNATURE, unsorted.get(OAUTH_SIGNATURE));
		return sorted;
	}
	
	public String debug() throws IOException {
		return ("\nnonce: " + nonce +
				"\ntimestamp: " + timestamp +
				"\nsignature: " + getSignature(baseURL + requestTokenURL));
	}
	
	/**
	 * Sets the value of this client's consumer key.
	 * @param consumerKey the consumer key.
	 */
	public void setConsumerKey(String consumerKey) {
		this.consumerKey = consumerKey;
	}
	
	/**
	 * Sets the value of this client's consumer secret, corresponding to the consumer key.
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
		return normalize(getAuthorizationParameters(), ",\n", true);
	}
	
}
