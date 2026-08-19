package com.the0shail.course_api.dto.response.course;

import com.the0shail.course_api.dto.response.user.UserDto;
import com.the0shail.course_api.entity.enumerate.PublicationStatus;

import java.math.BigDecimal;

public record CourseDto (
        Long id,
        UserDto author,
        String title,
        String description,
        BigDecimal price,
        PublicationStatus status
){}
