package lsteamer.elmexicano.com.maplisting.mvp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import lsteamer.elmexicano.com.maplisting.utils.Utils;


public class MapView extends SupportMapFragment implements Contract.MapViewContract, OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    public static final String TAG = "Map";

    private static final float DEFAULT_ZOOM = 14;

    private Contract.PresenterContract presenter;

    private GoogleMap map;

    public MapView() {
    }

    @Override
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        getMapAsync(this);
    }

    @Override
    public void setPresenter(Presenter presenterContract) {
        presenter = presenterContract;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        LatLng currentLocation = Utils.getLatLonWithLocation(presenter.getLocation());

        //map.addMarker(new MarkerOptions().position(currentLocation).title("Current Location"));


        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        map.setMyLocationEnabled(true);
        map.setOnMarkerClickListener(this);
        moveCamera(currentLocation, DEFAULT_ZOOM);

    }

    public GoogleMap getMapVariable(){
        return map;
    }

    public void addMarker(MarkerOptions marker){
        map.addMarker(marker);
    }

    @Override
    public void setMarkerVisible(Marker marker){

    }
    @Override
    public void setMarkerInvisible(Marker marker){
        marker.setVisible(false);

    }

    public void moveCamera(LatLng latLng, float zoom){
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        Log.d(TAG,"marker: " + marker.getTag());
        presenter.onMapLocationSelected((Integer) marker.getTag());
        return false;
    }
}
