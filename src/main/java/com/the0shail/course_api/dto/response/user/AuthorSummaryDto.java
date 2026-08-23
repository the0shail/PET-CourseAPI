package com.the0shail.course_api.dto.response.user;

public record AuthorSummaryDto(
        Long id,
        String firstName,
        String lastName,
        String avatarUrl
) {}
