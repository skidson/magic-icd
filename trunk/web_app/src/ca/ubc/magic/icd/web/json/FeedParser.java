package ca.ubc.magic.icd.web.json;

import java.util.List;

public interface FeedParser {
	List<JsonItem> parse();
}