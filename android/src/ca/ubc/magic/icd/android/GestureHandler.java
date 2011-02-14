package ca.ubc.magic.icd.android;

/**
 * Interface to be extended for customizing responses to detected gestures.
 * @author skidson
 *
 */
public interface GestureHandler {
	public void onFlingRight();
	public void onFlingLeft();
	public void onFlingUp();
	public void onFlingDown();
}
