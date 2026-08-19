package com.the0shail.course_api.mapper;

import com.the0shail.course_api.dto.request.course.UpdateCourseRequest;
import com.the0shail.course_api.dto.response.course.CourseDto;
import com.the0shail.course_api.entity.Course;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface CourseMapper {
    CourseDto toDto(Course course);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id",        ignore = true)
    @Mapping(target = "author",    ignore = true)
    @Mapping(target = "status",    ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateCourse(UpdateCourseRequest request, @MappingTarget Course course);
}
