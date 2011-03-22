package ca.ubc.magic.icd.android;

import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import ca.ubc.magic.icd.android.services.AndroidCoffeeShopService;
import ca.ubc.magic.icd.web.json.JsonItem;

public class UserScreen extends Activity {
	private AndroidCoffeeShopService magicService;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user);
        magicService = AndroidCoffeeShopService.getInstance(UserScreen.this);
        
        ImageView imgPhoto = (ImageView)findViewById(R.id.user_photo);
        TextView txtUsername = (TextView)findViewById(R.id.user_username);
        TextView txtName = (TextView)findViewById(R.id.user_name);
        TextView txtDescription = (TextView)findViewById(R.id.user_description);
        JsonItem userInfo = null;
        
        try {
        	userInfo = magicService.showUser((Integer)this.getIntent().getExtras().get("user_id")).getAsJsonItem("user");
        	txtUsername.setText(userInfo.getAsString(AndroidCoffeeShopService.USERNAME));
            txtName.setText(userInfo.getAsString(AndroidCoffeeShopService.NAME));
            txtDescription.setText(userInfo.getAsString(AndroidCoffeeShopService.DESCRIPTION));
        } catch (Exception e) {
        	Toast.makeText(UserScreen.this, "Failed to load user information", Toast.LENGTH_SHORT).show();
        	finish();
        }
        
        try {
        	imgPhoto.setImageDrawable(AndroidCoffeeShopService.getImageFromURL(userInfo.getAsString(AndroidCoffeeShopService.PHOTO)));
        } catch (IOException e) { /* use default portrait */}
    }
    
}