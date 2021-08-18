package com.languageschool.controller.command.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.languageschool.controller.command.Command;
import com.languageschool.controller.command.PagePath;
import com.languageschool.controller.command.ParameterAndAttribute;
import com.languageschool.controller.command.Router;
import com.languageschool.controller.command.Router.RouteType;
import com.languageschool.entity.Course;
import com.languageschool.model.service.CatalogService;
import com.languageschool.model.service.ServiceException;
import com.languageschool.model.service.ServiceFactory;
import com.languageschool.util.MessageKey;


/**
 * The command is responsible for search courses by language
 * 
 * @author N
 * @see Command
 */
public class ShowCoursesByLanguageCommand implements Command {
	
	  private static final Logger logger = LogManager.getLogger();
	  
		@Override
		public Router execute(HttpServletRequest request) {
			Router router;
			HttpSession session = request.getSession(true);
			CatalogService catalogService = ServiceFactory.getInstance().getCatalogService();
			String languageId = request.getParameter(ParameterAndAttribute.LANGUAGE_ID);
			String sortingMethod = request.getParameter(ParameterAndAttribute.SORTING_METHOD);
			try {
				List<Course> courses = catalogService.takeCoursesByLanguage(languageId, sortingMethod);
				if (!courses.isEmpty()) {
					request.setAttribute(ParameterAndAttribute.COURSES, courses);
					request.setAttribute(ParameterAndAttribute.LANGUAGE_ID, languageId);
				} else {
					session.setAttribute(ParameterAndAttribute.INFO_MESSAGE, MessageKey.INFO_NOTHING_FOUND_MESSAGE);
				}
				session.setAttribute(ParameterAndAttribute.CURRENT_PAGE, PagePath.SHOW_COURSES_BY_LANGUAGE + languageId);
				router = new Router(PagePath.MAIN, RouteType.FORWARD);
			} catch (ServiceException e) {
				logger.error("courses from language search error", e);
				router = new Router(PagePath.ERROR, RouteType.REDIRECT);
			}
			return router;
		}
	 
	}
