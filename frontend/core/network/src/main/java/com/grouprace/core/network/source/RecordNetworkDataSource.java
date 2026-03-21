package com.grouprace.core.network.source;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.grouprace.core.network.api.RecordApiService;
import com.grouprace.core.network.model.NetworkRecord;
import com.grouprace.core.network.model.RecordPayload;
import com.grouprace.core.network.utils.ApiResponse;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecordNetworkDataSource {

    private final RecordApiService apiService;

    @Inject
    public RecordNetworkDataSource(RecordApiService apiService) {
        this.apiService = apiService;
    }

    public LiveData<List<NetworkRecord>> getRecords() {
        MutableLiveData<List<NetworkRecord>> liveData = new MutableLiveData<>();

        apiService.getRecords().enqueue(new Callback<ApiResponse<RecordPayload>>() {
            @Override
            public void onResponse(Call<ApiResponse<RecordPayload>> call, Response<ApiResponse<RecordPayload>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ApiResponse<RecordPayload> apiResponse = response.body();
                    if (apiResponse.isSuccess() && apiResponse.getData() != null) {
                        Log.d("RecordNetworkDataSource", "Successfully fetched " + apiResponse.getData().getRecords().size() + " records");
                        liveData.postValue(apiResponse.getData().getRecords());
                    } else {
                        Log.e("RecordNetworkDataSource", "API returned success false or null data. Message: " + apiResponse.getMessage());
                        liveData.postValue(null);
                    }
                } else {
                    Log.e("RecordNetworkDataSource", "HTTP Error: " + response.code() + " " + response.message());
                    liveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<RecordPayload>> call, Throwable t) {
                Log.e("RecordNetworkDataSource", "Network Failure: " + t.getMessage(), t);
                liveData.postValue(null);
            }
        });

        return liveData;
    }
}
