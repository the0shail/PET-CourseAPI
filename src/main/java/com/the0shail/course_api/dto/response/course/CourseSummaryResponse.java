package com.the0shail.course_api.dto.response.course;

import com.the0shail.course_api.dto.response.user.AuthorSummaryDto;
import com.the0shail.course_api.entity.enumerate.PublicationStatus;

import java.math.BigDecimal;
import java.time.Instant;

public record CourseSummaryResponse (
        Long id,
        AuthorSummaryDto author,
        String title,
        String description,
        BigDecimal price,
        PublicationStatus status,
        Instant createdAt
){}
