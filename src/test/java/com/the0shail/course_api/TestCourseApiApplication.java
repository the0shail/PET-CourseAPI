package com.the0shail.course_api;

import org.springframework.boot.SpringApplication;

public class TestCourseApiApplication {

	public static void main(String[] args) {
		SpringApplication.from(CourseApiApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
