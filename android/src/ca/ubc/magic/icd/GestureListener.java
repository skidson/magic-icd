package ca.ubc.magic.icd;

import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;

public class GestureListener extends SimpleOnGestureListener{
	private int minDistance = 120;
	private int minVelocity = 200;
	private GestureHandler handler;
	
	public GestureListener(GestureHandler handler) {
		this.handler = handler;
	}
	
	public GestureListener(int minDistance, int minVelocity, GestureHandler handler) {
		this.minDistance = minDistance;
		this.minVelocity = minVelocity;
		this.handler = handler;
	}
	
	public void setHandler(GestureHandler handler) {
		this.handler = handler;
	}
	
	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
		if (e1.getX() - e2.getX() > minDistance && Math.abs(velocityX) > minVelocity) {
			handler.onFlingLeft();
		} else if (e2.getX() - e1.getX() > minDistance && Math.abs(velocityX) > minVelocity ) {
			handler.onFlingRight();
		}
		
		if (e1.getY() - e2.getY() > minDistance && Math.abs(velocityY) > minVelocity) {
			handler.onFlingUp();
		} else if (e2.getY() - e1.getY() > minDistance && Math.abs(velocityY) > minVelocity ) {
			handler.onFlingDown();
		}
		return false;
	}
	
}
