package com.mad.project.team3.places_near_me;

/**
 * Created by harsh on 4/5/16.
 */

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Place implements Serializable {
    private static final long serialVersionUID = -7060210544600464481L;
    private String id;
    private String icon;
    private String name;
    private String vicinity;
    private String photoreference;
    private Double latitude;
    private Double longitude;
    private String placeid;
    private String phone;
    private String rating;
    private String website;

    public Place() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVicinity() {
        return vicinity;
    }

    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }

    public String getPhotoreference() {
        return photoreference;
    }

    public void setPhotoreference(String photoreference) {
        this.photoreference = photoreference;
    }

    public String getPlaceid() {
        return placeid;
    }

    public void setPlaceid(String placeid) {
        this.placeid = placeid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    static Place jsonToPontoReferencia(JSONObject pontoReferencia) {
        try {
            Place result = new Place();
            JSONObject geometry = (JSONObject) pontoReferencia.get("geometry");
            JSONObject location = (JSONObject) geometry.get("location");
            if (pontoReferencia.has("photos")) {
                JSONArray photos = (JSONArray) pontoReferencia.get("photos");
                JSONObject photo = photos.getJSONObject(0);

                result.setPhotoreference((String)photo.get("photo_reference"));
            }
            else {
                result.setPhotoreference("");
            }
            result.setLatitude((Double) location.get("lat"));
            result.setLongitude((Double) location.get("lng"));
            result.setIcon(pontoReferencia.getString("icon"));
            result.setName(pontoReferencia.getString("name"));
            result.setVicinity(pontoReferencia.getString("vicinity"));
            result.setPlaceid(pontoReferencia.getString("place_id"));
            result.setId(pontoReferencia.getString("id"));
            return result;
        } catch (JSONException ex) {
            Logger.getLogger(Place.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public String toString() {
        return "Place{" + "id=" + id +
                ", icon=" + icon +
                ", name=" + name +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", longitude=" + longitude +
                ", placeid=" + placeid +
                ", phone=" + phone +
                ", rating=" + rating +
                ", website=" + website +
                '}';
    }

}