package com.languageschool.entity.builder.impl;

import java.time.LocalDateTime;
import java.util.Map;

import com.languageschool.entity.PaymentMethod;
import com.languageschool.entity.Subscription;
import com.languageschool.entity.SubscriptionStatus;
import com.languageschool.entity.builder.EntityBuilder;


/**
 * The builder is responsible for building subscription
 * 
 * @author N
 */
public class SubscriptionBuilder implements EntityBuilder<Subscription> {
	private static final SubscriptionBuilder instance = new SubscriptionBuilder();
	private static final Object SUBSCRIPTION_LIKED_ID = null;
	private static final Object PAYMENT_METHOD = null;

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
		subscription.setSubscriptionStatus(SubscriptionStatus.COURSE_ADDED);
		subscription.setDateTime(LocalDateTime.now());
		subscription.setPaymentMethod(
				PaymentMethod.valueOf(subscriptionInfo.get(PAYMENT_METHOD).toUpperCase()));
		
		return subscription;
	}
}
