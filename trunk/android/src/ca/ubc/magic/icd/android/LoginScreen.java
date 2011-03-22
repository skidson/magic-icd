package ca.ubc.magic.icd.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import ca.ubc.magic.icd.android.services.AndroidCoffeeShopService;

public class LoginScreen extends Activity {
	private static final String LOGIN_URL = "http://192.168.0.100:8010/web_app/mobile/android";
	private static final int TIMEOUT = 3000; // timeout duration in milliseconds
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // TODO determine if this user has credentials stored on this device,
        // if not, show Login Screen
        
        setContentView(R.layout.login);
        Button btnLogin = (Button)findViewById(R.id.login_btnLogin);
        btnLogin.setOnClickListener(new OnClickListener() {
        	public void onClick(View view) {
        		/*HttpParams httpParams = new BasicHttpParams();
        		HttpConnectionParams.setConnectionTimeout(httpParams, TIMEOUT);
        	    HttpClient httpClient = new DefaultHttpClient(httpParams);
        		
        		HttpGet request = new HttpGet(LOGIN_URL);
        		String username = ((EditText)findViewById(R.id.login_txtUsername)).getText().toString();
        		String password =  ((EditText)findViewById(R.id.login_txtPassword)).getText().toString();
        		request.getParams().setParameter("username", username);
        		request.getParams().setParameter("password", password);
        		Log.d("MAGIC", request.toString());
        		try {
	    			BufferedReader reader = new BufferedReader(
	    					new InputStreamReader(httpClient.execute(request).getEntity().getContent()));
	    			StringBuilder response = new StringBuilder();
	    			String line;
	    			while((line = reader.readLine()) != null)
	    				response.append(line);
	    			System.out.println(response.toString());
	    			Log.d("MAGIC", response.toString());
	    			if (response.toString().contains(username))
	    				AndroidCoffeeShopService.getInstance(LoginScreen.this); // initialize the service singleton (user prompted to authorize)
	    			else
	    				Toast.makeText(LoginScreen.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
//	        		startActivity(new Intent(LoginScreen.this, HomeScreen.class));
        		} catch (IOException e) {
        			Log.d("MAGIC", "IOEXCEPTION", e);
        			Toast.makeText(LoginScreen.this, "Error contacting server", Toast.LENGTH_SHORT).show();
        		}*/
        		new AndroidCoffeeShopService(LoginScreen.this); // initialize the service singleton (user prompted to authorize)
        		finish();
        	}
        });
    }
    
    
    
}