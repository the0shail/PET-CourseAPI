package com.the0shail.course_api.service;

import com.the0shail.course_api.dto.request.course.CreateCourseRequest;
import com.the0shail.course_api.dto.request.course.UpdateCourseRequest;
import com.the0shail.course_api.dto.response.course.CourseDto;
import com.the0shail.course_api.entity.Course;
import com.the0shail.course_api.entity.User;
import com.the0shail.course_api.entity.enumerate.PublicationStatus;
import com.the0shail.course_api.exception.TypeException;
import com.the0shail.course_api.exception.exception.BadRequestException;
import com.the0shail.course_api.exception.exception.NotFoundException;
import com.the0shail.course_api.mapper.CourseMapper;
import com.the0shail.course_api.repository.CourseRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;
    private final UserService userService;

    @Transactional(readOnly = true)
    public List<CourseDto> list(){
        return courseRepository.findAll().stream().map(courseMapper::toDto).toList();
    }

    @Transactional(readOnly = true)
    public CourseDto findById(Long id){
        Course course = courseRepository.findById(id).orElseThrow(() -> new NotFoundException("Курс не найден", TypeException.NOT_FOUND));

        return courseMapper.toDto(course);
    }

    public List<CourseDto> findByAuthorId(String email){
        User owner = userService.getByEmail(email);

        return courseRepository.findCoursesByAuthorId(owner.getId()).stream().map(courseMapper::toDto).toList();
    }

    @Transactional(readOnly = true)
    public Course getById(Long id){
        return courseRepository.findById(id).orElseThrow(() -> new NotFoundException("Курс не найден", TypeException.NOT_FOUND));
    }

    @Transactional
    public CourseDto create(CreateCourseRequest request, String email){
        User me = userService.getByEmail(email);

        Course course = new Course();

        me.addCourse(course);
        course.setTitle(request.title());
        course.setDescription(request.description());
        course.setPrice(request.price());
        course.setStatus(PublicationStatus.DRAFT);

        courseRepository.save(course);

        return courseMapper.toDto(course);
    }

    @Transactional
    public CourseDto update(Long id, UpdateCourseRequest request, String email) {
        User user = userService.getByEmail(email);
        Course course = getById(id);

        if (!user.getId().equals(course.getAuthor().getId()))
            throw new NotFoundException("Курс не найден", TypeException.NOT_FOUND);

        courseMapper.updateCourse(request, course);

        return courseMapper.toDto(course);
    }

    @Transactional
    public CourseDto archived(Long id, String email){
        User user = userService.getByEmail(email);
        Course course = getById(id);

        if (!user.getId().equals(course.getAuthor().getId()))
            throw new NotFoundException("Курс не найден", TypeException.NOT_FOUND);

        course.setStatus(PublicationStatus.ARCHIVED);

        return courseMapper.toDto(course);
    }

    @Transactional
    public CourseDto published(Long id, String email){
        User user = userService.getByEmail(email);
        Course course = getById(id);

        if (!user.getId().equals(course.getAuthor().getId()))
            throw new NotFoundException("Курс не найден", TypeException.NOT_FOUND);

        course.setStatus(PublicationStatus.PUBLISHED);

        return courseMapper.toDto(course);
    }

    @Transactional
    public boolean delete(Long id, String email){
        User user = userService.getByEmail(email);
        Course course = getById(id);

        if (!user.getId().equals(course.getAuthor().getId()))
            throw new NotFoundException("Курс не найден", TypeException.NOT_FOUND);

//        if (enrollmentRepository.existsByCourseId(id))
//            throw new BadRequestException(
//                    "Нельзя удалить курс, на который есть записи",
//                    TypeException.COURSE_HAS_ENROLLMENTS);

        courseRepository.delete(course);

        return true;
    }
}
