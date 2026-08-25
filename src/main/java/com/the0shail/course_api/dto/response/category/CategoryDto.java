package com.the0shail.course_api.dto.response.category;

public record CategoryDto (
        Long id,
        String name,
        Integer publishedCoursesCount
){}
