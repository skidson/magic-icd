package ca.ubc.magic.icd.web.model;

public class Message {
	String contents, title;
	int id;
	
	public Message(String contents, String title, int id){
		this.contents = contents;
		this.title = title;
		this.id = id;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
