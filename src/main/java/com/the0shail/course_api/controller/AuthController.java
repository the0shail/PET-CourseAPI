package com.the0shail.course_api.controller;

import com.the0shail.course_api.dto.request.auth.RefreshRequest;
import com.the0shail.course_api.dto.request.user.SignInRequest;
import com.the0shail.course_api.dto.request.user.SignUpRequest;
import com.the0shail.course_api.dto.response.auth.JwtAuthenticationResponse;
import com.the0shail.course_api.dto.response.user.UserDto;
import com.the0shail.course_api.entity.User;
import com.the0shail.course_api.service.UserService;
import com.the0shail.course_api.service.auth.JwtService;
import com.the0shail.course_api.service.auth.RefreshTokenService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthController {
    private final UserService userService;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody @Valid SignUpRequest request){
        return new ResponseEntity<>(userService.register(request), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponse> login(@RequestBody @Valid SignInRequest request){
        return new ResponseEntity<>(jwtService.login(request), HttpStatus.OK);
    }

    @PostMapping("/refresh")
    public JwtAuthenticationResponse refresh(@Valid @RequestBody RefreshRequest request) {
        return jwtService.refresh(request);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(Authentication auth) {
        User user = userService.getByEmail(auth.getName());
        refreshTokenService.revokeAll(user.getId());
        return ResponseEntity.noContent().build();
    }
}
