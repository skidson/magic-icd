package ca.ubc.magic.icd.web;

import java.io.InputStream;
import java.util.List;

import ca.ubc.magic.icd.web.json.JsonItem;

public interface MagicService {
	public static final int BIT = 1;
	public static final int PLACE = 2;
	public static final int TABLE = 3;
	public static final int DRINK = 4;
	public static final int FOOD = 5;
	public static final int DISPLAY = 6;
	public static final int CONTENT = 7;
	public static final int PERSON = 8;
	
	public JsonItem createBit(int type, String name, String description);
	
	public JsonItem createBit(int type, String name, String description, int place);
	
	public JsonItem showBit(int id);
	
	public JsonItem updateBitName(int id, String name);
	
	public JsonItem updateBitDescription(int id, String description);
	
	public JsonItem updateBitType(int id, int type);
	
	public JsonItem updateBitPlace(int id, int place);
	
	public JsonItem checkin(int id);
	
	public JsonItem createFriend(int id);
	
	public JsonItem destroyFriend(int id);
	
	public List<JsonItem> showFriends();
	
	public List<JsonItem> showFriends(int id);
	
	public JsonItem showUser();
	
	public JsonItem showUser(int id);
	
	public JsonItem searchUser(String query);
	
	// debug
	public List<String> getSparklrPhotoIds();
	public InputStream loadSparklrPhoto(String id);
	
}