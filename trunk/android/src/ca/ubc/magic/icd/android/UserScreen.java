package ca.ubc.magic.icd.android;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import ca.ubc.magic.icd.android.services.AndroidCoffeeShopService;
import ca.ubc.magic.icd.web.json.JsonItem;

/**
 * Presents a View displaying information on a specified user. This activity
 * looks for a "user_id" extra in its Intent to specify which user to pull data for.
 * @author Stephen Kidson
 * @author Jeffrey Payan
 */
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
        	userInfo = magicService.showUser(3/*(Integer)this.getIntent().getExtras().get("user_id")*/).getAsJsonItem("user");
        	txtUsername.setText(userInfo.getAsString(AndroidCoffeeShopService.USERNAME));
            txtName.setText(userInfo.getAsString(AndroidCoffeeShopService.NAME));
            txtDescription.setText(userInfo.getAsString(AndroidCoffeeShopService.DESCRIPTION));
        } catch (Exception e) {
        	Toast.makeText(HomeScreen.getContext(), "Failed to load user information", Toast.LENGTH_SHORT).show();
        	finish();
        }
        
        try {
        	imgPhoto.setImageDrawable(AndroidCoffeeShopService.getImageFromURL(userInfo.getAsString(AndroidCoffeeShopService.PHOTO)));
        } catch (Exception e) { /* use default portrait */}
    }
    
}