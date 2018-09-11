package lsteamer.elmexicano.com.maplisting.model;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CarRequestData {

    @GET("locations.json")
    Call<Feed> getData();
}


