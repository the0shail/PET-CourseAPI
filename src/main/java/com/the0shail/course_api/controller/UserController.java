package com.the0shail.course_api.controller;

import com.the0shail.course_api.dto.request.user.UpdateProfileRequest;
import com.the0shail.course_api.dto.response.user.UserDetailsDto;
import com.the0shail.course_api.dto.response.user.UserPublicDto;
import com.the0shail.course_api.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserPublicDto> get(@PathVariable Long id){
        return ResponseEntity.ok(userService.findById(id));
    }

    @GetMapping("/me")
    public ResponseEntity<UserDetailsDto> me(Authentication authentication){
        return ResponseEntity.ok(userService.findByEmail(authentication.getName()));
    }

    @PatchMapping("/me")
    public ResponseEntity<UserDetailsDto> updateMe(@RequestBody @Valid UpdateProfileRequest request, Authentication authentication){
        return ResponseEntity.ok(userService.updateProfile(request, authentication.getName()));
    }

}
