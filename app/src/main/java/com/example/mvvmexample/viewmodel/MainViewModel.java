package com.example.mvvmexample.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.Handler;
import android.util.Log;
import com.example.mvvmexample.model.User;

public class MainViewModel extends ViewModel {
    public final MutableLiveData<String> errorPassword = new MutableLiveData<>();
    public final MutableLiveData<String> errorEmail = new MutableLiveData<>();
    public final MutableLiveData<String> email = new MutableLiveData<>();
    public final MutableLiveData<String> password = new MutableLiveData<>();
    public MutableLiveData<Integer> busy;

    public MutableLiveData<Integer> getBusy() {
        if (busy == null) {
            busy = new MutableLiveData<>();
            busy.setValue(8);
        }
        return busy;
    }

    private MutableLiveData<User> userMutableLiveData;

    public LiveData<User> getUser() {
        if (userMutableLiveData == null) {
            userMutableLiveData = new MutableLiveData<>();
        }
        Log.e("getUser", "Yes");
        return userMutableLiveData;
    }

    public void setUser(final User user) {
        if (userMutableLiveData != null)
            userMutableLiveData.setValue(user);
    }

    public void onLoginClicked() {
        Log.e("Clicked", "Yes");
        getBusy().setValue(0); //View.VISIBLE
        final Handler handler = new Handler();
        handler.postDelayed(() -> {

            final User user = new User(email.getValue(), password.getValue());

            if (!user.isEmailValid()) {
                errorEmail.setValue("Enter a valid email address");
            } else {
                errorEmail.setValue(null);
            }


            if (!user.isPasswordLengthGreaterThan5()) {
                errorPassword.setValue("Password Length should be greater than 5");
            } else {
                errorPassword.setValue(null);
            }

            if (user.isEmailValid() && user.isPasswordLengthGreaterThan5()) {
                userMutableLiveData.setValue(user);
            }

            busy.setValue(8); //8 == View.GONE

        }, 3000);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.e("onCleared Main", "Yes");
    }
}
