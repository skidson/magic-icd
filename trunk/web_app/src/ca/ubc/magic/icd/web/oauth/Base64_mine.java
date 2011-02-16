package ca.ubc.magic.icd.web.oauth;

public class Base64_mine {
	private static final String BASE64_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
		+ "abcdefghijklmnopqrstuvwxyz" + "0123456789" + "+/";
	private static final int LINE_LENGTH = 76;
	private static final String UNICODE = "UTF-8";
	private static final String CRLF = "\r\n";
	
	public static String encode(String data) {
		byte[] bytes;
		try {
			bytes = data.getBytes(UNICODE);
		} catch (Exception e) {
			bytes = data.getBytes();
		}
		return encode(bytes);
	}
	
	public static String encode(byte[] bytes) {
		String encoded = "";
		
		int paddingCount = (3 - (bytes.length % 3)) % 3;
		bytes = equalsPad(bytes.length + paddingCount, bytes);
		
		// Pack the bytes
		for (int i = 0; i < bytes.length; i+= 3) {
			int charIndex = ((bytes[i] & 0xff) << 16) + 
				((bytes[i+1] & 0xff) << 8) +
				(bytes[i+2] & 0xff);
			encoded += BASE64_CHARACTERS.charAt((charIndex >> 18) & 0x3f) +
				BASE64_CHARACTERS.charAt((charIndex >> 12) & 0x3f) +
				BASE64_CHARACTERS.charAt((charIndex >> 6) & 0x3f) +
				BASE64_CHARACTERS.charAt(charIndex & 0x3f);
		}
		
		return splitLines(encoded.substring(0, encoded.length() - paddingCount) +
				"==".substring(0, paddingCount));
	}
	
	private static String splitLines(String data) {
		String lines  = "";
		for (int i = 0; i < data.length(); i += LINE_LENGTH) {
			lines += data.substring(i, Math.min(data.length(), i + LINE_LENGTH));
			lines += CRLF;
		}
		return lines;
	}
	
	public static byte[] equalsPad(int length, byte[] bytes) {
		byte[] padded = new byte[length];
		System.arraycopy(bytes, 0, padded, 0, bytes.length);
		return padded;
	}
}
