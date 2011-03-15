package ca.ubc.magic.icd.android.services;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import oauth.signpost.OAuth;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.OAuthProvider;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthProvider;
import oauth.signpost.exception.OAuthException;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.preference.PreferenceManager;
import ca.ubc.magic.icd.android.User;
import ca.ubc.magic.icd.web.json.JsonItem;
import ca.ubc.magic.icd.web.json.JsonParser;

public class AndroidCoffeeShopService {
	public static final String ID = "id";
	public static final String QR_IMAGE_URL = "qr_image_url";
	public static final String PLACES_ID = "places_id";
	public static final String DESCRIPTION = "description";
	public static final String NAME = "name";
	public static final String BITS_TYPES_ID = "bits_types_id";
	public static final String OAUTH_VERIFIER = "oauth_verifier";
	public static final String CALLBACK_URI = "x-oauthflow://callback";
	
	private static final String magicURLPattern = "http://kimberly.magic.ubc.ca:8080/1/";
	private static final String CONSUMER_KEY = "766bec602a9fe2795b43501ea4f9a9c9";
	private static final String CONSUMER_SECRET = "sad234fdsf243f4ff3f343kj43hj43g4hgf423f";
	private static final OAuthConsumer consumer = new CommonsHttpOAuthConsumer(
			"766bec602a9fe2795b43501ea4f9a9c9", 
			"sad234fdsf243f4ff3f343kj43hj43g4hgf423f");
	private static final OAuthProvider provider = new CommonsHttpOAuthProvider(
			magicURLPattern + "request_token",
			magicURLPattern + "access_token", 
			magicURLPattern + "authorize");
	private static boolean authorized = false;
	
	public AndroidCoffeeShopService() {}
	
	public static JsonItem showBit(Context context, int id) {
		authorize(context);
		String request = "bits/show?id=" + id;
		return (new JsonParser(compileInputStream(request))).parse().get(0);
	}
	
	public List<User> showFriends() {
		String request = "friends/show";
		Iterator<JsonItem> iterator = (new JsonParser(compileInputStream(request))).parse().iterator();
		List<User> list = new ArrayList<User>();
		while (iterator.hasNext()) {
			JsonItem friend = iterator.next().getAsJsonItem("user");
			
			String name, username, description, photo;
			Integer experience, points, id;
			try {
				name = friend.getAsString("name");
				username = friend.getAsString("username");
				description = friend.getAsString("description");
				photo = friend.getAsString("photo");
				id = friend.getAsInteger("id");
				try {
					experience = friend.getAsInteger("experience");
					points = friend.getAsInteger("points");
				} catch (NumberFormatException e) {
					experience = 0;
					points = 0;
				}
			} catch (NullPointerException e) {
				continue;
			}
			User user = new User(name, username, description, photo, id,
					experience, points);
			list.add(user);
		}
		return list;
	}
	
	public static User showUser(Context context) {
		authorize(context);
		String request = "users/show";
		JsonItem userInfo = (new JsonParser(compileInputStream(request))).parse().get(0);
		return new User(userInfo.getAsString("name"), 
				userInfo.getAsString("username"), 
				userInfo.getAsString("description"),
				userInfo.getAsString("photo"),
				userInfo.getAsInteger("id"),
				userInfo.getAsInteger("experience"),
				userInfo.getAsInteger("points"));
	}
	
	public JsonItem checkin(Context context, int id) {
		authorize(context);
		String request = "checkins/bit?id=" + id;
		return (new JsonParser(compileInputStream(request))).parse().get(0);
	}
	
	private static InputStream compileInputStream(String path) {
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet request = new HttpGet(magicURLPattern + path);
		InputStream data = null;
		try {
			consumer.sign(request);
			System.out.println("\n\n\n" + request.toString() + "\n\n\n"); // debug
			data = httpClient.execute(request).getEntity().getContent();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	
	private static void authorize(Context context) {
		if (authorized)
			return;
		String url = "";
		try {
			url = provider.retrieveRequestToken(consumer, CALLBACK_URI);
		} catch (OAuthException e) {
			e.printStackTrace();
		}
		Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url))
			.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | 
					Intent.FLAG_ACTIVITY_NO_HISTORY | 
					Intent.FLAG_FROM_BACKGROUND);
		context.startActivity(intent);
	}
	
	public static void verify(Context context, Uri uri) {
		if (authorized)
			return;
		if (uri != null && uri.getScheme().equals(CALLBACK_URI)) {
			final String oauth_verifier = uri.getQueryParameter(OAuth.OAUTH_VERIFIER);
			
			try {
				provider.retrieveAccessToken(consumer, oauth_verifier);
				SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
				final Editor editor = preferences.edit();
				editor.putString(OAuth.OAUTH_TOKEN, consumer.getToken());
				editor.putString(OAuth.OAUTH_TOKEN_SECRET, consumer.getTokenSecret());
				editor.commit();
				
				String token = preferences.getString(OAuth.OAUTH_TOKEN, "");
				String secret = preferences.getString(OAuth.OAUTH_TOKEN_SECRET, "");
				consumer.setTokenWithSecret(token, secret);
			} catch (OAuthException e) {
				e.printStackTrace();
			}
			authorized = true;
		}
	}
	
	public static boolean isAuthorized() {
		return authorized;
	}
	
	private OAuthConsumer getConsumer(SharedPreferences preferences) {
		String token = preferences.getString(OAuth.OAUTH_TOKEN, "");
		String secret = preferences.getString(OAuth.OAUTH_TOKEN_SECRET, "");
		OAuthConsumer consumer = new CommonsHttpOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
		consumer.setTokenWithSecret(token, secret);
		return consumer;
	}
}
