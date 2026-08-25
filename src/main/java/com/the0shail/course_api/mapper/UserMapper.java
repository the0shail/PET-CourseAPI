package com.the0shail.course_api.mapper;

import com.the0shail.course_api.dto.request.user.SignUpRequest;
import com.the0shail.course_api.dto.response.user.UserBriefDto;
import com.the0shail.course_api.dto.response.user.UserPrivateDto;
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
    @Mapping(target = "firstName", source = "profile.firstName")
    @Mapping(target = "lastName", source = "profile.lastName")
    @Mapping(target = "bio", source = "profile.bio")
    @Mapping(target = "avatarUrl", source = "profile.avatarUrl")
    UserPrivateDto toPrivateDto(User user);

    @Mapping(target = "firstName", source = "profile.firstName")
    @Mapping(target = "lastName", source = "profile.lastName")
    @Mapping(target = "avatarUrl", source = "profile.avatarUrl")
    UserBriefDto toBriefDto(User user);

    @Mapping(target = "firstName", source = "user.profile.firstName")
    @Mapping(target = "lastName", source = "user.profile.lastName")
    @Mapping(target = "bio", source = "user.profile.bio")
    @Mapping(target = "avatarUrl", source = "user.profile.avatarUrl")
    UserPublicDto toPublicDto(User user, Long publishedCoursesCount);

    @Mapping(target = "firstName", source = "user.profile.firstName")
    @Mapping(target = "lastName", source = "user.profile.lastName")
    @Mapping(target = "bio", source = "user.profile.bio")
    @Mapping(target = "publishedCoursesCount", ignore = true)
    UserPublicDto toPublicDto(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "profile", ignore = true)
    @Mapping(target = "courses", ignore = true)
    User toEntity(SignUpRequest request);
}
