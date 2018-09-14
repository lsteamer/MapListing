package lsteamer.elmexicano.com.maplisting.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lsteamer.elmexicano.com.maplisting.Contract;
import lsteamer.elmexicano.com.maplisting.Presenter;
import lsteamer.elmexicano.com.maplisting.R;
import lsteamer.elmexicano.com.maplisting.model.CarData;

public class ListViewLayer extends Fragment implements Contract.ListViewContract {

    public static final String TAG = "List";

    private Contract.PresenterContract presenter;

    private CarAdapter adapter;

    @BindView(R.id.loadingIndicator)
    ProgressBar loadingCircle;
    @BindView(R.id.recyclerViewCars)
    RecyclerView carRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.list_fragment, container, false);

        ButterKnife.bind(this, view);


        return view;
    }

    @Override
    public void startAdapter(List<CarData> carDataList) {

        carRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new CarAdapter(carDataList, getContext());
        carRecyclerView.setAdapter(adapter);
        toggleLayoutVisibility();
    }


    @Override
    public void setPresenter(Presenter presenterContract) {
        presenter = presenterContract;
    }


    private void toggleLayoutVisibility() {
        if (loadingCircle.getVisibility() == View.VISIBLE) {
            loadingCircle.setVisibility(View.INVISIBLE);
            carRecyclerView.setVisibility(View.VISIBLE);
        } else {
            loadingCircle.setVisibility(View.VISIBLE);
            carRecyclerView.setVisibility(View.INVISIBLE);
        }

    }


}
