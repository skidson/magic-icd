package ca.ubc.magic.icd;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class LoginScreen extends Activity {
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        
        final EditText username = (EditText) findViewById(R.id.login_txtUsername);
		final EditText password = (EditText) findViewById(R.id.login_txtPassword);
       
        Button btnLogin = (Button)findViewById(R.id.login_btnLogin);
        btnLogin.setOnClickListener(new OnClickListener() {
        	public void onClick(View view) {
        		password.setText("");
        		username.setText("");
        	}
        });
        
		Button btnClear = (Button)findViewById(R.id.login_btnClear);
        btnClear.setOnClickListener(new OnClickListener() {
        	public void onClick(View view) {
        		password.setText("");
        		username.setText("");
        	}
        });
    }
    
}
