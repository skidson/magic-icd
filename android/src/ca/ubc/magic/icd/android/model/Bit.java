package ca.ubc.magic.icd.android.model;

import ca.ubc.magic.icd.web.model.MagicItem;

/**
 * A basic object representing a Bit in the Magic Broker database.
 * @author Stephen Kidson
 * @author Jeffrey Payan
 */
public class Bit extends MagicItem {
	public static final int BIT = 1;
	public static final int PLACE = 2;
	public static final int TABLE = 3;
	public static final int DRINK = 4;
	public static final int FOOD = 5;
	public static final int DISPLAY = 6;
	public static final int CONTENT = 7;
	public static final int PERSON = 8;
	
	private String qrImage;
	private int typeID;
	
	/**
	 * A basic object representing a Bit in the Magic Broker database.
	 * @param name
	 * @param description
	 * @param qrImage
	 * @param id
	 * @param type
	 */
	public Bit(String name, String description, String qrImage, int id, int type) {
		super(id, name, description);
		this.qrImage = qrImage;
		this.typeID = type;
	}
	
	/**
	 * Returns the string URL for this bit's QR Code. The Drawable may be fetched
	 * using {@link ca.ubc.magic.icd.android.services.AndroidCoffeeShopService#getImageFromURL(String)} or 
	 * {@link ca.ubc.magic.icd.android.services.AndroidCoffeeShopService#getQRCode()}
	 * @return the URL of this bit's QR Code.
	 * @see ca.ubc.magic.icd.android.services.AndroidCoffeeShopService#getImageFromURL(String)
	 * @see ca.ubc.magic.icd.android.services.AndroidCoffeeShopService#getQRCode(String)
	 */
	public String getQrImage() {
		return qrImage;
	}
	
	/**
	 * Sets the URL for this bit's QR Code.
	 * @param qrImage the URL for a QR Code.
	 */
	public void setQrImage(String qrImage) {
		this.qrImage = qrImage;
	}
	
	/**
	 * Returns this bit's type as an integer.
	 * @return this bit's type integer.
	 * @see #getType()
	 */
	public int getTypeID() {
		return typeID;
	}
	
	/**
	 * Sets this bit's type. Constants are statically included in this class.
	 * @param type
	 * see #getType()
	 */
	public void setTypeID(int type) {
		this.typeID = type;
	}
	
	/**
	 * Returns the string representation of this bit's type.
	 * @return a string representing this bit's type.
	 */
	public String getType() {
		switch(typeID) {
		case BIT:
			return "Bit";
		case PLACE:
			return "Place";
		case DISPLAY:
			return "Display";
		case CONTENT:
			return "Content";
		case PERSON:
			return "Person";
		case DRINK:
			return "Drink";
		case FOOD:
			return "Food";
		case TABLE:
			return "Table";
		default:
			return "None";
		}
	}
	
	public String getImage() {
		return getQrImage();
	}
	
}
