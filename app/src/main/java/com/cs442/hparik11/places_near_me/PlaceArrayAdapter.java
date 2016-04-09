package com.cs442.hparik11.places_near_me;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.ArrayList;
import android.graphics.Color;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import org.w3c.dom.Document;


public class PlaceArrayAdapter extends BaseAdapter {
    private Context mycontext;
    private LayoutInflater inflater;
    private final ArrayList<Place> items ;
   // private AdapterCallback mAdapterCallback;
    public PlaceArrayAdapter(Context context, ArrayList<Place> objects) {
        // TODO Auto-generated constructor stub
        mycontext = context;
        inflater = LayoutInflater.from(context);
        items = objects;
    }
    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Place getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub


        ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.listview_item, null);
            holder = new ViewHolder();
            holder.tvContent = (TextView) convertView.findViewById(R.id.tvContent);
            holder.btCall = (ImageButton) convertView.findViewById(R.id.btCall);
            holder.btDirection = (ImageButton) convertView.findViewById(R.id.btDirection);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        final Place itemInfo = items.get(position);
        holder.tvContent.setText(itemInfo.getName() + "\n" + itemInfo.getVicinity());


        /*
        String addressLine = getItem(position).getName();
        addressLine += "\n";
        addressLine += getItem(position).getVicinity();
        addressLine += "\n";
        addressLine += getItem(position).getPhone();
        addressLine += "\n";
        addressLine += getItem(position).getLatitude() + "," + getItem(position).getLongitude();

        TextView rowAddress = new TextView(mycontext);

        if(items.get(position) != null )
        {
            rowAddress.setTextColor(Color.WHITE);
            rowAddress.setText(addressLine);
            rowAddress.setBackgroundColor(Color.RED);
            int color = Color.argb( 200, 255, 64, 64 );
            rowAddress.setBackgroundColor( color );

        }
        return rowAddress;
        */

        holder.btCall.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent phoneIntent = new Intent("android.intent.action.CALL",
                    Uri.parse("tel:" + itemInfo.getPhone()));
                phoneIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                v.getContext().startActivity(phoneIntent);
            }
        });
        holder.btDirection.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // assuming string and if you want to get the value on click of list item
                // do what you intend to do on click of listview row
                //Log.d("Address", String.valueOf(selectedAddress.getLongitude()) + String.valueOf(selectedAddress.getLatitude()));
                Intent mapIntent = new Intent(mycontext, GooglePlacesActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putSerializable("SelectedPlace", itemInfo);
                mapIntent.putExtras(mBundle);
                mapIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                v.getContext().startActivity(mapIntent);
                //SearchActivity thisactivity = (SearchActivity)mycontext;
                //thisactivity.setResult(1, intent);
                //thisactivity.finish();
            }
        });

        return convertView;
    }

    public class ViewHolder {
        public TextView tvContent;
        public ImageButton btCall;
        public ImageButton btDirection;
    }

}
