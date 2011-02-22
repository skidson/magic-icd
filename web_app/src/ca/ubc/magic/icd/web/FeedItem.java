package ca.ubc.magic.icd.web;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;

/**
 * A simple container for building parsed objects.
 */
public class FeedItem {
	protected static SimpleDateFormat formatter = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z");
	private Type type;
	protected URL link;
	
	/**
	 * Optional item source enumeration, types: { RSS, FACEBOOK, TWITTER, MAGIC, OTHER }
	 * @author skidson
	 */
	public enum Type { RSS, FACEBOOK, TWITTER, MAGIC, OTHER };
	
	/**
	 * A simple container for building parsed objects.
	 */
	protected FeedItem() {
		this.type = Type.OTHER;
	}
	
	/**
	 * A simple container for building parsed objects.
	 * @param type optional item source type (RSS, FACEBOOK, TWITTER, MAGIC, OTHER).
	 * @see Type
	 */
	protected FeedItem(Type type) {
		this.type = type;
	}
	
	/**
	 * Set optional type parameter using encapsulated Type enumeration {RSS, FACEBOOK, TWITTER, MAGIC, OTHER}.
	 * @param id
	 * @see Type
	 */
	public void setType(String id) {
		type = Type.valueOf(id);
	}
	
	/**
	 * Set optional type parameter using encapsulated Type enumeration {RSS, FACEBOOK, TWITTER, MAGIC, OTHER}.
	 * @param type Type enumeration 
	 * @see Type
	 */
	public void setType(Type type) {
		this.type = type;
	}
	
	/**
	 * Returns this item's optional source type.
	 * @param type Type enumeration 
	 * @see Type
	 */
	public Type getType() {
		return type;
	}
	
	/**
	 * Returns this item's URL.
	 * @return this item's URL.
	 */
	public URL getLink() {
		return this.link;
	}
	
	/**
	 * Set this item's URL.
	 * @param link string version of URL.
	 * @throws MalformedURLException
	 */
	public void setLink(String link) throws MalformedURLException {
		this.link = new URL(link);
	}
	
	/**
	 * Set this item's URL.
	 * @param link URL to set.
	 * @throws MalformedURLException
	 */
	public void setLink(URL link) {
		this.link = link;
	}
	
}
