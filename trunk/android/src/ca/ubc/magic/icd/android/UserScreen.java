package ca.ubc.magic.icd.android;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import ca.ubc.magic.icd.android.model.Bit;
import ca.ubc.magic.icd.android.model.User;
import ca.ubc.magic.icd.android.services.AndroidCoffeeShopService;
import ca.ubc.magic.icd.web.json.JsonItem;
import ca.ubc.magic.icd.web.model.MagicItem;

/**
 * Presents a View displaying information on a specified user. This activity
 * looks for a "user_id" extra in its Intent to specify which user to pull data for.
 * @author Stephen Kidson
 * @author Jeffrey Payan
 */
public class UserScreen extends Activity {
	private AndroidCoffeeShopService magicService;
	private int user_id;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user);
        magicService = AndroidCoffeeShopService.getInstance(UserScreen.this);
        user_id = (Integer)this.getIntent().getExtras().get("user_id");
        ImageView imgPhoto = (ImageView)findViewById(R.id.user_photo);
        TextView txtUsername = (TextView)findViewById(R.id.user_username);
        TextView txtName = (TextView)findViewById(R.id.user_name);
        TextView txtDescription = (TextView)findViewById(R.id.user_description);
        User protoUser = null;
        
        try {
        	JsonItem userInfo = magicService.showUser(user_id).getAsJsonItem("user");
        	protoUser = new User(userInfo.getAsString(AndroidCoffeeShopService.NAME),
        			userInfo.getAsString(AndroidCoffeeShopService.USERNAME),
        			userInfo.getAsString(AndroidCoffeeShopService.DESCRIPTION),
        			userInfo.getAsString(AndroidCoffeeShopService.PHOTO),
        			userInfo.getAsInteger(AndroidCoffeeShopService.ID),
        			userInfo.getAsInteger(AndroidCoffeeShopService.EXPERIENCE),
        			userInfo.getAsInteger(AndroidCoffeeShopService.POINTS));
        	txtUsername.setText(protoUser.getUsername());
            txtName.setText(protoUser.getName());
            txtDescription.setText(protoUser.getDescription());
        } catch (Exception e) {
        	HomeScreen.queueToast("Failed to load user information");
        	finish();
        }
        
        try {
        	imgPhoto.setImageDrawable(AndroidCoffeeShopService.getImageFromURL(protoUser.getPhoto()));
        } catch (Exception e) { /* use default portrait */}
        
        final User user = protoUser;
        Button btnBefriend = ((Button)findViewById(R.id.user_btnBefriend));
        btnBefriend.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					magicService.createFriend(user_id);
					Toast.makeText(UserScreen.this, user.getName() + " is now your friend!", Toast.LENGTH_SHORT).show();
				} catch (Exception e) {
					Toast.makeText(UserScreen.this, "Unable to add friend", Toast.LENGTH_SHORT).show();
				}
			}
        });
        
        ImageView header = ((ImageView)findViewById(R.id.coffeeshop_image));
        header.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(UserScreen.this, HomeScreen.class));
			}
        });
        showLinkedBits();
        
    }
    
    private void showLinkedBits() {
    	ListView listLinkedUsers = (ListView)findViewById(R.id.user_linkedBits);
    	List<MagicItem> linkedBits = new ArrayList<MagicItem>();
    	for (Bit bit : magicService.showBitLinksOfUser(user_id))
    		linkedBits.add(bit);
    	listLinkedUsers.setAdapter(new MagicAdapter(UserScreen.this, R.layout.list_item, linkedBits));
    	listLinkedUsers.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent = new Intent(UserScreen.this, BitScreen.class);
				intent.putExtra("bit_id", (Integer)arg1.getTag());
				startActivity(intent);
			}
    	});
    	((TextView)findViewById(R.id.user_lblLinkedBits)).setVisibility(TextView.VISIBLE);
    }
    
}