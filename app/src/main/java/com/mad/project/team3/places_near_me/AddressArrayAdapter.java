package com.mad.project.team3.places_near_me;

import android.content.Context;
import android.location.Address;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by fshen4 on 16-4-2.
 */
public class AddressArrayAdapter extends ArrayAdapter<Address> {
    Context mycontext;
    public AddressArrayAdapter(Context context, int textViewResourceId,
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
