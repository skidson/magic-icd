package ca.ubc.magic.icd.web.json;

public class Attribute {
	private String key;
	private String value;
	
	public Attribute(String key, String value) {
		this.key = key;
		this.value = value;
	}
	
	public String getKey() {
		return key;
	}
	
	public String getValue() {
		return value;
	}
	
	public boolean match(String key) {
		if(key.equals(this.key))
			return true;
		return false;
	}
	
	public String toString() {
		return (key + ": " + value.toString());
	}
}
