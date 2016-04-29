package com.mad.project.team3.places_near_me;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
<<<<<<< HEAD
=======
import android.widget.ImageView;
>>>>>>> 03d696b3a02bf80cc0927f6559280ec6276a0d81
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.CircleBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


public class PlaceArrayAdapter extends BaseAdapter {
    private Context mycontext;
    private LayoutInflater inflater;
    private final ArrayList<Place> items ;
    static class ViewHolder {
        public TextView tvContent;
        public ImageButton btCall;
        public ImageButton btDirection;
        public ImageButton btInfo;
        ImageView image;
    }

    ArrayList<String> images_urls = new ArrayList<String>();
    String[] imagesUrls;
    private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();

    private DisplayImageOptions options;

    public PlaceArrayAdapter(Context context, ArrayList<Place> objects) {
        // TODO Auto-generated constructor stub
        mycontext = context;
        inflater = LayoutInflater.from(context);
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.ic_stub)
                .showImageForEmptyUri(R.drawable.ic_empty)
                .showImageOnFail(R.drawable.ic_error)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .displayer(new CircleBitmapDisplayer(Color.WHITE, 5))
                .build();
        items = objects;

        //https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=CnRtAAAATLZNl354RwP_9UKbQ_5Psy40texXePv4oAlgP4qNEkdIrkyse7rPXYGd9D_Uj1rVsQdWT4oRz4QrYAJNpFX7rzqqMlZw2h2E2y5IKMUZ7ouD_SlcHxYq1yL4KbKUv3qtWgTK0A6QbGh87GB3sscrHRIQiG2RrmU_jF4tENr9wGS_YxoUSSDrYjWmrNfeEHSGSc3FyhNLlBU&key=YOUR_API_KEY
        for (int i = 0; i<items.size(); i++) {
            if (items.get(i).getPhotoreference() != "" && items.get(i).getPhotoreference() != null) {
                images_urls.add("https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=" +
                        items.get(i).getPhotoreference() + "&key=" +  context.getString(R.string.google_maps_key));
            }
            else {
                images_urls.add("drawable://" + R.drawable.ic_launcher);
            }
        }
        imagesUrls = images_urls.toArray(new String[items.size()]);
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
        final ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.listview_item, null);
            holder = new ViewHolder();
            holder.tvContent = (TextView) convertView.findViewById(R.id.tvContent);
            holder.btCall = (ImageButton) convertView.findViewById(R.id.btCall);
            holder.btDirection = (ImageButton) convertView.findViewById(R.id.btDirection);
            holder.btInfo = (ImageButton) convertView.findViewById(R.id.btInfo);
            holder.image = (ImageView) convertView.findViewById(R.id.image);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ImageLoader.getInstance().displayImage(imagesUrls[position], holder.image, options, animateFirstListener);

        final Place itemInfo = items.get(position);
        Log.d("@@Latitude", String.valueOf(itemInfo.getLatitude()));
        Log.d("@@Longitude", String.valueOf(itemInfo.getLongitude()));

        LatLng origin = new LatLng(41.838598, -87.627383);
        LatLng destPosition = new LatLng(itemInfo.getLatitude(), itemInfo.getLongitude());
        String dist = CalculationByDistance(origin,destPosition);

        holder.tvContent.setText(itemInfo.getName() + "\n" + dist + " miles");

        holder.btInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent infoIntent = new Intent(mycontext, Details_Place.class);
                Bundle mBundle = new Bundle();
                mBundle.putSerializable("SelectedPlace", itemInfo);
                infoIntent.putExtras(mBundle);
                infoIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                v.getContext().startActivity(infoIntent);

            }
        });



        holder.btCall.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (itemInfo.getPhone() != "") {
                    Intent phoneIntent = new Intent("android.intent.action.CALL",
                            Uri.parse("tel:" + itemInfo.getPhone()));
                    phoneIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    v.getContext().startActivity(phoneIntent);
                }
                else
                    Toast.makeText(mycontext, "No phone numbers", Toast.LENGTH_SHORT).show();
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
    public String CalculationByDistance(LatLng StartP, LatLng EndP) {
        int Radius = 6371;// radius of earth in Km
        double lat1 = StartP.latitude;
        double lat2 = EndP.latitude;
        double lon1 = StartP.longitude;
        double lon2 = EndP.longitude;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2)
                * Math.sin(dLon / 2);
        double c = 2 * Math.asin(Math.sqrt(a));
        double valueResult = Radius * c;
        double km = valueResult / 1;
        DecimalFormat newFormat = new DecimalFormat("####");
        int kmInDec = Integer.valueOf(newFormat.format(km));
        double meter = valueResult % 1000;
        int meterInDec = Integer.valueOf(newFormat.format(meter));
        Log.i("Radius Value", "" + valueResult + "   KM  " + kmInDec
                + " Meter   " + meterInDec);

        String mileValue = String.format("%.2f",km*1.60934);
        return mileValue;
    }

    private static class AnimateFirstDisplayListener extends SimpleImageLoadingListener {

        static final List<String> displayedImages = Collections.synchronizedList(new LinkedList<String>());

        @Override
        public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
            if (loadedImage != null) {
                ImageView imageView = (ImageView) view;
                boolean firstDisplay = !displayedImages.contains(imageUri);
                if (firstDisplay) {
                    FadeInBitmapDisplayer.animate(imageView, 500);
                    displayedImages.add(imageUri);
                }
            }
        }
    }

}
