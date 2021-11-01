package com.arekhava.languageschool.controller.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

@WebFilter(urlPatterns = { "/*" }, initParams = {
		@WebInitParam(name = "encoding", value = "UTF-8", description = "Encoding Param") })

public class EncodingFilter implements Filter {
	private static final String INIT_PARAMETER_ENCODING = "encoding";
	private String encoding;

	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		encoding = fConfig.getInitParameter(INIT_PARAMETER_ENCODING);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		request.setCharacterEncoding(encoding);
		response.setCharacterEncoding(encoding);
		chain.doFilter(request, response);
	}
/*
 *  String codeRequest = servletRequest.getCharacterEncoding();

	        if (!encoding.equalsIgnoreCase(codeRequest)) {
	            servletRequest.setCharacterEncoding(encoding);
	            servletResponse.setCharacterEncoding(encoding);
	        }
	        filterChain.doFilter(servletRequest, servletResponse);
 */
	@Override
	public void destroy() {
		encoding = null;
	}
}
