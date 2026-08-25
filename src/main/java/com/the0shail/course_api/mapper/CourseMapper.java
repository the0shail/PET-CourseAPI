package com.the0shail.course_api.mapper;

import com.the0shail.course_api.dto.request.course.CreateCourseRequest;
import com.the0shail.course_api.dto.request.course.UpdateCourseRequest;
import com.the0shail.course_api.dto.response.course.CourseDetailDto;
import com.the0shail.course_api.dto.response.course.CourseListItemDto;
import com.the0shail.course_api.dto.response.course.CourseMineDto;
import com.the0shail.course_api.entity.Course;
import com.the0shail.course_api.mapper.helper.FormatHelper;
import org.mapstruct.*;

@Mapper(
        uses = {
                FormatHelper.class, UserMapper.class, ModuleMapper.class, CategoryMapper.class
        },
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface CourseMapper {

    @Mapping(target = "shortDescription", source = "description", qualifiedByName = "truncate200")
    @Mapping(target = "price", source = "price", qualifiedByName = "formatPrice")
    @Mapping(target = "ratingAvg", ignore = true)
    @Mapping(target = "ratingCount", ignore = true)
    @Mapping(target = "studentsCount", ignore = true)
    CourseListItemDto toListItemDto(Course course);

    @Mapping(target = "price", source = "price", qualifiedByName = "formatPrice")
    @Mapping(target = "ratingAvg", ignore = true)
    @Mapping(target = "ratingCount", ignore = true)
    @Mapping(target = "studentsCount", ignore = true)
    CourseDetailDto toDetailsDto(Course course);

    @Mapping(target = "price", source = "price", qualifiedByName = "formatPrice")
    @Mapping(target = "ratingAvg", ignore = true)
    @Mapping(target = "ratingCount", ignore = true)
    @Mapping(target = "studentsCount", ignore = true)
    @Mapping(target = "modulesCount", ignore = true)
    @Mapping(target = "lessonsCount", ignore = true)
    @Mapping(target = "publishBlockers", ignore = true)
    CourseMineDto toMineDto(Course course);

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
