package com.arekhava.languageschool.model.dao.impl;

import static com.arekhava.languageschool.model.dao.ColumnName.COURSE_ID;
import static com.arekhava.languageschool.model.dao.ColumnName.COURSE_IMAGE_NAME;
import static com.arekhava.languageschool.model.dao.ColumnName.COURSE_NAME;
import static com.arekhava.languageschool.model.dao.ColumnName.COURSE_PRICE;
import static com.arekhava.languageschool.model.dao.ColumnName.LANGUAGES_ID;
import static com.arekhava.languageschool.model.dao.ColumnName.LANGUAGES_IMAGE_NAME;
import static com.arekhava.languageschool.model.dao.ColumnName.LANGUAGES_NAME;
import static com.arekhava.languageschool.model.dao.ColumnName.NEXT_START;
import static com.arekhava.languageschool.model.dao.ColumnName.SUBSCRIPTIONS_COST;
import static com.arekhava.languageschool.model.dao.ColumnName.SUBSCRIPTIONS_DATE_TIME;
import static com.arekhava.languageschool.model.dao.ColumnName.SUBSCRIPTIONS_ID;
import static com.arekhava.languageschool.model.dao.ColumnName.SUBSCRIPTIONS_PAYMENT_METHOD;
import static com.arekhava.languageschool.model.dao.ColumnName.SUBSCRIPTIONS_STATUS;
import static com.arekhava.languageschool.model.dao.ColumnName.SUBSCRIPTIONS_USER_ID;
import static com.arekhava.languageschool.model.dao.ColumnName.USERS_ID;
import static com.arekhava.languageschool.model.dao.ColumnName.USERS_LOGIN;
import static com.arekhava.languageschool.model.dao.ColumnName.USERS_NAME;
import static com.arekhava.languageschool.model.dao.ColumnName.USERS_PASSWORD;
import static com.arekhava.languageschool.model.dao.ColumnName.USERS_PHONE;
import static com.arekhava.languageschool.model.dao.ColumnName.USERS_ROLE;
import static com.arekhava.languageschool.model.dao.ColumnName.USERS_STATUS;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.arekhava.languageschool.entity.Course;
import com.arekhava.languageschool.entity.Language;
import com.arekhava.languageschool.entity.PaymentMethod;
import com.arekhava.languageschool.entity.Subscription;
import com.arekhava.languageschool.entity.SubscriptionStatus;
import com.arekhava.languageschool.entity.User;
import com.arekhava.languageschool.entity.UserRole;
import com.arekhava.languageschool.entity.UserStatus;

/**
 * The builder is responsible for building different entities
 * 
 * @author N
 */
final class DaoEntityBuilder {

	private DaoEntityBuilder() {
	}

	/**
	 * Builds new User
	 * 
	 * @param resultSet {@link ResultSet} database result set
	 * @return {@link User}
	 * @throws SQLException
	 */
	static User buildUser(ResultSet resultSet) throws SQLException {
		User user = new User();
		user.setUserId(resultSet.getLong(USERS_ID));
		user.setLogin(resultSet.getString(USERS_LOGIN));
		user.setPassword(resultSet.getString(USERS_PASSWORD));
		user.setRole(UserRole.valueOf(resultSet.getString(USERS_ROLE).toUpperCase()));
		user.setName(resultSet.getString(USERS_NAME));
		user.setPhone(resultSet.getString(USERS_PHONE));
		user.setStatus(UserStatus.valueOf(resultSet.getString(USERS_STATUS.toUpperCase())));
		return user;
	}

	/**
	 * Builds new Language
	 * 
	 * @param resultSet {@link Language} database result set
	 * @return {@link Language}
	 * @throws SQLException
	 */
	static Language buildLanguage(ResultSet resultSet) throws SQLException {
		Language language = new Language();
		language.setLanguageId(resultSet.getLong(LANGUAGES_ID));
		language.setLanguageName(resultSet.getString(LANGUAGES_NAME));
		language.setImageName(resultSet.getString(LANGUAGES_IMAGE_NAME));
		return language;
	}

	/**
	 * Builds new Course
	 * 
	 * @param resultSet {@link ResultSet} database result set
	 * @return {@link Course}
	 * @throws SQLException
	 */
	static Course buildCourse(ResultSet resultSet) throws SQLException {
		Course course = new Course();
		course.setCourseId(resultSet.getLong(COURSE_ID));
		course.setCourseName(resultSet.getString(COURSE_NAME));
		course.setImageName(resultSet.getString(COURSE_IMAGE_NAME));
		//course.setLanguageId(resultSet.getLong(LANGUAGE_ID));
		course.setNextStart(resultSet.getDate(NEXT_START));
		course.setPrice(resultSet.getBigDecimal(COURSE_PRICE));
		return course;
	}
	/**
	 * Builds new LikedCourse
	 * 
	 * @param resultSet {@link ResultSet} database result set
	 * @return {@link Course}
	 * @throws SQLException
	 */
	static Course buildLikedCourse(ResultSet resultSet) throws SQLException {
		Course course = new Course();
		course.setCourseId(resultSet.getLong(COURSE_ID));
		course.setCourseName(resultSet.getString(COURSE_NAME));
		//course.setImageName(resultSet.getString(COURSE_IMAGE_NAME));
		//course.setLanguageId(resultSet.getLong(LANGUAGE_ID));
		course.setNextStart(resultSet.getDate(NEXT_START));
		course.setPrice(resultSet.getBigDecimal(COURSE_PRICE));
		return course;
	}

	/**
	 * Builds new Subscription
	 * 
	 * @param resultSet {@link ResultSet} database result set
	 * @return {@link Subscription}
	 * @throws SQLException
	 */
	static Subscription buildSubscription(ResultSet resultSet) throws SQLException {
		Subscription subscription = new Subscription();
		subscription.setSubscriptionId(resultSet.getLong(SUBSCRIPTIONS_ID));
		subscription.setUserId(resultSet.getLong(SUBSCRIPTIONS_USER_ID));
		if (resultSet.getTimestamp(SUBSCRIPTIONS_DATE_TIME) != null) {
			subscription.setDateTime(resultSet.getTimestamp(SUBSCRIPTIONS_DATE_TIME).toLocalDateTime());
		}
		subscription.setSubscriptionStatus(SubscriptionStatus.valueOf(resultSet.getString(SUBSCRIPTIONS_STATUS)));
		if (resultSet.getString(SUBSCRIPTIONS_PAYMENT_METHOD) != null) {
			subscription.setPaymentMethod(
					PaymentMethod.valueOf(resultSet.getString(SUBSCRIPTIONS_PAYMENT_METHOD).toUpperCase()));
		}
		subscription.setCost(resultSet.getBigDecimal(SUBSCRIPTIONS_COST));

		return subscription;
	}

}