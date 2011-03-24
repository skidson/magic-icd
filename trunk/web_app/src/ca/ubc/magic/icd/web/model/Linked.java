package ca.ubc.magic.icd.web.model;

import java.io.Serializable;;

/**
 * A class to store the blahblahblah
 * @author Jeffrey Payan
 * @author Stephen Kidson
 *
 */
public class Linked implements Serializable {
	private static final long serialVersionUID = 3494464632450103033L;
	private boolean magic;
	
	public Linked() {}
	
	/**
	 * Set the current status of the magic
	 * @param linked - Incoming 
	 */
	public void setMagic(boolean linked) {
		magic = linked;
	}
	
	/**
	 * Get the boolean storing the status of the link to MAGIC
	 * @return The boolean containing the status of the link to MAGIC
	 */
	public boolean isMagic() {
		return magic;
	}

}