package com.arekhava.languageschool.model.service.validator.impl;

import com.arekhava.languageschool.model.service.validator.IdValidator;

public class IdValidatorImpl implements IdValidator {
	
	private static final String ID_PATTERN = "\\d{1,19}";

	@Override
	public boolean isValidId(String id) {
			return (id != null) ? id.matches(ID_PATTERN) : false;
		}

}
