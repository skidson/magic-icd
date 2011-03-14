package ca.ubc.magic.icd.android.services;

import java.io.IOException;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.OAuthProvider;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthProvider;
import oauth.signpost.exception.OAuthException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class AndroidCoffeeShopService {
	public static final String OAUTH_VERIFIER = "oauth_verifier";
	public static final String CALLBACK_URI = "magic://callback";
	private static final String magicURLPattern = "http://kimberly.magic.ubc.ca:8080/1/";
	private static final OAuthConsumer consumer = new CommonsHttpOAuthConsumer(
			"766bec602a9fe2795b43501ea4f9a9c9", 
			"sad234fdsf243f4ff3f343kj43hj43g4hgf423f");
	private static final OAuthProvider provider = new CommonsHttpOAuthProvider(
			magicURLPattern + "request_token",
			magicURLPattern + "access_token", 
			magicURLPattern + "authorize");
	private static boolean authorized = false;
	
	public AndroidCoffeeShopService() {}
	
	public static String showBit(int id) {
		String bit = "bit!";
		try {
			bit = connect("bits/show?id=" + id);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (OAuthException e) {
			e.printStackTrace();
		}
		return bit;
	}
	
	/*public JsonItem showFriends() {
		
	}
	
	public JsonItem showUser() {
		
	}
	
	public JsonItem checkin() {
		
	}*/
	
	private static String connect(String path) throws IOException, OAuthException {
		HttpPost request = new HttpPost(magicURLPattern + path);
		consumer.sign(request);
		HttpClient httpClient = new DefaultHttpClient();
		HttpResponse response = httpClient.execute(request);
		return response.toString();
	}
	
	public static void authorize(Context context) throws OAuthException  {
		Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(provider.retrieveRequestToken(consumer, CALLBACK_URI)));
		context.startActivity(intent);
	}
	
	public static void verify(String verifier) throws OAuthException {
		provider.retrieveAccessToken(consumer, verifier);
		authorized = true;
	}
	
	public static boolean isAuthorized() {
		return authorized;
	}
	
}
