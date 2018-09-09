package lsteamer.elmexicano.com.maplisting.model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Url;

public interface CarRequestData {


    //todo it doesn't get called yet, CarData is incomplete
    @GET("locations.json")
    Call<Feed> getData();
}


