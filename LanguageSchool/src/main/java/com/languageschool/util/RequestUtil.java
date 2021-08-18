package com.languageschool.util;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public final class RequestUtil {

	private RequestUtil() {
	}

	/**
	 * Gets request parameters
	 * 
	 * @param request {@link HttpServletRequest}
	 * @return {@link Map} of {@link String} and {@link String} the names of the
	 *         parameters and its values
	 */
	public static Map<String, String> getRequestParameters(HttpServletRequest request) {
		Enumeration<String> parameterNames = request.getParameterNames();
		Map<String, String> parameters = new HashMap<>();
		for (String name : Collections.list(parameterNames)) {
			parameters.put(name, request.getParameter(name));
		}
		return parameters;
	}
}
