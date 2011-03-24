package ca.ubc.magic.icd.web.model;

/**
 * A basic object to store a event (User linked to a bit, for example)
 * @author Jeffrey Payan
 * @author Stephen Kidson
 */
public class Event {
	private User user;
	private Bit bit;
	
	/**
	 * Construct an event with given user and bit
	 * @param user - User involved with the event
	 * @param bit - Bit involved with the event
	 */
	public Event(User user, Bit bit){
		this.user = user;
		this.bit = bit;
	}

	/**
	 * Return the user involved with this event
	 * @return Returns the user involved with this event
	 */
	public User getUser() {
		return user;
	}
	
	/**
	 * Set the user involved with this event
	 * @param user - the new user involved with this event
	 */
	public void setUser(User user) {
		this.user = user;
	}
	
	/**
	 * Returns the bit involved with this event
	 * @return Returns the bit involved with this event
	 */
	public Bit getBit() {
		return bit;
	}

	/**
	 * Sets the bit involved with this event
	 * @param bit - the new bit involved with this event
	 */
	public void setBit(Bit bit) {
		this.bit = bit;
	}
	
	
}
