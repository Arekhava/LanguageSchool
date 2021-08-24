package com.languageschool.model.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.languageschool.entity.Language;
import com.languageschool.model.dao.DaoException;
import com.languageschool.model.dao.LanguageDao;
import com.languageschool.model.pool.ConnectionPool;
import com.languageschool.model.pool.ConnectionPoolException;

/**
 * Works with database table languages
 * 
 * @author N
 * @see LanguageDao
 */
public class LanguageDaoImpl implements LanguageDao {
	private static final String SQL_SELECT_ALL_LANGUAGES = "SELECT ID, NAME, IMAGE_NAME FROM LANGUAGES";

	@Override
	public List<Language> findAll() throws DaoException {
		List<Language> languages = new ArrayList<>();
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				Statement statement = connection.createStatement()) {
			ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_LANGUAGES);
			while (resultSet.next()) {
				Language language = DaoEntityBuilder.buildLanguage(resultSet);
				languages.add(language);
			}
		} catch (ConnectionPoolException | SQLException e) {
			throw new DaoException("database error", e);
		}
		return languages;
	}

	@Override
	public void create(Language entity) throws DaoException {
		throw new UnsupportedOperationException("operation not supported for class " + this.getClass().getName());
	}

	@Override
	public boolean update(Language entity) throws DaoException {
		throw new UnsupportedOperationException("operation not supported for class " + this.getClass().getName());
	}
}
