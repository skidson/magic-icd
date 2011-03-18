package ca.ubc.magic.icd.android;

import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import ca.ubc.magic.icd.android.services.AndroidCoffeeShopService;
import ca.ubc.magic.icd.web.json.JsonItem;

public class BitScreen extends Activity {
	private AndroidCoffeeShopService magicService;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bit);
        magicService = AndroidCoffeeShopService.getInstance(BitScreen.this);
        
        ImageView imgQRCode = (ImageView)findViewById(R.id.bit_qrCode);
        TextView txtName = (TextView)findViewById(R.id.bit_name);
        TextView txtType = (TextView)findViewById(R.id.bit_type);
        TextView txtDescription = (TextView)findViewById(R.id.bit_description);
        
        JsonItem bitInfo = magicService.showBit(1).getAsJsonItem("bit");
        Bit bit = new Bit(bitInfo.getAsString(AndroidCoffeeShopService.NAME),
        		bitInfo.getAsString(AndroidCoffeeShopService.DESCRIPTION),
        		bitInfo.getAsString(AndroidCoffeeShopService.QR_IMAGE_URL),
        		bitInfo.getAsInteger(AndroidCoffeeShopService.ID),
        		bitInfo.getAsInteger(AndroidCoffeeShopService.BITS_TYPES_ID));
        
        txtName.setText(bit.getName());
        txtType.setText(bit.getType());
        txtDescription.setText(bit.getDescription());
        
        try {
        	imgQRCode.setImageDrawable(AndroidCoffeeShopService.getImageFromURL(bit.getQrImage()));
        } catch (IOException e) {
        	// TODO no qr image available, substitute with placeholder
        }
        
    }
    
}