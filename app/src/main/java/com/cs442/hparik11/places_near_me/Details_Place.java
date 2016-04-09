package com.cs442.hparik11.places_near_me;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

import java.text.DecimalFormat;

public class Details_Place extends AppCompatActivity {

    private EditText Website;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_place);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        Place newPlace = (Place) intent.getSerializableExtra("SelectedPlace");
        //Log.d("Place_Details", String.valueOf(newPlace.getLongitude()) + " " + String.valueOf(newPlace.getLatitude()));



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        EditText dist1 = (EditText) findViewById(R.id.txtDist);
        EditText address = (EditText) findViewById(R.id.txtAddress);
        EditText contact = (EditText) findViewById(R.id.txtCont);
        TextView name = (TextView) findViewById(R.id.txtName);
        RatingBar rating = (RatingBar) findViewById(R.id.ratingBar);
        EditText website = (EditText) findViewById(R.id.txtWeb);

        LatLng origin = new LatLng(41.838598, -87.627383);
        LatLng destPosition = new LatLng(newPlace.getLatitude(), newPlace.getLongitude());
        String dist = CalculationByDistance(origin,destPosition);

        dist1.setText(dist + " miles");
        address.setText(newPlace.getVicinity());
        contact.setText(newPlace.getPhone());
        name.setText(newPlace.getName());
        rating.setRating(Float.valueOf(newPlace.getRating()));
        website.setText(newPlace.getWebsite());


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

}
