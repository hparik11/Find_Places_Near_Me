package com.cs442.hparik11.places_near_me;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ScaleDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.io.IOException;
import java.util.List;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ScaleDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends Activity {
    //private ArrayList<String> todoItems = new ArrayList<String>();
    private Button bSearch;
    private ListView myListView;
    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_search);

        //final ArrayList<String> todoItems = new ArrayList<String>();
        //final ArrayAdapter<String> aa = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,todoItems);
        myListView = (ListView)findViewById(R.id.listView1);
        //myListView.setAdapter(aa);
        Drawable drawable = getResources().getDrawable(R.drawable.ic_menu_search);
        drawable.setBounds(0, 0, (int)(drawable.getIntrinsicWidth()*0.5),
                (int)(drawable.getIntrinsicHeight()*0.5));
        ScaleDrawable sd = new ScaleDrawable(drawable, 0, 1, 1);
        bSearch = (Button)findViewById(R.id.bSearch);
        bSearch.setCompoundDrawables(sd.getDrawable(), null, null, null); //set drawableLeft for example
        bSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //new ShowAddresses().execute(aa);
                getAddresses();
            }
        });

    }

    private class ShowAddresses extends AsyncTask<ArrayAdapter<String>, ArrayAdapter<String>, ArrayAdapter<String> > {

        @Override
        protected ArrayAdapter<String>  doInBackground(ArrayAdapter<String>... params) {
            getAddresses();
            return params[0];
        }

        @Override
        protected void onPostExecute(ArrayAdapter<String> result) {
            result.notifyDataSetChanged();
        }
    }

    private void getAddresses () {
        EditText tLocation = (EditText)findViewById(R.id.tAddress);
        String sLocation = tLocation.getText().toString();
        List<Address> addressList = null;

        if (sLocation != null || !sLocation.equals("")) {
            Geocoder geocoder = new Geocoder(this);
            try {
                addressList = geocoder.getFromLocationName(sLocation, 10);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (addressList != null) {
                /*
                todoItems.clear();
                for (int i = 0; i < addressList.size(); i++) {
                    todoItems.add("AdminArea:" + addressList.get(i).getAdminArea() +
                            "Latitude:" + addressList.get(i).getLatitude() +
                            "Longitude:" + addressList.get(i).getLongitude() +
                            "PostalCode:" + addressList.get(i).getPostalCode() +
                            "Phone:" + addressList.get(i).getPhone());
                    Log.d(String.valueOf(addressList.get(i).getAdminArea()), "AdminArea");
                    Log.d(String.valueOf(addressList.get(i).getLatitude()), "Latitude");
                    Log.d(String.valueOf(addressList.get(i).getLongitude()), "Longitude");
                    Log.d(String.valueOf(addressList.get(i).getPhone()), "Phone");
                    Log.d(String.valueOf(addressList.get(i).getPostalCode()), "PostalCode");
                */
                MyArrayAdapter adapter = new MyArrayAdapter(this, android.R.layout.simple_list_item_1, addressList);
                myListView.setAdapter(adapter);
            }
        }
    }

}
