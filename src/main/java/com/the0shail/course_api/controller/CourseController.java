package com.the0shail.course_api.controller;

import com.the0shail.course_api.dto.request.course.CreateCourseRequest;
import com.the0shail.course_api.dto.request.course.UpdateCourseRequest;
import com.the0shail.course_api.service.CourseService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/courses")
@AllArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @GetMapping
    public ResponseEntity<?> index() {
        return ResponseEntity.status(200).body(courseService.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        return ResponseEntity.status(200).body(courseService.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid CreateCourseRequest request, Authentication auth) {
        return ResponseEntity.status(201).body(courseService.create(request, auth.getName()));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid UpdateCourseRequest request, Authentication authentication) {
        return ResponseEntity.status(200).body(courseService.update(id, request, authentication.getName()));
    }

    @PostMapping("/{id}/publish")
    public ResponseEntity<?> publish(@PathVariable Long id, Authentication authentication) {
        return ResponseEntity.status(200).body(courseService.published(id, authentication.getName()));
    }

    @PostMapping("/{id}/archive")
    public ResponseEntity<?> archive(@PathVariable Long id, Authentication authentication) {
        return ResponseEntity.status(200).body(courseService.archived(id, authentication.getName()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id, Authentication authentication) {
        courseService.delete(id, authentication.getName());

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/my")
    public ResponseEntity<?> courses(Authentication authentication) {
        return ResponseEntity.status(200).body(courseService.findByAuthorId(authentication.getName()));
    }
}
