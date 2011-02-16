package ca.ubc.magic.icd.web.model;

import java.util.ArrayList;
import java.util.List;

public class User {
	private String name;
	private List<String> friends;
	private List<Bit> bits;
	
	public User(String name){
		this.name = name;
		friends = new ArrayList<String>();
		bits = new ArrayList<Bit>();
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
		Bit mocha = new Bit(93248);
		Bit cappuccino = new Bit(23894);
		mocha.setName("Mocha");
		cappuccino.setName("cappuccino");
		bits.add(mocha);
		bits.add(cappuccino);
		return bits;
	}
	
	public void addFriend(String name){
		friends.add(name);
	}
}
