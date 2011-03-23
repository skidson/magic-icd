package ca.ubc.magic.icd.web.services;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.security.oauth.consumer.OAuthRestTemplate;

import ca.ubc.magic.icd.web.json.JsonItem;
import ca.ubc.magic.icd.web.json.JsonParser;
import ca.ubc.magic.icd.web.model.Bit;
import ca.ubc.magic.icd.web.model.User;

/**
 * Implementation of Magic Service. Provides access to protected MAGIC resources provided
 * the user has a access token for the MAGIC broker.
 * @author Jeffrey Payan
 * @author Stephen Kidson
 */
public class CoffeeShopService implements MagicService {
	private String magicURLPattern;
	private String magicQRCodeURLPattern;
	private OAuthRestTemplate magicRestTemplate;


	/**
	 * Returns the data regarding the bit identified by id
	 * @param id - the id of the bit to show information for
	 * @return Returns a JsonItem containing the information for this bit
	 */
	@Override
	public JsonItem showBit(int id) {
		String request = "bits/show?id=" + id;
		return (new JsonParser(compileInputStream(request))).parse().get(0);
	}
	/**
	 * Creates the bit in the MAGIC broker database, returns a JsonItem containing its infomration
	 * and generates a QR code for this bit
	 * @param type - the bit_type_id of the new bit
	 * @param name - the name of the new bit
	 * @param description - the description of the new bit
	 * @return Returns a JsonItem containing this bits information
	 */
	@Override
	public JsonItem createBit(int type, String name, String description) {
		String request = "bits/create?bits_types_id=" + type + "&name="
			+ encode(name) + "&description=" + encode(description);
		return (new JsonParser(compileInputStream(request))).parse().get(0);
	}
	
	/**
	 * Creates the bit in the MAGIC Broker database and returns a JsonItem containing its info.
	 * @param type - the type ID (see MagicService constants).
	 * @param name - the name for this bit.
	 * @param description - the description for the new bit.
	 * @param place - the place ID (see getPlacesID())
	 * @return a JsonItem containing "bit", "place", and "bit_types" objects
	 * @see MagicService
	 */
	@Override
	public JsonItem createBit(int type, String name, String description, String place) {
		String request = "bits/create?bits_types_id=" + type + "&name="
			+ encode(name) + "&description=" + encode(description) + "&places_id=" + getPlaceID(place);
		return (new JsonParser(compileInputStream(request))).parse().get(0);
	}
	
	/**
	 * Update the name of the bit
	 * @param id - the id of the bit to update
	 * @param name - the new name of the bit
	 * @return Returns a JsonItem containing the updated information of the bit
	 */
	@Override
	public JsonItem updateBitName(int id, String name) {
		String request = "bits/update?id=" + id + "&name=" + name;
		return (new JsonParser(compileInputStream(request))).parse().get(0);
	}
	
	/**
	 * Update the description of this bit
	 * @param id - the id of the bit to update
	 * @param description - the new description of the bit
	 * @return Returns a JsonItem containing the updated information of the bit
	 */
	@Override
	public void updateBitDescription(int id, String description) {
		String request = "bits/update?id=" + id + "&description=" + encode(description);
		new JsonParser(compileInputStream(request)).parse();
	}
	
	/**
	 * Update the type of this bit
	 * @param id - the id of the bit to update
	 * @param type - the new typeID of the bit
	 * @return Returns a JsonItem containing the updated information of the bit
	 */
	@Override
	public JsonItem updateBitType(int id, int type) {
		String request = "bits/update?id=" + id + "&bits_types_id=" + type;
		return (new JsonParser(compileInputStream(request))).parse().get(0);
	}
	
	/**
	 * Update the placeID of this bit
	 * @param id - the id of the bit to update
	 * @param place - the new placesID of the bit
	 * @return Returns a JsonItem containing the updated information of the bit
	 */
	@Override
	public JsonItem updateBitPlace(int id, int place) {
		String request = "bits/update?id=" + id + "&places_id=" + place;
		return (new JsonParser(compileInputStream(request))).parse().get(0);
	}
	
	/**
	 * Returns a JsonItem containing a "checkin" item and the associated "user" item.
	 * @param id ID number for the bit to check into the current user.
	 * @return returns a JsonItem containing a "checkin" item and the associated "user" item
	 */
	public JsonItem checkin(int id) {
		String request = "checkins/bit?id=" + id;
		return (new JsonParser(compileInputStream(request))).parse().get(0);
	}
	
	/**
	 * Creates a friend between the authenticated user and the specified user
	 * @param id - the userID of the person to befriend
	 * 
	 */
	@Override
	public void createFriend(int id) {
		String request = "friends/create?id=" + id;
		(new JsonParser(compileInputStream(request))).parse();
	}
	
	/**
	 * Destroys a friendship between the authenticated user and the specified user
	 * @param id - the userID of the person to destroy the friendship with
	 */
	@Override
	public void destroyFriend(int id) {
		String request = "friends/destroy?id=" + id;
		(new JsonParser(compileInputStream(request))).parse();
	}
	
	/**
	 * Returns a List of users containing all the friends of the authenticated user
	 * @return Returns a list of users containing all the friends of the authenticated user
	 */
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
	 * Returns a list of Users containing all the friends of the specified user
	 * @param id - the id of the user to fetch the friends list for
	 * @return Returns a list of users containing all the friends of the specified user
	 */
	public List<User> showFriends(int id) {
		String request = "friends/show?id=" + id;
		Iterator<JsonItem> iterator = (new JsonParser(compileInputStream(request))).parse().iterator();
		List<User> list = new ArrayList<User>();
		while (iterator.hasNext()) {
			JsonItem friend = iterator.next().getAsJsonItem("user");
			
			String name, username, description, photo;
			Integer experience, points, userID;
			try {
				name = friend.getAsString("name");
				username = friend.getAsString("username");
				description = friend.getAsString("description");
				photo = friend.getAsString("photo");
				userID = friend.getAsInteger("id");
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
			User user = new User(name, username, description, photo, userID,
					experience, points);
			list.add(user);
		}
		return list;
	}
	
	/**
	 * Fetches the current user's data from the MAGIC Broker database.
	 * @return a JsonItem representing the "user".
	 * @see MagicService
	 */
	public JsonItem showUser() {
		String request = "users/show";
		return (new JsonParser(compileInputStream(request))).parse().get(0).getAsJsonItem("user");
	}
	
	/**
	 * Fetches the user's data from the MAGIC Broker database.
	 * @param id the ID of the user whose data to fetch from the MAGIC Broker database.
	 * @return a JsonItem representing the "user".
	 * @see MagicService
	 */
	public JsonItem showUser(int id) {
		String request = "users/show?id=" + id;
		return (new JsonParser(compileInputStream(request))).parse().get(0).getAsJsonItem("user");
	}
	
	/**
	 * Searches for a user in the MAGIC broker database. The query, or string, received is used to search for matches
	 * in username, name and email.
	 * @param query - the string used to search the database
	 * @return Returns a list of users containing matches found in the database
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
				name = match.getAsString("name");
				username = match.getAsString("username");
				description = match.getAsString("description");
				photo = match.getAsString("photo");
				userID = match.getAsInteger("id");
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
			User user = new User(name, username, description, photo, userID,
					experience, points);
			list.add(user);
		}
		return list;
	}
	
	/**
	 * Creates a link between the identified user and a bit in the MAGIC Broker database.
	 * @param id the identification number of the bit to link to.
	 * @return a JsonItem representing the link, containing all the linked "bit" and "user" items.
	 */
	public JsonItem createLink(int id) {
		String request = "links/create?id=" + id;
		return (new JsonParser(compileInputStream(request))).parse().get(0);
	}
	
	/**
	 * Destroys a link between the identified user and a bit in the MAGIC Broker database.
	 * @param id the identification number of the bit to destroy the link to.
	 * @return a JsonItem representing the link, containing all the linked "bit" and "user" items.
	 */
	public JsonItem destroyLink(int id) {
		String request = "links/destroy?id=" + id;
		return (new JsonParser(compileInputStream(request))).parse().get(0);
	}
	
	/**
	 * Returns a list of users containing all the users linked to the bit
	 * @param id the bitID of the bit whose links are to be fetched from the database
	 * @return A list of users containing all the users linked to this bit 
	 */
	public List<User> showUserLinkedToBit(int id) {
		String request = "links/users?id=" + id;
		Iterator<JsonItem> iterator = (new JsonParser(compileInputStream(request))).parse().iterator();
		List<User> list = new ArrayList<User>();
		while (iterator.hasNext()) {
			JsonItem userInfo = iterator.next().getAsJsonItem("user");
			
			String name, username, description, photo;
			Integer experience, points, userID;
			try {
				name = userInfo.getAsString("name");
				username = userInfo.getAsString("username");
				description = userInfo.getAsString("description");
				photo = userInfo.getAsString("photo");
				userID = userInfo.getAsInteger("id");
				try {
					experience = userInfo.getAsInteger("experience");
					points = userInfo.getAsInteger("points");
				} catch (NumberFormatException e) {
					experience = 0;
					points = 0;
				}
			} catch (NullPointerException e) {
				continue;
			}
			User user = new User(name, username, description, photo, userID,
					experience, points);
			list.add(user);
		}
		return list;
	}
	
	/**
	 * Returns a list of bits containing all the bits linked to the authenticated user
	 * @return A list of users containing all the bits linked to the authenticated user
	 */
	public List<Bit> showBitLinksOfUser() {
		String request = "links/show";
		Iterator<JsonItem> iterator = (new JsonParser(compileInputStream(request))).parse().iterator();
		List<Bit> list = new ArrayList<Bit>();
		while (iterator.hasNext()) {
			JsonItem match = iterator.next();
			try {
				Bit bit = null;
				if (match.containsKey("bit"))
					bit = new Bit(match);
				else
					continue;
				list.add(bit);
			} catch (NullPointerException e) {
				continue;
			}
		}
		return list;
	}
	
	/**
	 * Returns a list of bits containing all the bits linked to the user
	 * @param id the userID of the user whose links are to be fetched from the database
	 * @return A list of users containing all the bits linked to this user
	 */
	public List<Bit> showBitLinksOfUser(int id) {
		String request = "links/show?id=" + id;
		Iterator<JsonItem> iterator = (new JsonParser(compileInputStream(request))).parse().iterator();
		List<Bit> list = new ArrayList<Bit>();
		while (iterator.hasNext()) {
			JsonItem match = iterator.next();
			try {
				Bit bit = null;
				if (match.containsKey("bit"))
					bit = new Bit(match);
				else
					continue;
				list.add(bit);
			} catch (NullPointerException e) {
				continue;
			}
		}
		return list;
	}
	
	/**
	 * Create a tie between two bits in the database
	 * @param id -  the bitID of the bit to link from
	 * @param tieTo - the bitID of the bit to link to 
	 * @return - A JsonItem containing the information of the two tied bits
	 */
	public JsonItem createTie(int id, int tieTo){
		String request = "ties/create?id=" + id +"&tie=" + tieTo;
		return (new JsonParser(compileInputStream(request))).parse().get(0);		
	}
	
	/**
	 * Destroy a tie between two bits in the database
	 * @param id - the bitID of the bit to destroy the link from
	 * @param tieTo - the bitID of the bit to destroy the link to 
	 * @return - A JsonItem containing the information of the two tied bits
	 */
	public JsonItem destroyTie(int id, int tieTo){
		String request = "ties/destroy?id=" + id +"&tie=" + tieTo;
		return (new JsonParser(compileInputStream(request))).parse().get(0);		
	}
	
	/**
	 * Show all the ties of the specified bit
	 * @param id - the bitID of the bit whose ties are to be fetched from the database
	 * @return - A JsonItem containing the all the bits tied to this bit
	 */
	public List<Bit> showTies(int id){
		String request = "ties/show?id=" + id;
		Iterator<JsonItem> iterator = (new JsonParser(compileInputStream(request))).parse().iterator();
		List<Bit> list = new ArrayList<Bit>();
		while (iterator.hasNext()) {
			JsonItem match = iterator.next();
			try {
				Bit bit = null;
				if (match.containsKey("bit"))
					bit = new Bit(match);
				else
					continue;
				list.add(bit);
			} catch (NullPointerException e) {
				continue;
			}
		}
		return list;
	}
	
	/**
	 * Search the database for a bit. The query is used to match items in the database based on
	 * name and description
	 * @param query - the query for which to search the database
	 * @return A list of bits containing all the matches found in the database.
	 */
	public List<Bit> searchBits(String query){
		String request = "bits/search?q=" + query;
		Iterator<JsonItem> iterator = (new JsonParser(compileInputStream(request))).parse().iterator();
		List<Bit> list = new ArrayList<Bit>();
		while (iterator.hasNext()) {
			JsonItem match = iterator.next();
			try {
				Bit bit = null;
				if (match.containsKey("bit"))
					bit = new Bit(match);
				else
					continue;
				list.add(bit);
			} catch (NullPointerException e) {
				continue;
			}
		}
		return list;
	}
	
	public void setMagicRestTemplate(OAuthRestTemplate magicRestTemplate) {
		this.magicRestTemplate = magicRestTemplate;
	}

	public OAuthRestTemplate getMagicRestTemplate() {
		return magicRestTemplate;
	}

	public void setMagicQRCodeURLPattern(String magicQRCodeURLPattern) {
		this.magicQRCodeURLPattern = magicQRCodeURLPattern;
	}

	public String getMagicQRCodeURLPattern() {
		return magicQRCodeURLPattern;
	}
	
	public String getMagicURLPattern() {
		return magicURLPattern;
	}

	public void setMagicURLPattern(String magicURLPattern) {
		this.magicURLPattern = magicURLPattern;
	}
	
	private InputStream compileInputStream(String request) {
		return new ByteArrayInputStream(getMagicRestTemplate()
				.getForObject(URI.create(getMagicURLPattern() + request), byte[].class));
	}
	
	private static int getPlaceID(String place) {
		for (int i = 0; i < PLACES.length; i++)
			if ((PLACES[i].contains(place)))
				return i+1;
		return 1;
	}
	
	private static String encode(String parameter) {
		try {
    		return URLEncoder.encode(parameter, MagicService.ENCODING)
    			.replace("+", "%20").replace("*", "%2A").replace("%7E", "~");
		} catch (UnsupportedEncodingException e) {}
		return parameter;
	}

}
