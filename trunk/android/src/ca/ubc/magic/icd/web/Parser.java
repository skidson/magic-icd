package ca.ubc.magic.icd.web;

import java.io.IOException;
import java.util.List;

/**
 * Interface for a standard web parser. Implementations must implement the parse() method.
 * @author skidson
 *
 */
public interface Parser {
	List<? extends FeedItem> parse() throws IOException;
}