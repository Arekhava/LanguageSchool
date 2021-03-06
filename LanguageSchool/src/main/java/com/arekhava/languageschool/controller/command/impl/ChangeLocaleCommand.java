package com.arekhava.languageschool.controller.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.arekhava.languageschool.controller.command.Command;
import com.arekhava.languageschool.controller.command.PagePath;
import com.arekhava.languageschool.controller.command.ParameterAndAttribute;
import com.arekhava.languageschool.controller.command.Router;
import com.arekhava.languageschool.controller.command.Router.RouteType;

/**
 * The command is responsible for changing locale
 * 
 * @author N
 * @see Command
 */
public class ChangeLocaleCommand implements Command {

	@Override
	public Router execute(HttpServletRequest request) {
		Router router;
		HttpSession session = request.getSession(true);
		session.setAttribute(ParameterAndAttribute.LOCALE, request.getParameter(ParameterAndAttribute.COMMAND));
		String page = (String) session.getAttribute(ParameterAndAttribute.CURRENT_PAGE);
		if (page != null) {
			router = new Router(page, RouteType.REDIRECT);
		} else {
			router = new Router(PagePath.GO_TO_MAIN_PAGE, RouteType.REDIRECT);
		}
		return router;
	}
}
