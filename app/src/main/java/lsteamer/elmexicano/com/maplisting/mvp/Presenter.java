package lsteamer.elmexicano.com.maplisting.mvp;

import android.location.Location;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

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

    private GoogleMap googleMap;

    public Presenter(Contract.ListViewContract listViewLayer, Contract.MapViewContract mapViewLayer) {

        this.listView = listViewLayer;
        listView.setPresenter(this);

        this.mapView = mapViewLayer;
        mapView.setPresenter(this);

        unitSelectedFlag = false;
    }

    //Receiving the data for the List
    public void startDataInFragments(List<CarData> carList) {
        this.carDataList = carList;
        listView.startAdapter(carDataList);
        markerList = Utils.getMarkerList(carDataList, mapView.getMapVariable());
    }

    //Receiving the user Location
    public void setUserLocation(Location userLocation) {
        location = userLocation;
        //todo this causes a crash. Why?
        //mapView.setCameraToLocation(Utils.getLatLonWithLocation(location), googleMap);
    }

    //Receiving the Map variable
    public void setMap(GoogleMap map){
        googleMap = map;
    }


    public void onMapLocationSelected(String tag) {
        if (unitSelectedFlag) {
            for (Marker marker : markerList)
                mapView.setMarkerVisible(marker);

            unitSelectedFlag = false;
        } else {
            for (Marker marker : markerList)
                if (tag != marker.getTag())
                    mapView.setMarkerInvisible(marker);

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
