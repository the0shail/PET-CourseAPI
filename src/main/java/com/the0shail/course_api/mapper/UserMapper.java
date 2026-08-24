package com.the0shail.course_api.mapper;

import com.the0shail.course_api.dto.request.user.SignUpRequest;
import com.the0shail.course_api.dto.response.user.UserDetailsDto;
import com.the0shail.course_api.dto.response.user.UserPublicDto;
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
    UserDetailsDto toDto(User user);

    @Mapping(target = "publishedCoursesCount", source = "publishedCoursesCount")
    UserPublicDto toPublicDto(User user, Long publishedCoursesCount);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "profile", ignore = true)
    @Mapping(target = "courses", ignore = true)
    User toEntity(SignUpRequest request);
}
