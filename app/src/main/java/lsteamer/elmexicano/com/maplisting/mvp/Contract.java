package lsteamer.elmexicano.com.maplisting.mvp;

import android.location.Location;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import lsteamer.elmexicano.com.maplisting.model.CarData;

public interface Contract {

    interface ListViewContract{
        void setPresenter(Presenter presenter);
        void startAdapter(List<CarData> carDataList);

    }

    interface MapViewContract{
        void setPresenter(Presenter presenter);
        //void addMarker(MarkerOptions marker);
        void setMarkerVisible(Marker marker);
        void setMarkerInvisible(Marker marker);
        GoogleMap getMapVariable();
        void hideInfoWindowOfMarker(Marker marker);
    }

    interface PresenterContract {
        Location getLocation();
        double getLocationLatitude();
        double getLocationLongitude();
        List<CarData> getCarDataList();
        void onMapLocationSelected(int tag);
        boolean getUnitSelectedBoolean();
    }
}
