package com.the0shail.course_api.mapper;

import com.the0shail.course_api.dto.request.category.CreateCategoryRequest;
import com.the0shail.course_api.dto.request.category.UpdateCategoryRequest;
import com.the0shail.course_api.dto.response.category.CategoryDto;
import com.the0shail.course_api.entity.Category;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface CategoryMapper {

    @Mapping(target = "publishedCoursesCount", source = "publishedCoursesCount")
    CategoryDto toDto(Category category, Long publishedCoursesCount);

    @Mapping(target = "id", ignore = true)
    Category toEntity(CreateCategoryRequest request);


    void updateCategory(UpdateCategoryRequest request, @MappingTarget Category category);
}
