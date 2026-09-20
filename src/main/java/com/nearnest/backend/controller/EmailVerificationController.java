package com.nearnest.backend.controller;

import com.nearnest.backend.entity.User;
import com.nearnest.backend.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/auth")
public class EmailVerificationController {

    private final UserRepository userRepository;

    public EmailVerificationController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/verify-email")
    public String verifyEmail(@RequestParam String token) {

        User user = userRepository.findByVerificationToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid verification token"));

        if (user.getVerificationTokenExpiry() == null ||
                user.getVerificationTokenExpiry().isBefore(LocalDateTime.now())) {

            throw new RuntimeException("Verification token has expired");
        }

        user.setEmailVerified(true);
        user.setVerificationToken(null);
        user.setVerificationTokenExpiry(null);

        userRepository.save(user);

        return "Email verified successfully. You can now login.";
    }
}
