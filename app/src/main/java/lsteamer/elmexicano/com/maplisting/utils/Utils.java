package lsteamer.elmexicano.com.maplisting.utils;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

public class Utils {


    public static LatLng getLatLonWithCoordinates(double lat, double lon){
        return new LatLng(lat, lon);
    }

    public static LatLng getLatLonWithLocation(Location location){
        return new LatLng(location.getLatitude(), location.getLongitude());
    }

}
