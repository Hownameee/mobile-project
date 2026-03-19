package com.grouprace.feature.auth.ui;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.grouprace.feature.auth.R;

public class LoginFragment extends Fragment {
    private EditText editEmail, editPassword;
    private Button buttonLogin, buttonBack, buttonGoToRegister;

    private LoginViewModel viewModel;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        // TODO: Use the ViewModel
        initViews(view);
        setupListeners();

        viewModel.getToastMessage().observe(getViewLifecycleOwner(), message -> {
            if (message != null) {
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initViews(View view) {
        editEmail = view.findViewById(R.id.email_edit_text);
        editPassword = view.findViewById(R.id.password_edit_text);

        buttonLogin = view.findViewById(R.id.login_button);
        buttonBack = view.findViewById(R.id.back_button);
        buttonGoToRegister = view.findViewById(R.id.goto_register_button);
    }

    private void setupListeners() {
        buttonBack.setOnClickListener(v -> requireActivity().onBackPressed());
        buttonGoToRegister.setOnClickListener(v -> requireActivity().onBackPressed());

        buttonLogin.setOnClickListener(v -> {
            String username = editEmail.getText().toString().trim();
            String password = editPassword.getText().toString().trim();

            viewModel.login(username, password);
        });
    }
}