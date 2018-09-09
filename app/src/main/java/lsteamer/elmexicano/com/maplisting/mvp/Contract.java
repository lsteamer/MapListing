package lsteamer.elmexicano.com.maplisting.mvp;

import android.location.Location;

import com.google.android.gms.location.FusedLocationProviderClient;

import java.util.List;

import lsteamer.elmexicano.com.maplisting.model.CarData;

public interface Contract {

    interface ListViewContract{
        void setPresenter(Presenter presenter);
        void startAdapter(List<CarData> carDataList);

    }

    interface MapViewContract{
        void setPresenter(Presenter presenter);

    }

    interface PresenterContract {
        Location getLocation();
        double getLocationLatitude();
        double getLocationLongitude();
        List<CarData> getCarDataList();
    }
}
