package com.the0shail.course_api.service;

import com.the0shail.course_api.dto.request.module.CreateModuleRequest;
import com.the0shail.course_api.dto.request.module.UpdateModuleRequest;
import com.the0shail.course_api.dto.response.module.ModuleSummaryDto;
import com.the0shail.course_api.entity.Course;
import com.the0shail.course_api.entity.Module;
import com.the0shail.course_api.entity.User;
import com.the0shail.course_api.exception.TypeException;
import com.the0shail.course_api.exception.exception.NotFoundException;
import com.the0shail.course_api.mapper.ModuleMapper;
import com.the0shail.course_api.repository.ModuleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Slf4j
public class ModuleService {
    private final ModuleRepository moduleRepository;
    private final CourseService courseService;
    private final UserService userService;
    private final ModuleMapper moduleMapper;

    @Transactional
    public ModuleSummaryDto create(Long courseId, CreateModuleRequest request, String email) {
        Course course = courseService.getById(courseId);
        User me = userService.getByEmail(email);

        if (!course.getAuthor().getId().equals(me.getId()))
            throw new NotFoundException("Курс не найден", TypeException.NOT_FOUND);

        Module module = moduleMapper.toEntity(request);
        module.setCourse(course);

        Module saved = moduleRepository.save(module);

        return moduleMapper.toDto(saved);
    }

    @Transactional
    public ModuleSummaryDto update(Long id, UpdateModuleRequest request, String email) {
        Module module = getById(id);
        User me = userService.getByEmail(email);

        if (!module.getCourse().getAuthor().getId().equals(me.getId()))
            throw new NotFoundException("Курс не найден", TypeException.NOT_FOUND);

        moduleMapper.updateModule(request, module);

        return moduleMapper.toDto(module);
    }

    @Transactional(readOnly = true)
    public Module getById(Long id) {
        return moduleRepository.findById(id).orElseThrow(() -> new NotFoundException("Модуль не найден", TypeException.NOT_FOUND));
    }
}
