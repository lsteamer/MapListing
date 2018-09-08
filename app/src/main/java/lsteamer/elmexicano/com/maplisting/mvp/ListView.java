package lsteamer.elmexicano.com.maplisting.mvp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;
import lsteamer.elmexicano.com.maplisting.R;

public class ListView extends Fragment implements Contract.ListViewContract {

    public static final String TAG = "List";

    private Contract.PresenterContract presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //todo variable view is redundant
        View view = inflater.inflate(R.layout.list_fragment,container,false);

        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void setPresenter(Presenter presenterContract) {
        presenter = presenterContract;
    }

    @OnClick(R.id.buttonList)
    void someSome(){
        presenter.listClick();
    }

}
