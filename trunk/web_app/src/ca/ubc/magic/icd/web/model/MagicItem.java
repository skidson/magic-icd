package ca.ubc.magic.icd.web.model;

public abstract class MagicItem {
	protected int id;
	protected String description;
	protected String name;
	
	public MagicItem() {}
	
	public MagicItem(int id, String description, String name) {
		this.id = id;
		this.description = description;
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
