package com.languageschool.model.service.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.MapUtils;

import com.languageschool.controller.command.ParameterAndAttribute;
import com.languageschool.entity.PaymentMethod;
import com.languageschool.entity.SubscriptionStatus;
import com.languageschool.util.MessageKey;

/**
 * Validates subscription info
 * 
 * @author N
 */
public final class SubscriptionInfoValidator {
	private static final String COST_PATTERN = "^[1-9]\\d{0,8}(\\.\\d{2})?$";

	

	private SubscriptionInfoValidator() {
	}

	/**
	 * Looking for invalid user data
	 * 
	 * @param subscriptionInfo {@link Map} of {@link String} and {@link String} the names
	 *                  of the {@link Subscription} fields and its values
	 * @return {@link List} of {@link String} error messages if user info is
	 *         invalid, else emptyList
	 */
	public static List<String> findInvalidData(Map<String, String> subscriptionInfo) {
		List<String> errorMessageList = new ArrayList<>();
		if (MapUtils.isEmpty(subscriptionInfo)) {
			errorMessageList.add(MessageKey.ERROR_IMPOSSIBLE_OPERATION_MESSAGE);
			return errorMessageList;
		}
		if (!SubscriptionInfoValidator.isValidCost(subscriptionInfo.get(ParameterAndAttribute.COST))) {
			errorMessageList.add(MessageKey.ERROR_INCORRECT_PRICE_MESSAGE);
		}
		if (!SubscriptionInfoValidator.isValidPaymentMethod(subscriptionInfo.get(ParameterAndAttribute.PAYMENT_METHOD))) {
			errorMessageList.add(MessageKey.ERROR_INCORRECT_PAYMENT_METHOD_MESSAGE);
		}
		
		return errorMessageList;
	}


	/**
	 * Checks if cost is valid
	 * 
	 * @param cost {@link String}
	 * @return boolean true if cost is valid, else false
	 */
	public static boolean isValidCost(String cost) {
		return (cost != null) ? cost.matches(COST_PATTERN) : false;
	}

	/**
	 * Checks if payment method is valid
	 * 
	 * @param paymentMethod {@link String}
	 * @return boolean true if payment method is valid, else false
	 */
	public static boolean isValidPaymentMethod(String paymentMethod) {
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

	/**
	 * Checks if subscription status is valid
	 * 
	 * @param subscriptionStatus {@link String}
	 * @return boolean true if subscription status is valid, else false
	 */
	public static boolean isValidSubscriptionStatus(String subscriptionStatus) {
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
