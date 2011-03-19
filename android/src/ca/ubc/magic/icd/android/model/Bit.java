package ca.ubc.magic.icd.android.model;

import ca.ubc.magic.icd.android.services.AndroidCoffeeShopService;
import ca.ubc.magic.icd.web.model.MagicItem;

public class Bit extends MagicItem {
	private String qrImage;
	private int typeID;
	
	public Bit(String name, String description, String qrImage, int id, int type) {
		super(id, name, description);
		this.qrImage = qrImage;
		this.typeID = type;
	}

	public String getQrImage() {
		return qrImage;
	}
	
	public void setQrImage(String qrImage) {
		this.qrImage = qrImage;
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
	
	public String getImage() {
		return getQrImage();
	}
	
}
