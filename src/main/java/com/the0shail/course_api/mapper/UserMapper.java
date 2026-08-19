package com.the0shail.course_api.mapper;

import com.the0shail.course_api.dto.request.user.SignUpRequest;
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

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", constant = "STUDENT")
    @Mapping(target = "profile", ignore = true)
    @Mapping(target = "courses", ignore = true)
    User toEntity(SignUpRequest request);
}
