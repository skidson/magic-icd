package ca.ubc.magic.icd.web;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Driver {
	
	public static void main(String args[]) {
		URL url;
		URLConnection connection;
		try {
			url = new URL("http://kimberly.ubc.ca:8080/1/echo");
			connection = url.openConnection();
			
			connection.setDoOutput(true);
			OutputStreamWriter output = new OutputStreamWriter(connection.getOutputStream());
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
