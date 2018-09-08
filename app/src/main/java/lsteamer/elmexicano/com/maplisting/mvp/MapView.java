package lsteamer.elmexicano.com.maplisting.mvp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import lsteamer.elmexicano.com.maplisting.R;

public class MapView extends Fragment implements Contract.MapViewContract {

    public static final String TAG = "Map";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //todo variable view is redundant
        View view = inflater.inflate(R.layout.map_fragment,container,false);

        return view;
    }


    @Override
    public void setPresenter(Presenter presenter) {

    }
}
