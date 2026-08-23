package com.the0shail.course_api.dto.request.lesson;

public record UpdateLessonRequest(
        String title,
        String content,
        Integer orderIndex
) {}
