package com.example.front_end.service.api;


import com.example.front_end.model.api.ApiResponse;
import com.example.front_end.model.auth.LoginPayload;
import com.example.front_end.model.auth.RegisterPayload;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("/auth/register")
    Call<ApiResponse<JsonObject>> register(@Body RegisterPayload payload);

    @POST("/auth/login")
    Call<ApiResponse<String>> login(@Body LoginPayload payload);
}
