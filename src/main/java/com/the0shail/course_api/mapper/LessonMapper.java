package com.the0shail.course_api.mapper;

import com.the0shail.course_api.dto.request.lesson.CreateLessonRequest;
import com.the0shail.course_api.dto.request.lesson.UpdateLessonRequest;
import com.the0shail.course_api.dto.response.lesson.LessonBriefDto;
import com.the0shail.course_api.dto.response.lesson.LessonDetailDto;
import com.the0shail.course_api.entity.Lesson;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface LessonMapper {
    LessonBriefDto toBriefDto(Lesson lesson);

    @Mapping(target = "moduleId", source = "module.id")
    @Mapping(target = "courseId", source = "module.course.id")
    LessonDetailDto toDetailDto(Lesson lesson);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "module", ignore = true)
    Lesson toEntity(CreateLessonRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "module", ignore = true)
    void updateLesson(UpdateLessonRequest request, @MappingTarget Lesson lesson);
}
