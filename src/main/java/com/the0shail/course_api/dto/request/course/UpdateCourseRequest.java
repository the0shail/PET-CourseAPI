package com.the0shail.course_api.dto.request.course;

import jakarta.validation.constraints.DecimalMin;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

public record UpdateCourseRequest(
        @Length(min = 1, max = 255, message = "Название от {min} до {max} символов")
        String title,

        @Length(max = 5000, message = "Описание не длиннее {max} символов")
        String description,

        @DecimalMin(value = "0.0", message = "Цена не может быть отрицательной")
        BigDecimal price
) {}
