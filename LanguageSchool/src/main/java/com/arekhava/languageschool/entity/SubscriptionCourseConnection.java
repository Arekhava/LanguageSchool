package com.arekhava.languageschool.entity;

import java.io.Serializable;

/**
 * Describes the entity SubscriptionCourseConnection
 * 
 */
public class SubscriptionCourseConnection implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long subscriptionId;
	private Long courseId;


	/**
	 * Constructs a newSubscriptionCourseConnection
	 */
	public SubscriptionCourseConnection() {
	}

	/**
	 * Constructs a new SubscriptionCourseConnection with the specified subscriptionId, courseId
	 * 
	 * @param subscriptionId           {@link Long} subscriptionId
	 * @param courseId         {@link Long} courseId
	 */
	public SubscriptionCourseConnection(Long subscriptionId, Long courseId) {
		this.subscriptionId = subscriptionId;
		this.courseId = courseId;
	}


	
	public Long getSubscriptionId() {
		return subscriptionId;
	}

	public void setSubscriptionId(Long subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((subscriptionId == null) ? 0 : subscriptionId.hashCode());
		result = prime * result + ((courseId == null) ? 0 : courseId.hashCode());
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
		SubscriptionCourseConnection other = (SubscriptionCourseConnection) obj;
		if (subscriptionId == null) {
			if (other.subscriptionId != null)
				return false;
		} else if (!subscriptionId.equals(other.subscriptionId))
			return false;
		if (courseId == null) {
			if (other.courseId != null)
				return false;
		} else if (!courseId.equals(other.courseId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("subscriptionCoursesubscriptionConnection [subscriptionId=");
		builder.append(subscriptionId);
		builder.append(", courseId=");
		builder.append(courseId);
		builder.append("]");
		return builder.toString();
	}
}
