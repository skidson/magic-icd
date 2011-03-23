package ca.ubc.magic.icd.web.model;

/**
 * Used to store a link between a 
 * @author Jeffrey Payan
 * @author Stephen Kidson
 */
public class Event {
	private User user;
	private Bit bit;
	
	public Event(User user, Bit bit){
		this.user = user;
		this.bit = bit;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Bit getBit() {
		return bit;
	}

	public void setBit(Bit bit) {
		this.bit = bit;
	}
	
	
}
