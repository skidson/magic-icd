package ca.ubc.magic.icd.android.model;

import ca.ubc.magic.icd.web.model.MagicItem;

/**
 * A basic object representing a user in the Magic Broker database.
 * @author Stephen Kidson
 * @author Jeffrey Payan
 */
public class User extends MagicItem {
	private String username, photo;
	private Integer experience, points;
	
	/**
	 * A basic object representing a user in the Magic Broker database.
	 * @param name
	 * @param username
	 * @param description
	 * @param photo
	 * @param id
	 * @param experience
	 * @param points
	 */
	public User(String name, String username, String description, String photo, int id,
			int experience, int points) {
		super(id, name, description);
		this.username = username;
		this.photo = photo;
		this.experience = experience;
		this.points = points;
	}
	
	/**
	 * Returns this user's username.
	 * @return this user's username.
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * Sets this user's username.
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * Returns the URL for this user's photo.
	 * @return the URL for this user's photo.
	 */
	public String getPhoto() {
		return photo;
	}
	
	/**
	 * Sets the URL for this user's photo.
	 * @param photo
	 */
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	/**
	 * Return's this user's experience.
	 * @return this user's experience.
	 */
	public Integer getExperience() {
		return experience;
	}
	
	/**
	 * Sets this user's experience.
	 * @param experience
	 */
	public void setExperience(Integer experience) {
		this.experience = experience;
	}
	
	/**
	 * Returns this user's points.
	 * @return this user's points.
	 */
	public Integer getPoints() {
		return points;
	}
	
	/**
	 * Sets this user's points.
	 * @param points
	 */
	public void setPoints(Integer points) {
		this.points = points;
	}
	
	public String getImage() {
		return getPhoto();
	}
	
}
