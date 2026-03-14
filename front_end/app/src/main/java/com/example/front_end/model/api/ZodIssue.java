package com.example.front_end.model.api;

import java.util.List;

public class ZodIssue {
  private List<String> path;
  private String message;

  public List<String> getPath() {
    return path;
  }

  public String getMessage() {
    return message;
  }
}