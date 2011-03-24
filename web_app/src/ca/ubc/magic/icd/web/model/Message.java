package ca.ubc.magic.icd.web.model;

/**
 * A basic object to store information about messages.
 * Contains message contents, title and ID
 * @author Jeffrey Payan
 * @author Stephen Kidson
 *
 */
public class Message {
	String contents, title;
	int id;
	
	/**
	 * Construct a message with basic parameters
	 * @param contents - contents of this message
	 * @param title - title of this message
	 * @param id - id of this message
	 */
	public Message(String contents, String title, int id){
		this.contents = contents;
		this.title = title;
		this.id = id;
	}
	
	/**
	 * Returns the string containing the contents of this message
	 * @return The string containing the contents of this message
	 */
	public String getContents() {
		return contents;
	}

	/**
	 * Set the contents of this message
	 * @param contents - the new contents of this message
	 */
	public void setContents(String contents) {
		this.contents = contents;
	}

	/**
	 * Returns the title of this message
	 * @return - The title of this message
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Set the title of this message
	 * @param title - The new title of this message
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Returns the id of this message
	 * @return - The id of this message
	 */
	public int getId() {
		return id;
	}

	/**
	 * Set the title of this message
	 * @param id - the new id of this message
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	
}
