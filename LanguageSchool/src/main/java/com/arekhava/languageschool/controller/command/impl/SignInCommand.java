package com.arekhava.languageschool.controller.command.impl;

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
import com.arekhava.languageschool.model.service.ServiceException;
import com.arekhava.languageschool.model.service.ServiceFactory;
import com.arekhava.languageschool.model.service.UserService;
import com.arekhava.languageschool.util.MessageKey;
import com.arekhava.languageschool.util.UserControl;

/**
 * The command is responsible for the login
 * 
 * @author N
 * @see Command
 */
public class SignInCommand implements Command {
	private static final Logger logger = LogManager.getLogger();

	@Override
	public Router execute(HttpServletRequest request) {
		Router router;
		HttpSession session = request.getSession(true);
		if (session.getAttribute(ParameterAndAttribute.LOGIN) != null) {
			session.setAttribute(ParameterAndAttribute.ERROR_MESSAGE, MessageKey.ERROR_REPEATED_LOGIN_MESSAGE);
			router = new Router(PagePath.GO_TO_MAIN_PAGE, RouteType.REDIRECT);
			return router;
		}
		UserService userService = ServiceFactory.getInstance().getUserService();
		String login = request.getParameter(ParameterAndAttribute.LOGIN);
		String password = request.getParameter(ParameterAndAttribute.PASSWORD);
		try {
			Optional<User> userOptional = userService.authorization(login, password);
			if (userOptional.isPresent()) {
				User user = userOptional.get();
				UserControl.userStatusControl(user, session);
			} else {
				session.setAttribute(ParameterAndAttribute.ERROR_MESSAGE, MessageKey.ERROR_LOGIN_MESSAGE);
			}
			String page = (String) session.getAttribute(ParameterAndAttribute.CURRENT_PAGE);
			router = new Router(page, RouteType.REDIRECT);
		} catch (ServiceException e) {
			logger.error("user search error", e);
			router = new Router(PagePath.ERROR, RouteType.REDIRECT);
		}
		return router;
	}
}