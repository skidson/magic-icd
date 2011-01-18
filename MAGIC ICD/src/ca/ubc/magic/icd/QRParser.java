package ca.ubc.magic.icd;

public class QRParser {
	private final String TYPE_CONTACT = "MECARD";
	private final String TAG_NAME = "N";
	private final String TAG_PHONE = "TEL";
	private final String TAG_EMAIL = "EMAIL";
	private final String TAG_ADDRESS = "ADR";
	
	private String code;
	
	public QRParser(String code) {
		this.code = code;
	}
	
	public String parse() {
		// TODO
		return "";
	}
	
	public String next() {
		// TODO
		return "";
	}

}
