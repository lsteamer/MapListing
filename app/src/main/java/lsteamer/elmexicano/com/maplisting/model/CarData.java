package lsteamer.elmexicano.com.maplisting.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CarData {


    @SerializedName("coordinates")
    @Expose
    private List<String> coordinates;

    @SerializedName("address")
    @Expose
    private String address;

    @SerializedName("engineType")
    @Expose
    private String engineType;

    @SerializedName("exterior")
    @Expose
    private String exterior;

    @SerializedName("interior")
    @Expose
    private String interior;

    @SerializedName("fuel")
    @Expose
    private String fuel;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("vin")
    @Expose
    private String vin;

    public List<String> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<String> coordinates) {
        this.coordinates = coordinates;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public String getExterior() {
        return exterior;
    }

    public void setExterior(String exterior) {
        this.exterior = exterior;
    }

    public String getInterior() {
        return interior;
    }

    public void setInterior(String interior) {
        this.interior = interior;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }
}
