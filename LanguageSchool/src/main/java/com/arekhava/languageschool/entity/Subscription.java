package com.arekhava.languageschool.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Describes the entity Subscription
 * 
 */
public class Subscription implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long subscriptionId;
	private Long userId;
	private SubscriptionStatus subscriptionStatus;
	private List<Course> courses;
	private BigDecimal cost;
	private LocalDateTime dateTime;
	private PaymentMethod paymentMethod;


	/**
	 * Constructs a new Subscription
	 */
	public Subscription() {
	}

	/**
	 * Constructs a new Subscription with the specified user id
	 * 
	 * @param userId {@link Long} user id
	 */
	public Subscription(Long userId) {
		this.userId = userId;
	}

	/**
	 * Constructs a new Subscription with the specified subscription id and subscription id
	 * 
	 * @param subscriptionId {@link Long} subscription id
	 * @param userId  {@link Long} user id
	 */
	public Subscription(Long subscriptionId, Long userId) {
		this.subscriptionId = subscriptionId;
		this.userId = userId;
	}

	public Long getSubscriptionId() {
		return subscriptionId;
	}
	
	
	public void setSubscriptionId(Long subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public void Subscription(Long subscriptionId) {
		this.subscriptionId =subscriptionId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public SubscriptionStatus getSubscriptionStatus() {
		return subscriptionStatus;
	}

	public void setSubscriptionStatus(SubscriptionStatus SubscriptionStatus) {
		this.subscriptionStatus = SubscriptionStatus;
	}

	public List<Course> getCourses() {
		return Collections.unmodifiableList(courses);
	}


	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;

		result = prime * result + ((cost == null) ? 0 : cost.hashCode());
		result = prime * result + ((dateTime == null) ? 0 : dateTime.hashCode());
		result = prime * result + ((subscriptionId == null) ? 0 : subscriptionId.hashCode());
		result = prime * result + ((subscriptionStatus == null) ? 0 : subscriptionStatus.hashCode());
		result = prime * result + ((paymentMethod == null) ? 0 : paymentMethod.hashCode());
		result = prime * result + ((courses == null) ? 0 : courses.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Subscription other = (Subscription) obj;
		if (cost == null) {
			if (other.cost != null)
				return false;
		} else if (!cost.equals(other.cost))
			return false;
		if (dateTime == null) {
			if (other.dateTime != null)
				return false;
		} else if (!dateTime.equals(other.dateTime))
			return false;
		if (subscriptionId == null) {
			if (other.subscriptionId != null)
				return false;
		} else if (!subscriptionId.equals(other.subscriptionId))
			return false;
		if (subscriptionStatus != other.subscriptionStatus)
			return false;
		if (paymentMethod != other.paymentMethod)
			return false;
		if (courses == null) {
			if (other.courses != null)
				return false;
		} else if (!courses.equals(other.courses))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Subscription [subscriptionId=");
		builder.append(subscriptionId);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", subscriptionStatus=");
		builder.append(subscriptionStatus);
		builder.append(", courses=");
		builder.append(courses);
		builder.append(", cost=");
		builder.append(cost);
		builder.append(", dateTime=");
		builder.append(dateTime);
		builder.append(", paymentMethod=");
		builder.append(paymentMethod);
		builder.append("]");
		return builder.toString();
	}

}
