package ca.ubc.magic.icd.web;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;

public class FeedItem {
	protected static SimpleDateFormat formatter = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z");
	public enum Type { RSS, FACEBOOK, TWITTER, MAGIC, OTHER };
	private Type type;
	protected URL link;
	
	protected FeedItem() {
		this.type = Type.OTHER;
	}
	
	protected FeedItem(Type type) {
		this.type = type;
	}

	public void setType(int id) {
		switch (id) {
		case 0:
			this.type = Type.RSS;
			break;
		case 1:
			this.type = Type.FACEBOOK;
			break;
		case 2:
			this.type = Type.TWITTER;
			break;
		case 3:
			this.type = Type.MAGIC;
			break;
		default:
			this.type = Type.OTHER;
		}
	}
	
	public void setType(String id) {
		type = Type.valueOf(id);
	}
	
	public void setType(Type type) {
		this.type = type;
	}
	
	public Type getType() {
		return type;
	}

	public URL getLink() {
		return this.link;
	}
	
	public void setLink(String link) throws MalformedURLException {
		this.link = new URL(link);
	}
	
	public void setLink(URL link) {
		this.link = link;
	}
	
}
