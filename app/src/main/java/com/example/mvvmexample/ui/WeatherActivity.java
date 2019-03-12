package com.example.mvvmexample.ui;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import com.example.mvvmexample.R;
import com.example.mvvmexample.databinding.ActivityWeatherBinding;
import com.example.mvvmexample.model.ListWeather;
import com.example.mvvmexample.model.WeatherModel;
import com.example.mvvmexample.viewmodel.WeatherViewModel;

public class WeatherActivity extends AppCompatActivity {
    private ActivityWeatherBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_weather);
        final WeatherViewModel viewModel = ViewModelProviders.of(this).get(WeatherViewModel.class);
        binding.setWeatherViewModel(viewModel);
        binding.setLifecycleOwner(this);
        listenLoadData(viewModel);

        if (savedInstanceState == null) {
            Log.e("savedInst", "Yes");
        }else{
            Log.e("notNullsavedInst", "Yes");
        }
    }

    private void listenLoadData(final WeatherViewModel viewModel) {
        viewModel.getWeather().observe(this, weatherModel -> {
            if (weatherModel != null && !weatherModel.getList().isEmpty()) {
                try {
                    updateUI(weatherModel);
                } catch (IndexOutOfBoundsException ex) {
                    viewModel.errorRemote.setValue("Datalar Düzgün Alınamadı !");
                }
            }
        });
    }

    @SuppressLint("DefaultLocale")
    private void updateUI(final WeatherModel model) {
        binding.txtTemp.setText(String.format("%.1f", model.getList().get(0).getMain().getTemp() - 272.15));
        binding.txtCity.setText(model.getCity().getName());
        binding.txtMon.setText(weatherToStringDays(model.getList().get(1)));// monday
        binding.txtTue.setText(weatherToStringDays(model.getList().get(9)));// tuesday
        binding.txtWed.setText(weatherToStringDays(model.getList().get(17)));// wednesday
        binding.txtThu.setText(weatherToStringDays(model.getList().get(25)));// thursday
        binding.txtFri.setText(weatherToStringDays(model.getList().get(33))); // friday
        binding.txtMaxtemp.setText(String.format("%.1f", model.getList().get(0).getMain().getTempMax() - 272.15));
        binding.txtMintemp.setText(String.format("%.1f", model.getList().get(0).getMain().getTempMin() - 272.15));
    }

    @SuppressLint("DefaultLocale")
    private String weatherToStringDays(final ListWeather listWeather) {
        final double tempMin = listWeather.getMain().getTempMin() - 272.15;
        final double tempMax = listWeather.getMain().getTempMax() - 272.15;
        return ":tempMin/:tempMax".replace(":tempMin", String.format("%.1f", tempMin)).replace(":tempMax", String.format("%.1f", tempMax));
    }

}
