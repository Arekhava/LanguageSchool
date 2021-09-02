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
import com.arekhava.languageschool.entity.Subscription;
import com.arekhava.languageschool.entity.SubscriptionStatus;
import com.arekhava.languageschool.entity.UserRole;
import com.arekhava.languageschool.model.service.ServiceException;
import com.arekhava.languageschool.model.service.ServiceFactory;
import com.arekhava.languageschool.model.service.SubscriptionService;
import com.arekhava.languageschool.util.MessageKey;
import com.arekhava.languageschool.util.UserControl;


/**
 * The command is responsible for search user subscriptions
 * 
 * @author N
 * @see Command
 */
public class FindUserSubscriptionsCommand implements Command {
	private static final Logger logger = LogManager.getLogger();

	@Override
	public Router execute(HttpServletRequest request) {
		Router router;
		if (!UserControl.isLoggedInUser(request) || !UserControl.isValidForRole(request, UserRole.ADMIN)) {
			router = new Router(PagePath.GO_TO_MAIN_PAGE, RouteType.REDIRECT);
			return router;
		}
		HttpSession session = request.getSession(true);
		SubscriptionService subscriptionService = ServiceFactory.getInstance().getSubscriptionService();
		String login = request.getParameter(ParameterAndAttribute.LOGIN);
		try {
			List<Subscription> subscriptions = subscriptionService.takeSubscriptionsByLogin(login);
			if (!subscriptions.isEmpty()) {
				request.setAttribute(ParameterAndAttribute.SUBSCRIPTIONS, subscriptions);
			} else {
				session.setAttribute(ParameterAndAttribute.INFO_MESSAGE, MessageKey.INFO_NO_SUBSCRIPTIONS_MESSAGE);
			}
			session.setAttribute(ParameterAndAttribute.CURRENT_PAGE, PagePath.FIND_USER_SUBSCRIPTIONS + login);
			request.setAttribute(ParameterAndAttribute.SUBSCRIPTION_STATUS_LIST, SubscriptionStatus.values());
			router = new Router(PagePath.SUBSCRIPTIONS, RouteType.FORWARD);
		} catch (ServiceException e) {
			logger.error("subscriptions search error", e);
			router = new Router(PagePath.ERROR, RouteType.REDIRECT);
		}
		return router;
	}
}