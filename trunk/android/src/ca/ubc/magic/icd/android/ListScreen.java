package ca.ubc.magic.icd.android;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import ca.ubc.magic.icd.android.model.Bit;
import ca.ubc.magic.icd.android.model.User;
import ca.ubc.magic.icd.android.services.AndroidCoffeeShopService;
import ca.ubc.magic.icd.web.model.MagicItem;

/**
 * Presents a ListView of the current user's bits or friends. Looks for a "type" extra
 * in its Intent, set to either "bits" or "friends", to determine which.
 * @author Stephen Kidson
 * @see MagicAdapter
 */
public class ListScreen extends ListActivity {
	private List<MagicItem> items;
	private AndroidCoffeeShopService magicService;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.magicService = AndroidCoffeeShopService.getInstance(ListScreen.this);
        items = new ArrayList<MagicItem>();
        if (this.getIntent().getExtras().getString("type").equals("bits")) {
	        for(Bit bit : magicService.showBitLinksOfUser(0))
	    		items.add(bit);
        } else if (this.getIntent().getExtras().getString("type").equals("friends")){
        	try {
        		for(User user : magicService.showFriends())
        			items.add(user);
        	} catch (Exception e) {
        		HomeScreen.queueToast("Error loading friends list");
        		finish();
        	}
        }
        this.getListView().setBackgroundColor(Color.parseColor("#f76301"));
        setListAdapter(new MagicAdapter(this, R.layout.list_item, items));
    }

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		Intent intent = null;
		if (this.getIntent().getExtras().getString("type").equals("bits")) {
			intent = new Intent(ListScreen.this, BitScreen.class);
			intent.putExtra("bit_id", (Integer)v.getTag());
		} else if (this.getIntent().getExtras().getString("type").equals("friends")) {
			intent = new Intent(ListScreen.this, UserScreen.class);
			intent.putExtra("user_id", (Integer)v.getTag());
		}
		if (intent != null)
			startActivity(intent);
	}
    
    
}
