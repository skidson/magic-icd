package ca.ubc.magic.icd.android;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import ca.ubc.magic.icd.android.services.AndroidCoffeeShopService;
import ca.ubc.magic.icd.web.json.JsonItem;

public class BitScreen extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bit);
        
        ImageView imgQRCode = (ImageView)findViewById(R.id.bit_qrCode);
        TextView txtName = (TextView)findViewById(R.id.bit_name);
        TextView txtType = (TextView)findViewById(R.id.bit_type);
        TextView txtDescription = (TextView)findViewById(R.id.bit_description);
//        int bit_id = Integer.parseInt(getIntent().getExtras().getString("bit_id"));
        
        JsonItem bitInfo = AndroidCoffeeShopService.showBit(BitScreen.this, 1);
        
        txtName.setText(bitInfo.getAsJsonItem("bit").getAsString(AndroidCoffeeShopService.NAME));
        txtType.setText(bitInfo.getAsJsonItem("bit").getAsString(AndroidCoffeeShopService.BITS_TYPES_ID));
        txtDescription.setText(bitInfo.getAsJsonItem("bit").getAsString(AndroidCoffeeShopService.DESCRIPTION));
//        imgQRCode.setImageURI(Uri.parse(bitInfo.getAsJsonItem("bit").getAsString(AndroidCoffeeShopService.QR_IMAGE_URL)));
        
//        JsonItem bitInfo = magicService.showBit(Integer.parseInt(getIntent()
//        		.getExtras().getString("bitQR")));
        
		/*try {
			imgQRCode.setImageDrawable(item.getImageDrawable());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
        
    }
    
    @Override
	public void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		AndroidCoffeeShopService.verify(BitScreen.this, intent.getData());
	}
    
}