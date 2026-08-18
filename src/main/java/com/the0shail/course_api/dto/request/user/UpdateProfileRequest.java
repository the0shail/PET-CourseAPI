package com.the0shail.course_api.dto.request.user;

import org.hibernate.validator.constraints.Length;

public record UpdateProfileRequest(
        @Length(min = 1, max = 100, message = "Имя от {min} до {max} символов")
        String firstName,

        @Length(min = 1, max = 100, message = "Фамилия от {min} до {max} символов")
        String lastName,

        @Length(max = 2000, message = "Описание не длиннее {max} символов")
        String bio,

        @Length(max = 500, message = "Ссылка не длиннее {max} символов")
        String avatarUrl
) {}
