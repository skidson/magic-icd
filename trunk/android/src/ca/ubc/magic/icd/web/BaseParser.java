package ca.ubc.magic.icd.web;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Parser with basic functionality for registering request URL's.
 * @author Stephen Kidson
 * @author Jeffrey Payan
 */
public abstract class BaseParser implements Parser {
	private InputStream stream;
	
	/**
	 * Constructs a basic parser from the feed's URL.
	 * @param feedUrl the URL to parse data from.
	 */
	protected BaseParser(String feedUrl) {
		try {
			this.stream = (new URL(feedUrl)).openStream();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Constructs a basic parser from the feed's input stream.
	 * @param feedUrl the stream to parse data from.
	 */
	protected BaseParser(InputStream stream) {
		this.stream = stream;
	}
	
	/**
	 * Returns the input stream for this parser.
	 * @return This parser's input stream.
	 * @throws IOException
	 */
	protected InputStream getInputStream() throws IOException {
		return stream;
	}
}
