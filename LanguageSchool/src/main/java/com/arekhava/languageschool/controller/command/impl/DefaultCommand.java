package com.arekhava.languageschool.controller.command.impl;

import javax.servlet.http.HttpServletRequest;

import com.arekhava.languageschool.controller.command.Command;
import com.arekhava.languageschool.controller.command.PagePath;
import com.arekhava.languageschool.controller.command.Router;
import com.arekhava.languageschool.controller.command.Router.RouteType;

/**
 * The command is responsible for incorrect command name
 * 
 * @author N
 * @see Command
 */
public class DefaultCommand implements Command {

	@Override
	public Router execute(HttpServletRequest request) {
		Router router = new Router(PagePath.ERROR, RouteType.REDIRECT);
		return router;
	}
}