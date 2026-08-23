package com.the0shail.course_api.dto.request.module;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record CreateModuleRequest (
        @NotBlank
        String title,

        @Positive
        Integer orderIndex
){}
