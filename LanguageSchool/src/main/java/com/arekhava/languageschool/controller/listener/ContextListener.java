package com.arekhava.languageschool.controller.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.arekhava.languageschool.model.pool.ConnectionPool;
import com.arekhava.languageschool.model.pool.ConnectionPoolException;

/**
 * Application listener
 * 
 * @author N
 * @see ServletContextListener
 */
@WebListener
public class ContextListener implements ServletContextListener {
	private static final Logger logger = LogManager.getLogger();

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		try {
			ConnectionPool.getInstance().initPool();
		} catch (ConnectionPoolException e) {
			logger.fatal("connection pool initialization error", e);
			throw new RuntimeException("connection pool initialization error", e);
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		try {
			ConnectionPool.getInstance().destroyPool();
		} catch (ConnectionPoolException e) {
			logger.fatal("connection pool destruction error", e);
			throw new RuntimeException("connection pool destruction error", e);
		}
	}
}