package com.arekhava.languageschool.model.service.validator.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.MapUtils;

import com.arekhava.languageschool.controller.command.ParameterAndAttribute;
import com.arekhava.languageschool.entity.PaymentMethod;
import com.arekhava.languageschool.entity.SubscriptionStatus;
import com.arekhava.languageschool.model.service.validator.SubscriptionInfoValidator;
import com.arekhava.languageschool.util.MessageKey;

public class SubscriptionInfoValidatorImpl implements SubscriptionInfoValidator {
	
	private static final String COST_PATTERN = "^[1-9]\\d{0,8}(\\.\\d{2})?$";

	@Override
	public List<String> findInvalidData(Map<String, String> subscriptionInfo) {
		List<String> errorMessageList = new ArrayList<>();
		if (MapUtils.isEmpty(subscriptionInfo)) {
			errorMessageList.add(MessageKey.ERROR_IMPOSSIBLE_OPERATION_MESSAGE);
			return errorMessageList;
		}
		if (!isValidCost(subscriptionInfo.get(ParameterAndAttribute.COST))) {
			errorMessageList.add(MessageKey.ERROR_INCORRECT_PRICE_MESSAGE);
		}
		if (!isValidPaymentMethod(subscriptionInfo.get(ParameterAndAttribute.PAYMENT_METHOD))) {
			errorMessageList.add(MessageKey.ERROR_INCORRECT_PAYMENT_METHOD_MESSAGE);
		}
		
		return errorMessageList;
	}

	@Override
	public boolean isValidCost(String cost) {
		return (cost != null) ? cost.matches(COST_PATTERN) : false;
	}

	@Override
	public boolean isValidPaymentMethod(String paymentMethod) {
		if (paymentMethod == null) {
			return false;
		}
		try {
			PaymentMethod.valueOf(paymentMethod.toUpperCase());
		} catch (IllegalArgumentException e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean isValidSubscriptionStatus(String subscriptionStatus) {
		{
			if (subscriptionStatus == null) {
				return false;
			}
			try {
				SubscriptionStatus.valueOf(subscriptionStatus.toUpperCase());
			} catch (IllegalArgumentException e) {
				return false;
			}
			return true;
		}
	}
}
