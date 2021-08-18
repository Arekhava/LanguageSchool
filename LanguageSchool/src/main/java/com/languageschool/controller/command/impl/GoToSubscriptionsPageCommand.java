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
import com.languageschool.entity.Subscription;
import com.languageschool.entity.UserRole;
import com.languageschool.model.service.ServiceException;
import com.languageschool.model.service.ServiceFactory;
import com.languageschool.model.service.SubscriptionService;
import com.languageschool.util.MessageKey;
import com.languageschool.util.UserControl;

/**
 * The command is responsible for going to the subscriptions page
 * 
 * @author N
 * @see Command
 */
public class GoToSubscriptionsPageCommand implements Command {
	private static final Logger logger = LogManager.getLogger();

	@Override
	public Router execute(HttpServletRequest request) {
		Router router;
		if (!UserControl.isLoggedInUser(request) || !UserControl.isValidForRole(request, UserRole.STUDENT)) {
			router = new Router(PagePath.GO_TO_MAIN_PAGE, RouteType.REDIRECT);
			return router;
		}
		HttpSession session = request.getSession(true);
		SubscriptionService subscriptionService = ServiceFactory.getInstance().getSubscriptionService();
		String login = (String) session.getAttribute(ParameterAndAttribute.LOGIN);
		try {
			List<Subscription> subscriptions = subscriptionService.takeSubscriptionsByLogin(login);
			if (!subscriptions.isEmpty()) {
				request.setAttribute(ParameterAndAttribute.SUBSCRIPTIONS, subscriptions);
			} else {
				session.setAttribute(ParameterAndAttribute.INFO_MESSAGE, MessageKey.INFO_NO_SUBSCRIPTIONS_MESSAGE);
			}
			session.setAttribute(ParameterAndAttribute.CURRENT_PAGE, PagePath.GO_TO_SUBSCRIPTIONS_PAGE);
			router = new Router(PagePath.SUBSCRIPTIONS, RouteType.FORWARD);
		} catch (ServiceException e) {
			logger.error("subscriptions search error", e);
			router = new Router(PagePath.ERROR, RouteType.REDIRECT);
		}
		return router;
	}
}
