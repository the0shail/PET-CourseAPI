package com.the0shail.course_api.dto.response.auth;

public record AuthTokens(
        String accessToken,
        String refreshToken,
        String tokenType,
        long expiresIn
){}
