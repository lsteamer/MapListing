package lsteamer.elmexicano.com.maplisting.utils;

import android.location.Location;
import android.support.annotation.NonNull;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import lsteamer.elmexicano.com.maplisting.model.CarData;
import lsteamer.elmexicano.com.maplisting.model.CarRequestData;
import lsteamer.elmexicano.com.maplisting.model.Feed;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Utils {

    private Utils() {
        throw new AssertionError("No instances!");
    }

    public static final String FULL_URL = "https://s3-us-west-2.amazonaws.com/wunderbucket/";


    public static LatLng getLatLonWithListCoordinates(List<String> coordinates) {
        double lon = Double.parseDouble(coordinates.get(0));
        double lat = Double.parseDouble(coordinates.get(1));
        return new LatLng(lat, lon);
    }

    public static LatLng getDefaultLatLon() {
        return new LatLng(53.5511d, 9.9937d);
    }

    public static LatLng getLatLonWithLocation(Location location) {
        return new LatLng(location.getLatitude(), location.getLongitude());
    }


    //Retrofit call
    public static Call<Feed> getLoginRequestData(@NonNull String url) {
        Retrofit retrofit;
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CarRequestData requestData = retrofit.create(CarRequestData.class);
        return requestData.getData();
    }


    //Gets the markers information with the Car coordinates
    public static List<Marker> getMarkerList(List<CarData> carDataList, GoogleMap map) {
        List<Marker> markerList = new ArrayList<>();

        int tag = 0;

        for (CarData car : carDataList) {
            LatLng latLng = getLatLonWithListCoordinates(car.getCoordinates());
            Marker marker = map.addMarker(new MarkerOptions()
                    .position(latLng)
                    .title(car.getAddress()));

            marker.setTag(Integer.toString(tag));
            tag++;
            markerList.add(marker);
        }

        return markerList;
    }


}
