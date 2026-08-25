package com.the0shail.course_api.dto.response.course;

import com.the0shail.course_api.dto.response.category.CategoryDto;
import com.the0shail.course_api.dto.response.module.ModuleDto;
import com.the0shail.course_api.dto.response.user.UserPublicDto;
import com.the0shail.course_api.entity.enumerate.PublicationStatus;

import java.time.Instant;

public record CourseDetailDto (
        Long id,
        String title,
        String description,
        PublicationStatus status,
        String price,
        UserPublicDto author,
        CategoryDto[] categories,
        ModuleDto[] modules,
        Integer ratingAvg,
        Integer ratingCount,
        Integer studentsCount,
        Instant createdAt,
        Instant updatedAt
){}
