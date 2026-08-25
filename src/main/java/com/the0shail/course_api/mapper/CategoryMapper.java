package com.the0shail.course_api.mapper;

import com.the0shail.course_api.dto.response.category.CategoryDto;
import com.the0shail.course_api.entity.Category;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface CategoryMapper {

    @Mapping(target = "publishedCoursesCount", source = "publishedCoursesCount")
    CategoryDto toDto(Category category, Long publishedCoursesCount);
}
