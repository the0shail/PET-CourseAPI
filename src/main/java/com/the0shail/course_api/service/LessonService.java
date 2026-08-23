package com.the0shail.course_api.service;

import com.the0shail.course_api.dto.request.lesson.CreateLessonRequest;
import com.the0shail.course_api.dto.response.lesson.LessonSummaryDto;
import com.the0shail.course_api.entity.Lesson;
import com.the0shail.course_api.entity.Module;
import com.the0shail.course_api.entity.User;
import com.the0shail.course_api.exception.TypeException;
import com.the0shail.course_api.exception.exception.NotFoundException;
import com.the0shail.course_api.mapper.LessonMapper;
import com.the0shail.course_api.repository.LessonRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@AllArgsConstructor
public class LessonService {
    private final LessonRepository lessonRepository;
    private final ModuleService moduleService;
    private final UserService userService;
    private final LessonMapper lessonMapper;

    @Transactional
    public LessonSummaryDto create(Long moduleId, CreateLessonRequest request, String email){
        Module module = moduleService.getById(moduleId);
        User user = userService.getByEmail(email);

        if (!module.getCourse().getAuthor().getId().equals(user.getId()))
            throw new NotFoundException("Курс не найден", TypeException.NOT_FOUND);

        Lesson lesson = lessonMapper.toEntity(request);
        lesson.setModule(module);

        Lesson saved = lessonRepository.save(lesson);

        return lessonMapper.toDto(saved);
    }
}
