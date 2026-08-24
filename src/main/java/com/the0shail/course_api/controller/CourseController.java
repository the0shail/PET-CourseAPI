package com.the0shail.course_api.controller;

import com.the0shail.course_api.dto.request.course.CreateCourseRequest;
import com.the0shail.course_api.dto.request.course.UpdateCourseRequest;
import com.the0shail.course_api.dto.response.course.CourseDetailsResponse;
import com.the0shail.course_api.dto.response.course.CourseSummaryResponse;
import com.the0shail.course_api.service.CourseService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/courses")
@AllArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @GetMapping("/courses")
    public ResponseEntity<?> index() {
        return ResponseEntity.status(200).body(courseService.list());
    }

    @GetMapping("/courses/{id}")
    public ResponseEntity<CourseDetailsResponse> get(@PathVariable Long id) {
        return ResponseEntity.status(200).body(courseService.findById(id));
    }

    @PostMapping("/courses")
    public ResponseEntity<CourseSummaryResponse> create(@RequestBody @Valid CreateCourseRequest request, Authentication auth) {
        return ResponseEntity.status(201).body(courseService.create(request, auth.getName()));
    }

    @PatchMapping("/courses/{id}")
    public ResponseEntity<CourseSummaryResponse> update(@PathVariable Long id, @RequestBody @Valid UpdateCourseRequest request, Authentication authentication) {
        return ResponseEntity.status(200).body(courseService.update(id, request, authentication.getName()));
    }

    @PostMapping("/courses/{id}/publish")
    public ResponseEntity<CourseSummaryResponse> publish(@PathVariable Long id, Authentication authentication) {
        return ResponseEntity.status(200).body(courseService.published(id, authentication.getName()));
    }

    @PostMapping("/courses/{id}/archive")
    public ResponseEntity<CourseSummaryResponse> archive(@PathVariable Long id, Authentication authentication) {
        return ResponseEntity.status(200).body(courseService.archived(id, authentication.getName()));
    }

    @DeleteMapping("/courses/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id, Authentication authentication) {
        courseService.delete(id, authentication.getName());

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/courses/my")
    public ResponseEntity<List<CourseSummaryResponse>> courses(Authentication authentication) {
        return ResponseEntity.status(200).body(courseService.findByAuthorId(authentication.getName()));
    }


}
