package com.arekhava.languageschool.controller.filter;

import java.io.File;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.arekhava.languageschool.controller.command.PagePath;
import com.arekhava.languageschool.controller.command.ParameterAndAttribute;
import com.arekhava.languageschool.entity.UserRole;
import com.arekhava.languageschool.util.MessageKey;



@WebFilter(urlPatterns = { "/jsp/student/*" })
public class StudentAccessFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession session = httpRequest.getSession(true);
		if (session.getAttribute(ParameterAndAttribute.ROLE) != UserRole.STUDENT) {
			httpResponse.sendRedirect(httpRequest.getContextPath() + File.separator + PagePath.GO_TO_MAIN_PAGE);
			session.setAttribute(ParameterAndAttribute.ERROR_MESSAGE, MessageKey.ERROR_ACCESS_MESSAGE);
			return;
		}
		chain.doFilter(request, response);
	}
}
