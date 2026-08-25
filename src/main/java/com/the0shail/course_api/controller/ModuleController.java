package com.the0shail.course_api.controller;

import com.the0shail.course_api.dto.request.module.CreateModuleRequest;
import com.the0shail.course_api.dto.request.module.UpdateModuleRequest;
import com.the0shail.course_api.dto.response.module.ModuleDto;
import com.the0shail.course_api.service.ModuleService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class ModuleController {
    private final ModuleService moduleService;

    @GetMapping("/modules/{id}")
    public ResponseEntity<ModuleDto> get(@PathVariable Long id){
        return ResponseEntity.ok().body(moduleService.findById(id));
    }

    @PatchMapping("/modules/{id}")
    public ResponseEntity<ModuleDto> update(@PathVariable Long id, @RequestBody @Valid UpdateModuleRequest request, Authentication authentication){
        return ResponseEntity.status(200).body(moduleService.update(id, request, authentication.getName()));
    }

    @DeleteMapping("/modules/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, Authentication authentication){
        moduleService.delete(id, authentication.getName());

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/courses/{courseId}/modules")
    public ResponseEntity<List<ModuleDto>> list(@PathVariable Long courseId){
        return ResponseEntity.ok().body(moduleService.list(courseId));
    }

    @PostMapping("/courses/{courseId}/modules")
    public ResponseEntity<ModuleDto> createModule(@PathVariable Long courseId, @RequestBody @Valid CreateModuleRequest request, Authentication authentication){
        return ResponseEntity.status(201).body(moduleService.create(courseId, request, authentication.getName()));
    }


}
