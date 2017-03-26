package com.example.mohammedalani.go_find_me;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by mohammedalani on 2017-03-25.
 */

public interface GoFindMeService {
    @GET("lostItems")
    public Call<List<HuntedBeacon>> getHuntedBeacons();
}
