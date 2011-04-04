package ca.ubc.magic.icd.android;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import ca.ubc.magic.icd.android.services.AndroidCoffeeShopService;

/**
 * The launcher activity for the CoffeeShop application. Verifies the user. Currently does
 * not use CoffeeShop web-application's login form. The login button initializes
 * the OAuth Web Flow and directs the user to enter their Magic Broker credentials via
 * a Web View.
 * @author Stephen Kidson
 * @author Jeffrey Payan
 */
public class LoginScreen extends Activity {
	private static final String REGISTER_URL = "http://kimberly.magic.ubc.ca:8080/1/register";
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.login);
        
        Button btnLink = (Button)findViewById(R.id.login_btnLink);
        btnLink.setOnClickListener(new OnClickListener() {
        	public void onClick(View view) {
        		new AndroidCoffeeShopService(LoginScreen.this); // initialize the service singleton (user prompted to authorize)
        		finish();
        	}
        });
        
        Button btnRegister = (Button)findViewById(R.id.login_btnRegister);
        btnRegister.setOnClickListener(new OnClickListener() {
        	public void onClick(View view) {
	    		Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(REGISTER_URL))
	    			.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | 
	    					Intent.FLAG_ACTIVITY_NO_HISTORY | 
	    					Intent.FLAG_FROM_BACKGROUND);
	    		startActivity(intent);
        	}
        });
        
    }
    
    
    
}