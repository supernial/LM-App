package lt.asinica.lm;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class Main extends Activity {
	private boolean menuEnabled = true;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    	
    	SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
    	// if not logged in
    	if(!prefs.contains("lmsecret") || prefs.getString("lmsecret", "").length() == 0 ) {
    		initLogin();
    	} else {//if logged in
    		initSearch();
    	}
    	
    	if(prefs.getBoolean("autoupdatecheck", true))
    		 Updater.getInstance().checkForNewVersion();
    }
    private void initSearch() {
    	
		Button myButton = (Button) findViewById(R.id.fetch);
    	myButton.setOnClickListener(this.btnListener);
    	
		// display button at bottom to open prefs
		Button oSettings = (Button) findViewById(R.id.open_settings);
		oSettings.setVisibility(View.VISIBLE);
		oSettings.setOnClickListener(new OnClickListener() { public void onClick(View v) {
			Intent i = new Intent(Main.this, Preferences.class);
			startActivity(i);
		}});
    }
    private void initLogin() {
    	menuEnabled = false;
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	if(menuEnabled) {
	        MenuInflater inflater = getMenuInflater();
	        inflater.inflate(R.menu.menu, menu);
    	}
    	return true;
    }   
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
        case R.id.menu_settings:
        	Intent settingsActivity = new Intent(getBaseContext(),
        			Preferences.class);
        	startActivity(settingsActivity);
            return true;
        case R.id.menu_update:
        	Updater.getInstance().forceCheck();
        	return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }    

    
    
    public void toast(CharSequence text) {
    	Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }
    
	/**
     * button event listener
     */
    private OnClickListener btnListener = new OnClickListener() {
        public void onClick(View v) {
        	//httpget.setHeader("Cookie", "login=OTI3NDQ6TXpFMVptVXdaamxtWTJZM1pHTTNaV1ZpTURsbVpXVTNNekJsTUdGalpUaz0%3D;");
				//toast(e.getMessage());
        	
        	EditText searchField = (EditText) findViewById(R.id.search);
        	InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        	imm.hideSoftInputFromWindow(searchField.getWindowToken(), 0);
        	
        	CheckBox searchInDescriptions = (CheckBox) Main.this.findViewById(R.id.search_in_descriptions);
        	//search_in_descriptions
        	Intent intent = new Intent(getBaseContext(), TorrentList.class);
        	intent.putExtra("searchInDescriptions", searchInDescriptions.isChecked());
        	intent.putExtra("query", searchField.getText().toString());
        	startActivity(intent);
        }
    };
    
}