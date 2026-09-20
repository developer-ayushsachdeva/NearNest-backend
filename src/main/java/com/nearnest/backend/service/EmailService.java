package com.nearnest.backend.service;

import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.CreateEmailOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final Resend resend;

    public EmailService(
            @Value("${resend.api.key}") String apiKey
    ) {
        this.resend = new Resend(apiKey);
    }

    public void sendVerificationEmail(String to, String token) {

        String verificationLink =
                "http://localhost:5173/verify-email?token=" + token;

        CreateEmailOptions params = CreateEmailOptions.builder()
                .from("NearNest <onboarding@resend.dev>")
                .to(to)
                .subject("Verify your NearNest email")
                .html(
                        "<h2>Welcome to NearNest!</h2>" +
                                "<p>Please verify your email address to continue.</p>" +
                                "<p>" +
                                "<a href=\"" + verificationLink + "\">" +
                                "Verify My Email" +
                                "</a>" +
                                "</p>" +
                                "<p>This link will expire in 24 hours.</p>"
                )
                .build();

        try {
            resend.emails().send(params);
        } catch (ResendException exception) {
            throw new RuntimeException("Failed to send verification email", exception);
        }
    }
}