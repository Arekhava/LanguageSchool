package com.languageschool.model.service;

import com.languageschool.model.service.impl.CatalogServiceImpl;
import com.languageschool.model.service.impl.SubscriptionServiceImpl;
import com.languageschool.model.service.impl.UserServiceImpl;

/**
 * The factory is responsible for service instances
 * 
 * @author N
 */
public final class ServiceFactory {
	private static final ServiceFactory instance = new ServiceFactory();
	private final UserService userService = new UserServiceImpl();
	private final CatalogService catalogService = new CatalogServiceImpl();
	private final SubscriptionService subscriptionService = new SubscriptionServiceImpl();

	private ServiceFactory() {
	}

	/**
	 * Get instance of this class
	 * 
	 * @return {@link ServiceFactory} instance
	 */
	public static ServiceFactory getInstance() {
		return instance;
	}

	/**
	 * Get instance of {@link UserService}
	 * 
	 * @return {@link UserService} instance
	 */
	public UserService getUserService() {
		return userService;
	}

	/**
	 * Get instance of {@link CatalogService}
	 * 
	 * @return {@link CatalogService} instance
	 */
	public CatalogService getCatalogService() {
		return catalogService;
	}

	/**
	 * Get instance of {@link SubscriptionService}
	 * 
	 * @return {@link SubscriptionService} instance
	 */
	public SubscriptionService getSubscriptionService() {
		return subscriptionService;
	}
}	


