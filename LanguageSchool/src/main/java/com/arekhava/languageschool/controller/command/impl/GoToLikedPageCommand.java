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
import com.arekhava.languageschool.entity.Subscription;
import com.arekhava.languageschool.entity.UserRole;
import com.arekhava.languageschool.model.service.ServiceException;
import com.arekhava.languageschool.model.service.ServiceFactory;
import com.arekhava.languageschool.model.service.SubscriptionService;
import com.arekhava.languageschool.util.MessageKey;
import com.arekhava.languageschool.util.UserControl;


/**
 * The command is responsible for going to the liked page
 * 
 * @author N
 * @see Command
 */
public class GoToLikedPageCommand implements Command {
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
		Long userId = (Long) session.getAttribute(ParameterAndAttribute.USER_ID);
		Long subscriptionLikedId = (Long) session.getAttribute(ParameterAndAttribute.SUBSCRIPTION_LIKED_ID);
		try {
			Optional<Subscription> subscriptionLikedOptional = subscriptionService.takeSubscriptionLiked(userId, subscriptionLikedId);
			if (subscriptionLikedOptional.isPresent()) {
				Subscription subscriptionLiked = subscriptionLikedOptional.get();
				if (subscriptionLiked.getCourses().isEmpty()) {
					session.setAttribute(ParameterAndAttribute.INFO_MESSAGE, MessageKey.INFO_LIKED_EMPTY_MESSAGE);
				}
				session.setAttribute(ParameterAndAttribute.SUBSCRIPTION_LIKED_ID, subscriptionLiked.getSubscriptionId());
				session.setAttribute(ParameterAndAttribute.CURRENT_PAGE, PagePath.GO_TO_LIKED_PAGE);
				request.setAttribute(ParameterAndAttribute.SUBSCRIPTION, subscriptionLiked);
				router = new Router(PagePath.LIKED, RouteType.FORWARD);
			} else {
				session.setAttribute(ParameterAndAttribute.ERROR_MESSAGE,
						MessageKey.ERROR_IMPOSSIBLE_OPERATION_MESSAGE);
				router = new Router(PagePath.GO_TO_MAIN_PAGE, RouteType.REDIRECT);
			}
		} catch (ServiceException e) {
			logger.error("liked search error", e);
			router = new Router(PagePath.ERROR, RouteType.REDIRECT);
		}
		return router;
	}
}