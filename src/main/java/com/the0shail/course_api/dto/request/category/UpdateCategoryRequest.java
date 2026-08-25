package com.the0shail.course_api.dto.request.category;

import jakarta.validation.constraints.NotBlank;

public record UpdateCategoryRequest(
        @NotBlank
        String name
) {
}
