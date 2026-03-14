package com.example.front_end.util.api;

import com.example.front_end.model.api.ApiResponse;
import com.example.front_end.model.api.ZodIssue;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Response;

public class ApiErrorParser {
  private static final Gson gson = new Gson();

  public static String parseError(Response<?> response) {
    if (response.errorBody() == null) return "Unidentified error!";

    try {
      String errorString = response.errorBody().string();

      Type type = new TypeToken<ApiResponse<List<ZodIssue>>>(){}.getType();
      ApiResponse<List<ZodIssue>> errorResponse = gson.fromJson(errorString, type);

      if (errorResponse != null) {
        StringBuilder message = new StringBuilder(
          errorResponse.getMessage() != null ? errorResponse.getMessage() : "Request failed"
        );

        if (errorResponse.getData() != null && !errorResponse.getData().isEmpty()) {
          message.append(": ").append(errorResponse.getData().get(0).getMessage());
        }
        return message.toString();
      };
    } catch (Exception e) {
      e.printStackTrace();
    }

    return "Internal server error!";
  }
}
