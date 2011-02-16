package ca.ubc.magic.icd.web.model;

import java.util.ArrayList;
import java.util.List;

public class Bit {
	private String name;
	private int id;
	private List<String> friends;
	
	public Bit(int id){
		this.id = id;
		friends = new ArrayList<String>();
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setID(int id){
		this.id = id;
	}
	
	public int getID(){
		return this.id;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setFriends(List<String> friends){
		this.friends = friends;
	}
	
	public List<String> getFriends(){
		return this.friends;
	}
	
	public void addFriend(String name){
		friends.add(name);
	}
}

