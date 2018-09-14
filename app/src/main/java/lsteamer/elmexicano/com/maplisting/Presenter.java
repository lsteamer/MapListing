package lsteamer.elmexicano.com.maplisting;

import android.location.Location;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import java.util.List;

import lsteamer.elmexicano.com.maplisting.model.CarData;
import lsteamer.elmexicano.com.maplisting.utils.Utils;

public class Presenter implements Contract.PresenterContract {

    private Contract.ListViewContract listViewLayer;
    private Contract.MapViewContract mapViewLayer;

    private List<CarData> carDataList;
    private List<Marker> markerList;

    private boolean unitSelectedFlag;

    private LatLng latLng;

    private GoogleMap googleMap;

    public Presenter(Contract.ListViewContract listViewLayer, Contract.MapViewContract mapViewLayer) {

        //We get a default location in case the userLocation takes a while and/or there's poor signal atm.
        latLng = Utils.getDefaultLatLon();

        this.listViewLayer = listViewLayer;
        listViewLayer.setPresenter(this);

        this.mapViewLayer = mapViewLayer;
        mapViewLayer.setPresenter(this);

        unitSelectedFlag = false;
    }

    //Receiving the data for the List
    public void startDataInFragments(List<CarData> carList) {
        this.carDataList = carList;
        listViewLayer.startAdapter(carDataList);
        markerList = Utils.getMarkerList(carDataList, mapViewLayer.getMapVariable());
    }

    //Receiving the user Location
    public void setUserLocation(Location userLocation) {
        latLng = Utils.getLatLonWithLocation(userLocation);
    }


    //A Unit is selected in the MapViewLayer
    public void onMapLocationSelected(String tag) {
        if (unitSelectedFlag) {
            for (Marker marker : markerList)
                mapViewLayer.setMarkerVisible(marker);

            unitSelectedFlag = false;
        } else {
            for (Marker marker : markerList)
                if (tag != marker.getTag())
                    mapViewLayer.setMarkerInvisible(marker);

            unitSelectedFlag = true;
        }

    }

    public LatLng getLocation() {
        return latLng;
    }

    public boolean getUnitSelectedBoolean() {
        return unitSelectedFlag;
    }


}
