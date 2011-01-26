package ca.ubc.magic.icd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class EncodeScreen extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.encoder);
       
        Spinner spinner = (Spinner) findViewById(R.id.encoder_typeSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
        		this, R.array.encoder_typeArray, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
    
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
    	
    }
    
    public class OnSpinnerItemSelectedListener implements OnItemSelectedListener {
    	
    	public void onItemSelected(AdapterView<?> parent,
    			View view, int pos, long id) {
    		// TODO do stuff here for selected item
    	}
    	
    	public void onNothingSelected(AdapterView<?> parent) {
    		// do nothing
    	}
    	
    }
}