package ca.ubc.magic.icd.web.json;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import ca.ubc.magic.icd.web.FeedItem;

/**
 * Container for parsed JSON items.
 * @author Stephen Kidson
 *
 */
public class JsonItem extends FeedItem {
	private Map<String, Object> attributes;
	
	public JsonItem() {
		super();
		attributes = new HashMap<String, Object>();
	}
	
	public boolean containsKey(String key) {
		return attributes.containsKey(key);
	}
	
	public void addAttribute(String key, Object value) {
		this.attributes.put(key, value);
	}
	
	public boolean removeAttribute(String key) {
		if (this.attributes.remove(key) == null)
			return false;
		return true;
	}
	
	public List<JsonItem> getAsJsonList(String key) {
		return (List<JsonItem>)attributes.get(key);
	}
	
	public Integer getAsInteger(String key) throws NumberFormatException {
		return Integer.parseInt((String)this.attributes.get(key));
	}
	
	public String getAsString(String key) {
		return (String) this.attributes.get(key);
	}
	
	public Boolean getAsBoolean(String key) {
		String value = (String)this.attributes.get(key);
		if (value.equalsIgnoreCase("true"))
			return true;
		else
			return false;
	}
	
	public Object[] getAsArray(String key) {
		return (Object[])attributes.get(key);
	}
	
	public JsonItem getAsJsonItem(String key) {
		return (JsonItem)attributes.get(key);
	}
	
	public String toString() {
		Iterator<Map.Entry<String, Object>> iterator = attributes.entrySet().iterator();
		StringBuilder builder = new StringBuilder();
		while (iterator.hasNext()) {
			Map.Entry<String, Object> pairs = iterator.next();
			builder.append(pairs.getKey() + ": " + pairs.getValue() + "\n");
		}
		return builder.toString();
	}
	
	@Override
	public URL getLink() {
		if (attributes.containsKey("link"))
			try {
				return new URL(getAsString("link"));
			} catch (MalformedURLException e) {}
		return null;
	}
	
}
