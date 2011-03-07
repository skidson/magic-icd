package ca.ubc.magic.icd.web.model;

import java.util.ArrayList;
import java.util.List;

public class User {
	private String name, username, description;
	private int exp;
	private int points;
	private int id;
	private String imageURL;
	private List<String> friends;
	private List<Bit> bits;
	private boolean linked;
	
	public User(){}
	
	public User(String name, String username, String description, String imageURL, int id, int exp, int points){
		this.name = name;
		this.id = id;
		this.username = username;
		this.description = description;
		this.exp = exp;
		this.points = points;
		this.imageURL = imageURL;
		friends = new ArrayList<String>();
		bits = new ArrayList<Bit>();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setFriends(List<String> friends){
		this.friends = friends;
	}
	
	public List<String> getFriends(){
		friends.add("Ricard Simmons");
		friends.add("Justin Bieber");
		return this.friends;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public void setLinked(boolean linked) {
		this.linked = linked;
	}

	public boolean isLinked() {
		return linked;
	}
}
