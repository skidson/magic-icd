package ca.ubc.magic.icd.web.oauth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Map;

public class OAuthClient {
	public static final String OAUTH_CONSUMER_KEY = "oauth_consumer_key";
	public static final String OAUTH_TIMESTAMP = "oauth_timestamp";
	public static final String OAUTH_NONCE = "oauth_nonce";
	public static final String OAUTH_VERSION= "oauth_version=1.0";
	public static final String OAUTH_SIGNATURE_METHOD = "oauth_signature_method=HMAC-SHA1";
	
	protected String baseURL;
	protected String requestTokenURL;
	protected String userAuthorizationURL;
	protected String accessTokenURL;
	
	protected String consumerKey;
	protected String consumerSecret;
	protected String requestToken;
	
	protected Map<String, String> parameters;
	
	public OAuthClient(String baseURL, 
			String requestTokenURL, 
			String userAuthorizationURL,
			String accessTokenURL,
			String consumerKey,
			String consumerSecret) {
		this.requestTokenURL = requestTokenURL;
		this.userAuthorizationURL = userAuthorizationURL;
		this.accessTokenURL = accessTokenURL;
		this.consumerKey = consumerKey;
		this.consumerSecret = consumerSecret;
	}
	
	public String getRequestToken() throws IOException {
		if (this.requestToken == null) {
			URL url = new URL(baseURL + "/" + requestTokenURL);
			URLConnection connection = url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestProperty("Authorization", getCredentials());
			OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
			writer.write(getCredentials());
			writer.flush();
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			while((this.requestToken += reader.readLine()) != null);
		}
		return this.requestToken;
	}
	
	private String getCredentials() {
		return(OAUTH_CONSUMER_KEY + "=" + consumerKey
				+ "&" + OAUTH_NONCE + "=" + generateNonce()
				+ "&" + OAUTH_SIGNATURE_METHOD
				+ "&" + OAUTH_TIMESTAMP + "=" + System.currentTimeMillis()
				+ "&" + OAUTH_VERSION);
	}
	
	private byte[] generateNonce() {
		SecureRandom random = null;
		byte[] bytes = new byte[20];
		try {
			random = SecureRandom.getInstance("SHA1PRNG");
			random.nextBytes(bytes);
		} catch (NoSuchAlgorithmException e) {}
		return(bytes);
	}
	
	public void setConsumerKey(String consumerKey) {
		this.consumerKey = consumerKey;
	}
	
	public void setConsumerSecret(String consumerSecret) {
		this.consumerSecret	= consumerSecret;
	}
	
	public String toString() {
		// TODO (for testing!)
		return "";
	}
}
