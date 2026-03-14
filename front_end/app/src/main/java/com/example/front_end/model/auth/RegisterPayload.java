package com.example.front_end.model.auth;

public class RegisterPayload {
    private String username;
    private String fullname;
    private String email;
    private String password;
    private String birthdate;

    public RegisterPayload(String username, String fullname, String email, String birthdate, String password) {
        this.username = username;
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.birthdate = birthdate;
    }
}
