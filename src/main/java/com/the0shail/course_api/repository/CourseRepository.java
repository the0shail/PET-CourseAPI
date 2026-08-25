package com.the0shail.course_api.repository;

import com.the0shail.course_api.entity.Course;
import com.the0shail.course_api.entity.enumerate.PublicationStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    Page<Course> findCoursesByAuthorId(Long authorId, Pageable pageable);

    Long countByAuthorIdAndStatus(Long authorId, PublicationStatus status);

    Page<Course> findAll(Pageable pageable);
}
