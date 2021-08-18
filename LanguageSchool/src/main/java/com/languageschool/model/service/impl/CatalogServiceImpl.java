package com.languageschool.model.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.languageschool.controller.command.ParameterAndAttribute;
import com.languageschool.entity.Course;
import com.languageschool.entity.CourseList;
import com.languageschool.entity.Language;
import com.languageschool.entity.builder.impl.CourseBuilder;
import com.languageschool.entity.comparator.CourseComparator;
import com.languageschool.model.dao.CourseDao;
import com.languageschool.model.dao.DaoException;
import com.languageschool.model.dao.impl.CourseDaoImpl;
import com.languageschool.model.dao.impl.LanguageDaoImpl;
import com.languageschool.model.pool.ConnectionPoolException;
import com.languageschool.model.service.CatalogService;
import com.languageschool.model.service.InvalidDataException;
import com.languageschool.model.service.ServiceException;
import com.languageschool.model.service.validator.CourseInfoValidator;
import com.languageschool.model.service.validator.IdValidator;
import com.languageschool.util.MessageKey;
import com.languageschool.model.dao.LanguageDao;

/**
 * The service is responsible for operations with the course catalog
 * 
 * @author N
 * @see CatalogService
 */
public class CatalogServiceImpl implements CatalogService {
	
	private static final Logger logger = LogManager.getLogger();
	private LanguageDao languageDao = new LanguageDaoImpl();
	private CourseDao  courseDao = new CourseDaoImpl();

	@Override
	public void addCourse(Map<String, String> courseInfo) throws ServiceException, InvalidDataException {
		List<String> errorMessageList = CourseInfoValidator.findInvalidData(courseInfo);
		if (courseInfo != null && !IdValidator.isValidId(courseInfo.get(ParameterAndAttribute.LANGUAGE_ID))) {
			errorMessageList.add(MessageKey.ERROR_INCORRECT_COURSE_CATEGORY_MESSAGE);
		}
		
		if (!errorMessageList.isEmpty()) {
			throw new InvalidDataException("invalid data", errorMessageList);
		}
		Course course = CourseBuilder.getInstance().build(courseInfo);
		try {
			courseDao.create(course);
		} catch (DaoException e) {
			throw new ServiceException("course creation error", e);
		}
	}

		


	@Override
	public boolean changeCourseData(Map<String, String> courseInfo)  throws ServiceException, InvalidDataException {
		List<String> errorMessageList = CourseInfoValidator.findInvalidData(courseInfo);
		if (courseInfo != null && !IdValidator.isValidId(courseInfo.get(ParameterAndAttribute.COURSE_ID))) {
			errorMessageList.add(MessageKey.ERROR_INCORRECT_COURSE_ID_MESSAGE);
		}
		if (!errorMessageList.isEmpty()) {
			throw new InvalidDataException("invalid data", errorMessageList);
		}
		Course course = CourseBuilder.getInstance().build(courseInfo);
		boolean courseChanged;
		try {
			courseChanged = courseDao.update(course);
		} catch (DaoException e) {
			throw new ServiceException("course changing error", e);
		}
		return courseChanged;
	}

	@Override
	public List<Language> takeAllLanguages() throws ServiceException {
		List<Language> languages ;
		try {
			languages = languageDao.findAll();
		} catch (DaoException e) {
			throw new ServiceException("languages search error", e);
		}
		return languages;
	}
	

	@Override
	public Optional<Course> takeCourseById(String CourseId) throws ServiceException {
		if (!IdValidator.isValidId(CourseId)) {
			return Optional.empty();
		}
		Optional<Course> courseOptional;
		try {
			courseOptional = courseDao.findCoursesById(CourseId);
		} catch (DaoException e) {
			throw new ServiceException("course search error", e);
		}
		return courseOptional;
	}

	@Override
	public List<Course> takeCoursesByLanguage(String languageId, String sortingMethod) throws ServiceException {
		if (!IdValidator.isValidId(languageId)) {
			return Collections.emptyList();
		}
		List<Course> courses = new ArrayList<>();
		try {
			courses = courseDao.findCoursesByLanguage(languageId);
			if (!courses.isEmpty() && sortingMethod != null) {
				courses.sort(CourseComparator.valueOf(sortingMethod.toUpperCase()).getComporator());
			}
		} catch (DaoException e) {
			throw new ServiceException("courses By language search error", e);
		} catch (IllegalArgumentException e) {
			logger.error("impossible sorting method", e);
		}
		return courses;
	}

	@Override
	public List<Course> takeCoursesWithName(String courseName, String sortingMethod) throws ServiceException {
		if (!CourseInfoValidator.isValidName(courseName)) {
			return Collections.emptyList();
		}
		List<Course> courses = new ArrayList<>();
		try {
			courses = courseDao.findCoursesByName(courseName);
			if (!courses.isEmpty() && sortingMethod != null) {
				courses.sort(CourseComparator.valueOf(sortingMethod.toUpperCase()).getComporator());
			}
		} catch (DaoException e) {
			throw new ServiceException("courses search error", e);
		} catch (IllegalArgumentException e) {
			logger.error("impossible sorting method", e);
		}
		return courses;
	}


}
