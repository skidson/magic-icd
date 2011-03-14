package ca.ubc.magic.icd.android;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import android.graphics.drawable.Drawable;

/**
 * Parses and stores information from various popular QR-Code formats into
 * a backing Hashmap. Wraps the map and exposes the get() and put() interfaces.
 * Restricts keys and values to string values. Various convenience methods are
 * included for common QR-Code related tasks.
 * @author skidson
 *
 */
public class QRItem {
	private static final String CONTACT_INFO = "MECARD";
	private static final String MAGIC_ITEM = "MAGIC";
	private static final String URL = "http";
	
	public enum Type { CONTACT, MAGIC, URL, OTHER };
	
	private Map<String, String> tags;
	private String code;
	private Iterator<Map.Entry<String, String>> iterator;
	
	public QRItem(String code) {
		this.code = code;
		tags = this.parse(code);
	}
	
	/**
	 * Parses all recognized tags into a backing hashmap. Fields should be seperated with semicolons and
	 * with key-value pairs seperated by colons. An example input would be: Name:Bob;Phone:555-5555. If the
	 * code is a URL, it is associated with a single key "url".
	 * @param code the unparsed string read from a QR-Code
	 * @return a map of keys to their associated values.
	 */
	private Map<String, String> parse(String code) {
		String[] lines = code.split(";");
		String type = lines[0].split(":")[0];
		
		if (type.equals(CONTACT_INFO)) {
			tags = this.parseMagic(lines);
		} else if (type.equals(MAGIC_ITEM)) {
			 tags = this.parseMagic(lines);
		} else if (type.equals(URL)) {
			tags = new HashMap<String, String>();
			tags.put("url", lines[0]);
		} else
			return null;
		return tags;
	}
	
	private Map<String, String> parseMagic(String[] lines) {
		Map<String, String> temp = new HashMap<String, String>();
		
		// Remove type header
		String[] first = lines[0].split(":");
		String firstLine = first[1];
		for (int i = 2; i < first.length; i++)
			firstLine += ":" + first[i];
		lines[0] = firstLine;
		
		for (String line : lines) {
			String[] args = line.split(":");
			// Only use the first ":" as a the key delimiter (to avoid breaking urls, for example)
			for (int i = 2; i < args.length; i++)
				args[1] += args[i];
			temp.put(args[0].trim(), args[1].trim());
		}
		return temp;
	}
	
	/**
	 * Return to the start of this QR Code's fields.
	 */
	public void goToStart() {
		iterator = tags.entrySet().iterator();
	}
	
	/**
	 * Returns the value associated with this key.
	 * @param key
	 * @return the value associated with this key. If no value is associated
	 * with this key, the returned value will be null.
	 */
	public Object get(String key) {
		return tags.get(key);
	}
	
	/**
	 * Map the specified key to the specified value
	 * @param key the key.
	 * @param value the value.
	 */
	public void put(String key, String value) {
		tags.put(key, value);
	}
	
	/**
	 * Returns whether this QR-Code contains a value for the specified key.
	 * @param key the key to search for.
	 * @return true if this key is associated with a value, false otherwise.
	 */
	public boolean contains(String key) {
		return tags.containsKey(key);
	}
	
	/**
	 * Returns the String identifier of the next key-value pair.
	 * @return The key for the next key-value pair.
	 */
	public String nextKey() {
		if (iterator == null)
			goToStart();
		return (String)((Map.Entry<String, String>)iterator.next()).getKey();
	}
	
	/**
	 * Returns whether this code has another key-value pair.
	 * @return A boolean value representing whether this code has another key-value pair available
	 */
	public boolean hasNext() {
		if (iterator == null)
			return false;
		return iterator.hasNext();
	}
	
	public String toString() {
		// If no recognized format could be found, return raw code instead
		if (tags == null)
			return code;
		
		Iterator<Map.Entry<String, String>> temp = this.iterator;
		StringBuilder builder = new StringBuilder();
		
		goToStart();
		while (this.hasNext()) {
			String key = this.nextKey();
			builder.append(key + ": " + this.get(key) + "\n");
		}
		this.iterator = temp;
		return builder.toString();
	}
	
	/**
	 * Generates and returns a Drawable of this QR code via Google Charts API
	 * @return A Drawable image of this item's QR Code.
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public Drawable getImageDrawable() throws MalformedURLException, IOException {
		return getImageDrawable(250);
	}
	
	/**
	 * Generates and returns a Drawable of this QR code via Google Charts API
	 * @param size dimensional size of the image to be returned.
	 * @return A Drawable image of this item's QR Code.
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public Drawable getImageDrawable(int size) throws MalformedURLException, IOException {
		String chl = this.toString();
//		String chl = "MAGIC:name:French Vanilla;object:Coffee;category:Food;category2:Drink\nurl:http://skidson.com/magic"; // debug
		String chs = size + "x" + size, choe = "ISO-8859-1", chld = "L";
		return getImageDrawable(chl, chs, choe, chld);
	}
	
	/**
	 * Generates and returns a Drawable of this QR code via Google Charts API
	 * @param context the context the ImageView is for
	 * @param chl data to encode
	 * @param chs chart size (<width>x<height>)
	 * @param choe encoding data (UTF-8, Shift_JIS, ISO-8859-1)
	 * @param chld error correction level (L, M, Q, H) for (7%, 15%, 25%, 30% recovery)
	 * @return A Drawable image of this item's QR Code.
	 * @throws IOException 
	 * @throws MalformedURLException 
	 */
	public Drawable getImageDrawable(String chl, String chs, String choe, String chld) throws MalformedURLException, IOException {
		InputStream input = (InputStream) new URL("https://chart.googleapis.com/chart?cht=qr&chs=" + chs + "&chl=" + chl + "&choe=" + choe + "&chld=" + chld).getContent();
		return Drawable.createFromStream(input, "src name");
	}

}
