package ca.ubc.magic.icd.web;

import java.io.IOException;
import java.util.List;

public interface Parser {
	List<? extends FeedItem> parse() throws IOException;
}