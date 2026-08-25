package com.the0shail.course_api.controller;

import com.the0shail.course_api.dto.request.course.CreateCourseRequest;
import com.the0shail.course_api.dto.request.course.UpdateCourseRequest;
import com.the0shail.course_api.dto.response.course.CourseDetailDto;
import com.the0shail.course_api.dto.response.course.CourseListItemDto;
import com.the0shail.course_api.dto.response.course.CourseMineDto;
import com.the0shail.course_api.dto.response.util.Page;
import com.the0shail.course_api.service.CourseService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @GetMapping("/courses")
    public Page<CourseListItemDto> index(@PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        return courseService.list(pageable);
    }

    @GetMapping("/courses/{id}")
    public ResponseEntity<CourseDetailDto> get(@PathVariable Long id) {
        return ResponseEntity.status(200).body(courseService.findById(id));
    }

    @PostMapping("/courses")
    public ResponseEntity<CourseDetailDto> create(@RequestBody @Valid CreateCourseRequest request, Authentication auth) {
        return ResponseEntity.status(201).body(courseService.create(request, auth.getName()));
    }

    @PatchMapping("/courses/{id}")
    public ResponseEntity<CourseDetailDto> update(@PathVariable Long id, @RequestBody @Valid UpdateCourseRequest request, Authentication authentication) {
        return ResponseEntity.status(200).body(courseService.update(id, request, authentication.getName()));
    }

    @PostMapping("/courses/{id}/publish")
    public ResponseEntity<CourseDetailDto> publish(@PathVariable Long id, Authentication authentication) {
        return ResponseEntity.status(200).body(courseService.published(id, authentication.getName()));
    }

    @PostMapping("/courses/{id}/archive")
    public ResponseEntity<CourseDetailDto> archive(@PathVariable Long id, Authentication authentication) {
        return ResponseEntity.status(200).body(courseService.archived(id, authentication.getName()));
    }

    @DeleteMapping("/courses/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, Authentication authentication) {
        courseService.delete(id, authentication.getName());

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/courses/my")
    public Page<CourseMineDto> courses(@PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable, Authentication authentication) {
        return courseService.findByAuthorId(pageable, authentication.getName());
    }


}
