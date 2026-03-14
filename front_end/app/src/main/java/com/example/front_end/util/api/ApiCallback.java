package com.example.front_end.util.api;

import android.content.Context;
import android.widget.Toast;

import com.example.front_end.model.api.ApiResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class ApiCallback<T> implements Callback<ApiResponse<T>> {
  private final Context context;

  public ApiCallback(Context context) {
    this.context = context;
  }

  @Override
  public void onResponse(Call<ApiResponse<T>> call, Response<ApiResponse<T>> response) {
    if (response.isSuccessful() && response.body() != null) {
      ApiResponse<T> body = response.body();

      if (body.getMessage() != null && !body.getMessage().isEmpty()) {
        Toast.makeText(context, body.getMessage(), Toast.LENGTH_SHORT).show();
      }

      onSuccess(body.getData());
    } else {
      String errorMessage = ApiErrorParser.parseError(response);
      Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show();
      onFailureAction(errorMessage);
    }
  }


  @Override
  public void onFailure(Call<ApiResponse<T>> call, Throwable t) {
    String networkError = "Internal server error: " + t.getMessage();
    Toast.makeText(context, networkError, Toast.LENGTH_SHORT).show();
    onFailureAction(networkError);
  }

  protected abstract void onSuccess(T data);

  protected void onFailureAction(String errorMessage) {
  }
}
