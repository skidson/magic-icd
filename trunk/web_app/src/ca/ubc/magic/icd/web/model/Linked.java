package ca.ubc.magic.icd.web.model;

import java.io.Serializable;;

public class Linked implements Serializable {
	private static final long serialVersionUID = 3494464632450103033L;
	private boolean magic;
	
	public Linked() {}
	
	public void setMagic(boolean linked) {
		magic = linked;
	}

	public boolean isMagic() {
		return magic;
	}

}