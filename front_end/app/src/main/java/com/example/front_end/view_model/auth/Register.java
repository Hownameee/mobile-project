package com.example.front_end.view_model.auth;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.front_end.R;
import com.example.front_end.model.auth.RegisterPayload;
import com.example.front_end.service.api.ApiClient;
import com.example.front_end.service.api.ApiService;
import com.example.front_end.util.api.ApiCallback;
import com.google.gson.JsonObject;

public class Register extends AppCompatActivity {
  private EditText editUsername, editFullname, editEmail, editBirthdate, editPassword, editConfirmPassword;
  private Button buttonRegister, buttonBack, buttonGoToLogin;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.auth_activity_register);

    initViews();
    setupListeners();
  }


  private void initViews() {
    editUsername = findViewById(R.id.username_edit_text);
    editFullname = findViewById(R.id.fullname_edit_text);
    editEmail = findViewById(R.id.email_edit_text);
    editBirthdate = findViewById(R.id.birthdate_edit_text);
    editPassword = findViewById(R.id.password_edit_text);
    editConfirmPassword = findViewById(R.id.confirm_password_edit_text);

    buttonBack = findViewById(R.id.back_button);
    buttonRegister = findViewById(R.id.register_button);
    buttonGoToLogin = findViewById(R.id.goto_login_button);
  }

  private void setupListeners() {
    buttonBack.setOnClickListener(v -> finish());
    buttonGoToLogin.setOnClickListener(v -> {
      Intent intent = new Intent(Register.this, Login.class);
      startActivity(intent);
    });
    buttonRegister.setOnClickListener(v -> register());
  }

  private void register() {
    String username = editUsername.getText().toString().trim();
    String fullname = editFullname.getText().toString().trim();
    String email = editEmail.getText().toString().trim();
    String birthdate = editBirthdate.getText().toString().trim();
    String password = editPassword.getText().toString().trim();
    String confirmPassword = editConfirmPassword.getText().toString().trim();

    if (username.isEmpty() || fullname.isEmpty() || email.isEmpty() || birthdate.isEmpty() || password.isEmpty()) {
      Toast.makeText(this, "Please fill in all required fields!", Toast.LENGTH_SHORT).show();
      return;
    }
    
    if (!password.equals(confirmPassword)) {
      Toast.makeText(this, "Passwords do not match!", Toast.LENGTH_SHORT).show();
      return;
    }

    RegisterPayload payload = new RegisterPayload(username, fullname, email, birthdate, password);
    ApiService apiService = ApiClient.getApiService();

    apiService.register(payload).enqueue(new ApiCallback<JsonObject>(this) {
      @Override
      protected void onSuccess(JsonObject data) {
        finish();
      }
    });
  }
}