package ca.ubc.magic.icd.web.model;

import ca.ubc.magic.icd.web.json.JsonItem;
import ca.ubc.magic.icd.web.services.CoffeeShopService;
import ca.ubc.magic.icd.web.services.MagicService;

public class Bit extends MagicItem {
	private int placeID;
	private int typeID;
	private String imageURL;
	
	public Bit() {
		super();
	}
	
	public Bit(String name, String description, String imageURL, int bit_type_id, int places_id, int id){
		super(id, name, description);
		this.imageURL = imageURL;
		this.placeID = places_id;
		this.typeID = bit_type_id;
	}
	
	public Bit(JsonItem bitInfo) {
		super(bitInfo.getAsJsonItem("bit").getAsInteger(MagicService.ID),
				bitInfo.getAsJsonItem("bit").getAsString(MagicService.NAME),
				bitInfo.getAsJsonItem("bit").getAsString(MagicService.DESCRIPTION));
		
		this.imageURL = bitInfo.getAsJsonItem("bit").getAsString(MagicService.QR_IMAGE_URL);
		
		try {
			this.typeID = bitInfo.getAsJsonItem("bit").getAsInteger(MagicService.BITS_TYPES_ID);
			this.placeID = bitInfo.getAsJsonItem("bit").getAsInteger(MagicService.PLACES_ID);
		} catch (NumberFormatException e) {
			this.typeID = MagicService.BIT;
			this.placeID = 1;
		}
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
		default:
			return "None";
		}
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

