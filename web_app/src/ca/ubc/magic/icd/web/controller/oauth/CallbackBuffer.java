package ca.ubc.magic.icd.web.controller.oauth;

import java.util.ArrayList;
import java.util.List;

// TODO clear old buffer elements autmoatically
public class CallbackBuffer {
	private static final int FRONT = 0;
	private List<CallbackItem> buffer;
	private static CallbackBuffer instance;
	
	protected CallbackBuffer() {
		this.buffer = new ArrayList<CallbackItem>();
	}
	
	public static CallbackBuffer getInstance() {
		if (instance == null)
			return new CallbackBuffer();
		else
			return instance;
	}
	
	public void add(CallbackItem item) {
		buffer.add(item);
	}
	
	public CallbackItem next() {
		return buffer.remove(FRONT);
	}
}