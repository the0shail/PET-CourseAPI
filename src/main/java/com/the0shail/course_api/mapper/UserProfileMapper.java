package com.the0shail.course_api.mapper;

import com.the0shail.course_api.dto.response.user.UserProfileDto;
import com.the0shail.course_api.entity.UserProfile;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface UserProfileMapper {
    UserProfileDto toDto(UserProfile profile);
}

