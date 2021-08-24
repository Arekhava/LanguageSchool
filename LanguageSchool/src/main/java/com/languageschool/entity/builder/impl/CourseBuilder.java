package com.languageschool.entity.builder.impl;

import static com.languageschool.controller.command.ParameterAndAttribute.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

import com.languageschool.entity.Course;
import com.languageschool.entity.builder.EntityBuilder;


/**
 * The builder is responsible for building course
 * 
 * @author N
 */
public class CourseBuilder implements EntityBuilder<Course> {
	private static final CourseBuilder instance = new CourseBuilder();


	private CourseBuilder() {
	}

	/**
	 * Get instance of this class
	 * 
	 * @return {@link CourseBuilder} instance
	 */
	public static CourseBuilder getInstance() {
		return instance;
	}

	@Override
	public Course build(Map<String, String> courseInfo) {
		Course course = new Course();
		String courseId = courseInfo.get(COURSE_ID);
		if (courseId != null) {
			course.setCourseId(Long.valueOf(courseId));
		}
		String languageId =courseInfo.get(LANGUAGE_ID);
		if (languageId!= null) {
			course.setLanguageId(Long.valueOf(languageId));
		}
		course.setCourseName(courseInfo.get(LANGUAGE_NAME));
		course.setImageName(courseInfo.get(IMAGE_NAME));
		Date sqlDate = null;
		try {
			java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(courseInfo.get(NEXT_START));
			sqlDate = new java.sql.Date(date.getYear(), date.getMonth(), date.getDay());
		} catch (ParseException e) {
			
		}  
	    
	    course.setNextStart(sqlDate);
		course.setPrice(new BigDecimal(courseInfo.get(PRICE)));
		return course;
	}
}
