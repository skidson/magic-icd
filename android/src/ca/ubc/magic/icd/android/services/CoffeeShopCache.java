package ca.ubc.magic.icd.android.services;

import java.util.List;

import ca.ubc.magic.icd.android.model.Bit;
import ca.ubc.magic.icd.android.model.User;
import ca.ubc.magic.icd.web.json.JsonItem;

/**
 * Stores commonly accessed data locally. Manages several flags that should be set
 * whenever an Activity performs an operation that may require this data to
 * require being reloaded.
 * @author Stephen Kidson
 *
 */
public class CoffeeShopCache {
	private static List<User> friends;
	private static List<Bit> bits;
	private static User user;
	
	private static boolean bitsChanged = false;
	private static boolean friendsChanged = false;
	private static boolean userChanged = false;
	
	private static CoffeeShopCache instance;
	
	private static AndroidCoffeeShopService broker;
	
	protected CoffeeShopCache() {
		instance = this;
		broker = AndroidCoffeeShopService.getInstance();
	}
	
	public static CoffeeShopCache getInstance() {
		if (instance == null)
			instance = new CoffeeShopCache();
		return instance;
	}
	
	public static List<User> getFriends() {
		if (friends == null || !friendsChanged) {
			friends = broker.showFriends();
			friendsChanged = false;
		}
		return friends;
	}
	
	public static List<Bit> getBits() {
		if (bits == null || !bitsChanged) {
			bits = broker.showBitLinksOfUser();
			bitsChanged = false;
		}
		return bits;
	}
	
	public static User getUser() {
		if (user == null || !userChanged) {
			JsonItem userInfo = broker.showUser();
			String realName = userInfo.getAsString(AndroidCoffeeShopService.NAME);
			String description = userInfo.getAsString(AndroidCoffeeShopService.DESCRIPTION);
			String username = userInfo.getAsString(AndroidCoffeeShopService.USERNAME);
			String photo = userInfo.getAsString(AndroidCoffeeShopService.PHOTO);
			Integer id = userInfo.getAsInteger(AndroidCoffeeShopService.ID);
	        Integer exp, points;
			
			try {
				exp = userInfo.getAsInteger(AndroidCoffeeShopService.EXPERIENCE);
				points = userInfo.getAsInteger(AndroidCoffeeShopService.POINTS);
			} catch (NumberFormatException e) {
				exp = 0;
				points = 0;
			}
	        user = new User(realName, username, description, photo, id, exp, points);
			bitsChanged = false;
		}
		return user;
	}
	
	public static void invalidateCachedUser() {
		userChanged = true;
	}
	
	public static void invalidateCachedFriends() {
		friendsChanged = true;
	}
	
	public static void invalidateCachedBits() {
		bitsChanged = true;
	}
	
}
