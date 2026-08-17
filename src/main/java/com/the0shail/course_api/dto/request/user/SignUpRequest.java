package com.the0shail.course_api.dto.request.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;


public record SignUpRequest(
        @NotBlank(message = "Email не должен быть пустым")
        @Email(message = "Некорректный формат email")
        String email,

        @NotBlank(message = "Пароль не должен быть пустым")
        @Length(min = 8, max = 64, message = "Пароль должен содержать от {min} до {max} символов")
        String password
) {}
