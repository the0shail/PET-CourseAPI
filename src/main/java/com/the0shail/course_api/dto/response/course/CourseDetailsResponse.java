package com.the0shail.course_api.dto.response.course;

import com.the0shail.course_api.dto.response.module.ModuleDetailsDto;
import com.the0shail.course_api.dto.response.user.AuthorSummaryDto;
import com.the0shail.course_api.entity.enumerate.PublicationStatus;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public record CourseDetailsResponse(
        Long id,
        AuthorSummaryDto author,
        String title,
        String description,
        BigDecimal price,
        PublicationStatus status,
        Instant createdAt,
        List<ModuleDetailsDto> modules
) {}
