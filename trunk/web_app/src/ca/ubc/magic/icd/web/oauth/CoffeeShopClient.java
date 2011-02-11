package ca.ubc.magic.icd.web.oauth;

public class CoffeeShopClient extends OAuthClient {
	public static final int PERSON = 1;
	public static final int PLACE = 2;
	public static final int TABLE = 3;
	public static final int DRINK = 4;
	public static final int FOOD = 5;
	public static final int DISPLAY = 6;
	public static final int CONTENT = 7;
	
	public CoffeeShopClient(String baseURL, 
			String requestTokenURL, 
			String userAuthorizationURL,
			String accessTokenURL,
			String consumerKey,
			String consumerSecret) {
		super(baseURL, requestTokenURL, userAuthorizationURL, accessTokenURL, consumerKey, consumerSecret);
	}
}
