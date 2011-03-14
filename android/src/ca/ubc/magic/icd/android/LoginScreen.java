package ca.ubc.magic.icd.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class LoginScreen extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // TODO determine if this user has credentials stored on this device,
        // if not, show Login Screen
        
        setContentView(R.layout.login);
        
        Button btnLogin = (Button)findViewById(R.id.login_btnLogin);
        btnLogin.setOnClickListener(new OnClickListener() {
        	public void onClick(View view) {
        		// TODO verify credentials
        		
        		startActivity(new Intent(LoginScreen.this, HomeScreen.class));
        	}
        });
    }
    
}