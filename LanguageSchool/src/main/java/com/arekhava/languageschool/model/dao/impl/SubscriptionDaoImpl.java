package com.arekhava.languageschool.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.arekhava.languageschool.entity.Subscription;
import com.arekhava.languageschool.entity.SubscriptionStatus;
import com.arekhava.languageschool.model.dao.ColumnName;
import com.arekhava.languageschool.model.dao.DaoException;
import com.arekhava.languageschool.model.dao.SubscriptionDao;
import com.arekhava.languageschool.model.pool.ConnectionPool;
import com.arekhava.languageschool.model.pool.ConnectionPoolException;

 

/**
 * Works with database table subscriptions
 * 
 * @author N
 * @see SubscriptionDao
 */
public class SubscriptionDaoImpl implements SubscriptionDao {
		private static final String SQL_INSERT_SUBSCRIPTION = "INSERT INTO SQL_INSERT_SUBSCRIPTIONS (USER_ID, STATUS) VALUES (?, 'LIKED')";
		private static final String SQL_UPDATE_SUBSCRIPTION = "UPDATE SUBSCRIPTIONS SET DATE_TIME=?, STATUS=?, COST=?, PAYMENTMETHOD_METHOD=? WHERE ID=?";
		private static final String SQL_UPDATE_SUBSCRIPTION_STATUS = "UPDATE SUBSCRIPTIONS SET STATUS=? WHERE ID=? AND STATUS=?";
		private static final String SQL_SELECT_SUBSCRIPTION_LIKED_BY_USER_ID = "SELECT ID FROM SUBSCRIPTIONS WHERE USER_ID=? AND STATUS='ADDED'";
		private static final String SQL_SELECT_SUBSCRIPTIONS_BY_ID = "SELECT ID, USER_ID, DATE_TIME, STATUS, PAYMENT_METHOD, COST FROM SUBSCRIPTIONSS WHERE ID=?";
		private static final String SQL_SELECT_SUBSCRIPTIONS_BY_LOGIN = "SELECT SUBSCRIPTIONS.ID, USER_ID, DATE_TIME, SUBSCRIPTIONS.STATUS, PAYMENT_METHOD FROM ORDERS JOIN USERS ON ORDERS.USER_ID=USERS.ID WHERE LOGIN=? AND SUBSCRIPTIONS.STATUS!='LIKED'";
		private static final String SQL_SELECT_SUBSCRIPTIONS_BY_STATUS = "SELECT ID, USER_ID, DATE_TIME, STATUS, PAYMENT_METHOD, COST SUBSCRIPTIONS WHERE STATUS=?";

		@Override
		public void create(Subscription subscription) throws DaoException {
			
			try (Connection connection = ConnectionPool.getInstance().getConnection();
					PreparedStatement statement = connection.prepareStatement(SQL_INSERT_SUBSCRIPTION,
							Statement.RETURN_GENERATED_KEYS)) {
				statement.setLong(1, subscription.getUserId());
				statement.executeUpdate();
				ResultSet resultSet = statement.getGeneratedKeys();
				if (resultSet.next()) {
					subscription.setSubscriptionId(resultSet.getLong(1));
				}
			} catch (ConnectionPoolException | SQLException e) {
				throw new DaoException("database error", e);
			}
		}

		@Override
		public boolean update(Subscription subscription) throws DaoException {
			int numberUpdatedRows;
			try (Connection connection = ConnectionPool.getInstance().getConnection();
					PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_SUBSCRIPTION)) {
				statement.setTimestamp(1, Timestamp.valueOf(subscription.getDateTime()));
				statement.setString(2, String.valueOf(subscription.getSubscriptionStatus()));
				statement.setString(3, String.valueOf(subscription.getPaymentMethod()));
				statement.setBigDecimal(4, subscription.getCost());
				statement.setString(5, String.valueOf(subscription.getPaymentMethod()));
				statement.setLong(6, subscription.getSubscriptionId());
				numberUpdatedRows = statement.executeUpdate();
			} catch (ConnectionPoolException | SQLException e) {
				throw new DaoException("database error", e);
			}
			return (numberUpdatedRows != 0);
		}

		@Override
		public boolean updateStatus(String subscriptionId, SubscriptionStatus statusFrom, SubscriptionStatus statusTo) throws DaoException {
			int numberUpdatedRows;
			try (Connection connection = ConnectionPool.getInstance().getConnection();
					PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_SUBSCRIPTION_STATUS)) {
				statement.setString(1, String.valueOf(statusTo));
				statement.setString(2, subscriptionId);
				statement.setString(3, String.valueOf(statusFrom));
				numberUpdatedRows = statement.executeUpdate();
			} catch (ConnectionPoolException | SQLException e) {
				throw new DaoException("database error", e);
			}
			return (numberUpdatedRows != 0);
		}

		@Override
		public Optional<Long> findSubscriptionAddedId(Long userId) throws DaoException {
			Optional<Long> subscriptionLikedIdOptional;
			try (Connection connection = ConnectionPool.getInstance().getConnection();
					PreparedStatement statement = connection.prepareStatement(SQL_SELECT_SUBSCRIPTION_LIKED_BY_USER_ID)) {
				statement.setLong(1, userId);
				ResultSet resultSet = statement.executeQuery();
				if (resultSet.next()) {
					Long subscriptionLikedId = resultSet.getLong(ColumnName.SUBSCRIPTIONS_ID);
					subscriptionLikedIdOptional = Optional.of(subscriptionLikedId);
				} else {
					subscriptionLikedIdOptional = Optional.empty();
				}
			} catch (ConnectionPoolException | SQLException e) {
				throw new DaoException("database error", e);
			}
			return subscriptionLikedIdOptional;
		}

		@Override
		public Optional<Subscription> findSubscriptionById(String subscriptionId) throws DaoException {
			Optional<Subscription> subscriptionOptional;
			try (Connection connection = ConnectionPool.getInstance().getConnection();
					PreparedStatement statement = connection.prepareStatement(SQL_SELECT_SUBSCRIPTIONS_BY_ID)) {
				statement.setString(1, subscriptionId);
				ResultSet resultSet = statement.executeQuery();
				if (resultSet.next()) {
					Subscription subscription = DaoEntityBuilder.buildSubscription(resultSet);
					subscriptionOptional = Optional.of(subscription);
				} else {
					subscriptionOptional = Optional.empty();
				}
			} catch (ConnectionPoolException | SQLException e) {
				throw new DaoException("database error", e);
			}
			return subscriptionOptional;
		}

		@Override
		public List<Subscription> findSubscriptionByLogin(String login) throws DaoException {
			List<Subscription> subscriptions = new ArrayList<>();
			try (Connection connection = ConnectionPool.getInstance().getConnection();
					PreparedStatement statement = connection.prepareStatement(SQL_SELECT_SUBSCRIPTIONS_BY_LOGIN)) {
				statement.setString(1, login);
				ResultSet resultSet = statement.executeQuery();
				while (resultSet.next()) {
					Subscription subscription = DaoEntityBuilder.buildSubscription(resultSet);
					subscriptions.add(subscription);
				}
			} catch (ConnectionPoolException | SQLException e) {
				throw new DaoException("database error", e);
			}
			return subscriptions;
		}

		@Override
		public List<Subscription> findSubscriptionsByStatus(String subscriptionStatus) throws DaoException {
			List<Subscription> subscriptions = new ArrayList<>();
			try (Connection connection = ConnectionPool.getInstance().getConnection();
					PreparedStatement statement = connection.prepareStatement(SQL_SELECT_SUBSCRIPTIONS_BY_STATUS)) {
				statement.setString(1, subscriptionStatus);
				ResultSet resultSet = statement.executeQuery();
				while (resultSet.next()) {
					Subscription subscription = DaoEntityBuilder.buildSubscription(resultSet);
					subscriptions.add(subscription);
				}
			} catch (ConnectionPoolException | SQLException e) {
				throw new DaoException("database error", e);
			}
			return subscriptions;
		}

	@Override
	public List<Subscription> findAll() throws DaoException {
		throw new UnsupportedOperationException("operation not supported for class " + this.getClass().getName());
	}

}
