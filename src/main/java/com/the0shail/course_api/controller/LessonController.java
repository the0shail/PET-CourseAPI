package com.the0shail.course_api.controller;

import com.the0shail.course_api.dto.request.lesson.CreateLessonRequest;
import com.the0shail.course_api.dto.response.lesson.LessonDetailDto;
import com.the0shail.course_api.service.LessonService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class LessonController {
    private final LessonService lessonService;

    @GetMapping("/lessons/{id}")
    public void get(@PathVariable Long id){

    }

    @PatchMapping("/lessons/{id}")
    public void update(@PathVariable Long id){

    }

    @DeleteMapping("/lessons/{id}")
    public void delete(@PathVariable Long id){

    }

    @GetMapping("/modules/{moduleId}/lessons")
    public void getModulesLessons(@PathVariable Long moduleId){

    }

    @PostMapping("/modules/{moduleId}/lessons")
    public ResponseEntity<LessonDetailDto> lessons(@PathVariable Long moduleId, @RequestBody @Valid CreateLessonRequest request, Authentication authentication){
        return ResponseEntity.status(201).body(lessonService.create(moduleId, request, authentication.getName()));
    }
}
