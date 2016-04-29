package com.mad.project.team3.places_near_me;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ScaleDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import java.util.ArrayList;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

public class SearchActivity extends Activity {
    private ImageButton bSearch;
    private ArrayList<Place> list;
    private PlaceArrayAdapter adapter;
    private ListView myListView;
    private EditText address;

    private PlaceSqliteController placeController;

    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search);

        //final ArrayList<String> todoItems = new ArrayList<String>();
        //final ArrayAdapter<String> aa = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,todoItems);
        myListView = (ListView)findViewById(R.id.listView1);

        Drawable drawable = getResources().getDrawable(R.drawable.ic_menu_search);
        drawable.setBounds(0, 0, (int)(drawable.getIntrinsicWidth()*0.5),
                (int)(drawable.getIntrinsicHeight()*0.5));
        ScaleDrawable sd = new ScaleDrawable(drawable, 0, 1, 1);

        Intent intent1 = getIntent();
        final String category = intent1.getStringExtra("Category");

        address = (EditText)findViewById(R.id.tAddress);
        address.setText(category);
        //address.setCompoundDrawables(sd.getDrawable(), null, null, null);

        bSearch = (ImageButton)findViewById(R.id.bSearch);

        bSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //new ShowAddresses().execute(aa);
                getAddresses();
            }
        });

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long arg3) {
                //Address selectedAddress = (Address) adapter.getItemAtPosition(position);
                Place selectedPlace = (Place) adapter.getItemAtPosition(position);
                // assuming string and if you want to get the value on click of list item
                // do what you intend to do on click of listview row
                //Log.d("Address", String.valueOf(selectedAddress.getLongitude()) + String.valueOf(selectedAddress.getLatitude()));
                Intent intent = new Intent(SearchActivity.this, GooglePlacesActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putSerializable("SelectedPlace", selectedPlace);
                intent.putExtras(mBundle);
                setResult(1, intent);
                //finish();
            }
        });

        list = new ArrayList<Place>();
        adapter = new PlaceArrayAdapter(getApplicationContext(), list);
        myListView.setAdapter(adapter);

        placeController = new PlaceSqliteController(getApplicationContext());

        initImageLoader(getApplicationContext());
    }

    /**初始化图片加载类配置信息**/
    public static void initImageLoader(Context context) {
        // This configuration tuning is custom. You can tune every option, you may tune some of them,
        // or you can create default configuration by ImageLoaderConfiguration.createDefault(this); method.
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .threadPriority(Thread.NORM_PRIORITY - 2)//加载图片的线程数
                .denyCacheImageMultipleSizesInMemory() //解码图像的大尺寸将在内存中缓存先前解码图像的小尺寸。
                .discCacheFileNameGenerator(new Md5FileNameGenerator())//设置磁盘缓存文件名称
                .tasksProcessingOrder(QueueProcessingType.LIFO)//设置加载显示图片队列进程
                .writeDebugLogs() // Remove for release app
                .build();
        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config);
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

        new GetPlaces(sLocation.toLowerCase().replace("-", "_").replace(" ", "_"), 5000).execute();


    }

    private class GetPlaces extends AsyncTask<Void, Void, ArrayList<Place>> {
        private String places;
        private double radius;

        public GetPlaces(String places, double radius) {
            this.places = places;
            this.radius = radius;
        }

        @Override
        protected void onPostExecute(ArrayList<Place> result) {
            super.onPostExecute(result);
            //if (dialog.isShowing()) {
            //    dialog.dismiss();
            //}
            // see more on Marker: https://developers.google.com/maps/documentation/android/reference/com/google/android/gms/maps/model/Marker

            PlaceArrayAdapter adapter = new PlaceArrayAdapter(getApplicationContext(), result);
            myListView.setAdapter(adapter);
                /*
                mMap.addMarker(new MarkerOptions()
                        .title(result.get(i).getName())
                        .position(
                                new LatLng(result.get(i).getLatitude(), result
                                        .get(i).getLongitude()))
                        .icon(BitmapDescriptorFactory
                                .fromResource(com.example.fshen4.CS442_Places_Near_Me.R.drawable.pin))
                        .snippet(result.get(i).getVicinity()));
                LatLng tempLoc = new LatLng(result.get(i).getLatitude(), result.get(i).getLongitude());
                md = new GMapV2Direction(cll, tempLoc, GMapV2Direction.MODE_WALKING);
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
                    // just ignore here, possible exceptions thrown by the
                    // md.get() call:
                    // see
                    // http://developer.android.com/reference/android/os/AsyncTask.html#get()
                }
            }
            /*
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng(result.get(0).getLatitude(), result
                            .get(0).getLongitude())) // Sets the center of the map to
                            // Mountain View
                    .zoom(13) // Sets the zoom
                    .tilt(30) // Sets the tilt of the camera to 30 degrees
                    .build(); // Creates a CameraPosition from the builder
            mMap.animateCamera(CameraUpdateFactory
                    .newCameraPosition(cameraPosition));
            */
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //dialog = new ProgressDialog(getApplicationContext());
            //dialog.setCancelable(false);
            //dialog.setMessage("Loading..");
            //dialog.isIndeterminate();
            //dialog.show();
        }

        @Override
        protected ArrayList<Place> doInBackground(Void... arg0) {
            PlacesService service = new PlacesService("AIzaSyBAsU2YP6fGQcRNne5c772-Y6H3J3gD2Us");
            ArrayList<Place> findPlaces = service.findPlaces(41.843730, -87.621782, places, radius);

            for (int i = 0; i < findPlaces.size(); i++) {

                Place placeDetail = findPlaces.get(i);
                //Log.e("MAPS", "places : " + placeDetail.getName());
                placeController.insertplace(
                        placeDetail.getLongitude(),
                        placeDetail.getLatitude(),
                        placeDetail.getId(),
                        placeDetail.getIcon(),
                        placeDetail.getName(),
                        placeDetail.getVicinity(),
                        placeDetail.getPlaceid(),
                        placeDetail.getPhone(),
                        placeDetail.getRating(),
                        placeDetail.getWebsite()
                );
            }
            return findPlaces;
        }

    }

    public boolean onSearchRequested() {
        EditText tLocation = (EditText)findViewById(R.id.tAddress);
        String sLocation = tLocation.getText().toString();
        //ArrayList<Address> addressList = null;
        /*if (sLocation != null || !sLocation.equals("")) {
            Geocoder geocoder = new Geocoder(this);
            try {
                addressList = geocoder.getFromLocationName(sLocation, 5);
            } catch (IOException e) {
                e.printStackTrace();
            }
            for(int i = 0; i<addressList.size(); i++) {
                Log.d(String.valueOf(addressList.get(i).getAdminArea()), "AdminArea");
                Log.d(String.valueOf(addressList.get(i).getLatitude()), "Latitude");
                Log.d(String.valueOf(addressList.get(i).getLongitude()), "Longitude");
                Log.d(String.valueOf(addressList.get(i).getPhone()), "Phone");
                Log.d(String.valueOf(addressList.get(i).getPostalCode()), "PostalCode");
            }
            Address address = addressList.get(0);
            LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
            mMap.addMarker(new MarkerOptions().position(latLng).title("Marker"));
            mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
            mMap = ((SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(com.example.fshen4.CS442_Places_Near_Me.R.id.map)).getMap();
            LatLng sourcePosition = new LatLng(41.843730, -87.621782);
            LatLng destPosition = new LatLng(41.864909, -87.666747);
            md = new GMapV2Direction(sourcePosition, destPosition, GMapV2Direction.MODE_WALKING);
            md.execute(); */
        return true;
    }

    @Override
    public void onBackPressed() {
        ImageLoader.getInstance().stop();
        super.onBackPressed();
    }
}
