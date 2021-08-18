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
import com.languageschool.entity.Language;
import com.languageschool.model.service.CatalogService;
import com.languageschool.model.service.ServiceException;
import com.languageschool.model.service.ServiceFactory;

/**
 * The command is responsible for going to the main page
 * 
 * @author N
 * @see Command
 */
public class GoToMainPageCommand implements Command {
	private static final Logger logger = LogManager.getLogger();

	@Override
	public Router execute(HttpServletRequest request) {
		Router router;
		HttpSession session = request.getSession(true);
		CatalogService catalogService = ServiceFactory.getInstance().getCatalogService();
		try {
			List<Language> languages = catalogService.takeAllLanguages();
			session.setAttribute(ParameterAndAttribute.CURRENT_PAGE, PagePath.MAIN);
			session.setAttribute(ParameterAndAttribute.LANGUAGES, languages);
			router = new Router(PagePath.MAIN, RouteType.FORWARD);
		} catch (ServiceException e) {
			logger.error("course langugagues search error", e);
			router = new Router(PagePath.ERROR, RouteType.REDIRECT);
		}
		return router;
	}
	
}

