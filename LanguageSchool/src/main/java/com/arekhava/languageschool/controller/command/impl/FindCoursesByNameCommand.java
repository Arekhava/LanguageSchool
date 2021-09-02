package com.arekhava.languageschool.controller.command.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.arekhava.languageschool.controller.command.Command;
import com.arekhava.languageschool.controller.command.PagePath;
import com.arekhava.languageschool.controller.command.ParameterAndAttribute;
import com.arekhava.languageschool.controller.command.Router;
import com.arekhava.languageschool.controller.command.Router.RouteType;
import com.arekhava.languageschool.entity.Course;
import com.arekhava.languageschool.model.service.CatalogService;
import com.arekhava.languageschool.model.service.ServiceException;
import com.arekhava.languageschool.model.service.ServiceFactory;
import com.arekhava.languageschool.util.MessageKey;


/**
 * The command is responsible for search courses by name
 * 
 * @author N
 * @see Command
 */
public class FindCoursesByNameCommand implements Command {
	private static final Logger logger = LogManager.getLogger();

	@Override
	public Router execute(HttpServletRequest request) {
		Router router;
		HttpSession session = request.getSession(true);
		CatalogService catalogService = ServiceFactory.getInstance().getCatalogService();
		String courseName = request.getParameter(ParameterAndAttribute.COURSE_NAME);
		String sortingMethod = request.getParameter(ParameterAndAttribute.SORTING_METHOD);
		try {
			List<Course> courses = catalogService.takeCoursesWithName(courseName, sortingMethod);
			if (!courses.isEmpty()) {
				request.setAttribute(ParameterAndAttribute.COURSES, courses);
			} else {
				session.setAttribute(ParameterAndAttribute.INFO_MESSAGE, MessageKey.INFO_NOTHING_FOUND_MESSAGE);
			}
			session.setAttribute(ParameterAndAttribute.CURRENT_PAGE, PagePath.FIND_COURSES_BY_NAME + courseName);
			router = new Router(PagePath.MAIN, RouteType.FORWARD);
		} catch (ServiceException e) {
			logger.error("courses search error", e);
			router = new Router(PagePath.ERROR, RouteType.REDIRECT);
		}
		return router;
	}
}