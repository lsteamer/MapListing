package lsteamer.elmexicano.com.maplisting.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;


import butterknife.ButterKnife;
import lsteamer.elmexicano.com.maplisting.R;
import lsteamer.elmexicano.com.maplisting.model.CarData;

public class CarViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.carName)
    TextView carName;

    @BindView(R.id.carAddress)
    TextView carAddress;

    public CarViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void populate(CarData car) {
        carAddress.setText(car.getAddress());
        carName.setText(car.getName());
    }

}
