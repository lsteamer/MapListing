package lsteamer.elmexicano.com.maplisting.mvp;

import android.location.Location;

import com.google.android.gms.location.FusedLocationProviderClient;

public interface Contract {

    interface ListViewContract{
        void setPresenter(Presenter presenter);

    }

    interface MapViewContract{
        void setPresenter(Presenter presenter);

    }

    interface PresenterContract {
        Location getLocation();
        double getLocationLatitude();
        double getLocationLongitude();
    }
}
