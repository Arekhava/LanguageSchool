package com.languageschool.controller.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.languageschool.controller.command.Command;
import com.languageschool.controller.command.PagePath;
import com.languageschool.controller.command.Router;
import com.languageschool.controller.command.Router.RouteType;

/**
 * The command is responsible for the logout
 * 
 * @author N
 * @see Command
 */
public class SignOutCommand implements Command {

	@Override
	public Router execute(HttpServletRequest request) {
		Router router = new Router(PagePath.GO_TO_MAIN_PAGE, RouteType.REDIRECT);
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		return router;
	}
}
