package ca.ubc.magic.icd.android;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
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
	        for(Bit bit : magicService.showBitLinksOfUser())
	    		items.add(bit);
        } else if (this.getIntent().getExtras().getString("type").equals("friends")){
        	for(User user : magicService.showFriends())
        		items.add(user);
        	getListView().setOnItemClickListener(new OnItemClickListener() {
    			@Override
    			public void onItemClick(AdapterView<?> magicAdapter, View listItem, int index,
    					long rowID) {
    				Intent intent = new Intent(ListScreen.this, UserScreen.class);
    				intent.putExtra("user_id", (Integer)listItem.getTag());
    			}
            });
        }
        this.getListView().setBackgroundColor(Color.parseColor("#f76301"));
        setListAdapter(new MagicAdapter(this, R.layout.list_item, items));
    }
}
