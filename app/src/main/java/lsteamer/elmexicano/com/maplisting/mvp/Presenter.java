package lsteamer.elmexicano.com.maplisting.mvp;

import android.location.Location;
import android.util.Log;

import com.google.android.gms.location.FusedLocationProviderClient;

import java.util.List;

import lsteamer.elmexicano.com.maplisting.model.CarData;

public class Presenter implements Contract.PresenterContract {


    private Contract.ListViewContract listView;
    private Contract.MapViewContract mapView;

    private FusedLocationProviderClient locationProvider;

    private List<CarData> carDataList;


    private Location location;

    public Presenter(Contract.ListViewContract listViewLayer, Contract.MapViewContract mapViewLayer, FusedLocationProviderClient locationProvider, Location location, List<CarData> carDataList){

        this.locationProvider = locationProvider;
        this.location = location;

        this.carDataList = carDataList;

        this.listView = listViewLayer;
        listView.setPresenter(this);

        this.mapView = mapViewLayer;
        mapView.setPresenter(this);

    }


    public Location getLocation(){
        return location;
    }

    public double getLocationLongitude(){
        return location.getLongitude();
    }

    public double getLocationLatitude(){
        return location.getLatitude();
    }


}
