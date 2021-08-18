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
import com.languageschool.entity.SubscriptionStatus;
import com.languageschool.entity.UserRole;
import com.languageschool.model.service.ServiceException;
import com.languageschool.model.service.ServiceFactory;
import com.languageschool.model.service.SubscriptionService;
import com.languageschool.util.MessageKey;
import com.languageschool.util.UserControl;


/**
 * The command is responsible for search subscriptions by status
 * 
 * @author N
 * @see Command
 */
public class FindSubscriptionsByStatusCommand  implements Command {
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
		String subscriptionStatus = request.getParameter(ParameterAndAttribute.STATUS);
		try {
			List<Subscription> subscriptions = subscriptionService.takeSubscriptionsByStatus(subscriptionStatus);
			if (!subscriptions.isEmpty()) {
				request.setAttribute(ParameterAndAttribute.SUBSCRIPTIONS, subscriptions);
			} else {
				session.setAttribute(ParameterAndAttribute.INFO_MESSAGE, MessageKey.INFO_NO_SUBSCRIPTIONS_MESSAGE);
			}
			session.setAttribute(ParameterAndAttribute.CURRENT_PAGE, PagePath.FIND_SUBSCRIPTIONS_BY_STATUS + subscriptionStatus);
			request.setAttribute(ParameterAndAttribute.SUBSCRIPTION_STATUS_LIST, SubscriptionStatus.values());
			router = new Router(PagePath.SUBSCRIPTIONS, RouteType.FORWARD);
		} catch (ServiceException e) {
			logger.error("subscriptions search error", e);
			router = new Router(PagePath.ERROR, RouteType.REDIRECT);
		}
		return router;
	}
}