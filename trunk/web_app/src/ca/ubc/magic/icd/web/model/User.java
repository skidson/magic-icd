package ca.ubc.magic.icd.web.model;

import java.util.ArrayList;
import java.util.List;

import ca.ubc.magic.icd.web.json.JsonItem;
import ca.ubc.magic.icd.web.services.MagicService;

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
	
	public User(String name, String username, String description, String imageURL, int id, int exp, int points){
		super(id, name, description);
		this.username = username;
		this.exp = exp;
		this.points = points;
		this.imageURL = imageURL;
		friends = new ArrayList<String>();
		bits = new ArrayList<Bit>();
	}
	
	public User(JsonItem userInfo) {
		super(userInfo.getAsJsonItem("user").getAsInteger(MagicService.ID),
				userInfo.getAsJsonItem("user").getAsString(MagicService.NAME),
				userInfo.getAsJsonItem("user").getAsString(MagicService.DESCRIPTION));
		
		this.username = userInfo.getAsJsonItem("user").getAsString(MagicService.USERNAME);
		this.imageURL = userInfo.getAsJsonItem("user").getAsString(MagicService.PHOTO);
		
		try {
			this.exp = userInfo.getAsJsonItem("user").getAsInteger(MagicService.EXPERIENCE);
			this.points = userInfo.getAsJsonItem("user").getAsInteger(MagicService.POINTS);
		} catch (NumberFormatException e) {
			this.exp = 0;
			this.points = 0;
		}
		
		friends = new ArrayList<String>();
		bits = new ArrayList<Bit>();
	}
	
	public void setBits(List<Bit> bits){
		this.bits = bits;
	}
	
	public List<Bit> getBits(){
		Bit mocha = new Bit("Mocha", "Pretty good", "", 4, 2, 3);
		Bit cappuccino = new Bit("Cappuccino", "good", "", 4, 1, 5);
		bits.add(mocha);
		bits.add(cappuccino);
		return bits;
	}
	
	public void addFriend(String name){
		friends.add(name);
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public String getImageURL() {
		return imageURL;
	}
}
