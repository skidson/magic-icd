package ca.ubc.magic.icd.android.tasks;

import oauth.signpost.OAuth;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.OAuthProvider;
import oauth.signpost.exception.OAuthException;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.AsyncTask;
import android.preference.PreferenceManager;

public class RetrieveAccessTokenTask extends AsyncTask {
	private OAuthProvider provider;
	private OAuthConsumer consumer;
	private Context context;
	
	public RetrieveAccessTokenTask(Context context, OAuthProvider provider, OAuthConsumer consumer) {
		this.provider = provider;
		this.consumer = consumer;
		this.context = context;
	}
	
	@Override
	public Object doInBackground(Object...params) {
		final Uri uri = (Uri)params[0];
		final String oauth_verifier = uri.getQueryParameter(OAuth.OAUTH_VERIFIER);
		
		try {
			provider.retrieveAccessToken(consumer, oauth_verifier);
			SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
			final Editor editor = preferences.edit();
			editor.putString(OAuth.OAUTH_TOKEN, consumer.getToken());
			editor.putString(OAuth.OAUTH_TOKEN_SECRET, consumer.getTokenSecret());
			editor.commit();
			
			String token = preferences.getString(OAuth.OAUTH_TOKEN, "");
			String secret = preferences.getString(OAuth.OAUTH_TOKEN_SECRET, "");
			consumer.setTokenWithSecret(token, secret);
		} catch (OAuthException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}