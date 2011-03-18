package ca.ubc.magic.icd.android;

/*
 * TODO have Magic OAuth Authorization occur inside an in-application WebView or POST form
 * TODO implement Login page for CoffeeShop
 * TODO touch-up graphics and icons
 * TODO compensate for different screen sizes (ScrollViews, etc.)
 * TODO settings menu
 */

import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import ca.ubc.magic.icd.android.services.AndroidCoffeeShopService;

public class HomeScreen extends Activity {
	private static final String SCANNER = "com.google.zxing.client.android.SCAN";
	private static final int SCANNER_REQUEST_CODE = 0;
	private static final String SCAN_RESULT = "SCAN_RESULT";
	private static final String MAGIC_QR_PATTERN = "MAGIC:";
	private AndroidCoffeeShopService magicService;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        magicService = AndroidCoffeeShopService.getInstance(HomeScreen.this);
        
        User user = magicService.showUser();
        
        Button btnCheckin = (Button)findViewById(R.id.home_btnCheckin);
        btnCheckin.setOnClickListener(new OnClickListener() {
        	public void onClick(View view) {
        		Intent intent = new Intent(SCANNER);
        		intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
        		startActivityForResult(intent, SCANNER_REQUEST_CODE);
        	}
        });
        
        Button btnFriends = (Button) findViewById(R.id.home_btnFriends);
        btnFriends.setOnClickListener(new OnClickListener() {
        	public void onClick(View view) {
        		
        	}
        });
        
        Button btnBits = (Button) findViewById(R.id.home_btnBits);
        btnBits.setOnClickListener(new OnClickListener() {
        	public void onClick(View view) {
        		Intent intent = new Intent(HomeScreen.this, BitScreen.class);
        		startActivity(intent);
        	}
        });
        
        TextView txtUsername = (TextView) findViewById(R.id.home_username);
        TextView txtPoints = (TextView) findViewById(R.id.home_points);
        TextView txtExperience = (TextView) findViewById(R.id.home_exp);
        ProgressBar prgExperience = (ProgressBar) findViewById(R.id.home_expBar);
        ImageView imgPortrait = (ImageView) findViewById(R.id.home_portrait);
        
//        txtUsername.setText(user.getUsername());
//        txtPoints.setText(user.getPoints() + " points");
//        txtExperience.setText(user.getExperience() + "/100 EXP");
//        prgExperience.setProgress(user.getExperience());
//        
//        try {
//        	imgPortrait.setImageDrawable(AndroidCoffeeShopService.getImageFromURL(user.getPhoto()));
//        } catch (IOException ignore) { /* use default portrait */ }
    }
    
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
    	// If returning from scanner, forward to bit screen
    	switch (requestCode) {
    	case SCANNER_REQUEST_CODE:
            if (resultCode == RESULT_OK && intent.getStringExtra("SCAN_RESULT_FORMAT").equals("QR_CODE")) {
            	// TODO sanitize this input
            	String qrResult = intent.getStringExtra(SCAN_RESULT);
        		if (qrResult.contains(MAGIC_QR_PATTERN)) {
        			// Fetch and display this bit's info on the BitScreen
        			try {
    	    			Intent newIntent = new Intent(HomeScreen.this, BitScreen.class);
    	    			newIntent.putExtra("bit_id", qrResult.split(":")[1]);
    	    			startActivity(newIntent);
        			} catch (Exception e) {
        				e.printStackTrace(); // FIXME
        			}
        		}
            }
    		break;
    	}
    }
    
    @Override
	protected void onResume() {
//		oauthCallback(this.getIntent());
		System.out.println(">>>>>>>>>>>>>> ON RESUME <<<<<<<<<<<<<<"); // debug
		super.onResume();
	}

	@Override
	public void onNewIntent(Intent intent) {
		oauthCallback(intent);
		System.out.println(">>>>>>>>>>>>>> ON NEW INTENT <<<<<<<<<<<<<<"); // debug
		super.onNewIntent(intent);
	}
	
	private void oauthCallback(Intent intent) {
		Uri uri = intent.getData();
		if (uri != null && uri.toString().startsWith(AndroidCoffeeShopService.CALLBACK_URI))
				magicService.verify(HomeScreen.this, uri);
	}
    
}