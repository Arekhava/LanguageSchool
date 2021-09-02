package com.arekhava.languageschool.controller.command.impl;

import java.util.Arrays;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.arekhava.languageschool.controller.command.Command;
import com.arekhava.languageschool.controller.command.PagePath;
import com.arekhava.languageschool.controller.command.ParameterAndAttribute;
import com.arekhava.languageschool.controller.command.Router;
import com.arekhava.languageschool.controller.command.Router.RouteType;
import com.arekhava.languageschool.entity.User;
import com.arekhava.languageschool.entity.UserRole;
import com.arekhava.languageschool.model.service.ServiceException;
import com.arekhava.languageschool.model.service.ServiceFactory;
import com.arekhava.languageschool.model.service.UserService;
import com.arekhava.languageschool.util.MessageKey;
import com.arekhava.languageschool.util.UserControl;

/**
 * The command is responsible for search user by id
 * 
 * @author N
 * @see Command
 */
public class FindUserByIdCommand implements Command {
	private static final Logger logger = LogManager.getLogger();

	@Override
	public Router execute(HttpServletRequest request) {
		Router router;
		if (!UserControl.isLoggedInUser(request) || !UserControl.isValidForRole(request, UserRole.ADMIN)) {
			router = new Router(PagePath.GO_TO_MAIN_PAGE, RouteType.REDIRECT);
			return router;
		}
		HttpSession session = request.getSession(true);
		UserService userService = ServiceFactory.getInstance().getUserService();
		String userId = request.getParameter(ParameterAndAttribute.USER_ID);
		try {
			Optional<User> userOptional = userService.takeUserById(userId);
			if (userOptional.isPresent()) {
				request.setAttribute(ParameterAndAttribute.USERS, Arrays.asList(userOptional.get()));
			} else {
				session.setAttribute(ParameterAndAttribute.INFO_MESSAGE, MessageKey.INFO_NOTHING_FOUND_MESSAGE);
			}
			session.setAttribute(ParameterAndAttribute.CURRENT_PAGE, PagePath.FIND_USER_BY_ID + userId);
			router = new Router(PagePath.STUDENTS, RouteType.FORWARD);
		} catch (ServiceException e) {
			logger.error("user search error", e);
			router = new Router(PagePath.ERROR, RouteType.REDIRECT);
		}
		return router;
	}
}