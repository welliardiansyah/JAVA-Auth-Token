package com.auth_services.auth_services.payload.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;

import java.util.Set;

public class SignupRequest {
    @Size(min = 3, max = 20)
    private String name;

    @Size(min = 3, max = 20)
    private String username;

    @Size(max = 50)
    @Email
    private String email;

    @NotEmpty
    private Set<String> role;

    @Size(min = 6, max = 40)
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRole() {
        return this.role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }
}
