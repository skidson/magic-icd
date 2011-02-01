package ca.ubc.magic.icd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class HomeScreen extends Activity {
	private ViewFlipper viewFlipper;
//	private final GestureDetector gestureDetector = new GestureDetector(new GestureListener(new HomeGestureHandler()));
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        viewFlipper = (ViewFlipper)findViewById(R.id.home_viewFlipper);
        
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
        		viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(view.getContext(), R.anim.push_left_out));
        		viewFlipper.setInAnimation(AnimationUtils.loadAnimation(view.getContext(), R.anim.push_left_in));
        		viewFlipper.showNext();
        		
        		/*Intent intent = new Intent(HomeScreen.this, LoginScreen.class);
        		startActivity(intent);*/
        	}
        });
        
        Button btnEncode = (Button)findViewById(R.id.home_btnEncode);
        btnEncode.setOnClickListener(new OnClickListener() {
        	public void onClick(View view) {
        		viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(view.getContext(), R.anim.push_right_out));
    			viewFlipper.setInAnimation(AnimationUtils.loadAnimation(view.getContext(), R.anim.push_right_in));
    			viewFlipper.showNext();
        		
        		/*Intent intent = new Intent(HomeScreen.this, EncodeScreen.class);
        		startActivity(intent);*/
        	}
        });
        
        Spinner spinner = (Spinner) findViewById(R.id.encoder_typeSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.encoder_typeArray, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        
//        this.viewFlipper = (ViewFlipper)findViewById(R.id.home_viewFlipper);
//        findViewById(R.layout.home).setOnTouchListener(new OnTouchListener() {
//        	public boolean onTouch(final View view, final MotionEvent event) {
//        		gestureDetector.onTouchEvent(event);
//        		return true;
//        	}
//        });
    }
    
    /**
     * Called when an activity launched from this one has completed.
     */
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
    
    /**
     * Custom collection of methods to be executed when the owning GestureListener
     * identifies an onFling() event.
     * @author skidson
     *
     */
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