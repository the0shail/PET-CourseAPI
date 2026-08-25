package com.the0shail.course_api.dto.response.course;

import com.the0shail.course_api.dto.response.category.CategoryDto;
import com.the0shail.course_api.dto.response.user.UserBriefDto;

import java.time.Instant;

public record CourseListItemDto (
        Long id,
        String title,
        String shortDescription,
        String price,
        UserBriefDto author,
        CategoryDto[] categories,
        Integer ratingAvg,
        Integer ratingCount,
        Integer studentsCount,
        Instant createdAt
){}
