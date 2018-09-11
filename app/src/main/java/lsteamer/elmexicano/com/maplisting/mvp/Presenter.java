package lsteamer.elmexicano.com.maplisting.mvp;

import android.location.Location;
import android.util.Log;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import lsteamer.elmexicano.com.maplisting.model.CarData;
import lsteamer.elmexicano.com.maplisting.utils.Utils;

public class Presenter implements Contract.PresenterContract {

    private final static String TAG = "Presenter layer ";

    private Contract.ListViewContract listView;
    private Contract.MapViewContract mapView;

    private FusedLocationProviderClient locationProvider;

    private List<CarData> carDataList;
    private List<MarkerOptions> markerOptions;


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

    //When we finally receive the data
    public void startDataInFragments(List<CarData> carList){
        this.carDataList = carList;
        markerOptions = Utils.getMarkerOptionsList(carDataList);
        listView.startAdapter(carDataList);
        setMarkerOptions();

    }

    private void setMarkerOptions(){
        for(MarkerOptions marker : markerOptions){
            mapView.addMarker(marker);
        }

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
