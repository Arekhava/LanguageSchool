package com.arekhava.languageschool.model.service.impl;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.arekhava.languageschool.controller.command.ParameterAndAttribute;
import com.arekhava.languageschool.entity.Course;
import com.arekhava.languageschool.entity.Language;
import com.arekhava.languageschool.model.dao.CourseDao;
import com.arekhava.languageschool.model.dao.DaoException;
import com.arekhava.languageschool.model.dao.LanguageDao;
import com.arekhava.languageschool.model.service.InvalidDataException;
import com.arekhava.languageschool.model.service.ServiceException;


public class CatalogServiceImplTest {
	@Mock
	private LanguageDao languageDao;
	@Mock
	private CourseDao courseDao;
	@Mock
	private Map<String, String> courseInfo;
	private Course course;
	private AutoCloseable autoCloseable;
	@InjectMocks
	private CatalogServiceImpl catalogService;

	@BeforeClass
	public void setUp() {
		courseInfo = new HashMap<>();
		courseInfo.put(ParameterAndAttribute.COURSE_ID, "1");
		courseInfo.put(ParameterAndAttribute.LANGUAGE_ID, "1");
		courseInfo.put(ParameterAndAttribute.PRICE, "320");
		courseInfo.put(ParameterAndAttribute.COURSE_NAME, "Spanish for adults");
		courseInfo.put(ParameterAndAttribute.IMAGE_NAME, "spanish.jpg");
		course = new Course();
		course.setCourseId(1L);
		course.setLanguageId(1L);
		course.setCourseName("Spanish for adults");
		course.setImageName("spanish.jpg");
		course.setPrice(new BigDecimal(320));
	}

	@BeforeMethod
	public void init() {
		autoCloseable = MockitoAnnotations.openMocks(this);
	}

	@AfterMethod
	public void tierDown() throws Exception {
		autoCloseable.close();
	}


	@Test(expectedExceptions = InvalidDataException.class)
	public void addCourseInvalidDataExceptionTest() throws ServiceException, InvalidDataException {
		catalogService.addCourse(Collections.emptyMap());
	}
	
	@Test(expectedExceptions = InvalidDataException.class)
	public void changeCourseDataInvalidDataExceptionTest()
			throws DaoException, ServiceException, InvalidDataException {
		Assert.assertFalse(catalogService.changeCourseData(Collections.emptyMap()));
	}

	@Test
	public void takeAllLanguagesPositiveTest() throws DaoException, ServiceException {
		List<Language> expected = new ArrayList<>();
		expected.add(new Language(1L, "Arabic"));
		when(languageDao.findAll()).thenReturn(expected);
		List<Language> actual = catalogService.takeAllLanguages();
		Assert.assertEquals(actual, expected);
	}

	@Test
	public void takeAllLanguagesNegativeTest() throws DaoException, ServiceException {
		List<Language> expected = Collections.emptyList();
		when(languageDao.findAll()).thenReturn(expected);
		List<Language> actual = catalogService.takeAllLanguages();
		Assert.assertEquals(actual, expected);
	}

	@Test(expectedExceptions = ServiceException.class)
	public void takeAllLanguagesServiceExceptionTest() throws DaoException, ServiceException {
		when(languageDao.findAll()).thenThrow(DaoException.class);
		catalogService.takeAllLanguages();
	}

	@Test
	public void takeCourseByIdPositiveTest() throws DaoException, ServiceException {
		Optional<Course> expected = Optional.of(course);
		when(courseDao.findCoursesById(anyString())).thenReturn(expected);
		Optional<Course> actual = catalogService.takeCourseById("1");
		Assert.assertEquals(actual, expected);
	}

	@Test
	public void takeCourseByIdNegativeTest() throws ServiceException {
		Optional<Course> expected = Optional.empty();
		Optional<Course> actual = catalogService.takeCourseById("f");
		Assert.assertEquals(actual, expected);
	}

	@Test(expectedExceptions = ServiceException.class)
	public void takeCourseByIdServiceExceptionTest() throws DaoException, ServiceException {
		when(courseDao.findCoursesById(anyString())).thenThrow(DaoException.class);
		catalogService.takeCourseById("1");
	}

	@Test
	public void takeCoursesLanguagePositiveTest() throws DaoException, ServiceException {
		List<Course> expected = new ArrayList<>();
		expected.add(course);
		when(courseDao.findCoursesByLanguage(anyString())).thenReturn(expected);
		List<Course> actual = catalogService.takeCoursesByLanguage("1", null);
		Assert.assertEquals(actual, expected);
	}

	@Test
	public void takeCoursesFromCategoryNegativeTest() throws ServiceException {
		List<Course> expected = Collections.emptyList();
		List<Course> actual = catalogService.takeCoursesByLanguage("f", null);
		Assert.assertEquals(actual, expected);
	}

	@Test(expectedExceptions = ServiceException.class)
	public void takeCoursesFromCategoryServiceExceptionTest() throws DaoException, ServiceException {
		when(courseDao.findCoursesByLanguage(anyString())).thenThrow(DaoException.class);
		catalogService.takeCoursesByLanguage("1", null);
	}

	@Test
	public void takeCoursesWithNamePositiveTest() throws DaoException, ServiceException {
		List<Course> expected = new ArrayList<>();
		expected.add(course);
		when(courseDao.findCoursesByName(anyString())).thenReturn(expected);
		List<Course> actual = catalogService.takeCoursesWithName("desk", null);
		Assert.assertEquals(actual, expected);
	}

	@Test
	public void takeCoursesWithNameNegativeTest() throws ServiceException {
		List<Course> expected = Collections.emptyList();
		List<Course> actual = catalogService.takeCoursesWithName(null, null);
		Assert.assertEquals(actual, expected);
	}

	@Test(expectedExceptions = ServiceException.class)
	public void takeCoursesWithNameServiceExceptionTest() throws DaoException, ServiceException {
		when(courseDao.findCoursesByName(anyString())).thenThrow(DaoException.class);
		catalogService.takeCoursesWithName("desk", null);
	}

}
