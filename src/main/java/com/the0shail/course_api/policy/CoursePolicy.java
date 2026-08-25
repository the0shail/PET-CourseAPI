package com.the0shail.course_api.policy;

import com.the0shail.course_api.entity.Course;
import com.the0shail.course_api.entity.User;
import com.the0shail.course_api.entity.enumerate.Role;
import com.the0shail.course_api.exception.TypeException;
import com.the0shail.course_api.exception.exception.ForbiddenException;
import com.the0shail.course_api.provider.CurrentUserProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CoursePolicy {
    private final CurrentUserProvider userProvider;

    public boolean isOwner(Course course) {
        return course.getAuthor().getId().equals(userProvider.require().getId());
    }

    public boolean isAdmin() {
        return userProvider.require().getRole() == Role.ADMIN;
    }

    public boolean isOwnerOrAdmin(Course course) {
        return isAdmin() || isOwner(course);
    }

    public void ensureOwnerOrAdmin(Course course) {
        if (!isOwnerOrAdmin(course)) {
            throw new ForbiddenException("У вас нет прав на этот ресурс", TypeException.FORBIDDEN);
        }
    }

    public void ensureAdmin() {
        if (!isAdmin()) {
            throw new ForbiddenException("У вас нет прав на этот ресурс", TypeException.FORBIDDEN);
        }
    }

}
