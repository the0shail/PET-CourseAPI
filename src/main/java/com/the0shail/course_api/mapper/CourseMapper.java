package com.the0shail.course_api.mapper;

import com.the0shail.course_api.dto.request.course.CreateCourseRequest;
import com.the0shail.course_api.dto.request.course.UpdateCourseRequest;
import com.the0shail.course_api.dto.response.course.CourseDetailsResponse;
import com.the0shail.course_api.dto.response.course.CourseSummaryResponse;
import com.the0shail.course_api.entity.Course;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface CourseMapper {

    @Mapping(target = "author.id", source = "author.id")
    @Mapping(target = "author.firstName", source = "author.profile.firstName")
    @Mapping(target = "author.lastName", source = "author.profile.lastName")
    @Mapping(target = "author.avatarUrl", source = "author.profile.avatarUrl")
    CourseSummaryResponse toDto(Course course);

    @Mapping(target = "author.id", source = "author.id")
    @Mapping(target = "author.firstName", source = "author.profile.firstName")
    @Mapping(target = "author.lastName", source = "author.profile.lastName")
    @Mapping(target = "author.avatarUrl", source = "author.profile.avatarUrl")
    CourseDetailsResponse toDetailsDto(Course course);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "status", constant = "DRAFT")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Course toEntity(CreateCourseRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateCourse(UpdateCourseRequest request, @MappingTarget Course course);
}
