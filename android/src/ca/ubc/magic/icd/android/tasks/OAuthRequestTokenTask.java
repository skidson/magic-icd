package ca.ubc.magic.icd.android.tasks;

import ca.ubc.magic.icd.android.services.AndroidCoffeeShopService;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.OAuthProvider;
import oauth.signpost.exception.OAuthException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;

public class OAuthRequestTokenTask extends AsyncTask {
	private OAuthProvider provider;
	private OAuthConsumer consumer;
	private Context context;
	
	public OAuthRequestTokenTask(Context context, OAuthProvider provider, OAuthConsumer consumer) {
		this.provider = provider;
		this.consumer = consumer;
		this.context = context;
	}
	
	@Override
	protected Object doInBackground(Object... params) {
		String url = "";
		try {
			url = provider.retrieveRequestToken(consumer, AndroidCoffeeShopService.CALLBACK_URI);
		} catch (OAuthException e) {
			e.printStackTrace();
		}
		Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url))
			.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | 
					Intent.FLAG_ACTIVITY_NO_HISTORY | 
					Intent.FLAG_FROM_BACKGROUND);
		context.startActivity(intent);
		return null;
	}
	
}