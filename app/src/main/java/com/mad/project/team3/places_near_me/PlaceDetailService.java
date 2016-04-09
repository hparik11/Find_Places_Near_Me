package com.mad.project.team3.places_near_me;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by fshen4 on 16-4-6.
 */
public class PlaceDetailService {

    private String API_KEY;

    public PlaceDetailService(String apikey) {
        this.API_KEY = apikey;
    }

    public void setApiKey(String apikey) {
        this.API_KEY = apikey;
    }

    public PlaceDetail getPlaceDetail(String placeID) {

        String urlString = makeUrl(placeID);
        PlaceDetail placeDetail = null;
        try {
            String json = getJSON(urlString);

            //System.out.println(json);
            JSONObject object = new JSONObject(json).getJSONObject("result");

            placeDetail = PlaceDetail
                    .jsonToPontoReferencia((JSONObject) object);
            Log.v("Place Detail Service ", "" + placeID);

        } catch (JSONException ex) {
            Logger.getLogger(PlaceDetailService.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
        return placeDetail;
    }

    //https://maps.googleapis.com/maps/api/place/details/json?placeid=ChIJN1t_tDeuEmsRUsoyG83frY4&key=mykey
    private String makeUrl(String placeid) {
        StringBuilder urlString = new StringBuilder(
                "https://maps.googleapis.com/maps/api/place/details/json?placeid=");

        if (placeid.equals("")) {
            urlString.append("ChIJN1t_tDeuEmsRUsoyG83frY4");
            urlString.append("&key=" + API_KEY);
        } else {
            urlString.append(placeid);
            urlString.append("&key=" + API_KEY);
        }
        return urlString.toString();
    }

    protected String getJSON(String url) {
        return getUrlContents(url);
    }

    private String getUrlContents(String theUrl) {
        StringBuilder content = new StringBuilder();

        try {
            URL url = new URL(theUrl);
            URLConnection urlConnection = url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(urlConnection.getInputStream()), 8);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }
            bufferedReader.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }
}
