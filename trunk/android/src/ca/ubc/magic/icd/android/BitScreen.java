package ca.ubc.magic.icd.android;

import oauth.signpost.exception.OAuthException;
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
        
        magicService = new AndroidCoffeeShopService();
        
//        ImageView imgQRCode = (ImageView)findViewById(R.id.bit_qrCode);
        TextView txtName = (TextView)findViewById(R.id.bit_name);
        TextView txtType = (TextView)findViewById(R.id.bit_type);
        TextView txtDescription = (TextView)findViewById(R.id.bit_description);
        
        /*try {
			magicService.authorize(BitScreen.this);
		} catch (OAuthException e) {
			e.printStackTrace();
		}*/
        
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
    
}