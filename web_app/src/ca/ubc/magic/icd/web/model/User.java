package ca.ubc.magic.icd.web.model;

import java.util.ArrayList;
import java.util.List;

public class User {
	private String name;
	private List<String> friends;
	
	public User(String name){
		this.name = name;
		friends = new ArrayList<String>();
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
	
	public void addFriend(String name){
		friends.add(name);
	}
}
