package ca.ubc.magic.icd.web;

import ca.ubc.magic.icd.web.oauth.CoffeeShopClient;

public class TestDriver {
	private static final String CONSUMER_KEY = "766bec602a9fe2795b43501ea4f9a9c9";
	private static final String CONSUMER_SECRET = "sad234fdsf243f4ff3f343kj43hj43g4hgf423f";
	public static void main(String args[]) {
		CoffeeShopClient client = new CoffeeShopClient("http://kimberly.magic.ubc.ca:8080/1/",
				"request_token", "authorize", "access_token", CONSUMER_KEY, CONSUMER_SECRET);
		
		
		
	}
}