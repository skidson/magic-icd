package ca.ubc.magic.icd.web.model;

import ca.ubc.magic.icd.web.json.JsonItem;
import ca.ubc.magic.icd.web.services.CoffeeShopService;
import ca.ubc.magic.icd.web.services.MagicService;

/**
 * This class implements ---______________________________________---
 * @author Jeffrey Payan
 * @author Stephen Kidson
 */
public class Bit extends MagicItem {
	private int placeID;
	private int typeID;
	private String imageURL;
	
	public Bit() {
		super();
	}
	/**
	 * Construct a bit with parameters, name, description, imageURL, type ID, places ID and the bit ID
	 * @param name - name of the bit
	 * @param description - a short description of the bit
	 * @param imageURL -  a URL to the QR code of the bit
	 * @param bit_type_id - int representing the type of the bit
	 * @param places_id -  int used to geo-locate this bit
	 * @param id - the id of this bit
	 */
	public Bit(String name, String description, String imageURL, int bit_type_id, int places_id, int id){
		super(id, name, description);
		this.imageURL = imageURL;
		this.placeID = places_id;
		this.typeID = bit_type_id;
	}
	
	/**
	 * Construct a bit with a JsonItem
	 * @param bitInfo - the JsonItem to create the bit with
	 */
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
	
	/**
	 * Returns the placeID of this bit
	 * @return Returns the placeID of this bit
	 */
	public int getPlaceID() {
		return placeID;
	}

	/**
	 * Set the placeID of this bit
	 * @param places_id - the new placeID
	 */
	public void setPlaceID(int places_id) {
		this.placeID = places_id;
	}
	
	/**
	 * Returns the typeID of this bit
	 * @return Returns the typeID of this bit
	 */
	public int getTypeID() {
		return typeID;
	}
	
	/**
	 * Set the typeID of this bit
	 * @param bit_type_id - the new typeID
	 */
	public void setTypeID(int bit_type_id) {
		this.typeID = bit_type_id;
	}
	
	/**
	 * Returns the String representing the typeID of this bit
	 * @return Returns the String representing the typeID of this bit
	 */
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
	
	/**
	 * Set the imageURL of this bit
	 * @param imageURL - the new imageURL
	 */
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	
	/**
	 * Returns the imageURL of this bit
	 * @return Returns the imageURL of this bit
	 */
	public String getImageURL() {
		return imageURL;
	}
	
	/**
	 * Returns a String representation of this bit. This representation consists of the name, type, id, placesID,
	 * imageURL and description of the bit
	 */
	public String toString() {
		return ("name: " + name + "\ntype: " + getType() + "\nid:" + id + "\nplaceID: + "
			+ placeID + "\nimageURL: " + imageURL + "\nDescription: " + description);
	}
	
}

