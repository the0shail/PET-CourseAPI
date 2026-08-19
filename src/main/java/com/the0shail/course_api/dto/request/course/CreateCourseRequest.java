package com.the0shail.course_api.dto.request.course;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

public record CreateCourseRequest(
        @NotBlank(message = "Название не должно быть пустым")
        @Length(max = 255) String title,

        String description,

        @NotNull(message = "Цена обязательна")
        @DecimalMin(value = "0.0", message = "Цена не может быть отрицательной")
        BigDecimal price
) {}
