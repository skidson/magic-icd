package ca.ubc.magic.icd.web.model;

import ca.ubc.magic.icd.web.json.JsonItem;
import ca.ubc.magic.icd.web.services.CoffeeShopService;
import ca.ubc.magic.icd.web.services.MagicService;

public class Bit {
	private String name;
	private int id;
	private int placeID;
	private int typeID;
	private String type;
	private String description;
	private String imageURL;
	
	public Bit() {}
	
	public Bit(String name, String description, String imageURL, int bit_type_id, int places_id, int id){
		this.id = id;
		this.name = name;
		this.description = description;
		this.imageURL = imageURL;
		this.placeID = places_id;
		this.typeID = bit_type_id;
	}
	
	public Bit(JsonItem bitInfo) {
		this(bitInfo.getAsJsonItem("bit").getAsString(MagicService.NAME),
				bitInfo.getAsJsonItem("bit").getAsString(MagicService.DESCRIPTION),
				bitInfo.getAsJsonItem("bit").getAsString(MagicService.QR_IMAGE_URL),
				bitInfo.getAsJsonItem("bit").getAsInteger(MagicService.BITS_TYPES_ID),
				bitInfo.getAsJsonItem("bit").getAsInteger(MagicService.PLACES_ID),
				bitInfo.getAsJsonItem("bit").getAsInteger(MagicService.ID));
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

	public int getPlaceID() {
		return placeID;
	}

	public void setPlaceID(int places_id) {
		this.placeID = places_id;
	}

	public int getTypeID() {
		return typeID;
	}

	public void setTypeID(int bit_type_id) {
		this.typeID = bit_type_id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getType() {
		switch(typeID) {
		case CoffeeShopService.BIT:
			return "Bit";
		case CoffeeShopService.PLACE:
			return "Place";
		case CoffeeShopService.DISPLAY:
			return "Display";
		case CoffeeShopService.CONTENT:
			return "Content";
		case CoffeeShopService.PERSON:
			return "Person";
		case CoffeeShopService.DRINK:
			return "Drink";
		case CoffeeShopService.FOOD:
			return "Food";
		case CoffeeShopService.TABLE:
			return "Table";
		}
		return "None";
	}
	
	public void setType(String type) {
		this.type = type;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public String getImageURL() {
		return imageURL;
	}
	
	public String toString() {
		return ("name: " + name + "\ntype: " + getType() + "\nid:" + id + "\nplaceID: + "
			+ placeID + "\nimageURL: " + imageURL + "\nDescription: " + description);
	}
	
}

