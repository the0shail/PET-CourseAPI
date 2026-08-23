package com.the0shail.course_api.dto.response.module;

import com.the0shail.course_api.dto.response.lesson.LessonSummaryDto;

import java.util.List;

public record ModuleDetailsDto(
    Long id,
    String title,
    int orderIndex,
    List<LessonSummaryDto> lessons
) {}
