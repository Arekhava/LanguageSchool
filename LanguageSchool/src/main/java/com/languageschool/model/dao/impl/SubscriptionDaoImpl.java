package com.languageschool.model.dao.impl;

import java.util.List;
import java.util.Optional;

import com.languageschool.entity.Subscription;
import com.languageschool.entity.SubscriptionStatus;
import com.languageschool.model.dao.DaoException;
import com.languageschool.model.dao.SubscriptionDao;



/**
 * Works with database table subscriptions
 * 
 * @author N
 * @see SubscriptionDao
 */
public class SubscriptionDaoImpl implements SubscriptionDao {

	@Override
	public void create(Subscription entity) throws DaoException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean update(Subscription entity) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Subscription> findAll() throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateStatus(String subscriptionId, SubscriptionStatus statusFrom, SubscriptionStatus statusTo)
			throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Optional<Long> findSubscriptionAddedId(Long userId) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Subscription> findSubscriptionById(String subscriptionId) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Subscription> findSubscriptionByLogin(String login) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Subscription> findSubscriptionsByStatus(String subscriptionStatus) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
