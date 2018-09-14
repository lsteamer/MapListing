package lsteamer.elmexicano.com.maplisting;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import java.util.List;

import lsteamer.elmexicano.com.maplisting.model.CarData;

public interface Contract {

    interface ListViewContract{
        void setPresenter(Presenter presenter);
        void startAdapter(List<CarData> carDataList);

    }

    interface MapViewContract{
        void setPresenter(Presenter presenter);
        void setMarkerVisible(Marker marker);
        void setMarkerInvisible(Marker marker);
        GoogleMap getMapVariable();
        void setCameraToLocation(LatLng latLng);
    }

    interface PresenterContract {
        LatLng getLocation();
        void onMapLocationSelected(String tag);
        boolean getUnitSelectedBoolean();
    }
}
