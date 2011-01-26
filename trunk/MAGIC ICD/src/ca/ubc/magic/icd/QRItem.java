package ca.ubc.magic.icd;

/* Parser accepts an encoded String and parses into a map, where keys
 * 
 */

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class QRItem {
	private static final String CONTACT_INFO = "MECARD";
	private static final String MAGIC_ITEM = "MAGIC";
	
	private Map<String, Object> tags;
	private String code;
	private Iterator<Map.Entry<String, Object>> iterator;
	
	public QRItem(String code) {
		this.code = code;
		tags = this.parse(code);
	}
	
	/**
	 * Parses all recognized tags into a backing hashmap. Fields should be seperated with semicolons and
	 * with key-value pairs seperated by colons. An example input would be Name:Bob;Phone:555-5555.
	 * @param code
	 * @return
	 */
	private Map<String, Object> parse(String code) {
		String[] lines = code.split(";");
		String type = lines[0].split(":")[0];
		
		if (type.equals(CONTACT_INFO) || type.equals(MAGIC_ITEM)) {
			// Remove id tag from start
			lines[0] = lines[0].replace(type + ":", "");
			tags = this.parseFormatA(lines);
		} else
			return null;
		return tags;
	}
	
	/* *********************** Helper Methods *********************** */
	
	private Map<String, Object> parseFormatA(String[] lines) {
		Map<String, Object> temp = new HashMap<String, Object>();
		for (String line : lines) {
			String[] fields = line.split(":");
			temp.put(fields[0], fields[1]);
		}
		return temp;
	}
	
	public void goToStart() {
		iterator = tags.entrySet().iterator();
	}
	
	public Object get(String key) {
		return tags.get(key);
	}
	
	public boolean contains(String key) {
		return tags.containsKey(key);
	}
	
	public String nextKey() {
		if (iterator == null)
			goToStart();
		return (String)((Map.Entry<String, Object>)iterator.next()).getKey();
	}
	
	public boolean hasNext() {
		if (iterator == null)
			return false;
		return iterator.hasNext();
	}
	
	public String toString() {
		// If no recognized format could be found, return raw code instead
		if (tags == null)
			return code;
		
		Iterator<Map.Entry<String, Object>> temp = this.iterator;
		StringBuilder builder = new StringBuilder();
		
		goToStart();
		while (this.hasNext()) {
			String key = this.nextKey();
			builder.append(key + ": " + this.get(key) + "\n");
		}
		return builder.toString();
	}

}
