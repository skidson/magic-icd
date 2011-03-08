package ca.ubc.magic.icd.web.model;

import org.springframework.stereotype.Service;

@Service("linkManager")
public class LinkManager {
	private Linked linked;
	
	public void linkMagic(boolean link) {
		linked.setMagic(link);
	}

	public Linked getLinked() {
		return linked;
	}

	public void setLinked(Linked linked) {
		this.linked = linked;
	}
	
}