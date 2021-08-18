package com.languageschool.controller.command.impl;

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
import com.languageschool.entity.UserRole;
import com.languageschool.model.service.ServiceException;
import com.languageschool.model.service.ServiceFactory;
import com.languageschool.model.service.SubscriptionService;
import com.languageschool.util.MessageKey;
import com.languageschool.util.UserControl;


	
/**
 * The command is responsible for adding a course to the liked
 * 
 * @author N
 * @see Command
 */
public class AddCourseToLikedCommand implements Command {
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
		String courseId = request.getParameter(ParameterAndAttribute.COURSE_ID);
		try {
			Optional<Subscription> subscriptionLikedOptional = subscriptionService.addCourseToLiked(userId, subscriptionLikedId, courseId);
			if (subscriptionLikedOptional.isPresent()) {
				session.setAttribute(ParameterAndAttribute.INFO_MESSAGE,
						MessageKey.INFO_COURSE_ADDED_TO_LIKED_MESSAGE);
				session.setAttribute(ParameterAndAttribute.SUBSCRIPTION_LIKED_ID, subscriptionLikedOptional.get().getSubscriptionId());
				String page = (String) session.getAttribute(ParameterAndAttribute.CURRENT_PAGE);
				router = new Router(page, RouteType.REDIRECT);
			} else {
				session.setAttribute(ParameterAndAttribute.ERROR_MESSAGE,
						MessageKey.ERROR_IMPOSSIBLE_OPERATION_MESSAGE);
				router = new Router(PagePath.GO_TO_MAIN_PAGE, RouteType.REDIRECT);
			}
		} catch (ServiceException e) {
			logger.error("error adding a ccourse to liked", e);
			router = new Router(PagePath.ERROR, RouteType.REDIRECT);
		}
		return router;
	}
}