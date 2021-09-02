package com.arekhava.languageschool.model.service.validator;

public class IdValidator {
	private static final String ID_PATTERN = "\\d{1,19}";

	private IdValidator() {
	}
	
	public static boolean isValidId(String id) {
		return (id != null) ? id.matches(ID_PATTERN) : false;
	}
}

