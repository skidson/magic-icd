package ca.ubc.magic.icd;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import android.widget.ImageView;

import com.google.zxing.WriterException;

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
		
		if (type.equals(CONTACT_INFO) || type.equals(MAGIC_ITEM)) {
			// Remove id tag from start
			lines[0] = lines[0].replace(type + ":", "");
			tags = this.parseMagic(lines);
		} else if (type.equals(URL)) {
			tags = new HashMap<String, String>();
			tags.put("url", lines[0]);
		} else
			return null;
		return tags;
	}
	
	/* *********************** Helper Methods *********************** */
	
	private Map<String, String> parseMagic(String[] lines) {
		Map<String, String> temp = new HashMap<String, String>();
		for (String line : lines) {
			String[] fields = line.split(":");
			temp.put(fields[0], fields[1]);
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
	
	public String nextKey() {
		if (iterator == null)
			goToStart();
		return (String)((Map.Entry<String, String>)iterator.next()).getKey();
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
		
		Iterator<Map.Entry<String, String>> temp = this.iterator;
		StringBuilder builder = new StringBuilder();
		
		goToStart();
		while (this.hasNext()) {
			String key = this.nextKey();
			builder.append(key + ": " + this.get(key) + "\n");
		}
		return builder.toString();
	}
	
	
	/*public ImageView encode() throws WriterException {
		 TODO look into Google Charts API:
		/* https://chart.googleapis.com/chart?cht=qr&chs=128x128&...additional_parameters
		 * cht=qr									-	chart type
		 * chs=<width>x<height>						-	chart size
		 * chl=<data>								- 	data to encode
		 * choe=<output_encoding>					-	encoding data (UTF-8, Shift_JIS, ISO-8859-1)
		 * chld=<error_correction_level>|<margin>	-	L, M, Q, H (7%, 15%, 25%, 30% recovery)
		 
		
		QRCode qrCode = new QRCode();
		Encoder.encode(code, ErrorCorrectionLevel.L, qrCode);
		return qrCode;
		String chl = "MAGIC:" + this.toString();
		String chs = "128x128", choe = "ISO-8859-1", chld = "L";
		
		try {
			URL url = new URL("https://chart.googleapis.com/chart?cht=qr&chs=" + chs + "&chl=" + chl + "&choe=" + choe + "&chld=" + chld);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}*/

}
