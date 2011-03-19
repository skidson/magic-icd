package ca.ubc.magic.icd.android;

import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import ca.ubc.magic.icd.android.model.User;
import ca.ubc.magic.icd.android.services.AndroidCoffeeShopService;
import ca.ubc.magic.icd.web.json.JsonItem;

public class UserScreen extends Activity {
	private AndroidCoffeeShopService magicService;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bit);
        magicService = AndroidCoffeeShopService.getInstance(UserScreen.this);
        
        ImageView imgPhoto = (ImageView)findViewById(R.id.user_photo);
        TextView txtUsername = (TextView)findViewById(R.id.user_username);
        TextView txtName = (TextView)findViewById(R.id.user_name);
        TextView txtDescription = (TextView)findViewById(R.id.user_description);
        
        // FIXME
        JsonItem userInfo = magicService.showUser(2).getAsJsonItem("user");
        User user = new User(userInfo.getAsString("name"), 
				userInfo.getAsString("username"), 
				userInfo.getAsString("description"),
				userInfo.getAsString("photo"),
				userInfo.getAsInteger("id"),
				userInfo.getAsInteger("experience"),
				userInfo.getAsInteger("points"));
        
        txtUsername.setText(user.getName());
        txtName.setText(user.getName());
        txtDescription.setText(user.getDescription());
        
        try {
        	imgPhoto.setImageDrawable(AndroidCoffeeShopService.getImageFromURL(user.getPhoto()));
        } catch (IOException ignore) { /* user default placeholder */ }
        
    }
    
}