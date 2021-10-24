package com.arekhava.languageschool.entity.builder.impl;

import static com.arekhava.languageschool.controller.command.ParameterAndAttribute.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

import com.arekhava.languageschool.entity.PaymentMethod;
import com.arekhava.languageschool.entity.Subscription;
import com.arekhava.languageschool.entity.SubscriptionStatus;
import com.arekhava.languageschool.entity.builder.EntityBuilder;

/**
 * The builder is responsible for building subscription
 * 
 * @author N
 */
public class SubscriptionBuilder implements EntityBuilder<Subscription> {
	private static final SubscriptionBuilder instance = new SubscriptionBuilder();
	

	private SubscriptionBuilder() {
	}

	/**
	 * Get instance of this class
	 * 
	 * @return {@link SubscriptionBuilder} instance
	 */
	public static SubscriptionBuilder getInstance() {
		return instance;
	}

	@Override
	public Subscription build(Map<String, String> subscriptionInfo) {
		Subscription subscription = new Subscription();
	
		subscription.setSubscriptionId(Long.valueOf(subscriptionInfo.get(SUBSCRIPTION_LIKED_ID)));
		subscription.setSubscriptionStatus(SubscriptionStatus.ADDED_COURSE);
		subscription.setCost(new BigDecimal(subscriptionInfo.get(COST)));
		subscription.setDateTime(LocalDateTime.now());
		subscription.setPaymentMethod(
				PaymentMethod.valueOf(subscriptionInfo.get(PAYMENT_METHOD).toUpperCase()));
		return subscription;
	}
}
