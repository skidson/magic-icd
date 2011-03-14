package ca.ubc.magic.icd.android;

import oauth.signpost.exception.OAuthException;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import ca.ubc.magic.icd.android.services.AndroidCoffeeShopService;

public class HomeScreen extends Activity {
	private static final String SCANNER = "com.google.zxing.client.android.SCAN";
	private static final int SCANNER_REQUEST_CODE = 0;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        
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
        		try {
					AndroidCoffeeShopService.authorize(HomeScreen.this);
					Intent intent = new Intent(HomeScreen.this, BitScreen.class);
					String bitDescription = AndroidCoffeeShopService.showBit(1);
	        		intent.putExtra("bitName", "bitName");
	        		intent.putExtra("bitType", "bitType");
	        		intent.putExtra("bitDescription", bitDescription);
	        		startActivity(intent);
				} catch (OAuthException e) {
					e.printStackTrace();
				}
        	}
        });
        
        Button btnBits = (Button) findViewById(R.id.home_btnBits);
        btnBits.setOnClickListener(new OnClickListener() {
        	public void onClick(View view) {
        		Intent intent = new Intent(HomeScreen.this, BitScreen.class);
        		intent.putExtra("bitName", "bitName");
        		intent.putExtra("bitType", "bitType");
        		intent.putExtra("bitDescription", "bitDescription");
        		startActivity(intent);
        	}
        });
        
    }
    
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
    	// If returning from scanner, forward to bit screen
    	// TODO ensure this is the correct method of passing parameters to new intents.
    	switch (requestCode) {
    	case SCANNER_REQUEST_CODE:
    		Intent newIntent = new Intent(HomeScreen.this, BitScreen.class);
    		newIntent.putExtra("bitQR", intent.getStringExtra("SCAN_RESULT"));
    		startActivity(newIntent);
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
	public void onResume() {
		super.onResume();
		Uri uri = this.getIntent().getData();
		if (uri != null && uri.toString().startsWith(AndroidCoffeeShopService.CALLBACK_URI)) {
			try {
				AndroidCoffeeShopService.verify(uri.getQueryParameter(AndroidCoffeeShopService.OAUTH_VERIFIER));
			} catch (OAuthException e) {
				e.printStackTrace();
			}
		}
	}
    
}