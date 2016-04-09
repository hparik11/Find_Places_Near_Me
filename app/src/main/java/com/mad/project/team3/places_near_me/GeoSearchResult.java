package com.mad.project.team3.places_near_me;

/**
 * Created by harsh on 4/5/16.
 */
import android.location.Address;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by fshen4 on 16-4-2.
 */

public class GeoSearchResult {
    private Address address;

    public GeoSearchResult(Address address)
    {
        this.address = address;
    }

    public String getAddress(){

        String display_address = "";

        display_address += address.getAddressLine(0) + "\n";

        for(int i = 1; i < address.getMaxAddressLineIndex(); i++)
        {
            display_address += address.getAddressLine(i) + ", ";
        }

        display_address = display_address.substring(0, display_address.length() - 2);

        return display_address;
    }

    public LatLng getLatLng()
    {
        LatLng lt = new LatLng(address.getLatitude(), address.getLongitude());
        return  lt;
    }


    public String toString(){
        String display_address = "";

        if(address.getFeatureName() != null)
        {
            display_address += address + ", ";
        }

        for(int i = 0; i < address.getMaxAddressLineIndex(); i++)
        {
            display_address += address.getAddressLine(i);
        }

        return display_address;
    }

}