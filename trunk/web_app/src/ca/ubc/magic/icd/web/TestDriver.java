package ca.ubc.magic.icd.web;

import ca.ubc.magic.icd.web.oauth.CoffeeShopClient;

public class TestDriver {
	public static void main(String args[]) {
		CoffeeShopClient client = new CoffeeShopClient();
		client.login();
	}
}