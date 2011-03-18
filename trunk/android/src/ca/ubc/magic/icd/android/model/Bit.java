package ca.ubc.magic.icd.android.model;

import ca.ubc.magic.icd.android.services.AndroidCoffeeShopService;

public class Bit {
	String name, description, qrImage;
	int id, typeID;
	
	public Bit(String name, String description, String qrImage, int id, int type) {
		super();
		this.name = name;
		this.description = description;
		this.qrImage = qrImage;
		this.id = id;
		this.typeID = type;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getQrImage() {
		return qrImage;
	}
	
	public void setQrImage(String qrImage) {
		this.qrImage = qrImage;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getTypeID() {
		return typeID;
	}
	
	public void setTypeID(int type) {
		this.typeID = type;
	}
	
	public String getType() {
		switch(typeID) {
		case AndroidCoffeeShopService.BIT:
			return "Bit";
		case AndroidCoffeeShopService.PLACE:
			return "Place";
		case AndroidCoffeeShopService.DISPLAY:
			return "Display";
		case AndroidCoffeeShopService.CONTENT:
			return "Content";
		case AndroidCoffeeShopService.PERSON:
			return "Person";
		case AndroidCoffeeShopService.DRINK:
			return "Drink";
		case AndroidCoffeeShopService.FOOD:
			return "Food";
		case AndroidCoffeeShopService.TABLE:
			return "Table";
		default:
			return "None";
		}
	}
	
}
