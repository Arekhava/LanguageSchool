package com.arekhava.languageschool.controller.command.impl;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.arekhava.languageschool.controller.command.Command;
import com.arekhava.languageschool.controller.command.PagePath;
import com.arekhava.languageschool.controller.command.ParameterAndAttribute;
import com.arekhava.languageschool.controller.command.Router;
import com.arekhava.languageschool.controller.command.Router.RouteType;
import com.arekhava.languageschool.model.service.InvalidDataException;
import com.arekhava.languageschool.model.service.ServiceException;
import com.arekhava.languageschool.model.service.ServiceFactory;
import com.arekhava.languageschool.model.service.UserService;
import com.arekhava.languageschool.util.MessageKey;
import com.arekhava.languageschool.util.RequestUtil;

/**
 * The command is responsible for user registration
 * 
 * @author N
 * @see Command
 */
public class SignUpCommand implements Command {
	private static final Logger logger = LogManager.getLogger();

	@Override
	public Router execute(HttpServletRequest request) {
		Router router;
		HttpSession session = request.getSession(true);
		UserService userService = ServiceFactory.getInstance().getUserService();
		Map<String, String> userInfo = RequestUtil.getRequestParameters(request);
		try {
			if (userService.registration(userInfo)) {
				session.setAttribute(ParameterAndAttribute.INFO_MESSAGE,
						MessageKey.INFO_CONFIRMATION_OF_REGISTRATION_MESSAGE);
			} else {
				session.setAttribute(ParameterAndAttribute.INFO_MESSAGE,
						MessageKey.INFO_CONFIRMATION_OF_REGISTRATION_MESSAGE_NOT_SENT);
			}
			router = new Router(PagePath.GO_TO_MAIN_PAGE, RouteType.REDIRECT);
		} catch (InvalidDataException e) {
			logger.error("invalid data", e);
			session.setAttribute(ParameterAndAttribute.ERROR_MESSAGE, e.getErrorDescription());
			router = new Router(PagePath.REGISTRATION, RouteType.REDIRECT);
		} catch (ServiceException e) {
			logger.error("user creation error", e);
			router = new Router(PagePath.ERROR, RouteType.REDIRECT);
		}
		System.out.println(request.getParameter(ParameterAndAttribute.USER_NAME));
		return router;
	}
}
