package com.cs442.hparik11.places_near_me;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.logging.Level;
import java.util.logging.Logger;


public class PlaceDetail {
    private String id;
    private Double latitude;
    private Double longitude;
    private String formatted_phone_number;

    private String rating;
    private String website;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public void setPhone(String phonenumber) {
        this.formatted_phone_number = phonenumber;
    }

    public String getPhone() {return formatted_phone_number;}

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    static PlaceDetail jsonToPontoReferencia(JSONObject pontoReferencia) {
        try {
            PlaceDetail result = new PlaceDetail();
            JSONObject geometry = (JSONObject) pontoReferencia.get("geometry");
            JSONObject location = (JSONObject) geometry.get("location");
            result.setLatitude((Double) location.get("lat"));
            result.setLongitude((Double) location.get("lng"));
            result.setId(pontoReferencia.getString("id"));
            result.setPhone(pontoReferencia.getString("formatted_phone_number"));
            result.setRating(pontoReferencia.getString("rating"));
            result.setWebsite(pontoReferencia.getString("website"));
            return result;
        } catch (JSONException ex) {
            Logger.getLogger(PlaceDetail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public String toString() {
        return "PlaceDetail{" + "id=" + id + ", latitude=" + latitude + ", longitude=" + longitude + ", phone =" + formatted_phone_number + ", rating =" + rating + ", website =" + website + '}';
    }

}