package com.grouprace.core.network.api;

import com.grouprace.core.network.model.RecordPayload;
import com.grouprace.core.network.utils.ApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RecordApiService {
    @GET("api/users/1/records")
    Call<ApiResponse<RecordPayload>> getRecords();
}
