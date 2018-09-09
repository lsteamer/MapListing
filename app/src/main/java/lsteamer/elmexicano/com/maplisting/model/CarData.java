package lsteamer.elmexicano.com.maplisting.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CarData {

    //todo No constructor, no getters and setters and no Coordinates!


    //Data Object for Coordinate

    @SerializedName("address")
    @Expose
    String address;

    @SerializedName("engineType")
    @Expose
    String engineType;

    @SerializedName("exterior")
    @Expose
    String exterior;

    @SerializedName("interior")
    @Expose
    String interior;

    @SerializedName("fuel")
    @Expose
    String fuel;

    @SerializedName("name")
    @Expose
    String name;

    @SerializedName("vin")
    @Expose
    String vin;


}
