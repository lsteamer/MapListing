package lsteamer.elmexicano.com.maplisting.view;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import java.util.Objects;

import lsteamer.elmexicano.com.maplisting.Contract;
import lsteamer.elmexicano.com.maplisting.Presenter;


public class MapViewLayer extends SupportMapFragment implements Contract.MapViewContract, OnMapReadyCallback, GoogleMap.OnMarkerClickListener {


    private static final float DEFAULT_ZOOM = 14;

    private Contract.PresenterContract presenter;
    private GoogleMap map;

    public MapViewLayer() {
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

        if (ActivityCompat.checkSelfPermission(Objects.requireNonNull(getActivity()), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        map.setMyLocationEnabled(true);
        map.setOnMarkerClickListener(this);

        setCameraToLocation(presenter.getLocation());

    }

    public GoogleMap getMapVariable() {
        return map;
    }

    @Override
    public void setMarkerVisible(Marker marker) {
        marker.setVisible(true);

    }

    @Override
    public void setMarkerInvisible(Marker marker) {
        marker.setVisible(false);

    }

    public void setCameraToLocation(LatLng latLng) {
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, DEFAULT_ZOOM));
    }


    @Override
    public boolean onMarkerClick(Marker marker) {
        presenter.onMapLocationSelected((String) marker.getTag());
        return !presenter.getUnitSelectedBoolean();
    }
}
