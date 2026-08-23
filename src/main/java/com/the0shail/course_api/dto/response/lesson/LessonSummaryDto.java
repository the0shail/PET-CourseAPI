package com.the0shail.course_api.dto.response.lesson;

public record LessonSummaryDto(
        Long id,
        String title,
        String content,
        Integer orderIndex
) {}
