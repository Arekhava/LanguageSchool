package com.arekhava.languageschool.model.service.validator;

import java.util.List;
import java.util.Map;

import com.arekhava.languageschool.entity.Subscription;

public interface SubscriptionInfoValidator {
	
	/**
	 * Looking for invalid user data
	 * 
	 * @param subscriptionInfo {@link Map} of {@link String} and {@link String} the names
	 *                  of the {@link Subscription} fields and its values
	 * @return {@link List} of {@link String} error messages if user info is
	 *         invalid, else emptyList
	 */
	List<String> findInvalidData(Map<String, String> subscriptionInfo);
	
	/**
	 * Checks if cost is valid
	 * 
	 * @param cost {@link String}
	 * @return boolean true if cost is valid, else false
	 */
	boolean isValidCost(String cost); 

	/**
	 * Checks if payment method is valid
	 * 
	 * @param paymentMethod {@link String}
	 * @return boolean true if payment method is valid, else false
	 */
	boolean isValidPaymentMethod(String paymentMethod); 

	/**
	 * Checks if subscription status is valid
	 * 
	 * @param subscriptionStatus {@link String}
	 * @return boolean true if subscription status is valid, else false
	 */
	boolean isValidSubscriptionStatus(String subscriptionStatus); 

}
