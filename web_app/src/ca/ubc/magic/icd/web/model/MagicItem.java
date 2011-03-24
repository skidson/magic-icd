package ca.ubc.magic.icd.web.model;

/**
 * A class to store the common attributes of all the items stored in the MAGIC broker database. Contains
 * id, description and name.
 * @author Jeffrey Payan
 * @author Stephen Kidson
 *
 */
public abstract class MagicItem {
	protected int id;
	protected String description;
	protected String name;
	
	/**
	 * Empty constructor
	 */
	public MagicItem() {}
	
	/**
	 * Construct a MagicItem with given id, name, description
	 * @param id - the id of the MagicItem
	 * @param name - the name of the MagicItem
	 * @param description - the description of the MagicItem
	 */
	public MagicItem(int id, String name, String description) {
		this.id = id;
		this.description = description;
		this.name = name;
	}
	
	/**
	 * Returns the id of this MagicItem
	 * @return The id of this MagicItem
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Set the id of this MagicItem
	 * @param id - the new id of this MagicItem
	 * 
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Returns the descriptions of this MagicItem
	 * @return The description of this MagicItem
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Set the description of this MagicItem
	 * @param description - the new description of this MagicItem
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Returns the name of this MagicItem
	 * @return The name of this MagicItem
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Set the name of this MagicItem
	 * @param name - the new name of this MagicItem
	 */
	public void setName(String name) {
		this.name = name;
	}
	
}
