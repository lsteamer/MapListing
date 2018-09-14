package lsteamer.elmexicano.com.maplisting.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import lsteamer.elmexicano.com.maplisting.R;
import lsteamer.elmexicano.com.maplisting.model.CarData;

public class CarAdapter extends RecyclerView.Adapter<CarViewHolder> {


    private List<CarData> carList;

    /*
     * Context is not need with the app's current state
     * but it will if the app is to be expanded.
     */
    private Context context;

    public CarAdapter(List<CarData> list, Context context) {
        this.carList = list;
        this.context = context;
    }

    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.car_list_item, parent, false);

        return new CarViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CarViewHolder holder, int position) {
        CarData car = carList.get(position);
        holder.populate(car);
    }

    @Override
    public int getItemCount() {
        return carList.size();
    }
}
