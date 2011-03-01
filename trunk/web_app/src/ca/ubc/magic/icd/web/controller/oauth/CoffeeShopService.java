package ca.ubc.magic.icd.web.controller.oauth;

import java.util.List;

import org.springframework.security.oauth.consumer.OAuthRestTemplate;

import ca.ubc.magic.icd.web.MagicService;
import ca.ubc.magic.icd.web.json.JsonItem;
import ca.ubc.magic.icd.web.json.JsonParser;

public class CoffeeShopService implements MagicService {
	private String magicURLPattern;
	private String magicImageURLPattern;
	private OAuthRestTemplate magicRestTemplate;
	
	public String getMagicURLPattern() {
		return magicURLPattern;
	}

	public void setMagicURLPattern(String magicURLPattern) {
		this.magicURLPattern = magicURLPattern;
	}

	public String getMagicImageURLPattern() {
		return magicImageURLPattern;
	}

	public void setMagicImageURLPattern(String magicImageURLPattern) {
		this.magicImageURLPattern = magicImageURLPattern;
	}

	@Override
	public JsonItem showBit(int id) {
		String request = "bit/show?id=" + id;
		return (new JsonParser(magicURLPattern + request)).parse().get(0);
	}

	@Override
	public JsonItem createBit(int type, String name, String description) {
		String request = "create?bits_types=" + type + "&name="
			+ name + "&description=" + description;
		return (new JsonParser(magicURLPattern + request)).parse().get(0);
	}
	
	@Override
	public JsonItem createBit(int type, String name, String description, int place) {
		String request = "bit/create?bits_types=" + type + "&name="
			+ name + "&description=" + description + "&places_id=" + place;
		return (new JsonParser(magicURLPattern + request)).parse().get(0);
	}

	@Override
	public JsonItem updateBitName(int id, String name) {
		String request = "bit/update?id=" + id + "&name=" + name;
		return (new JsonParser(magicURLPattern + request)).parse().get(0);
	}

	@Override
	public JsonItem updateBitDescription(int id, String description) {
		String request = "bit/update?id=" + id + "&desription=" + description;
		return (new JsonParser(magicURLPattern + request)).parse().get(0);
	}

	@Override
	public JsonItem updateBitType(int id, int type) {
		String request = "bit/update?id=" + id + "&bits_types_id=" + type;
		return (new JsonParser(magicURLPattern + request)).parse().get(0);
	}

	@Override
	public JsonItem updateBitPlace(int id, int place) {
		String request = "bit/update?id=" + id + "&places_id=" + place;
		return (new JsonParser(magicURLPattern + request)).parse().get(0);
	}
	
	public JsonItem checkin(int id) {
		String request = "chekin/bit?id=" + id;
		return (new JsonParser(magicURLPattern + request)).parse().get(0);
	}
	
	@Override
	public JsonItem createFriend(int id) {
		String request = "friend/create?id=" + id;
		return (new JsonParser(magicURLPattern + request)).parse().get(0);
	}

	@Override
	public JsonItem destroyFriend(int id) {
		String request = "friend/destroy?id=" + id;
		return (new JsonParser(magicURLPattern + request)).parse().get(0);
	}
	
	public List<JsonItem> showFriends() {
		String request = "friends/show";
		return (new JsonParser(magicURLPattern + request)).parse();
	}
	
	public List<JsonItem> showFriends(int id) {
		String request = "friends/show?id=" + id;
		return (new JsonParser(magicURLPattern + request)).parse();
	}
	
	public JsonItem showUser() {
		String request = "friends/show";
		return (new JsonParser(magicURLPattern + request)).parse().get(0);
	}
	
	public JsonItem showUser(int id) {
		String request = "friends/show?id=" + id;
		return (new JsonParser(magicURLPattern + request)).parse().get(0);
	}
	
	public JsonItem searchUser(String query) {
		String request = "friends/search?q=" + query;
		return (new JsonParser(magicURLPattern + request)).parse().get(0);
	}

	public void setMagicRestTemplate(OAuthRestTemplate magicRestTemplate) {
		this.magicRestTemplate = magicRestTemplate;
	}

	public OAuthRestTemplate getMagicRestTemplate() {
		return magicRestTemplate;
	}

}
