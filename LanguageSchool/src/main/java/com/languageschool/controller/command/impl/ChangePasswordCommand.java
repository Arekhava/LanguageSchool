package com.languageschool.controller.command.impl;

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
import com.languageschool.model.service.InvalidDataException;
import com.languageschool.model.service.ServiceException;
import com.languageschool.model.service.ServiceFactory;
import com.languageschool.model.service.UserService;
import com.languageschool.util.MessageKey;
import com.languageschool.util.UserControl;

/**
 * The command is responsible for changing password
 * 
 * @author N
 * @see Command
 */
public class ChangePasswordCommand implements Command {
	private static final Logger logger = LogManager.getLogger();

	@Override
	public Router execute(HttpServletRequest request) {
		Router router;
		if (!UserControl.isLoggedInUser(request) || !UserControl.isValidForRole(request, UserRole.STUDENT)) {
			router = new Router(PagePath.GO_TO_MAIN_PAGE, RouteType.REDIRECT);
			return router;
		}
		HttpSession session = request.getSession(true);
		UserService userService = ServiceFactory.getInstance().getUserService();
		String login = (String) session.getAttribute(ParameterAndAttribute.LOGIN);
		String currentPassword = request.getParameter(ParameterAndAttribute.PASSWORD);
		String newPassword = request.getParameter(ParameterAndAttribute.NEW_PASSWORD);
		try {
			if (userService.changePassword(login, currentPassword, newPassword)) {
				session.setAttribute(ParameterAndAttribute.INFO_MESSAGE, MessageKey.INFO_SAVED_SUCCESSFULLY_MESSAGE);
			} else {
				session.setAttribute(ParameterAndAttribute.ERROR_MESSAGE, MessageKey.ERROR_INCORRECT_PASSWORD_MESSAGE);
			}
			router = new Router(PagePath.GO_TO_PROFILE_PAGE, RouteType.REDIRECT);
		} catch (InvalidDataException e) {
			logger.error("invalid data", e);
			session.setAttribute(ParameterAndAttribute.ERROR_MESSAGE, e.getErrorDescription());
			router = new Router(PagePath.GO_TO_PROFILE_PAGE, RouteType.REDIRECT);
		} catch (ServiceException e) {
			logger.error("changing password error", e);
			router = new Router(PagePath.ERROR, RouteType.REDIRECT);
		}
		return router;
	}
}