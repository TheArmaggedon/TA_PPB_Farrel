package com.example.ta_pbb_farrel;

import com.example.ta_pbb_farrel.model.Headlines;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("top-headlines")
    Call<Headlines> getHeadlines(
            @Query("sources") String sources,
            @Query("apiKey") String apiKey

    );

}
