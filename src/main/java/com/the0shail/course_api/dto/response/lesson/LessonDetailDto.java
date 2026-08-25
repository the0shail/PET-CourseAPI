package com.the0shail.course_api.dto.response.lesson;

public record LessonDetailDto (
        Long id,
        Long moduleId,
        Long courseId,
        String title,
        String content,
        Integer orderIndex
){}
