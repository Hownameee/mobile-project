package com.grouprace.feature.auth.service;

import com.google.gson.JsonObject;
import com.grouprace.feature.auth.model.ApiResponse;
import com.grouprace.feature.auth.model.LoginPayload;
import com.grouprace.feature.auth.model.RegisterPayload;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("/auth/register")
    Call<ApiResponse<JsonObject>> register(@Body RegisterPayload payload);

    @POST("/auth/login")
    Call<ApiResponse<String>> login(@Body LoginPayload payload);
}