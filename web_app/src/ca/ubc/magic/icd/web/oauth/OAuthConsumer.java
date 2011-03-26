package ca.ubc.magic.icd.web.oauth;

/**
 * @deprecated
 * @author Stephen Kidson
 *
 */
public class OAuthConsumer {
	protected String baseURL;
	protected String requestTokenURL;
	protected String userAuthorizationURL;
	protected String accessTokenURL;
	protected String callbackURL;
	protected String consumerKey;
	protected String consumerSecret;
	protected String token = "";
	protected String tokenSecret = "";
	protected String httpMethod = "POST";
	
	/**
	 * @deprecated
	 * OAuth1.0 consumer client. Implementations should extend this class to provide
	 * customized access to their application. Use this client's convenience methods to build
	 * an OAuthRequest object, sign it with OAuth.sign(OAuthRequest request), then send. This
	 * behaviour should be abstracted behind setters and getters offered by subclasses.
	 * @param baseURL the base URL for the service provider, including the scheme (e.g. "http://api.example.com/").
	 * @param requestTokenURL the URL path to be added to the base URL to obtain an unauthorized request token (e.g. "request_token").
	 * @param userAuthorizationURL the URL path to be added to the base URL to authorize an obtained request token (e.g. "authorize").
	 * @param accessTokenURL the URL path to be added to the base URL to request an access token (e.g. "access_token").
	 * @param callbackURL the full URL to have tokens from service provider "delivered".
	 * @param consumerKey the pre-determined consumer key for this application.
	 * @param consumerSecret the pre-determined consumer secret for this application.
	 */
	public OAuthConsumer(String baseURL, 
			String requestTokenURL, 
			String userAuthorizationURL,
			String accessTokenURL,
			String callbackURL,
			String consumerKey,
			String consumerSecret) {
		this.baseURL = baseURL;
		this.requestTokenURL = requestTokenURL;
		this.userAuthorizationURL = userAuthorizationURL;
		this.accessTokenURL = accessTokenURL;
		this.callbackURL = callbackURL;
		this.consumerKey = consumerKey;
		this.consumerSecret = consumerSecret;
		
		if(!baseURL.endsWith("/"))
			this.baseURL.concat("/");
	}
	
	/**
	 * Generates an OAuth token request.
	 * @return an OAuthRequest for requesting a request token.
	 */
	protected OAuthRequest getRequestToken() {
		return new OAuthRequest(baseURL + requestTokenURL,
				consumerKey, consumerSecret);
	}
	
	protected OAuthRequest getAccessToken() {
		return new OAuthRequest(baseURL + accessTokenURL, consumerKey,
				consumerSecret, tokenSecret);
	}

	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}

	public void setTokenSecret(String tokenSecret) {
		this.tokenSecret = tokenSecret;
	}

}
