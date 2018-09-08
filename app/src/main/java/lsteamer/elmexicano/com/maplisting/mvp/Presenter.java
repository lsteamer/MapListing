package lsteamer.elmexicano.com.maplisting.mvp;

public class Presenter implements Contract.PresenterContract {


    private Contract.ListViewContract listView;
    private Contract.MapViewContract mapView;

    public Presenter(Contract.ListViewContract listViewLayer, Contract.MapViewContract mapViewLayer){

        this.listView = listViewLayer;
        listView.setPresenter(this);

        this.mapView = mapViewLayer;
        mapView.setPresenter(this);

    }


}
