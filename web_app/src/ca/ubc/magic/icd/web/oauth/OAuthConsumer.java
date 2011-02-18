package ca.ubc.magic.icd.web.oauth;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedHashMap;
import java.util.Map;

import ca.ubc.magic.icd.web.oauth.OAuth.Encoding;

public class OAuthConsumer {
	protected String baseURL;
	protected String requestTokenURL;
	protected String userAuthorizationURL;
	protected String accessTokenURL;
	protected String consumerKey;
	protected String consumerSecret;
	protected String token = "";
	protected String tokenSecret = "";
	protected String httpMethod = "POST";
	
	public OAuthConsumer(String baseURL, 
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
	}
	
	public void connect() {

	}
	
	private URLConnection initConnection(URL url) throws IOException {
		URLConnection connection = url.openConnection();
		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setAllowUserInteraction(false);
		((HttpURLConnection)connection).setRequestMethod(httpMethod);
//		connection.addRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		return connection;
	}

	public String getBaseURL() {
		return baseURL;
	}

	public void setBaseURL(String baseURL) {
		this.baseURL = baseURL;
	}

	public String getRequestTokenURL() {
		return requestTokenURL;
	}

	public void setRequestTokenURL(String requestTokenURL) {
		this.requestTokenURL = requestTokenURL;
	}

	public String getUserAuthorizationURL() {
		return userAuthorizationURL;
	}

	public void setUserAuthorizationURL(String userAuthorizationURL) {
		this.userAuthorizationURL = userAuthorizationURL;
	}

	public String getAccessTokenURL() {
		return accessTokenURL;
	}

	public void setAccessTokenURL(String accessTokenURL) {
		this.accessTokenURL = accessTokenURL;
	}

	public String getConsumerKey() {
		return consumerKey;
	}

	public void setConsumerKey(String consumerKey) {
		this.consumerKey = consumerKey;
	}

	public String getConsumerSecret() {
		return consumerSecret;
	}

	public void setConsumerSecret(String consumerSecret) {
		this.consumerSecret = consumerSecret;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getTokenSecret() {
		return tokenSecret;
	}

	public void setTokenSecret(String tokenSecret) {
		this.tokenSecret = tokenSecret;
	}

}
