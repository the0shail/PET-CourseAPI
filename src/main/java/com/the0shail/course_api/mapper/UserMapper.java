package com.the0shail.course_api.mapper;

import com.the0shail.course_api.dto.request.user.CreateUserRequest;
import com.the0shail.course_api.dto.response.user.UserDto;
import com.the0shail.course_api.entity.User;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface UserMapper {

    UserDto toDto(User user);

//    @Mapping(ignore = true)
        // id генерит БД, из запроса не берём
    User toEntity(CreateUserRequest request);
}
