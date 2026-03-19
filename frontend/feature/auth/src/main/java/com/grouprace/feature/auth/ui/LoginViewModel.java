package com.grouprace.feature.auth.ui;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.grouprace.feature.auth.model.LoginPayload;

public class LoginViewModel extends ViewModel {
    private final MutableLiveData<String> toastMessage = new MutableLiveData<>();

    public LiveData<String> getToastMessage() {
        return toastMessage;
    }

    private LoginPayload payload;

    public void login(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {
            toastMessage.setValue("Please fill in all required fields!");
            return;
        }

        payload = new LoginPayload(username, password);

        Log.d("LOGIN", "login: " + payload.toString());

        toastMessage.setValue("Validation passed! Ready to call API.");
    }
}