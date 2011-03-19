package ca.ubc.magic.icd.android.model;

import ca.ubc.magic.icd.web.model.MagicItem;

public class User extends MagicItem {
	private String username, photo;
	private Integer experience, points;
	
	public User(String name, String username, String description, String photo, int id,
			int experience, int points) {
		super(id, name, description);
		this.username = username;
		this.photo = photo;
		this.experience = experience;
		this.points = points;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPhoto() {
		return photo;
	}
	
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	public Integer getExperience() {
		return experience;
	}
	
	public void setExperience(Integer experience) {
		this.experience = experience;
	}
	
	public Integer getPoints() {
		return points;
	}
	
	public void setPoints(Integer points) {
		this.points = points;
	}
	
	public String getImage() {
		return getPhoto();
	}
	
}
