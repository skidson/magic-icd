package ca.ubc.magic.icd.web.model;

import java.util.ArrayList;
import java.util.List;

public class Bit {
	private String name;
	private int id;
	private int places_id;
	private int bit_type_id;
	private String description;
	
	public Bit(String name, String description, int bit_type_id, int places_id, int id){
		this.id = id;
		this.name = name;
		this.description = description;
		this.bit_type_id = bit_type_id;
		this.places_id = places_id;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPlaces_id() {
		return places_id;
	}

	public void setPlaces_id(int places_id) {
		this.places_id = places_id;
	}

	public int getBit_type_id() {
		return bit_type_id;
	}

	public void setBit_type_id(int bit_type_id) {
		this.bit_type_id = bit_type_id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}

