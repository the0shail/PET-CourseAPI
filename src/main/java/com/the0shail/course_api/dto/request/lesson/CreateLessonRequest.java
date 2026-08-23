package com.the0shail.course_api.dto.request.lesson;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record CreateLessonRequest(
        @NotBlank
        String title,

        String content,

        @Positive
        Integer orderIndex
) {}
