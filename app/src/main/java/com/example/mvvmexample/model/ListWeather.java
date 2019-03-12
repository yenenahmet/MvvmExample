package com.example.mvvmexample.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ListWeather {
    @SerializedName("main")
    @Expose
    private Main main;
    @SerializedName("weather")
    @Expose
    private List<WeatherModel.Weather> weather;

    public Main getMain() {
        if (main == null) {
            return new Main();
        }
        return main;
    }


    public List<WeatherModel.Weather> getWeather() {
        if (weather == null) {
            return new ArrayList<>(0);
        }
        return this.weather;
    }
}
