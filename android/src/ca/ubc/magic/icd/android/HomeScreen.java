package ca.ubc.magic.icd.android;

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
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        
        User user = AndroidCoffeeShopService.showUser(this);
        // TODO pull photo, username, points, and exp
        // TODO update coffeeshop_portrait, coffeeshop_username, coffeeshop_points, coffeeshop_exp
        
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
        
        txtUsername.setText(user.getUsername());
        txtPoints.setText(user.getPoints() + " points");
        txtExperience.setText(user.getExperience() + "/100 EXP");
        prgExperience.setProgress(user.getExperience());
        imgPortrait.setImageURI(Uri.parse(user.getPhoto()));
    }
    
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
    	// If returning from scanner, forward to bit screen
    	switch (requestCode) {
    	case SCANNER_REQUEST_CODE:
    		String qrResult = intent.getStringExtra(SCAN_RESULT);
    		if (qrResult.contains(MAGIC_QR_PATTERN)) {
    			try {
	    			Intent newIntent = new Intent(HomeScreen.this, BitScreen.class);
	    			newIntent.putExtra("bit_id", qrResult.split(":")[1]);
	    			startActivity(newIntent);
    			} catch (Exception e) {
    				e.printStackTrace();
    			}
    		}
    		break;
    	default:
    	}
    	
    	/*TextView txtResult = (TextView)findViewById(R.id.home_txtResult);
    	ImageView imgResult = (ImageView)findViewById(R.id.home_imgResult);
    	
    	if (requestCode == 0) {
            if (resultCode == RESULT_OK && intent.getStringExtra("SCAN_RESULT_FORMAT").equals("QR_CODE")) {
                QRItem item = new QRItem(intent.getStringExtra("SCAN_RESULT"));
                txtResult.setText(item.toString());
                try {
					imgResult.setImageDrawable(item.getImageDrawable());
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
            } else
            	txtResult.setText("Could not scan correctly.");
        }*/
    }
    
    @Override
	protected void onResume() {
		super.onResume();
		
		System.out.println(">>>>>>>>>>>>>> RESUMED <<<<<<<<<<<<<<"); // debug
	}

	@Override
	public void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		AndroidCoffeeShopService.verify(HomeScreen.this, intent.getData());
	}
    
}