package ca.ubc.magic.icd.web.oauth;

import java.io.IOException;

import ca.ubc.magic.icd.web.json.JsonItem;

/**
 * Provides various convenience methods for working with the MAGIC Broker 2.0's interface
 * for the Interactive Community Displays (ICD) project.
 * @author skidson
 *
 */
public class CoffeeShopClient extends OAuthConsumer {
	public static final int BIT = 1;
	public static final int PLACE = 2;
	public static final int TABLE = 3;
	public static final int DRINK = 4;
	public static final int FOOD = 5;
	public static final int DISPLAY = 6;
	public static final int CONTENT = 7;
	public static final int PERSON = 8;
	
	private static final String HTTP_METHOD = "POST";
	
	public CoffeeShopClient() {
		super("http://api.platybox.com/1/", 
				"request_token",
				"authorize",
				"access_token",
				"766bec602a9fe2795b43501ea4f9a9c9",
				"sad234fdsf243f4ff3f343kj43hj43g4hgf423f");
	}
	
	public CoffeeShopClient(String baseURL, 
			String requestTokenURL, 
			String userAuthorizationURL,
			String accessTokenURL,
			String consumerKey,
			String consumerSecret) {
		super(baseURL, requestTokenURL, userAuthorizationURL, accessTokenURL, consumerKey, consumerSecret);
	}
	
	public void login() {
		// TODO
		OAuthRequest request = new OAuthRequest(baseURL + requestTokenURL,
				consumerKey, consumerSecret);
		OAuth.sign(request);
		try {
			request.send();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public JsonItem getFriend(String id) {
		// TODO
		return null;
	}
	
	public void addFriend(String id) {
		// TODO
	}
	
	public void removeFriend(String id) {
		// TODO
	}
	
	public JsonItem getBit(String id) {
		// TODO
		return null;
	}
	
	public void setBit(String id, String value) {
		// TODO
	}
	
	public void checkin(String id) {
		
	}
	
}
