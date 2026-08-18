package com.the0shail.course_api.controller;

import com.the0shail.course_api.dto.request.user.UpdateProfileRequest;
import com.the0shail.course_api.dto.response.user.UserDto;
import com.the0shail.course_api.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
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
    public ResponseEntity<UserDto> get(@PathVariable("id") Long id){
        return ResponseEntity.ok(userService.getById(id));
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> me(Authentication authentication){
        return ResponseEntity.ok(userService.getByEmail(authentication.getName()));
    }

    @PatchMapping("/me")
    public ResponseEntity<UserDto> updateMe(@RequestBody @Valid UpdateProfileRequest request, Authentication authentication){
        return ResponseEntity.ok(userService.updateProfile(request, authentication.getName()));
    }

}
