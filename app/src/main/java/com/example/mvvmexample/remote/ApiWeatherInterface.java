package com.example.mvvmexample.remote;

import com.example.mvvmexample.model.WeatherModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiWeatherInterface {
    @GET("/data/2.5/forecast?")
    Call<WeatherModel> getWeatherAll(@Query("id") String id, @Query("APPID") String appid);
}
