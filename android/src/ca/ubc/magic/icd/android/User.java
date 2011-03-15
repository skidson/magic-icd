package ca.ubc.magic.icd.android;

public class User {
	private String name, username, description, photo;
	private Integer experience, points, id;
	
	public User(String name, String username, String description, String photo, int id,
			int experience, int points) {
		this.name = name;
		this.username = username;
		this.description = description;
		this.photo = photo;
		this.id = id;
		this.experience = experience;
		this.points = points;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
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
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
}
