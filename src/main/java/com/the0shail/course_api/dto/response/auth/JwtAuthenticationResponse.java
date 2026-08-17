package com.the0shail.course_api.dto.response.auth;

public record JwtAuthenticationResponse(
        String token,
        String tokenType,
        long expiresIn
){}
