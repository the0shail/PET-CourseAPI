package com.the0shail.course_api.dto.response.user;

public record UserProfileDto(
        String firstName,
        String lastName,
        String bio,
        String avatarUrl
) {
}
