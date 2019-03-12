package com.example.mvvmexample.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import com.example.mvvmexample.R;
import com.example.mvvmexample.databinding.ActivityMainBinding;
import com.example.mvvmexample.util.Utility;
import com.example.mvvmexample.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        binding.setMainViewModel(viewModel);
        binding.setLifecycleOwner(this);
        userObserve(viewModel);
    }

    private void userObserve(final MainViewModel viewModel) {
        viewModel.getUser().observe(this, user -> {
            if (user != null) {
                Log.e("observe", "Yes");
                clearBind();
                goWeather();
            }
            // Rotasyondan Geldi //
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Ekran Rotasyonda livedata yapısı observe içindeki datayla tetikler // Bu tetikleme sonucu
        // Kontrol noktası aşar ve yeni activity açar // Bu da soruna sebeb olacağı için
        // Rotasyon işleminde ekranı atlatmaması için livedata null yapılmıştır !
        if (viewModel != null) {
            viewModel.setUser(null);
        }
    }

    // MainActivity ile işimiz bittği için
    // artık observe dinleyici referansların siliyoruz !
    private void clearBind() {
        if (binding != null) {
            binding.unbind();
            binding = null;
            viewModel = null;
        }
    }

    private void goWeather() {
        finish();
        Utility.openIntent(this, WeatherActivity.class);
    }
}
