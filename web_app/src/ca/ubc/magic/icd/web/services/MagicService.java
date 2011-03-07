package ca.ubc.magic.icd.web.services;

import java.util.List;

import ca.ubc.magic.icd.web.json.JsonItem;
import ca.ubc.magic.icd.web.model.User;

public interface MagicService {
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
	public static final String BITS_TYPE_ID = "bits_type_id";
	public static final String GEOLONG = "geolong";
	public static final String GEOLAT = "geolat";
	
	public static final String[] PLACES = {"Utopia, Nowhere", "Vancouver, BC"};
	
	public JsonItem createBit(int type, String name, String description);
	
	public JsonItem createBit(int type, String name, String description, int place);
	
	public JsonItem showBit(int id);
	
	public JsonItem updateBitName(int id, String name);
	
	public JsonItem updateBitDescription(int id, String description);
	
	public JsonItem updateBitType(int id, int type);
	
	public JsonItem updateBitPlace(int id, int place);
	
	public JsonItem checkin(int id);
	
	public void createFriend(int id);
	
	public void destroyFriend(int id);
	
	public List<User> showFriends();
	
	public List<User> showFriends(int id);
	
	public JsonItem showUser();
	
	public JsonItem showUser(int id);
	
	public JsonItem searchUser(String query);
	
}
