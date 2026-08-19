package com.the0shail.course_api.dto.response.user;

import com.the0shail.course_api.entity.enumerate.Role;

public record UserDto(Long id, String email, Role role, UserProfileDto profile) {}
