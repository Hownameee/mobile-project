package com.example.front_end.model.api;

public class ApiResponse<T> {
    private boolean success;
    private T data;
    private String message;

    public boolean isSuccess() {
        return success;
    }

    public T getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }
}
