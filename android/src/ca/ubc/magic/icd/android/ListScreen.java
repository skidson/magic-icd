package ca.ubc.magic.icd.android;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import ca.ubc.magic.icd.android.model.Bit;
import ca.ubc.magic.icd.android.model.User;
import ca.ubc.magic.icd.android.services.AndroidCoffeeShopService;
import ca.ubc.magic.icd.web.model.MagicItem;

public class ListScreen extends ListActivity {
	private List<MagicItem> items;
	private AndroidCoffeeShopService magicService;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.magicService = AndroidCoffeeShopService.getInstance(ListScreen.this);
        items = new ArrayList<MagicItem>();
        if (this.getIntent().getExtras().getString("type").equals("bits")) {
        	System.out.println("Showing bit list"); // debug
	        for(Bit bit : magicService.showBitLinksOfUser())
	    		items.add(bit);
	        /*getListView().setOnItemClickListener(new OnItemClickListener() {
    			@Override
    			public void onItemClick(AdapterView<?> magicAdapter, View listItem, int index,
    					long rowID) {
    				Intent intent = new Intent(ListScreen.this, BitScreen.class);
    				intent.putExtra("bit_id", (Integer)listItem.getTag());
    			}
            });*/
        } else if (this.getIntent().getExtras().getString("type").equals("friends")){
        	System.out.println("Showing friend list"); // debug
        	for(User user : magicService.showFriends())
        		items.add(user);
        	/*getListView().setOnItemClickListener(new OnItemClickListener() {
    			@Override
    			public void onItemClick(AdapterView<?> magicAdapter, View listItem, int index,
    					long rowID) {
    				Intent intent = new Intent(ListScreen.this, UserScreen.class);
    				intent.putExtra("user_id", (Integer)listItem.getTag());
    			}
            });*/
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
