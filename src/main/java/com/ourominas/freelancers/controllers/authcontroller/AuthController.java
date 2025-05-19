package com.ourominas.freelancers.controllers.authcontroller;

import com.ourominas.freelancers.domain.Users;
import com.ourominas.freelancers.domain.dto.request.AuthRequest;
import com.ourominas.freelancers.domain.dto.request.AuthResponse;
import com.ourominas.freelancers.repositories.UserRepository;
import com.ourominas.freelancers.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    @PostMapping("/login")
    public AuthResponse login(@RequestBody @Validated AuthRequest request) {
        try {
            var authToken = new UsernamePasswordAuthenticationToken(request.email(), request.password());
            authenticationManager.authenticate(authToken);

            Users user = userRepository.findByEmail(request.email())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            String token = jwtService.generateToken(user);

            return new AuthResponse(token);
        } catch (AuthenticationException e) {
            throw new RuntimeException("Invalid credentials");
        }
    }
}
