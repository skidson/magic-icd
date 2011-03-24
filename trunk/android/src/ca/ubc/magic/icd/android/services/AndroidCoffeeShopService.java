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
import ca.ubc.magic.icd.android.model.Bit;
import ca.ubc.magic.icd.android.model.User;
import ca.ubc.magic.icd.web.json.JsonItem;
import ca.ubc.magic.icd.web.json.JsonParser;

/**
 * A static convenience class that provides various methods and constants
 * for implementing interaction with the Magic Broker database's OAuth interface.
 * Implements the GoF Singleton Design Pattern to provide global static access
 * to all Activities. <p><p>One instance of this service should exist for a single
 * OAuth client. Reference to this instance can be obtained by calling 
 * <code>getInstance(Context)</code>. The constructor of this singleton is public to provide the ability to reset
 * an OAuth session.
 * @author Stephen Kidson
 * @author Jeffrey Payan
 */
public class AndroidCoffeeShopService {
	public static final String USERNAME = "username";
	public static final String EXPERIENCE = "experience";
	public static final String POINTS = "points";
	public static final String PHOTO = "photo";
	public static final String ID = "id";
	public static final String QR_IMAGE_URL = "qr_image_url";
	public static final String PLACES_ID = "places_id";
	public static final String DESCRIPTION = "description";
	public static final String NAME = "name";
	public static final String BITS_TYPES_ID = "bits_types_id";
	public static final String OAUTH_VERIFIER = "oauth_verifier";
	public static final String CALLBACK_URI = "x-oauthflow://callback";
	
	private static final String MAGIC_URL_PATTERN = "http://kimberly.magic.ubc.ca/1/";
	private static final String CONSUMER_KEY = "766bec602a9fe2795b43501ea4f9a9c9";
	private static final String CONSUMER_SECRET = "sad234fdsf243f4ff3f343kj43hj43g4hgf423f";
	
	private static OAuthProvider provider;
	private static OAuthConsumer consumer;
	
	private static AndroidCoffeeShopService instance;
	
	/**
	 * A Variation of the Singleton Pattern, this constructor is exposed so we 
	 * may manually force re-authorization. This constructor should at most be
	 * called once for each OAuth session. In most cases, {@link #getInstance(Context)}
	 * should be used instead.
	 * @param context
	 */
	@SuppressWarnings("static-access")
	public AndroidCoffeeShopService(Context context) {
		this.instance = this;
		this.provider = new CommonsHttpOAuthProvider(
				MAGIC_URL_PATTERN + "request_token",
				MAGIC_URL_PATTERN + "access_token", 
				MAGIC_URL_PATTERN + "authorize");
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
	
	/**
	 * Invokes the Magic Broker's <code>/bits/show?id=</code> method.
	 * @param id the ID of the bit whose data to fetch.
	 * @return a JsonItem representing the bit.
	 */
	public JsonItem showBit(int id) {
		String request = "bits/show?id=" + id;
		return (new JsonParser(compileInputStream(request))).parse().get(0);
	}
	
	/**
	 * Invokes the Magic Broker's <code>/friends/show</code> method/
	 * @return a List of User objects corresponding to the current user's friends.
	 */
	// FIXME currently returns an empty, [], array
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
	
	/**
	 * Invokes the Magic Broker's <code>/users/show</code> method.
	 * @return a JsonItem representing the current user.
	 */
	// FIXME for some reason this returns null, from the server.... using int param method works though...
	public JsonItem showUser() {
		String request = "users/show";
		return (new JsonParser(compileInputStream(request))).parse().get(0);
	}
	
	/**
	 * Invokes the Magic Broker's <code>/users/show?id=</code> method.
	 * @param id
	 * @return a JsonItem representing the specified user.
	 */
	public JsonItem showUser(int id) {
		String request = "users/show?id=" + id;
		return (new JsonParser(compileInputStream(request))).parse().get(0);
	}
	
	/**
	 * Invokes the Magic Broker's <code>/checkins/bit?id=</code> method.
	 * @param id
	 * @return a JsonItem representing the checkin.
	 */
	public JsonItem checkin(int id) {
		String request = "checkins/bit?id=" + id;
		return (new JsonParser(compileInputStream(request))).parse().get(0);
	}
	
	/**
	 * Invokes the Magic Broker's <code>/users/search?q=</code> method.
	 * @param query
	 * @return a List of Users that match the search query.
	 */
	public List<User> searchUser(String query) {
		String request = "users/search?q=" + query;
		Iterator<JsonItem> iterator = (new JsonParser(compileInputStream(request))).parse().iterator();
		List<User> list = new ArrayList<User>();
		while (iterator.hasNext()) {
			JsonItem match = iterator.next().getAsJsonItem("user");
			
			String name, username, description, photo;
			Integer experience, points, userID;
			try {
				name = match.getAsString(AndroidCoffeeShopService.NAME);
				username = match.getAsString(AndroidCoffeeShopService.USERNAME);
				description = match.getAsString(AndroidCoffeeShopService.DESCRIPTION);
				photo = match.getAsString(AndroidCoffeeShopService.PHOTO);
				userID = match.getAsInteger(AndroidCoffeeShopService.ID);
				try {
					experience = match.getAsInteger("experience");
					points = match.getAsInteger("points");
				} catch (NumberFormatException e) {
					experience = 0;
					points = 0;
				}
			} catch (NullPointerException e) {
				continue;
			}
			System.out.println(name); // debug
			User user = new User(name, username, description, photo, userID,
					experience, points);
			list.add(user);
		}
		return list;
	}
	
	/**
	 * Invokes the Magic Broker's <code>/links/show</code> method.
	 * @return a List of Bits linked to the current user.
	 */
	public List<Bit> showBitLinksOfUser() {
		String request = "links/show";
		Iterator<JsonItem> iterator = (new JsonParser(compileInputStream(request))).parse().iterator();
		List<Bit> list = new ArrayList<Bit>();
		while (iterator.hasNext()) {
			JsonItem bit = iterator.next().getAsJsonItem("bit");
			try {
				String name = bit.getAsString(AndroidCoffeeShopService.NAME);
				String description = bit.getAsString(AndroidCoffeeShopService.DESCRIPTION);
				String qrImage = bit.getAsString(AndroidCoffeeShopService.QR_IMAGE_URL);
				Integer id, type;
				try {
					id = bit.getAsInteger(AndroidCoffeeShopService.ID);
					type = bit.getAsInteger(AndroidCoffeeShopService.BITS_TYPES_ID);;
				} catch (NumberFormatException e) {
					id = 0;
					type = 0;
				}
				list.add(new Bit(name, description, qrImage, id, type));
			} catch (Exception e) {
				continue;
			}
		}
		return list;
	}
	
	/**
	 * Invokes the Magic Broker's <code>/bits/search?q=</code> method.
	 * @param query
	 * @return a List of Bits matching the search query.
	 */
	public List<Bit> searchBits(String query){
		String request = "bits/search?q=" + query;
		Iterator<JsonItem> iterator = (new JsonParser(compileInputStream(request))).parse().iterator();
		List<Bit> list = new ArrayList<Bit>();
		while (iterator.hasNext()) {
			JsonItem match = iterator.next();
			try {
				Bit bit = null;
				if (match.containsKey("bit")) {
					String name = match.getAsString(AndroidCoffeeShopService.NAME);
					String description = match.getAsString(AndroidCoffeeShopService.DESCRIPTION);
					String qrImage = match.getAsString(AndroidCoffeeShopService.QR_IMAGE_URL);
					Integer id, type;
					try {
						id = match.getAsInteger(AndroidCoffeeShopService.ID);
						type = match.getAsInteger(AndroidCoffeeShopService.BITS_TYPES_ID);;
					} catch (NumberFormatException e) {
						id = 0;
						type = 0;
					}
					System.out.println(name); // debug
					bit = new Bit(name, description, qrImage, id, type);
				} else
					continue;
				list.add(bit);
			} catch (NullPointerException e) {
				continue;
			}
		}
		return list;
	}
	
	/**
	 * Creates a link between the identified user and a bit in the MAGIC Broker database.
	 * @param id the identification number of the bit to link to.
	 * @return a JsonItem representing the link, containing all the linked "bit" and "user" items.
	 */
	public void createLink(int id) {
		String request = "links/create?id=" + id;
		(new JsonParser(compileInputStream(request))).parse().get(0);
	}
	
	/**
	 * Compiles and executes the request's Http methods and returns the response's
	 * associated InputStream.
	 * @param query the specific query to append to this service's URL pattern.
	 * @return the InputStream of the recieved HttpResponse.
	 */
	private InputStream compileInputStream(String query) {
		HttpClient httpClient = new DefaultHttpClient();
		
		HttpGet request = new HttpGet(MAGIC_URL_PATTERN + query);
		
		HttpResponse response = null;
		try {
			consumer.sign(request);
			
			/*
			// Ensure the signpost libraries do not implement the "id" parameter
			if (!path.contains("id=")) {
				Log.d("MAGIC", "id removed: " + request.getParams().removeParameter("id"));
				Log.d("MAGIC", "request URI: " + request.getURI().toString());
			}
			*/
			response = httpClient.execute(request);
			
			/*
			// Debug - intercepts the received content and logs it before returning
			BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			StringBuilder builder = new StringBuilder();
			String line;
			while((line = reader.readLine()) != null)
				builder.append(line);
			Log.d("MAGIC", builder.toString());
			System.out.println(builder.toString());
			return(new ByteArrayInputStream(builder.toString().getBytes()));
			*/
			
			return response.getEntity().getContent();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Initiates the OAuth dance by fetching a request token. This method
	 * will redirect the user from the current context to a WebView for
	 * credentials authorization.
	 * @param context the context to initate authorization from.
	 */
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
	
	/**
	 * The final stage of the OAuth dance, verifying the authorized access token.
	 * Call this method after the user has been redirected to the callback URI, with
	 * the authorized query parameter included. The context from which this is
	 * invoked should be, in most situations, declared "singleTask".
	 * @param context the context from which the callback was received and to
	 * return to.
	 * @param uri the URI containing the <code>oauth_token</code> and
	 * <code>oauth_verifier</code>.
	 */
	public void verify(Context context, Uri uri) {
		final String oauth_verifier = uri.getQueryParameter(OAuth.OAUTH_VERIFIER);
		try {
			provider.retrieveAccessToken(consumer, oauth_verifier);
		} catch (OAuthException e) {
			e.printStackTrace();
		}
		context.startActivity(new Intent(context, context.getClass()));
	}
	
	/**
	 * Returns a Drawable fetched from the specified URL.
	 * @param url
	 * @return a Drawable downloaded from the specified URL.
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public static Drawable getImageFromURL(String url) throws MalformedURLException, IOException {
		InputStream input = (InputStream) new URL(url).getContent();
		return Drawable.createFromStream(input, "src name");
	}
	
	/**
	 * Returns a Drawable fetched from Google Charts API
	 * @param size
	 * @param data
	 * @return a Drawable downloaded from {@link https://charts.googleapis.com/}
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public static Drawable getGoogleQR(int size, String data) throws MalformedURLException, IOException {
		InputStream input = (InputStream) new URL("https://chart.googleapis.com/chart?cht=qr&chs=" + size + "&chl=" + data + "&choe=" + "UTF-8").getContent();
		return Drawable.createFromStream(input, "src name");
	}
}
