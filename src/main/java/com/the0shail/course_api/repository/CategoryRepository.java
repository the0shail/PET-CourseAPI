package com.the0shail.course_api.repository;

import com.the0shail.course_api.entity.Category;
import com.the0shail.course_api.entity.enumerate.PublicationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
