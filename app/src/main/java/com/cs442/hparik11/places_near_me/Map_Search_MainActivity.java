package com.cs442.hparik11.places_near_me;

import java.io.File;
import android.app.Activity;
import android.content.Intent;
import android.net.http.HttpResponseCache;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class Map_Search_MainActivity extends Activity {
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launcher);
		try {
			File httpCacheDir = new File(getCacheDir(), "http");
			long httpCacheSize = 10 * 1024 * 1024; //10MiB
			HttpResponseCache.install(httpCacheDir, httpCacheSize);
		} catch (Exception e) {
			System.out.print("HTTP response cache installation failed");
		}
		
	}

	/*
	 * call the poi selection screen
	 */
    public void selectPois(View v)
    {
    	Intent intent = new Intent(this, PoiSelectionActivity.class);
    	startActivity(intent);
    }

    /*
     * handles search of particular places like cafe, atm, hospital etc
     * uses google places api
     */
    public void searchPlaces(View v)
    {
    	Intent intent = new Intent(this, ShowPlacesActivity.class);
    	EditText searchType = (EditText) findViewById(R.id.search_type);
    	EditText searchRadius = (EditText) findViewById(R.id.search_radius);
    	String typeText = searchType.getText().toString();
    	String radiusText = searchRadius.getText().toString();
    	Bundle extras = new Bundle();
    	extras.putString("EXTRA_PLACE",typeText);
    	extras.putString("EXTRA_RADIUS",radiusText);
    	intent.putExtras(extras);
    	startActivity(intent);
    }
    
    public void trackUser(View v)
    {
    	//tracking user and calory calculation
    	Intent intent = new Intent(this, TrackingUser.class);
    	startActivity(intent);
    	
    }


}

