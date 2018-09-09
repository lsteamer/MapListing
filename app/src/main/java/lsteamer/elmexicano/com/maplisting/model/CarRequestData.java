package lsteamer.elmexicano.com.maplisting.model;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CarRequestData {


    //todo it doesn't get called yet, CarData is incomplete
    @GET
    Call<CarData> getData();
}
