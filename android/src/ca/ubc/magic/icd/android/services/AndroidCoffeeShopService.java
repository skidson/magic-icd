package ca.ubc.magic.icd.android.services;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import oauth.signpost.OAuth;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.OAuthProvider;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthProvider;
import oauth.signpost.exception.OAuthException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import ca.ubc.magic.icd.android.User;
import ca.ubc.magic.icd.web.json.JsonItem;
import ca.ubc.magic.icd.web.json.JsonParser;

public class AndroidCoffeeShopService {
	public static final int BIT = 1;
	public static final int PLACE = 2;
	public static final int TABLE = 3;
	public static final int DRINK = 4;
	public static final int FOOD = 5;
	public static final int DISPLAY = 6;
	public static final int CONTENT = 7;
	public static final int PERSON = 8;
	
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
	
	private static OAuthProvider provider;
	private static OAuthConsumer consumer;
	
	private static AndroidCoffeeShopService instance;
	
	@SuppressWarnings("static-access")
	protected AndroidCoffeeShopService(Context context) {
		this.instance = this;
		this.provider = new CommonsHttpOAuthProvider(
				magicURLPattern + "request_token",
				magicURLPattern + "access_token", 
				magicURLPattern + "authorize");
		this.consumer = new CommonsHttpOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
		authorize(context);
	}
	
	/**
	 * Returns the singleton instance of the CoffeeShop Service. If the service has not been constructed yet 
	 * (by a previous call to this method), a Web Browser intent is launched to prompt the user for authorization.
	 * @param context the context to register this instance with. May be null for subsequent calls.
	 * @return the singleton instance of this service.
	 */
	public static AndroidCoffeeShopService getInstance(Context context) {
		if (instance == null)
			return new AndroidCoffeeShopService(context);
		else
			return instance;
	}
	
	public JsonItem showBit(int id) {
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
	
	public User showUser() {
		String request = "users/show?";
		JsonItem userInfo = (new JsonParser(compileInputStream(request))).parse().get(0).getAsJsonItem("user");
		return new User(userInfo.getAsString("name"), 
				userInfo.getAsString("username"), 
				userInfo.getAsString("description"),
				userInfo.getAsString("photo"),
				userInfo.getAsInteger("id"),
				userInfo.getAsInteger("experience"),
				userInfo.getAsInteger("points"));
	}
	
	public JsonItem checkin(int id) {
		String request = "checkins/bit?id=" + id;
		return (new JsonParser(compileInputStream(request))).parse().get(0);
	}
	
	private InputStream compileInputStream(String path) {
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet request = new HttpGet(magicURLPattern + path);
		HttpResponse response = null;
		try {
			consumer.sign(request);
			response = httpClient.execute(request);
			return response.getEntity().getContent();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private void authorize(Context context) {
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
	
	public void verify(Context context, Uri uri) {
		final String oauth_verifier = uri.getQueryParameter(OAuth.OAUTH_VERIFIER);
		try {
			provider.retrieveAccessToken(consumer, oauth_verifier);
		} catch (OAuthException e) {
			e.printStackTrace();
		}
		context.startActivity(new Intent(context, context.getClass()));
	}
	
	public static Drawable getImageFromURL(String url) throws MalformedURLException, IOException {
		InputStream input = (InputStream) new URL(url).getContent();
		return Drawable.createFromStream(input, "src name");
	}
	
}
