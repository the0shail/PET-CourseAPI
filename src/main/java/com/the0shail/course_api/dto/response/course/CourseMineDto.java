package com.the0shail.course_api.dto.response.course;

import com.the0shail.course_api.entity.enumerate.PublicationStatus;

import java.time.Instant;

public record CourseMineDto(
        Long id,
        String title,
        PublicationStatus status,
        String price,
        Integer modulesCount,
        Integer lessonsCount,
        Integer ratingAvg,
        Integer ratingCount,
        Integer studentsCount,
        String[] publishBlockers,
        Instant createdAt,
        Instant updatedAt
){}
