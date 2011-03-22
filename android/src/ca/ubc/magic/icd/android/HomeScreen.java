package ca.ubc.magic.icd.android;

/*
 * TODO have Magic OAuth Authorization occur inside an in-application WebView or POST form
 * TODO implement Login page for CoffeeShop
 * TODO touch-up graphics and icons
 * TODO compensate for different screen sizes (ScrollViews, etc.)
 * TODO settings menu
 * TODO sanitize inputs
 * TODO clickable lists for bits and friends
 * TODO loading screens
 * TODO reduce loading times
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
import ca.ubc.magic.icd.android.model.User;
import ca.ubc.magic.icd.android.services.AndroidCoffeeShopService;
import ca.ubc.magic.icd.web.json.JsonItem;

public class HomeScreen extends Activity {
	private static final String SCANNER = "com.google.zxing.client.android.SCAN";
	private static final int SCANNER_REQUEST_CODE = 0;
	private static final String SCAN_RESULT = "SCAN_RESULT";
	private static final String MAGIC_QR_PATTERN = "MAGIC:";
	private AndroidCoffeeShopService magicService;
	
	private User user;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        magicService = AndroidCoffeeShopService.getInstance(HomeScreen.this);
        
        // FIXME for some reason showUser(#) works but this fails...
        try {
        	updateUser(magicService.showUser().getAsJsonItem("user"));
        } catch (Exception e) {
        	updateUser(magicService.showUser(2).getAsJsonItem("user"));
        }
        
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
        		Intent intent = new Intent(HomeScreen.this, ListScreen.class);
        		intent.putExtra("type", "friends");
        		startActivity(intent);
        	}
        });
        
        Button btnBits = (Button) findViewById(R.id.home_btnBits);
        btnBits.setOnClickListener(new OnClickListener() {
        	public void onClick(View view) {
        		Intent intent = new Intent(HomeScreen.this, ListScreen.class);
        		intent.putExtra("type", "bits");
        		startActivity(intent);
        	}
        });
        
        ImageView imgPhoto = (ImageView) findViewById(R.id.home_portrait);
        imgPhoto.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(HomeScreen.this, UserScreen.class);
				startActivity(intent);
			}
        });
    }
    
    @Override
	protected void onResume() {
    	// FIXME for some reason showUser(1) works but this fails...
		updateUser(magicService.showUser(2).getAsJsonItem("user"));
		super.onResume();
	}

	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
    	// If returning from scanner, forward to bit screen
    	switch (requestCode) {
    	case SCANNER_REQUEST_CODE:
            if (resultCode == RESULT_OK && intent.getStringExtra("SCAN_RESULT_FORMAT").equals("QR_CODE")) {
            	// TODO sanitize this input
            	String qrResult = intent.getStringExtra(SCAN_RESULT);
            	Intent newIntent = new Intent(HomeScreen.this, BitScreen.class);
            	// Fetch and display this bit's info on the BitScreen
            	int bit_id = 1;
        		if (qrResult.contains(MAGIC_QR_PATTERN))
        			bit_id = Integer.parseInt(qrResult.split(":")[1].trim());
    			newIntent.putExtra("bit_id", bit_id);
        		
    			startActivity(newIntent);
            }
    		break;
    	}
    }
	
	@Override
	public void onNewIntent(Intent intent) {
		oauthCallback(intent);
		super.onNewIntent(intent);
	}
	
	private void oauthCallback(Intent intent) {
		Uri uri = intent.getData();
		if (uri != null && uri.toString().startsWith(AndroidCoffeeShopService.CALLBACK_URI))
				magicService.verify(HomeScreen.this, uri);
	}
    
	private void updateUser(JsonItem userInfo) {
		String realName = userInfo.getAsString(AndroidCoffeeShopService.NAME);
		String description = userInfo.getAsString(AndroidCoffeeShopService.DESCRIPTION);
		String username = userInfo.getAsString(AndroidCoffeeShopService.USERNAME);
		String photo = userInfo.getAsString(AndroidCoffeeShopService.PHOTO);
		Integer id = userInfo.getAsInteger(AndroidCoffeeShopService.ID);
        Integer exp, points;
		
		try {
			exp = userInfo.getAsInteger(AndroidCoffeeShopService.EXPERIENCE);
			points = userInfo.getAsInteger(AndroidCoffeeShopService.POINTS);
		} catch (NumberFormatException e) {
			exp = 0;
			points = 0;
		}
        user = new User(realName, username, description, photo, id, exp, points);
        updateFields();
	}
	
	private void updateFields() {
		((TextView) findViewById(R.id.home_username)).setText(user.getName());
        ((TextView) findViewById(R.id.home_points)).setText(user.getPoints() + " points");
        ((TextView) findViewById(R.id.home_exp)).setText(user.getExperience() + "/100 EXP");;
        ((ProgressBar) findViewById(R.id.home_expBar)).setProgress(user.getExperience());
        
        try {
        	((ImageView) findViewById(R.id.home_portrait))
        		.setImageDrawable(AndroidCoffeeShopService.getImageFromURL(user.getPhoto()));
        } catch (IOException ignore) { /* use default portrait */ }
	}
    
}