package ca.ubc.magic.icd.web.model;

import java.io.Serializable;;

public class Linked implements Serializable {
	private String magic = "false";
	
	public Linked() {}

	public void setMagic(String magic) {
		this.magic = magic;
	}
	
	public void setMagic(boolean linked) {
		if (linked)
			magic = "true";
		else
			magic = "false";
	}

	public String isMagic() {
		return magic;
	}

}
