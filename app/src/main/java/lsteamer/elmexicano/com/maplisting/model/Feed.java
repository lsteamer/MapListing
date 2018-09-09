package lsteamer.elmexicano.com.maplisting.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Feed {

    @SerializedName("placemarks")
    @Expose
    private List<CarData> carData;

    public List<CarData> getCarData() {
        return carData;
    }

    public void setCarData(List<CarData> carData) {
        this.carData = carData;
    }
}
