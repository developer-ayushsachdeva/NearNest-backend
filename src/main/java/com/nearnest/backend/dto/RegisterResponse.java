package com.nearnest.backend.dto;

public class RegisterResponse {

    private Long id;
    private String name;
    private String email;
    private boolean emailVerified;

    public RegisterResponse(
            Long id,
            String name,
            String email,
            boolean emailVerified
    ) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.emailVerified = emailVerified;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public boolean isEmailVerified() {
        return emailVerified;
    }
}