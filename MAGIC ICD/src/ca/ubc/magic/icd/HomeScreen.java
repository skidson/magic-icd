package ca.ubc.magic.icd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class HomeScreen extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        
        Button btnScan = (Button)findViewById(R.id.home_btnScan);
        
        btnScan.setOnClickListener(new OnClickListener() {
        	public void onClick(View view) {
        		Intent intent = new Intent("com.google.zxing.client.android.SCAN");
        		intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
        		startActivityForResult(intent, 0);
        	}
        });
    }
    
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
    	TextView txtResult = (TextView)findViewById(R.id.home_btnScan);
    	if (requestCode == 0) {
            if (resultCode == RESULT_OK && intent.getStringExtra("SCAN_RESULT_FORMAT").equals("QR_CODE")) {
                QRItem item = new QRItem(intent.getStringExtra("SCAN_RESULT"));
                StringBuilder builder = new StringBuilder();
                while(item.hasNext()) {
                	String key = item.nextKey();
                	builder.append(key + ": " + item.get(key) + "\n");
                }
                txtResult.setText(builder.toString());
            } else {
            	txtResult.setText("FAILURE");
            }
        }
    }
}