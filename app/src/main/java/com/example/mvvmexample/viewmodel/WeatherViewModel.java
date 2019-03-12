package com.example.mvvmexample.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.example.mvvmexample.databinding.ActivityWeatherBinding;
import com.example.mvvmexample.model.WeatherModel;
import com.example.mvvmexample.remote.ApiClient;
import com.example.mvvmexample.remote.ApiWeatherInterface;
import com.example.mvvmexample.util.Utility;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherViewModel extends ViewModel {

    public final MutableLiveData<String> errorRemote = new MutableLiveData<>();
    private MutableLiveData<WeatherModel> weatherModel;

    public LiveData<WeatherModel> getWeather() {
        if (weatherModel == null) {
            weatherModel = new MutableLiveData<>();
            loadData();
        }
        return weatherModel;
    }

    private void loadData() {
        final Call<WeatherModel> call = ApiClient.createService(ApiWeatherInterface.class).getWeatherAll("745044", Utility.appId);
        call.enqueue(new Callback<WeatherModel>() {
            @Override
            public void onResponse(Call<WeatherModel> call, Response<WeatherModel> response) {
                if (ApiClient.responseOperation(response)) {
                    weatherModel.setValue(response.body());
                    errorRemote.setValue("Başarılı");
                } else {
                    errorRemote.setValue("Veri Alış Verişinde Sorun Var !");
                }
            }
            @Override
            public void onFailure(Call<WeatherModel> call, Throwable t) {
                errorRemote.setValue(ApiClient.failMessage(t));
            }
        });
    }
    @Override
    protected void onCleared() {
        super.onCleared();
        Log.e("onCleared", "Yes Weather");
    }
}
