package com.arekhava.languageschool.controller.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.arekhava.languageschool.controller.command.Command;
import com.arekhava.languageschool.controller.command.PagePath;
import com.arekhava.languageschool.controller.command.ParameterAndAttribute;
import com.arekhava.languageschool.controller.command.Router;
import com.arekhava.languageschool.controller.command.Router.RouteType;
import com.arekhava.languageschool.entity.UserRole;
import com.arekhava.languageschool.model.service.ServiceException;
import com.arekhava.languageschool.model.service.ServiceFactory;
import com.arekhava.languageschool.model.service.SubscriptionService;
import com.arekhava.languageschool.util.MessageKey;
import com.arekhava.languageschool.util.UserControl;


/**
* The command is responsible for subscription cancelation
* * @author N
* @see Command
*/
	public class CancelSubscriptionCommand implements Command {
		private static final Logger logger = LogManager.getLogger();

		@Override
		public Router execute(HttpServletRequest request) {
			Router router;
			if (!UserControl.isLoggedInUser(request)) {
				router = new Router(PagePath.GO_TO_MAIN_PAGE, RouteType.REDIRECT);
				return router;
			}
			HttpSession session = request.getSession(true);
			SubscriptionService subscriptionService = ServiceFactory.getInstance().getSubscriptionService();
			String subscriptionId = request.getParameter(ParameterAndAttribute.SUBSCRIPTION_ID);
			String subscriptionStatus = request.getParameter(ParameterAndAttribute.STATUS);
			UserRole userRole = (UserRole) session.getAttribute(ParameterAndAttribute.ROLE);
			try {
				if (subscriptionService.cancelSubscription(subscriptionId, subscriptionStatus, userRole)) {
					session.setAttribute(ParameterAndAttribute.INFO_MESSAGE, MessageKey.INFO_SUBSCRIPTION_CANCELED_MESSAGE);
				} else {
					session.setAttribute(ParameterAndAttribute.ERROR_MESSAGE,
							MessageKey.ERROR_IMPOSSIBLE_OPERATION_MESSAGE);
				}
				String page = (String) session.getAttribute(ParameterAndAttribute.CURRENT_PAGE);
				router = new Router(page, RouteType.REDIRECT);
			} catch (ServiceException e) {
				logger.error("subscription cancelation error", e);
				router = new Router(PagePath.ERROR, RouteType.REDIRECT);
			}
			return router;
		}
	}
