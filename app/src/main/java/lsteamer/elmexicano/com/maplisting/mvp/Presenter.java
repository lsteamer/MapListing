package lsteamer.elmexicano.com.maplisting.mvp;

import android.location.Location;
import android.util.Log;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import lsteamer.elmexicano.com.maplisting.model.CarData;
import lsteamer.elmexicano.com.maplisting.utils.Utils;

public class Presenter implements Contract.PresenterContract {

    private final static String TAG = "Presenter layer ";

    private Contract.ListViewContract listView;
    private Contract.MapViewContract mapView;

    private List<CarData> carDataList;
    private List<Marker> markerList;

    private boolean unitSelectedFlag;


    private Location location;

    public Presenter(Contract.ListViewContract listViewLayer, Contract.MapViewContract mapViewLayer, Location location, List<CarData> carList) {
        this.location = location;


        this.listView = listViewLayer;
        listView.setPresenter(this);

        this.mapView = mapViewLayer;
        mapView.setPresenter(this);


        this.carDataList = carList;

        unitSelectedFlag = false;
    }

    //When we finally receive the data
    public void startDataInFragments(List<CarData> carList) {
        this.carDataList = carList;
        listView.startAdapter(carDataList);
        markerList = Utils.getMarkerList(carDataList, mapView.getMapVariable());
    }

    public void setUserLocation(Location userLocation) {
        location = userLocation;
    }


    public void onMapLocationSelected(String tag) {
        if (unitSelectedFlag) {
            for (Marker marker : markerList) {
                mapView.setMarkerVisible(marker);
                if (tag == marker.getTag())
                    mapView.hideInfoWindowOfMarker(marker);
            }
            unitSelectedFlag = false;
        } else {
            for (Marker marker : markerList) {
                if (tag != marker.getTag())
                    mapView.setMarkerInvisible(marker);
            }
            unitSelectedFlag = true;
        }

    }

    public Location getLocation() {
        return location;
    }

    public double getLocationLongitude() {
        return location.getLongitude();
    }

    public double getLocationLatitude() {
        return location.getLatitude();
    }

    public List<CarData> getCarDataList() {
        return carDataList;
    }

    public boolean getUnitSelectedBoolean() {
        return unitSelectedFlag;
    }


}
