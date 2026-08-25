package com.the0shail.course_api.dto.response.module;

import com.the0shail.course_api.dto.response.lesson.LessonBriefDto;

public record ModuleDto (
        Long id,
        Long courseId,
        String title,
        Integer orderIndex,
        LessonBriefDto[] lessons
){}
