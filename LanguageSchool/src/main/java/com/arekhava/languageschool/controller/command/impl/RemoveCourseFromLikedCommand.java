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
 * The command is responsible for removing a course from the liked
 * 
 * @author N
 * @see Command
 */
public class RemoveCourseFromLikedCommand implements Command {
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
		Long subscriptionLikedId = (Long) session.getAttribute(ParameterAndAttribute.SUBSCRIPTION_LIKED_ID);
		String courseId = request.getParameter(ParameterAndAttribute.COURSE_ID);
		try {
			if (subscriptionService.removeCourseFromSubscription(subscriptionLikedId, courseId)) {
				session.setAttribute(ParameterAndAttribute.INFO_MESSAGE, MessageKey.INFO_SAVED_SUCCESSFULLY_MESSAGE);
				router = new Router(PagePath.GO_TO_LIKED_PAGE, RouteType.REDIRECT);
			} else {
				session.setAttribute(ParameterAndAttribute.ERROR_MESSAGE,
						MessageKey.ERROR_IMPOSSIBLE_OPERATION_MESSAGE);
				router = new Router(PagePath.GO_TO_MAIN_PAGE, RouteType.REDIRECT);
			}
		} catch (ServiceException e) {
			logger.error("error removing a course from the liked", e);
			router = new Router(PagePath.ERROR, RouteType.REDIRECT);
		}
		return router;
	}
}