package com.example.front_end.view_model.auth;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.front_end.R;
import com.example.front_end.model.auth.LoginPayload;
import com.example.front_end.service.api.ApiClient;
import com.example.front_end.service.api.ApiService;
import com.example.front_end.util.api.ApiCallback;
import com.google.gson.JsonObject;

public class Login extends AppCompatActivity {
  private EditText editUsername, editPassword;
  private Button buttonLogin, buttonBack, buttonGoToRegister;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    EdgeToEdge.enable(this);
    setContentView(R.layout.auth_activity_login);

    initViews();
    setupListeners();
  }

  private void initViews() {
    editUsername = findViewById(R.id.username_edit_text);
    editPassword = findViewById(R.id.password_edit_text);

    buttonLogin = findViewById(R.id.login_button);
    buttonBack = findViewById(R.id.back_button);
    buttonGoToRegister = findViewById(R.id.goto_register_button);
  }

  private void setupListeners() {
    buttonBack.setOnClickListener(v -> finish());

    buttonGoToRegister.setOnClickListener(v -> {
      Intent intent = new Intent(Login.this, Register.class);
      startActivity(intent);
    });

    buttonLogin.setOnClickListener(v -> login());
  }

  private void login() {
    String username = editUsername.getText().toString().trim();
    String password = editPassword.getText().toString().trim();

    if (username.isEmpty() || password.isEmpty()) {
      Toast.makeText(this, "Please fill in all required fields!", Toast.LENGTH_SHORT).show();
      return;
    }

    LoginPayload payload = new LoginPayload(username, password);
    ApiService apiService = ApiClient.getApiService();

    apiService.login(payload).enqueue(new ApiCallback<String>(this) {
      @Override
      protected void onSuccess(String data) {
        String token = data;
        Log.d("AUTH_DEBUG", "Token: " + token);
        saveTokenToSharedPreferences(token);
        Toast.makeText(Login.this, "Logged in successfully!", Toast.LENGTH_SHORT).show();
        finish();
      }
    });
  }

  private void saveTokenToSharedPreferences(String token) {
    SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
    SharedPreferences.Editor editor = sharedPreferences.edit();
    editor.putString("auth_token", token);
    editor.apply();
  }
}