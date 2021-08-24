package com.languageschool.controller.command.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.languageschool.controller.command.Command;
import com.languageschool.controller.command.PagePath;
import com.languageschool.controller.command.ParameterAndAttribute;
import com.languageschool.controller.command.Router;
import com.languageschool.controller.command.Router.RouteType;
import com.languageschool.entity.UserRole;
import com.languageschool.model.service.CatalogService;
import com.languageschool.model.service.InvalidDataException;
import com.languageschool.model.service.ServiceException;
import com.languageschool.model.service.ServiceFactory;
import com.languageschool.util.FileUtil;
import com.languageschool.util.MessageKey;
import com.languageschool.util.RequestUtil;
import com.languageschool.util.UserControl;

/**
* The command is responsible for adding a course to the catalog
* 
* @author N
* @see Command
*/
public class AddCourseToCatalogCommand implements Command {
		private static final Logger logger = LogManager.getLogger();

		@Override
		public Router execute(HttpServletRequest request) {
			Router router;
			if (!UserControl.isLoggedInUser(request) || !UserControl.isValidForRole(request, UserRole.ADMIN)) {
				router = new Router(PagePath.GO_TO_MAIN_PAGE, RouteType.REDIRECT);
				return router;
			}
			HttpSession session = request.getSession(true);
			CatalogService catalogService = ServiceFactory.getInstance().getCatalogService();
			Map<String, String> courseInfo = new HashMap<String, String>();
			try {
				courseInfo = RequestUtil.getRequestParameters(request);
				catalogService.addCourse(courseInfo); 
				session.setAttribute(ParameterAndAttribute.INFO_MESSAGE, MessageKey.INFO_COURSE_ADDED_TO_CATALOG_MESSAGE);
				String languageId = request.getParameter(ParameterAndAttribute.LANGUAGE_ID);
				router = new Router(PagePath.SHOW_COURSES_BY_LANGUAGE + languageId, RouteType.REDIRECT);
		} catch (InvalidDataException e) {
			logger.error("invalid data", e);
			FileUtil.deleteFile(
					courseInfo.get(ParameterAndAttribute.PATH) +courseInfo.get(ParameterAndAttribute.IMAGE_NAME));
			session.setAttribute(ParameterAndAttribute.ERROR_MESSAGE, e.getErrorDescription());
			router = new Router(PagePath.ADDED_COURSE, RouteType.REDIRECT);
		} catch (ServiceException e) {
			logger.error("error adding a course to the catalog", e);
			FileUtil.deleteFile(
					courseInfo.get(courseInfo.get(ParameterAndAttribute.PATH) + ParameterAndAttribute.IMAGE_NAME));
			router = new Router(PagePath.ERROR, RouteType.REDIRECT);
		}
			return router;
		}
	}
