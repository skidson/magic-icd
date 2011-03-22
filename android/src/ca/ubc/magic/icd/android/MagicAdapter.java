package ca.ubc.magic.icd.android;

import java.io.IOException;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import ca.ubc.magic.icd.android.services.AndroidCoffeeShopService;
import ca.ubc.magic.icd.web.model.MagicItem;

public class MagicAdapter extends BaseAdapter {
	private Context context;
	private List<MagicItem> itemList;
	private int rowResID;
	
	public MagicAdapter(Context context, int rowResID, List<MagicItem> itemList) {
		this.context = context;
		this.rowResID = rowResID;
		this.itemList = itemList;
	}
	
	public int getCount() {
		return itemList.size();
	}
	
	public Object getItem(int position) {
		return itemList.get(position);
	}
	
	public long getItemId(int position) {
		return position;
	}
	
	public View getView(int position, View convertView, ViewGroup parent) {
		MagicItem item = itemList.get(position);
		View view = View.inflate(context, rowResID, null);
		
		TextView txtName = (TextView)view.findViewById(R.id.list_name);
		TextView txtDescription = (TextView)view.findViewById(R.id.list_description);
		ImageView imgPhoto = (ImageView)view.findViewById(R.id.list_image);
		
		view.setTag(item.getId());
		
		if(txtName != null) 
			txtName.setText(item.getName());
		if(txtDescription != null)
			txtDescription.setText(item.getDescription());
		if (imgPhoto != null) {
			try {
				imgPhoto.setImageDrawable(AndroidCoffeeShopService.getImageFromURL(item.getImage()));
			} catch (IOException ignore) {}
		}
		
		return view;
	}
	
}
