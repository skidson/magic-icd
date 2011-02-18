package ca.ubc.magic.icd.web;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;


public abstract class BaseParser implements Parser {
	private URL feedUrl;
	
	protected BaseParser(String feedUrl) {
		try {
			this.feedUrl = new URL(feedUrl);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	protected InputStream getInputStream() throws IOException {
		return feedUrl.openConnection().getInputStream();
	}
}
