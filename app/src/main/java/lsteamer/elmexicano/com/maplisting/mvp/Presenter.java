package lsteamer.elmexicano.com.maplisting.mvp;

import android.location.Location;
import android.util.Log;

public class Presenter implements Contract.PresenterContract {


    private Contract.ListViewContract listView;
    private Contract.MapViewContract mapView;

    private Location location;

    public Presenter(Contract.ListViewContract listViewLayer, Contract.MapViewContract mapViewLayer, Location location){

        this.location = location;

        this.listView = listViewLayer;
        listView.setPresenter(this);

        this.mapView = mapViewLayer;
        mapView.setPresenter(this);

    }

    public void mapClick(){
        Log.d("MAP", "Latitude " + location.getLatitude());
    }

    public void listClick(){
        Log.d("LIST", "Longitude "+ location.getLongitude());

    }


}
