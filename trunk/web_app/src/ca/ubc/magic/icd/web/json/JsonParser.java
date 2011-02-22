package ca.ubc.magic.icd.web.json;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.stream.JsonReader;
import ca.ubc.magic.icd.web.BaseParser;
import ca.ubc.magic.icd.web.FeedItem;

public class JsonParser extends BaseParser {
	private FeedItem.Type type;
	
	public JsonParser(String feedUrl) {
		super(feedUrl);
		this.type = FeedItem.Type.OTHER;
	}
	
	public JsonParser(String feedUrl, FeedItem.Type type) {
		super(feedUrl);
		this.type = type;
	}
	
	/** Parses all items available at this parser's URL. Foreknowledge of 
	 * @return a List containing JSON items fetched from this parser's URL.
	 */
	public List<JsonItem> parse() {
		// Determines whether we are fetching an array or just one item and parses accordingly
		try {
			JsonReader reader = new JsonReader(new InputStreamReader(this.getInputStream(), "UTF-8"));
			List<JsonItem> items = null;
			String nextToken = reader.peek().toString();
			if (nextToken.equals("BEGIN_ARRAY"))
				items = this.parseArray(reader);
			else if (nextToken.equals("BEGIN_OBJECT")) {
				items = new ArrayList<JsonItem>();
				items.add(this.parseItem(reader));
			}
			return items;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<JsonItem> parseArray(JsonReader reader) throws IOException {
		List<JsonItem> items = new ArrayList<JsonItem>();
		reader.beginArray();
		while(reader.hasNext())
			items.add(this.parseItem(reader));
		reader.endArray();
		return items;
	}
	
	public JsonItem parseItem(JsonReader reader) throws IOException {
		JsonItem item = new JsonItem();
		String key;
		reader.beginObject();
		while(reader.hasNext()) {
			key = reader.nextName();
			String nextToken = reader.peek().toString();
			if (nextToken.equals("BEGIN_ARRAY")) {
				item.addAttribute(key, this.parseArray(reader));
			} else if (nextToken.equals("BEGIN_OBJECT")) {
				item.addAttribute(key, this.parseItem(reader));
			} else {
				String value;
				try {
					value = reader.nextString().trim();
				} catch (IllegalStateException e) {
					reader.skipValue();
					continue;
				}
				item.addAttribute(key, value);
				if (this.type != null)
					item.setType(type);
			}
		}
		reader.endObject();
		return item;
	}
}