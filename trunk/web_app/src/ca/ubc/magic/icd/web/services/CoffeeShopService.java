package ca.ubc.magic.icd.web.services;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.oauth.consumer.OAuthRestTemplate;

import ca.ubc.magic.icd.web.json.JsonItem;
import ca.ubc.magic.icd.web.json.JsonParser;

public class CoffeeShopService implements MagicService {
	private String magicURLPattern;
	private String magicQRCodeURLPattern;
	private OAuthRestTemplate magicRestTemplate;

	@Override
	public JsonItem showBit(int id) {
		String request = "bit/show?id=" + id;
		return (new JsonParser(compileInputStream(request))).parse().get(0);
	}

	@Override
	public JsonItem createBit(int type, String name, String description) {
		String request = "bit/create?bits_types=" + type + "&name="
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
	public JsonItem createBit(int type, String name, String description, int place) {
		String request = "bit/create?bits_types=" + type + "&name="
			+ name + "&description=" + description + "&places_id=" + place;
		return (new JsonParser(compileInputStream(request))).parse().get(0);
	}

	@Override
	public JsonItem updateBitName(int id, String name) {
		String request = "bit/update?id=" + id + "&name=" + name;
		return (new JsonParser(compileInputStream(request))).parse().get(0);
	}

	@Override
	public JsonItem updateBitDescription(int id, String description) {
		String request = "bit/update?id=" + id + "&desription=" + description;
		return (new JsonParser(compileInputStream(request))).parse().get(0);
	}

	@Override
	public JsonItem updateBitType(int id, int type) {
		String request = "bit/update?id=" + id + "&bits_types_id=" + type;
		return (new JsonParser(compileInputStream(request))).parse().get(0);
	}

	@Override
	public JsonItem updateBitPlace(int id, int place) {
		String request = "bit/update?id=" + id + "&places_id=" + place;
		return (new JsonParser(compileInputStream(request))).parse().get(0);
	}
	
	/**
	 * Returns a JsonItem containing a "checkin" item and the associated "user" item.
	 * @param id ID number for the bit to check into the current user.
	 */
	public JsonItem checkin(int id) {
		String request = "checkin/bit?id=" + id;
		return (new JsonParser(compileInputStream(request))).parse().get(0);
	}
	
	@Override
	public void createFriend(int id) {
		String request = "friends/create?id=" + id;
		(new JsonParser(compileInputStream(request))).parse();
	}

	@Override
	public void destroyFriend(int id) {
		String request = "friend/destroy?id=" + id;
		(new JsonParser(compileInputStream(request))).parse();
	}
	
	public List<JsonItem> showFriends() {
		String request = "friends/show";
		return new JsonParser(compileInputStream(request)).parse();
	}
	
	public List<JsonItem> showFriends(int id) {
		String request = "friends/show?id=" + id;
		List<JsonItem> raw = (new JsonParser(compileInputStream(request))).parse();
		List<JsonItem> users = new ArrayList<JsonItem>();
		for (JsonItem user : raw)
			users.add(raw.get(0).getAsJsonItem("user"));
		return users;
	}
	
	/**
	 * Fetches the current user's data from the MAGIC Broker database.
	 * @return a JsonItem representing the "user".
	 * @see MagicService
	 */
	public JsonItem showUser() {
		String request = "user/show";
		return (new JsonParser(compileInputStream(request))).parse().get(0).getAsJsonItem("user");
	}
	
	/**
	 * Fetches the user's data from the MAGIC Broker database.
	 * @param id the ID of the user whose data to fetch from the MAGIC Broker database.
	 * @return a JsonItem representing the "user".
	 * @see MagicService
	 */
	public JsonItem showUser(int id) {
		String request = "user/show?id=" + id;
		return (new JsonParser(compileInputStream(request))).parse().get(0).getAsJsonItem("user");
	}
	
	public JsonItem searchUser(String query) {
		String request = "friends/search?q=" + query;
		return (new JsonParser(compileInputStream(request))).parse().get(0);
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
//		getMagicRestTemplate().getResource().getAdditionalParameters().put("oauth_callback", "http://localhost:8010/web_app/basic/callback?oauth_verifier=lol");
		return new ByteArrayInputStream(getMagicRestTemplate()
				.getForObject(URI.create(getMagicURLPattern() + request), byte[].class));
	}
	
	public static int getPlaceID(String place) {
		for (int i = 0; i < PLACES.length; i++)
			if (place.matches(PLACES[i]))
				return i;
		return 0;
	}

}
