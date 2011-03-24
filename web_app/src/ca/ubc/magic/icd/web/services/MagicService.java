package ca.ubc.magic.icd.web.services;

import java.util.List;

import ca.ubc.magic.icd.web.json.JsonItem;
import ca.ubc.magic.icd.web.model.Bit;
import ca.ubc.magic.icd.web.model.User;
/**
 * 
 * An interface defining the 
 * @author Jeffrey Payan
 * @author Stephen Kidson
 *
 */
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
	public static final String BITS_TYPES_ID = "bits_types_id";
	public static final String GEOLONG = "geolong";
	public static final String GEOLAT = "geolat";
	
	public static final String USERNAME = "username";
	public static final String EXPERIENCE = "experience";
	public static final String POINTS = "points";
	public static final String PHOTO = "photo";
	
	public static final String[] PLACES = {"Utopia, Nowhere", "Vancouver, BC"};
	public static final String ENCODING = "UTF-8";
	
	public JsonItem createBit(int type, String name, String description);
	
	public JsonItem createBit(int type, String name, String description, String place);
	
	public JsonItem showBit(int id);
	
	public JsonItem updateBitName(int id, String name);
	
	public void updateBitDescription(int id, String description);
	
	public JsonItem updateBitType(int id, int type);
	
	public JsonItem updateBitPlace(int id, int place);
	
	public JsonItem checkin(int id);
	
	public void createFriend(int id);
	
	public void destroyFriend(int id);
	
	public List<User> showFriends();
	
	public List<User> showFriends(int id);
	
	public JsonItem showUser();
	
	public JsonItem showUser(int id);
	
	public List<User> searchUser(String query);
	
	public JsonItem createLink(int id);
	
	public JsonItem destroyLink(int id);
	
	public List<User> showUserLinkedToBit(int id);
	
	public List<Bit> showBitLinksOfUser(); 
	
	public List<Bit> showBitLinksOfUser(int id); 
	
	public List<Bit> searchBits(String q);
	
	public List<Bit> showTies(int id);
	
	public JsonItem createTie(int id, int tie);
	
	public JsonItem destroyTie(int id, int tie);
	
}
