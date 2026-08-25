package com.the0shail.course_api.controller;

import com.the0shail.course_api.dto.request.user.UpdateProfileRequest;
import com.the0shail.course_api.dto.response.course.CourseListItemDto;
import com.the0shail.course_api.dto.response.user.UserPrivateDto;
import com.the0shail.course_api.dto.response.user.UserPublicDto;
import com.the0shail.course_api.dto.response.util.Page;
import com.the0shail.course_api.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/users/me")
    public ResponseEntity<UserPrivateDto> me(Authentication authentication){
        return ResponseEntity.ok(userService.findByEmail(authentication.getName()));
    }

    @PatchMapping("/users/me")
    public ResponseEntity<UserPrivateDto> updateMe(@RequestBody @Valid UpdateProfileRequest request, Authentication authentication){
        return ResponseEntity.ok(userService.updateProfile(request, authentication.getName()));
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserPublicDto> get(@PathVariable Long id){
        return ResponseEntity.ok(userService.findById(id));
    }

//    @GetMapping("/users/{id}/courses")
//    public ResponseEntity<Page<CourseListItemDto>> userListCourses(@PathVariable Long id){
////        return ResponseEntity.status(200).body(userService.findById(id));
//
//    }

}
