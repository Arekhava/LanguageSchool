package com.languageschool.controller.command.impl;

import java.util.Map;

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
import com.languageschool.util.MessageKey;
import com.languageschool.util.RequestUtil;
import com.languageschool.util.UserControl;

/**
 * The command is responsible for changing course data
 * 
 * @author N
 * @see Command
 */
public class ChangeCourseDataCommand implements Command {
	private static final Logger logger = LogManager.getLogger();

	@Override
	public Router execute(HttpServletRequest request) {
		Router router;
		if (!UserControl.isLoggedInUser(request) || !UserControl.isValidForRole(request, UserRole.ADMIN)) {
			router = new Router(PagePath.GO_TO_MAIN_PAGE, RouteType.REDIRECT);
			return router;
		}
		HttpSession session = request.getSession(true);
		String page = (String) session.getAttribute(ParameterAndAttribute.CURRENT_PAGE);
		CatalogService catalogService = ServiceFactory.getInstance().getCatalogService();
		Map<String, String> courseInfo = RequestUtil.getRequestParameters(request);
		try {
			if (catalogService.changeCourseData(courseInfo)) {
				session.setAttribute(ParameterAndAttribute.INFO_MESSAGE, MessageKey.INFO_SAVED_SUCCESSFULLY_MESSAGE);
			} else {
				session.setAttribute(ParameterAndAttribute.ERROR_MESSAGE, MessageKey.ERROR_SAVE_MESSAGE);
			}
			router = new Router(page, RouteType.REDIRECT);
		} catch (InvalidDataException e) {
			logger.error("invalid data", e);
			session.setAttribute(ParameterAndAttribute.ERROR_MESSAGE, e.getErrorDescription());
			router = new Router(page, RouteType.REDIRECT);
		} catch (ServiceException e) {
			logger.error("error changing course data", e);
			router = new Router(PagePath.ERROR, RouteType.REDIRECT);
		}
		return router;
	}

}
