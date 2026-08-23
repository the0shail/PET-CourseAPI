package com.the0shail.course_api.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/lessons")
@AllArgsConstructor
public class LessonController {

    @GetMapping("/{id}")
    public void get(@PathVariable Long id){

    }

    @PatchMapping("/{id}")
    public void update(@PathVariable Long id){

    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){

    }
}
