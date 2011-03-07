package ca.ubc.magic.icd.web.services;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.security.oauth.consumer.OAuthRestTemplate;

import ca.ubc.magic.icd.web.json.JsonItem;
import ca.ubc.magic.icd.web.json.JsonParser;
import ca.ubc.magic.icd.web.model.User;

public class CoffeeShopService implements MagicService {
	private String magicURLPattern;
	private String magicQRCodeURLPattern;
	private OAuthRestTemplate magicRestTemplate;

	@Override
	public JsonItem showBit(int id) {
		String request = "bits/show?id=" + id;
		return (new JsonParser(compileInputStream(request))).parse().get(0);
	}

	@Override
	public JsonItem createBit(int type, String name, String description) {
		String request = "bits/create?bits_types_id=" + type + "&name="
			+ name + "&description=" + description;
		return (new JsonParser(compileInputStream(request))).parse().get(0);
	}
	
	/**
	 * Creates the bit in the MAGIC Broker database and returns a JsonItem containing its info.
	 * @param type the type ID (see MagicService constants).
	 * @param name the name for this bit.
	 * @param description the description for the new bit.
	 * @param place the place ID (see getPlacesID())
	 * @return a JsonItem containing "bit", "place", and "bit_types" objects
	 * @see MagicService
	 */
	@Override
	public JsonItem createBit(int type, String name, String description, String place) {
		String request = "bits/create?bits_types_id=" + type + "&name="
			+ name + "&description=" + description + "&places_id=" + getPlaceID(place);
		return (new JsonParser(compileInputStream(request))).parse().get(0);
	}

	@Override
	public JsonItem updateBitName(int id, String name) {
		String request = "bits/update?id=" + id + "&name=" + name;
		return (new JsonParser(compileInputStream(request))).parse().get(0);
	}

	@Override
	public JsonItem updateBitDescription(int id, String description) {
		String request = "bits/update?id=" + id + "&desription=" + description;
		return (new JsonParser(compileInputStream(request))).parse().get(0);
	}

	@Override
	public JsonItem updateBitType(int id, int type) {
		String request = "bits/update?id=" + id + "&bits_types_id=" + type;
		return (new JsonParser(compileInputStream(request))).parse().get(0);
	}

	@Override
	public JsonItem updateBitPlace(int id, int place) {
		String request = "bits/update?id=" + id + "&places_id=" + place;
		return (new JsonParser(compileInputStream(request))).parse().get(0);
	}
	
	/**
	 * Returns a JsonItem containing a "checkin" item and the associated "user" item.
	 * @param id ID number for the bit to check into the current user.
	 */
	public JsonItem checkin(int id) {
		String request = "checkins/bit?id=" + id;
		return (new JsonParser(compileInputStream(request))).parse().get(0);
	}
	
	@Override
	public void createFriend(int id) {
		String request = "friends/create?id=" + id;
		(new JsonParser(compileInputStream(request))).parse();
	}

	@Override
	public void destroyFriend(int id) {
		String request = "friends/destroy?id=" + id;
		(new JsonParser(compileInputStream(request))).parse();
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
		return 0;
	}

}
