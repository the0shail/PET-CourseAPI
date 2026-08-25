package com.the0shail.course_api.dto.response.user;

import java.time.Instant;

public record UserPrivateDto(
        Long id,
        String email,
        String firstName,
        String lastName,
        String bio,
        String avatarUrl,
        String role,
        Instant createdAt
) {
}
