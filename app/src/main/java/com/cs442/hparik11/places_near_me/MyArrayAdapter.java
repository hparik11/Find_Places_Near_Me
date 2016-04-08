package com.cs442.hparik11.places_near_me;

/**
 * Created by harsh on 4/5/16.
 */
import android.content.Context;
import android.location.Address;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;


public class MyArrayAdapter extends ArrayAdapter<Address> {
    Context mycontext;
    public MyArrayAdapter(Context context, int textViewResourceId,
                          List<Address> objects) {
        super(context, textViewResourceId, objects);
        // TODO Auto-generated constructor stub
        mycontext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        int maxAddressLineIndex = getItem(position).getMaxAddressLineIndex();
        GeoSearchResult searchresult = new GeoSearchResult(getItem(position));

        String addressLine = searchresult.getAddress();
        addressLine += "\n";
        addressLine += searchresult.getLatLng().toString();

        TextView rowAddress = new TextView(mycontext);
        rowAddress.setText(addressLine);

        return rowAddress;

    }



}
