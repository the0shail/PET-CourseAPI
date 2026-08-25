package com.the0shail.course_api.dto.response.lesson;

public record LessonBriefDto (
        Long id,
        String title,
        Integer orderIndex
){}
