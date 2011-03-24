package ca.ubc.magic.icd.android;

import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import ca.ubc.magic.icd.android.model.Bit;
import ca.ubc.magic.icd.android.services.AndroidCoffeeShopService;
import ca.ubc.magic.icd.web.json.JsonItem;

/**
 * Presents a View displaying information on a specified bit. This activity
 * looks for a "bit_id" extra in its Intent to specify which bit to pull data for.
 * @author Stephen Kidson
 * @author Jeffrey Payan
 */
public class BitScreen extends Activity {
	private AndroidCoffeeShopService magicService;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bit);
        magicService = AndroidCoffeeShopService.getInstance(BitScreen.this);
        
        int bit_id = this.getIntent().getExtras().getInt("bit_id");
        JsonItem bitInfo = null;
        try {
	        bitInfo = magicService.showBit(bit_id).getAsJsonItem("bit");
        } catch (Exception e) {
        	Toast.makeText(HomeScreen.getContext(), "Invalid QR code", Toast.LENGTH_SHORT);
        	finish();
        }
        final Bit bit = updateBit(bitInfo);
        Button btnCheckin = (Button) findViewById(R.id.bit_btnCheckin);
    	btnCheckin.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
    				magicService.checkin(bit.getId());
    				Toast.makeText(BitScreen.this, "You are now checked into this bit", Toast.LENGTH_SHORT).show();
    			} catch (Exception e) {
    				Toast.makeText(BitScreen.this, "Unable to check into this bit", Toast.LENGTH_SHORT).show();
    				e.printStackTrace();
    			}
			}
    	});
    	
    	Button btnLink = (Button) findViewById(R.id.bit_btnLink);
    	btnLink.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
    				magicService.createLink(bit.getId());
    				Toast.makeText(BitScreen.this, "You are now linked into this bit", Toast.LENGTH_SHORT).show();
    			} catch (Exception e) {
    				Toast.makeText(BitScreen.this, "Unable to link to this bit", Toast.LENGTH_SHORT).show();
    				e.printStackTrace();
    			}
			}
    	});
    }
    
    /**
     * Updates the bit associated with this Activity.
     * @param bitInfo a JsonItem containing the bit's data.
     * @return the updated Bit.
     */
    private Bit updateBit(JsonItem bitInfo) {
		Bit bit = new Bit(bitInfo.getAsString(AndroidCoffeeShopService.NAME),
		bitInfo.getAsString(AndroidCoffeeShopService.DESCRIPTION),
		bitInfo.getAsString(AndroidCoffeeShopService.QR_IMAGE_URL),
		bitInfo.getAsInteger(AndroidCoffeeShopService.ID),
		bitInfo.getAsInteger(AndroidCoffeeShopService.BITS_TYPES_ID));
		updateFields(bit);
    	return bit;
    }
    
    /**
     * Writes the passed bit's values into this Activity's text and image views.
     * @param bit the bit whose info to display.
     */
    private void updateFields(Bit bit) {
    	((TextView)findViewById(R.id.bit_name)).setText(bit.getName());
        ((TextView)findViewById(R.id.bit_type)).setText(bit.getType());
        ((TextView)findViewById(R.id.bit_description)).setText(bit.getDescription());
    
        try {
        	((ImageView)findViewById(R.id.bit_qrCode)).setImageDrawable(AndroidCoffeeShopService.getImageFromURL(bit.getQrImage()));
        } catch (IOException e) {
        	try {
        		// If QR code could not be retrieved from server, use google charts api instead
        		((ImageView)findViewById(R.id.bit_qrCode)).setImageDrawable(AndroidCoffeeShopService.getGoogleQR(128, "MAGIC: " + bit.getId()));
        	} catch (IOException e2) {}
        }
    }
    
}