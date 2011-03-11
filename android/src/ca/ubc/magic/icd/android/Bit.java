package ca.ubc.magic.icd.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Bit extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.bit);
        
        Button btnLogin = (Button)findViewById(R.id.bit_btnCheckin);
        btnLogin.setOnClickListener(new OnClickListener() {
        	public void onClick(View view) {
        		// TODO verify credentials
        		startActivity(new Intent(Bit.this, HomeScreen.class));
        	}
        });
    }
    
}