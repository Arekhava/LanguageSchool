package com.languageschool.controller.command.impl;

import java.util.Arrays;
import java.util.Optional;

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
 * The command is responsible for search subscription by id
 * 
 * @author N
 * @see Command
 */
public class FindSubscriptionByIdCommand implements Command {
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
		String subscriptionId = request.getParameter(ParameterAndAttribute.SUBSCRIPTION_ID);
		try {
			Optional<Subscription> subscriptionOptional = subscriptionService.takeSubscriptionById(subscriptionId);
			if (subscriptionOptional.isPresent()) {
				request.setAttribute(ParameterAndAttribute.SUBSCRIPTIONS, Arrays.asList(subscriptionOptional.get()));
			} else {
				session.setAttribute(ParameterAndAttribute.INFO_MESSAGE, MessageKey.INFO_NOTHING_FOUND_MESSAGE);
			}
			session.setAttribute(ParameterAndAttribute.CURRENT_PAGE, PagePath.FIND_SUBSCRIPTION_BY_ID + subscriptionId);
			request.setAttribute(ParameterAndAttribute.SUBSCRIPTION_STATUS_LIST, SubscriptionStatus.values());
			router = new Router(PagePath.SUBSCRIPTIONS, RouteType.FORWARD);
		} catch (ServiceException e) {
			logger.error("subscription search error", e);
			router = new Router(PagePath.ERROR, RouteType.REDIRECT);
		}
		return router;
	}
}