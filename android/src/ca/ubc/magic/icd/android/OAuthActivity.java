package ca.ubc.magic.icd.android;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.OAuthProvider;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthProvider;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import ca.ubc.magic.icd.android.tasks.OAuthRequestTokenTask;
import ca.ubc.magic.icd.android.tasks.RetrieveAccessTokenTask;

public class OAuthActivity extends Activity {
	public static final String OAUTH_VERIFIER = "oauth_verifier";
	public static final String CALLBACK_URI = "x-oauthflow://callback";
	private static final String magicURLPattern = "http://kimberly.magic.ubc.ca:8080/1/";
	private static final OAuthConsumer consumer = new CommonsHttpOAuthConsumer(
			"766bec602a9fe2795b43501ea4f9a9c9", 
			"sad234fdsf243f4ff3f343kj43hj43g4hgf423f");
	private static final OAuthProvider provider = new CommonsHttpOAuthProvider(
			magicURLPattern + "request_token",
			magicURLPattern + "access_token", 
			magicURLPattern + "authorize");

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		new OAuthRequestTokenTask(this, provider, consumer).execute();
	}
	
	@Override
	public void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		final Uri uri = intent.getData();
		if (uri != null && uri.getScheme().equals(CALLBACK_URI)) {
			new RetrieveAccessTokenTask(this, provider, consumer).execute(uri);
			finish();
		}
	}
	
}