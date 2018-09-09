package lsteamer.elmexicano.com.maplisting.mvp;

import android.location.Location;
import android.util.Log;

import com.google.android.gms.location.FusedLocationProviderClient;

import java.util.List;

import lsteamer.elmexicano.com.maplisting.model.CarData;

public class Presenter implements Contract.PresenterContract {

    private final static String TAG = "Presenter layer ";

    private Contract.ListViewContract listView;
    private Contract.MapViewContract mapView;

    private FusedLocationProviderClient locationProvider;

    private List<CarData> carDataList;


    private Location location;

    public Presenter(Contract.ListViewContract listViewLayer, Contract.MapViewContract mapViewLayer, FusedLocationProviderClient locationProvider, Location location, List<CarData> carList){

        this.locationProvider = locationProvider;
        this.location = location;


        this.listView = listViewLayer;
        listView.setPresenter(this);

        this.mapView = mapViewLayer;
        mapView.setPresenter(this);


        this.carDataList = carList;

    }

    public void setAdapter(List<CarData> carList){
        this.carDataList = carList;
        Log.d(TAG, "some; " + carDataList.get(0).getAddress());
        listView.startAdapter(carDataList);

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

    public List<CarData> getCarDataList(){
        return carDataList;
    }


}
