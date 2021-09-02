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
import com.arekhava.languageschool.entity.UserRole;
import com.arekhava.languageschool.model.service.InvalidDataException;
import com.arekhava.languageschool.model.service.ServiceException;
import com.arekhava.languageschool.model.service.ServiceFactory;
import com.arekhava.languageschool.model.service.SubscriptionService;
import com.arekhava.languageschool.util.MessageKey;
import com.arekhava.languageschool.util.RequestUtil;
import com.arekhava.languageschool.util.UserControl;

/**
* The command is responsible for checkout
* 
* @author N
* @see Command
*/
public class CheckoutCommand implements Command {
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
		Map<String, String> subscriptionInfo = RequestUtil.getRequestParameters(request);
		String subscriptionLikedId = String.valueOf(session.getAttribute(ParameterAndAttribute.SUBSCRIPTION_LIKED_ID));
		subscriptionInfo.put(ParameterAndAttribute.SUBSCRIPTION_LIKED_ID, subscriptionLikedId);
		try {
			if (subscriptionService.checkout(subscriptionInfo)) {
				session.removeAttribute(ParameterAndAttribute.SUBSCRIPTION_LIKED_ID);
				session.setAttribute(ParameterAndAttribute.INFO_MESSAGE, MessageKey.INFO_SUBSCRIPTIONS_PLACED_MESSAGE);
				router = new Router(PagePath.GO_TO_SUBSCRIPTIONS_PAGE, RouteType.REDIRECT);
			} else {
				session.setAttribute(ParameterAndAttribute.ERROR_MESSAGE, MessageKey.ERROR_IMPOSSIBLE_OPERATION_MESSAGE);
				router = new Router(PagePath.GO_TO_SUBSCRIPTIONS_PAGE, RouteType.REDIRECT);
			}
		} catch (InvalidDataException e) {
			logger.error("invalid data", e);
			session.setAttribute(ParameterAndAttribute.ERROR_MESSAGE, e.getErrorDescription());
			router = new Router(PagePath.GO_TO_SUBSCRIPTIONS_PAGE, RouteType.REDIRECT);
		} catch (ServiceException e) {
			logger.error("checkout error", e);
			router = new Router(PagePath.ERROR, RouteType.REDIRECT);
		}
		return router;
	}
}
