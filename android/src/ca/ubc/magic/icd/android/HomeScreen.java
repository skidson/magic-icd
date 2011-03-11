package ca.ubc.magic.icd.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class HomeScreen extends Activity {
	private static final String SCANNER = "com.google.zxing.client.android.SCAN";
	
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
        		startActivityForResult(intent, 0);
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
        		startActivity(new Intent(HomeScreen.this, BitScreen.class));
        	}
        });
        
    }
    
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
    	// If returning from scanner, forward to bit screen
    	// TODO ensure this is the correct method of passing parameters to new intents.
    	if (intent.getAction().equals(SCANNER)) {
    		Intent newIntent = new Intent(HomeScreen.this, BitScreen.class);
    		String bitName = "name", bitType = "type", bitDescription = "description";
    		newIntent.putExtra("bitName", bitName);
    		newIntent.putExtra("bitType", bitType);
    		newIntent.putExtra("bitDescription", bitDescription);
    		newIntent.putExtra("bitQR", intent.getStringExtra("SCAN_RESULT"));
    		startActivity(newIntent);
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
    
}