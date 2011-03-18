package ca.ubc.magic.icd.web.model;

import java.util.ArrayList;
import java.util.List;

import ca.ubc.magic.icd.web.json.JsonItem;
import ca.ubc.magic.icd.web.services.MagicService;
/**
 * 
 * @author Jeffrey Payan
 * @author Stephen Kidson
 */
public class User extends MagicItem {
	private String username;
	private int exp;
	private int points;
	private String imageURL;
	private List<String> friends;
	private List<Bit> bits;
	
	public User() {
		super();
	}
	/**
	 * Construct a user with all the given information
	 * @param name - name of the user
	 * @param username - username of the user
	 * @param description - description of the user
	 * @param imageURL - URL location of a picture for the user
	 * @param id - id of the user
	 * @param exp - exp of the user
	 * @param points - points of the user
	 */
	public User(String name, String username, String description, String imageURL, int id, int exp, int points){
		super(id, name, description);
		this.username = username;
		this.exp = exp;
		this.points = points;
		this.imageURL = imageURL;
		friends = new ArrayList<String>();
		bits = new ArrayList<Bit>();
	}
	
	/**
	 * Construct a User with properties pulled from the JsonItem userInfo
	 * @param userInfo - JsonItem to generate user from
	 */
	public User(JsonItem userInfo) {
		super(userInfo.getAsInteger(MagicService.ID),
				userInfo.getAsString(MagicService.NAME),
				userInfo.getAsString(MagicService.DESCRIPTION));
		
		this.username = userInfo.getAsString(MagicService.USERNAME);
		this.imageURL = userInfo.getAsString(MagicService.PHOTO);
		
		try {
			this.exp = userInfo.getAsInteger(MagicService.EXPERIENCE);
			this.points = userInfo.getAsInteger(MagicService.POINTS);
		} catch (NumberFormatException e) {
			this.exp = 0;
			this.points = 0;
		}
		
		friends = new ArrayList<String>();
		bits = new ArrayList<Bit>();
	}
	/**
	 * Set this users bit list
	 * @param bits - the new list of bits
	 */
	public void setBits(List<Bit> bits){
		this.bits = bits;
	}
	
	/**
	 * Returns this users list of bits
	 * @return Returns this users list of bits
	 */
	public List<Bit> getBits(){
		return bits;
	}
	
	/**
	 * Returns the username of this user
	 * @return Returns the username of this user
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * Set the username of this user
	 * @param username - the new username
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	 /**
	  * Returns this users EXP
	  * @return Returns this users EXP
	  */
	public int getExp() {
		return exp;
	}
	
	/**
	 * Set this users EXP
	 * @param exp - the new EXP
	 */
	public void setExp(int exp) {
		this.exp = exp;
	}
	
	/**
	 * Returns this users points
	 * @return Returns this users points
	 */
	public int getPoints() {
		return points;
	}
	 /**
	  * Set this users points
	  * @param points - the new points
	  */
	public void setPoints(int points) {
		this.points = points;
	}
	
	/**
	 * Set this users imageURL
	 * @param imageURL - the new imageURL
	 */
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	
	/**
	 * Get this users imageURL
	 * @return Returns this users imageURL
	 */
	public String getImageURL() {
		return imageURL;
	}
}
