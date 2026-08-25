package com.the0shail.course_api.controller;


import com.the0shail.course_api.dto.request.category.CreateCategoryRequest;
import com.the0shail.course_api.dto.request.category.UpdateCategoryRequest;
import com.the0shail.course_api.dto.response.category.CategoryDto;
import com.the0shail.course_api.service.CategoryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDto>> list(){
        return ResponseEntity.ok(categoryService.findAll());
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<CategoryDto> get(@PathVariable Long id){
        return ResponseEntity.ok(categoryService.findById(id));
    }

    @PostMapping("/categories")
    public ResponseEntity<CategoryDto> create(@RequestBody @Valid CreateCategoryRequest request){
        return ResponseEntity.status(201).body(categoryService.create(request));
    }

    @PatchMapping("/categories/{id}")
    public ResponseEntity<CategoryDto> update(@PathVariable Long id, @RequestBody @Valid UpdateCategoryRequest request){
        return ResponseEntity.ok(categoryService.update(id, request));
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        categoryService.delete(id);

        return ResponseEntity.noContent().build();
    }

}
