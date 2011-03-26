package ca.ubc.magic.icd.web.oauth;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 *  * @deprecated
 * @author Stephen Kidson
 *
 */
public class OAuth {
	public static final String ENCODING = "UTF-8";
	public static final String OAUTH_CONSUMER_KEY = "oauth_consumer_key";
    public static final String OAUTH_TOKEN = "oauth_token";
    public static final String OAUTH_TOKEN_SECRET = "oauth_token_secret";
    public static final String OAUTH_SIGNATURE_METHOD = "oauth_signature_method";
    public static final String OAUTH_SIGNATURE = "oauth_signature";
    public static final String OAUTH_TIMESTAMP = "oauth_timestamp";
    public static final String OAUTH_NONCE = "oauth_nonce";
    public static final String OAUTH_VERSION = "oauth_version";
    public static final String OAUTH_CALLBACK = "oauth_callback";
    public static final String OAUTH_CALLBACK_CONFIRMED = "oauth_callback_confirmed";
    public static final String OAUTH_VERIFIER = "oauth_verifier";
    public static final String VERSION_1_0 = "1.0";
    
    public enum Encoding {PLAINTEXT, HMAC_SHA1, RSA_SHA1 };
    
    public static void sign(OAuthRequest request) {
    	String signature = "";
    	switch(request.getEncoding()) {
    	case PLAINTEXT:
    		break;
    	case HMAC_SHA1:
    		request.setNonce(generateNonce());
    		request.setTimestamp(generateTimestamp());
    		try {
    			Mac mac = Mac.getInstance("HmacSHA1");
    			mac.init(new SecretKeySpec(request.getEncryptionKey(), mac.getAlgorithm()));
    			signature = new String(Base64.encodeBase64(mac.doFinal(getBaseString(request).getBytes())));
    		} catch (NoSuchAlgorithmException e) {
    		} catch (InvalidKeyException e) {
    			e.printStackTrace();
    		}
    		break;
    	case RSA_SHA1:
    		// TODO currently unsupported
    		break;
    	}
    	request.setSignature(signature);
    }
    
    private static String getBaseString(OAuthRequest request) {
    	return request.getHttpMethod() + "&"
				+ encode(request.getUrl()) + "&"
				+ encode(normalize(request));
    }
    
    public static String normalize(OAuthRequest request) {
    	Iterator<Map.Entry<String, String>> iterator = request.iterator();
    	LinkedHashMap<String, String> parameters = new LinkedHashMap<String, String>();
    	while (iterator.hasNext()) {
    		Map.Entry<String, String>  parameter = iterator.next();
    		parameters.put(parameter.getKey(), parameter.getValue());
    	}
    	return normalize(parameters);
    }
    
    public static String normalize(Map<String, String> parameters) {
    	return normalize(parameters, "&", false);
    }
    
    public static String normalize(Map<String, String> parameters, String delim, boolean quoted) {
    	parameters = sort(parameters);
		String quotes = "";
		if (quoted)
			quotes = "\"";
		
		StringBuilder builder = new StringBuilder();
		Iterator<Map.Entry<String, String>> iterator = parameters.entrySet().iterator();
		while(iterator.hasNext()) {
			Map.Entry<String, String> parameter = iterator.next();
			builder.append(encode(parameter.getKey()) + "=" + quotes + encode(parameter.getValue()) + quotes + delim);
		}
		if (!delim.equals(""))
			return builder.toString().substring(0, builder.lastIndexOf(delim));
		return builder.toString();
    }
    
    public static String encode(String data) {
    	String encoded = data;
		try {
			encoded = URLEncoder.encode(data, ENCODING).replace("*", "%2A").replace("+", "%20").replace("%7E", "~");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return encoded;
	}
    
    /**
     * Sorts the map's parameters lexographically by key in ascending order.
     */
    public static LinkedHashMap<String, String> sort(Map<String, String> parameters) {
    	Iterator<Map.Entry<String, String>> iterator = parameters.entrySet().iterator();
    	List<String> keys = new ArrayList<String>();
    	
    	while (iterator.hasNext())
    		keys.add(iterator.next().getKey());
    	
    	Collections.sort(keys);
    	LinkedHashMap<String, String> sorted = new LinkedHashMap<String, String>();
    	for (String key : keys)
    		sorted.put(key, parameters.get(key));
    	
    	return sorted;
    }
    
    public static LinkedHashMap<String, String> sort(OAuthRequest request) {
    	LinkedHashMap<String, String> sorted = new LinkedHashMap<String, String>();
    	List<String> keys = new ArrayList<String>();
    	keys.addAll(request.getParameterKeys());
    	Collections.sort(keys);
    	
    	for (String key : keys)
    		sorted.put(key, request.getParameterValue(key));
    	
    	return sorted;
    }
    
    private static String generateNonce() {
    	return generateNonce(32);
    }
    
    private static String generateNonce(int length) {
		String nonce = "";
		String source = "abcdefghijklmnopqrstuvwxyz" + "1234567890";
		SecureRandom random = null;
		try {
			random = SecureRandom.getInstance("SHA1PRNG");
			for (int i = 0; i < length; i++)
				nonce += source.charAt(random.nextInt(source.length()-1));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return(nonce);
	}
    
    private static String generateTimestamp() {
    	return String.valueOf(System.currentTimeMillis()/1000);
    }
    
}
