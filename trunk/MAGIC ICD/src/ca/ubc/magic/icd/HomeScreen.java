package ca.ubc.magic.icd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class HomeScreen extends Activity {
	private ViewFlipper viewFlipper;
//	private final GestureDetector gestureDetector = new GestureDetector(new GestureListener(new HomeGestureHandler()));
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        
        Button btnScan = (Button)findViewById(R.id.home_btnScan);
        btnScan.setOnClickListener(new OnClickListener() {
        	public void onClick(View view) {
        		Intent intent = new Intent("com.google.zxing.client.android.SCAN");
        		intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
        		startActivityForResult(intent, 0);
        	}
        });
        
        Button btnLogin = (Button)findViewById(R.id.home_btnLogin);
        btnLogin.setOnClickListener(new OnClickListener() {
        	public void onClick(View view) {
        		Intent intent = new Intent(HomeScreen.this, LoginScreen.class);
        		startActivity(intent);
        	}
        });
        
//        this.viewFlipper = (ViewFlipper)findViewById(R.id.home_viewFlipper);
//        findViewById(R.layout.home).setOnTouchListener(new OnTouchListener() {
//        	public boolean onTouch(final View view, final MotionEvent event) {
//        		gestureDetector.onTouchEvent(event);
//        		return true;
//        	}
//        });
    }
    
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
    	TextView txtResult = (TextView)findViewById(R.id.home_txtResult);
    	if (requestCode == 0) {
            if (resultCode == RESULT_OK && intent.getStringExtra("SCAN_RESULT_FORMAT").equals("QR_CODE")) {
                QRItem item = new QRItem(intent.getStringExtra("SCAN_RESULT"));
                txtResult.setText(item.toString());
            } else
            	txtResult.setText("Could not scan correctly.");
        }
    }
    
    private class HomeGestureHandler implements GestureHandler {
		public void onFlingRight() {
			viewFlipper.showNext();
		}

		public void onFlingLeft() {
			viewFlipper.showPrevious();
		}

		public void onFlingUp() {
			// ignore
		}
		
		public void onFlingDown() {
    		// ignore
    	}
    }
}