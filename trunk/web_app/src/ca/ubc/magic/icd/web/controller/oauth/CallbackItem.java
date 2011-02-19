package ca.ubc.magic.icd.web.controller.oauth;

public class CallbackItem {
	private String token;
	private String tokenSecret;
	private String callbackConfirmed;
	private Long timestamp;
	
	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public CallbackItem(String token, String tokenSecret, String callbackConfirmed) {
		this.token = token;
		this.tokenSecret = tokenSecret;
		this.callbackConfirmed = callbackConfirmed;
		this.timestamp = System.currentTimeMillis();
	}
	
	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
	
	public String getTokenSecret() {
		return tokenSecret;
	}
	
	public void setTokenSecret(String tokenSecret) {
		this.tokenSecret = tokenSecret;
	}
	
	public String getCallbackConfirmed() {
		return callbackConfirmed;
	}
	
	public void setCallbackConfirmed(String callbackConfirmed) {
		this.callbackConfirmed = callbackConfirmed;
	}
	
}
