package ca.ubc.magic.icd.web;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Parser with basic functionality for registering request URL's.
 * @author skidson
 *
 */
public abstract class BaseParser implements Parser {
	private URL feedUrl;
	
	/**
	 * Constructs a basic parser from the feed's URL.
	 * @param feedUrl the URL to parse data from.
	 */
	protected BaseParser(String feedUrl) {
		try {
			this.feedUrl = new URL(feedUrl);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns the input stream for this parser.
	 * @return This parser's input stream.
	 * @throws IOException
	 */
	protected InputStream getInputStream() throws IOException {
		return feedUrl.openConnection().getInputStream();
	}
}
