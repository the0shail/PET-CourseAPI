package com.the0shail.course_api.dto.response.user;

import java.time.Instant;

public record UserPublicDto(
        Long id,
        String role,
        UserProfileDto profile,
        Integer publishedCoursesCount,
        Instant createdAt
) {}
