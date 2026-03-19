package com.grouprace.feature.auth.ui;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.JsonObject;
import com.grouprace.feature.auth.model.ApiResponse;
import com.grouprace.feature.auth.model.RegisterPayload;
import com.grouprace.feature.auth.service.ApiClient;
import com.grouprace.feature.auth.service.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private final MutableLiveData<String> toastMessage = new MutableLiveData<>();
    public LiveData<String> getToastMessage() {
        return toastMessage;
    }

    private final MutableLiveData<Boolean> registerSuccess = new MutableLiveData<>();
    public LiveData<Boolean> getRegisterSuccess() { return  registerSuccess; }

    public void register(String username, String fullname, String email, String birthdate, String password, String confirmPassword) {
        if (username.isEmpty() || fullname.isEmpty() || email.isEmpty() ||
                birthdate.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            toastMessage.setValue("Please fill in all required fields!");
            return;
        }

        if (!password.equals(confirmPassword)) {
            toastMessage.setValue("Passwords do not match!");
            return;
        }

        RegisterPayload payload = new RegisterPayload(username, fullname, email, birthdate, password);

        ApiService apiService = ApiClient.getApiService();

        apiService.register(payload).enqueue(new Callback<ApiResponse<JsonObject>>() {
            @Override
            public void onResponse(Call<ApiResponse<JsonObject>> call, Response<ApiResponse<JsonObject>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    toastMessage.setValue("Registered successfully");
                    registerSuccess.setValue(true);
                } else {
                    toastMessage.setValue("Registration failed! Please try again.");
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<JsonObject>> call, Throwable t) {
                toastMessage.setValue("Network error: " + t.getMessage());
            }
        });

        Log.d("REGISTER", "register: " + payload.toString());
    }
}