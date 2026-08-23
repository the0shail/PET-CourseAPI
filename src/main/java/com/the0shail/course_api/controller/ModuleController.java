package com.the0shail.course_api.controller;

import com.the0shail.course_api.dto.request.lesson.CreateLessonRequest;
import com.the0shail.course_api.dto.request.module.UpdateModuleRequest;
import com.the0shail.course_api.dto.response.lesson.LessonSummaryDto;
import com.the0shail.course_api.dto.response.module.ModuleSummaryDto;
import com.the0shail.course_api.service.LessonService;
import com.the0shail.course_api.service.ModuleService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/modules")
@AllArgsConstructor
public class ModuleController {
    private final ModuleService moduleService;
    private final LessonService lessonService;

    @PatchMapping("/{id}")
    public ResponseEntity<ModuleSummaryDto> update(@PathVariable Long id, @RequestBody @Valid UpdateModuleRequest request, Authentication authentication){
        return ResponseEntity.status(200).body(moduleService.update(id, request, authentication.getName()));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){

    }

    @PostMapping("/{id}/lessons")
    public ResponseEntity<LessonSummaryDto> lessons(@PathVariable("id") Long moduleId, @RequestBody @Valid CreateLessonRequest request, Authentication authentication){
        return ResponseEntity.status(201).body(lessonService.create(moduleId, request, authentication.getName()));
    }


}
