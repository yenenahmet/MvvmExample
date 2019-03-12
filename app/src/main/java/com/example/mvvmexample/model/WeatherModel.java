package com.example.mvvmexample.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;
import static com.example.mvvmexample.util.Utility.intNullControl;
import static com.example.mvvmexample.util.Utility.stringNullControl;

public class WeatherModel {

    @SerializedName("list")
    @Expose
    private List<ListWeather> list = null;
    @SerializedName("city")
    @Expose
    private City city;

    public List<ListWeather> getList() {
        if (list == null) {
            return new ArrayList<>(0);
        }
        return list;
    }

    public City getCity() {
        if (city == null) {
            return new City();
        }
        return city;
    }

    public class City {
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("country")
        @Expose
        private String country;

        public String getName() {
           return stringNullControl(name);
        }

        public String getCountry() {
            return stringNullControl(country);
        }

    }


    public class Weather {
        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("main")
        @Expose
        private String main;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("icon")
        @Expose
        private String icon;

        public Integer getId() {
            return intNullControl(id);
        }

        public String getMain() {
            return stringNullControl(main);
        }

        public String getDescription() {
            return stringNullControl(description);
        }

        public String getIcon() {
            return stringNullControl(icon);
        }

    }



}
