package com.the0shail.course_api.service;

import com.the0shail.course_api.dto.request.category.CreateCategoryRequest;
import com.the0shail.course_api.dto.request.category.UpdateCategoryRequest;
import com.the0shail.course_api.dto.response.category.CategoryDto;
import com.the0shail.course_api.entity.Category;
import com.the0shail.course_api.entity.enumerate.PublicationStatus;
import com.the0shail.course_api.exception.TypeException;
import com.the0shail.course_api.exception.exception.NotFoundException;
import com.the0shail.course_api.mapper.CategoryMapper;
import com.the0shail.course_api.repository.CategoryRepository;
import com.the0shail.course_api.repository.CourseRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CourseRepository courseRepository;
    private final CategoryMapper categoryMapper;

    @Transactional(readOnly = true)
    public List<CategoryDto> findAll() {
        return categoryRepository.findAll()
                .stream()
                .map(category -> categoryMapper
                        .toDto(category, courseRepository.countByCategoriesIdAndStatus(category.getId(), PublicationStatus.PUBLISHED)))
                .toList();
    }

    @Transactional(readOnly = true)
    public CategoryDto findById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new NotFoundException("Категория не найдена", TypeException.NOT_FOUND));
        Long countCoursesInCategory = courseRepository.countByCategoriesIdAndStatus(category.getId(), PublicationStatus.PUBLISHED);

        return categoryMapper.toDto(category, countCoursesInCategory);
    }

    @Transactional
    public CategoryDto create(CreateCategoryRequest request) {
        Category category = categoryMapper.toEntity(request);
        Category saved = categoryRepository.save(category);

        return categoryMapper.toDto(saved, 0L);
    }

    @Transactional
    public CategoryDto update(Long id, UpdateCategoryRequest request) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new NotFoundException("Категория не найдена", TypeException.NOT_FOUND));
        Long countCoursesInCategory = courseRepository.countByCategoriesIdAndStatus(category.getId(), PublicationStatus.PUBLISHED);

        categoryMapper.updateCategory(request, category);

        return categoryMapper.toDto(category, countCoursesInCategory);
    }

    @Transactional
    public void delete(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new NotFoundException("Категория не найдена", TypeException.NOT_FOUND));
        categoryRepository.delete(category);
    }
}
