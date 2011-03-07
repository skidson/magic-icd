package ca.ubc.magic.icd.web.model;

public class LinkManager {
	private Linked status;
	
	public void linkMagic(boolean linked) {
		status.setMagic(linked);
	}

	public Linked getStatus() {
		return status;
	}

	public void setStatus(Linked status) {
		this.status = status;
	}
	
}
