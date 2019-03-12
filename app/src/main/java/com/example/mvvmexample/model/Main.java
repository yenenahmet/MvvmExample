package com.example.mvvmexample.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Main {
    @SerializedName("temp")
    @Expose
    private Double temp;
    @SerializedName("temp_min")
    @Expose
    private Double tempMin;
    @SerializedName("temp_max")
    @Expose
    private Double tempMax;
    @SerializedName("pressure")
    @Expose
    private Double pressure;
    @SerializedName("sea_level")
    @Expose
    private Double seaLevel;
    @SerializedName("grnd_level")
    @Expose
    private Double grndLevel;
    @SerializedName("humidity")
    @Expose
    private Double humidity;
    @SerializedName("temp_kf")
    @Expose
    private Double tempKf;

    public Double getTemp() {
        return doubleNullControl(temp);
    }

    public Double getTempMin() {
        return doubleNullControl(tempMin);
    }

    public Double getTempMax() {
        return doubleNullControl(tempMax);
    }

    public Double getPressure() {
        return doubleNullControl(pressure);
    }


    public Double getSeaLevel() {
        return doubleNullControl(seaLevel);
    }

    public Double getGrndLevel() {
        return doubleNullControl(grndLevel);
    }

    public Double getHumidity() {
        return doubleNullControl(humidity);
    }

    public Double getTempKf() {
        return doubleNullControl(tempKf);
    }

    private double doubleNullControl(final Double val) {
        if (val == null) {
            return 0d;
        }
        return val;
    }
}
