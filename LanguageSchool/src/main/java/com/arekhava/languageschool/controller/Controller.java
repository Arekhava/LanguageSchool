package com.arekhava.languageschool.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.arekhava.languageschool.controller.command.Command;
import com.arekhava.languageschool.controller.command.CommandProvider;
import com.arekhava.languageschool.controller.command.PagePath;
import com.arekhava.languageschool.controller.command.ParameterAndAttribute;
import com.arekhava.languageschool.controller.command.Router;

/**
 * Controller receive request from student (get or post)
 * 
 * @author N
 * @see HttpServlet
 */
@WebServlet(name = "controller", urlPatterns = { "/controller" })
public class Controller extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		processRequest(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}

	/**
	 * Processes get and post requests
	 * 
	 * @param request  {@link HttpServletRequest}
	 * @param response {@link HttpServletResponse}
	 * @throws ServletException
	 * @throws IOException
	 */
	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
 		String commandName =  request.getParameter(ParameterAndAttribute.COMMAND);
		Command command = CommandProvider.defineCommand(commandName);
		Router router = command.execute(request);
		switch (router.getRouteType()) {
		case REDIRECT:
			response.sendRedirect(router.getPagePath());
			break;
		case FORWARD:
			RequestDispatcher dispatcher = request.getRequestDispatcher(router.getPagePath());
			dispatcher.forward(request, response);
			break;
		default:
			logger.error("incorrect route type " + router.getRouteType());
			response.sendRedirect(PagePath.ERROR);
			
		}
	}
}