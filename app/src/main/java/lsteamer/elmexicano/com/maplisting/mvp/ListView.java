package lsteamer.elmexicano.com.maplisting.mvp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lsteamer.elmexicano.com.maplisting.R;
import lsteamer.elmexicano.com.maplisting.model.CarData;
import lsteamer.elmexicano.com.maplisting.utils.CarAdapter;

public class ListView extends Fragment implements Contract.ListViewContract {

    public static final String TAG = "List";

    private Contract.PresenterContract presenter;

    private CarAdapter adapter;

    @BindView(R.id.recyclerViewCars)
    RecyclerView carRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.list_fragment,container,false);

        ButterKnife.bind(this, view);



        return view;
    }

    @Override
    public void startAdapter(List<CarData> carDataList) {

        //todo clean this one up later
        carRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new CarAdapter(carDataList, getContext());
        carRecyclerView.setAdapter(adapter);
    }


    @Override
    public void setPresenter(Presenter presenterContract) {
        presenter = presenterContract;
    }



}
