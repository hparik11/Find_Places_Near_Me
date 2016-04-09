package com.cs442.hparik11.places_near_me;

import android.Manifest;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ScaleDrawable;
import android.location.Address;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import org.w3c.dom.Document;

import java.util.ArrayList;
import java.util.List;


public class GooglePlacesActivity extends FragmentActivity implements OnMapReadyCallback{

    private GoogleMap mMap;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private GMapV2Direction md;
    private EditText address;
    private LatLng origin;
    private Button bSearch,bView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        setUpMapIfNeeded();
        // Get the intent, verify the action and get the query
        Intent intent = getIntent();
        Intent intent1 = getIntent();
        final String category = intent1.getStringExtra("Category");
        /*Log.d("@ CAtegory ~", category);*/



        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            //doMySearch(query);
        }

        Drawable drawable = getResources().getDrawable(R.drawable.ic_menu_search);
        drawable.setBounds(0, 0, (int) (drawable.getIntrinsicWidth() * 0.5),
                (int) (drawable.getIntrinsicHeight() * 0.5));
        ScaleDrawable sd = new ScaleDrawable(drawable, 0, 1, 1);
        bSearch = (Button)findViewById(R.id.Bsearch);
        bSearch.setCompoundDrawables(sd.getDrawable(), null, null, null);


        Drawable drawable1 = getResources().getDrawable(R.drawable.street_view);
        drawable1.setBounds(0, 0, (int)(drawable1.getIntrinsicWidth()*0.5),
                (int)(drawable1.getIntrinsicHeight()*0.5));
        ScaleDrawable sd1 = new ScaleDrawable(drawable1, 0, 1, 1);
        bView = (Button)findViewById(R.id.Btype);
        bView.setCompoundDrawables(sd1.getDrawable(), null, null, null);

        address = (EditText)findViewById(R.id.TFaddress);
        address.setText(category);
        address.setCompoundDrawables(sd.getDrawable(), null, null, null); //set drawableLeft for example
        address.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return onSearchRequesting(category);
            }
        });

        origin = new LatLng(41.838598, -87.627383);


    }


    private void setUpMapIfNeeded() {
        return;
    }




    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(true);

        moveMap(origin);
        addMarker(origin, "Current Location", "Life Science");
        mMap.setMyLocationEnabled(true);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

            return;
        }
        mMap.setMyLocationEnabled(true);
        Intent intent = getIntent();
        Place newPlace = (Place) intent.getSerializableExtra("SelectedPlace");
        if (newPlace != null) {
            Log.d("Place", String.valueOf(newPlace.getLongitude()) + " " + String.valueOf(newPlace.getLatitude()));
            // see more on Marker: https://developers.google.com/maps/documentation/android/reference/com/google/android/gms/maps/model/Marker
            //mMap.clear();
            mMap.addMarker(new MarkerOptions()
                    .title(newPlace.getName())
                    .position(
                            new LatLng(newPlace.getLatitude(), newPlace.getLongitude()))
                    .icon(BitmapDescriptorFactory
                            .fromResource(R.drawable.pin))
                    .snippet(newPlace.getVicinity()));
            moveMap(new LatLng(newPlace.getLatitude(), newPlace.getLongitude()));
            Document doc = null;
            LatLng destPosition = new LatLng(newPlace.getLatitude(), newPlace.getLongitude());
            md = new GMapV2Direction(origin, destPosition, GMapV2Direction.MODE_WALKING);
            md.execute();
            try {
                doc = md.get();

                ArrayList<LatLng> directionPoint = md.getDirection(doc);
                PolylineOptions rectLine = new PolylineOptions().width(8).color(Color.BLUE);

                for (int i = 0; i < directionPoint.size(); i++) {
                    rectLine.add(directionPoint.get(i));
                }
                Polyline polylin = mMap.addPolyline(rectLine);
            } catch (Exception e) {
                //nothing to do for now
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Maps Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.cs442.hparik11.places_near_me/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Maps Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.cs442.hparik11.places_near_me/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }


    private void moveMap(LatLng place) {
        CameraPosition cameraPosition =
                new CameraPosition.Builder()
                        .target(place)
                        .zoom(13)
                        .build();

        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    private void addMarker(LatLng place, String title, String snippet) {
        BitmapDescriptor icon =
                com.google.android.gms.maps.model.BitmapDescriptorFactory.fromResource(R.mipmap.ic_map_marker);

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(place)
                .title(title)
                .snippet(snippet)
                .icon(icon);

        mMap.addMarker(markerOptions);
    }
    public boolean onSearchRequesting(String category) {
        //tracking user and calory calculation
        Intent intent = new Intent(this, SearchActivity.class);
        intent.putExtra("Category", category);
        startActivity(intent);
        setResult(1, intent);
        return true;
    }

    @Override
    public void onActivityResult(int reqCode, int resCode, android.content.Intent data) {
        super.onActivityResult(reqCode, resCode, data);
        switch(reqCode) {
            case (1) : {
                if (resCode == 1) {
                    //ss newAddress = (Address)data.getParcelableExtra("SelectedAddress");
                    Place newPlace = (Place) data.getSerializableExtra("SelectedPlace");
                    Log.d("Place", String.valueOf(newPlace.getLongitude()) + " " + String.valueOf(newPlace.getLatitude()));
                    // see more on Marker: https://developers.google.com/maps/documentation/android/reference/com/google/android/gms/maps/model/Marker
                    mMap.clear();
                    mMap.addMarker(new MarkerOptions()
                            .title(newPlace.getName())
                            .position(
                                    new LatLng(newPlace.getLatitude(), newPlace.getLongitude()))
                            .icon(BitmapDescriptorFactory
                                    .fromResource(R.drawable.pin))
                            .snippet(newPlace.getVicinity()));
                    moveMap(new LatLng(newPlace.getLatitude(), newPlace.getLongitude()));Document doc = null;
                    LatLng destPosition = new LatLng(newPlace.getLatitude(), newPlace.getLongitude());
                    md = new GMapV2Direction(origin, destPosition, GMapV2Direction.MODE_WALKING);
                    md.execute();
                    try {
                        doc = md.get();

                        ArrayList<LatLng> directionPoint = md.getDirection(doc);
                        PolylineOptions rectLine = new PolylineOptions().width(8).color(Color.BLUE);

                        for (int i = 0; i < directionPoint.size(); i++) {
                            rectLine.add(directionPoint.get(i));
                        }
                        Polyline polylin = mMap.addPolyline(rectLine);
                    } catch (Exception e) {
                        //nothing to do for now
                    }
                }
                break;
            }
            default: break;
        }
    }

    public boolean onSearch(View view) {
        EditText location_tf = (EditText) findViewById(R.id.TFaddress);
        String location = location_tf.getText().toString();
        List<Address> addressList = null;

        mMap.clear();

        /*Document doc = null;
        try {
            doc = md.get();

            ArrayList<LatLng> directionPoint = md.getDirection(doc);
            PolylineOptions rectLine = new PolylineOptions().width(8)
                    .color(Color.BLUE);

            for (int i = 0; i < directionPoint.size(); i++) {
                rectLine.add(directionPoint.get(i));
            }
            Polyline polylin = mMap.addPolyline(rectLine);
        } catch (Exception e) {
            //nothing to do for now
        }*/

        new GetPlaces(this, location.toLowerCase().replace("-", "_").replace(" ", "_"), 5000).execute();
        //}


        return true;
    }



    /*public void onZoom(View view) {
        if(view.getId() == R.id.Bzoomin) {
            mMap.animateCamera(CameraUpdateFactory.zoomIn());
        }
        if(view.getId() == R.id.Bzoomout) {
            mMap.animateCamera(CameraUpdateFactory.zoomOut());
        }
    }*/

    public void changeType(View view) {
        if (mMap.getMapType() == GoogleMap.MAP_TYPE_NORMAL) {
            mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        } else
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }



    private class GetPlaces extends AsyncTask<Void, Void, ArrayList<Place>> {

        private ProgressDialog dialog;
        private String places;
        private double radius;

        public GetPlaces(GooglePlacesActivity mapsActivity, String places, double radius) {
            this.places = places;
            this.radius = radius;
        }

        @Override
        protected void onPostExecute(ArrayList<Place> result) {
            super.onPostExecute(result);
            //if (dialog.isShowing()) {
            //    dialog.dismiss();
            //}


            LatLng cll = new LatLng(41.843730, -87.621782);
            // see more on Marker: https://developers.google.com/maps/documentation/android/reference/com/google/android/gms/maps/model/Marker
            mMap.addMarker(new MarkerOptions().position(cll).title("Life Science"));
            for (int i = 0; i < result.size(); i++) {
                mMap.addMarker(new MarkerOptions()
                        .title(result.get(i).getName())
                        .position(
                                new LatLng(result.get(i).getLatitude(), result
                                        .get(i).getLongitude()))
                        .icon(BitmapDescriptorFactory
                                .fromResource(R.drawable.pin))
                        .snippet(result.get(i).getVicinity()));
                LatLng tempLoc = new LatLng(result.get(i).getLatitude(), result.get(i).getLongitude());

                //Log.d("Position "+String.valueOf(i), result.get(i).getName());

                /*md = new GMapV2Direction(cll, tempLoc, GMapV2Direction.MODE_WALKING);
                md.execute();
                Document doc = null;
                // Document doc = md.getDocument(cll, zll,
                // GMapV2Direction.MODE_DRIVING);
                try {
                    // get the result from the asynctask returned by Google,
                    // wait if necessary
                    doc = md.get();

                    // now process/parse the results from Google
                    ArrayList<LatLng> directionPoint = md.getDirection(doc);

                    // here, draw the lines based on the direction points
                    PolylineOptions rectLine = new PolylineOptions().width(8)
                            .color(Color.BLUE);

                    for (int j = 0; j < directionPoint.size(); j++) {
                        rectLine.add(directionPoint.get(j));
                    }
                    Polyline polylin = mMap.addPolyline(rectLine);

                } catch (Exception e) {

                }*/
            }

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected ArrayList<Place> doInBackground(Void... arg0) {
            PlacesService service = new PlacesService("AIzaSyC0eOv4pn7xPPauCtkVE960iG3UM_O-ZSc");
            ArrayList<Place> findPlaces = service.findPlaces(41.843730, -87.621782, places, radius);

            for (int i = 0; i < findPlaces.size(); i++) {

                Place placeDetail = findPlaces.get(i);
                Log.e("MAPS", "places : " + placeDetail.getName());
            }
            return findPlaces;
        }






    }


}