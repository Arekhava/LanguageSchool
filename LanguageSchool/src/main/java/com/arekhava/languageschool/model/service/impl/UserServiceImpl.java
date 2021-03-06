package com.arekhava.languageschool.model.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import com.arekhava.languageschool.controller.command.PagePath;
import com.arekhava.languageschool.controller.command.ParameterAndAttribute;
import com.arekhava.languageschool.entity.User;
import com.arekhava.languageschool.entity.UserRole;
import com.arekhava.languageschool.entity.UserStatus;
import com.arekhava.languageschool.entity.builder.impl.UserBuilder;
import com.arekhava.languageschool.model.dao.DaoException;
import com.arekhava.languageschool.model.dao.UserDao;
import com.arekhava.languageschool.model.dao.impl.UserDaoImpl;
import com.arekhava.languageschool.model.service.InvalidDataException;
import com.arekhava.languageschool.model.service.ServiceException;
import com.arekhava.languageschool.model.service.UserService;
import com.arekhava.languageschool.model.service.validator.IdValidator;
import com.arekhava.languageschool.model.service.validator.UserInfoValidator;
import com.arekhava.languageschool.model.service.validator.impl.IdValidatorImpl;
import com.arekhava.languageschool.model.service.validator.impl.UserInfoValidatorImpl;
import com.arekhava.languageschool.util.MailSender;
import com.arekhava.languageschool.util.MessageKey;
import com.arekhava.languageschool.util.PasswordEncryption;



	/**
	 * The service is responsible for user operations
	 * 
	 * @author N
	 * @see UserService
	 */
	public class UserServiceImpl implements UserService {
		private static final String BUNDLE_NAME = "path";
		private static final String PATH_APP = "path.app";
		private static final int NUMBER_PASSWORD_CHARACTERS = 8;
		private UserDao userDao = new UserDaoImpl();
		private UserInfoValidator userInfoValidator = new UserInfoValidatorImpl();
		private IdValidator idValidator = new IdValidatorImpl();
		
		
		@Override
		public boolean registration(Map<String, String> userInfo) throws ServiceException, InvalidDataException {
			if (MapUtils.isEmpty(userInfo)) {
				throw new InvalidDataException("invalid data",
						Arrays.asList(MessageKey.ERROR_IMPOSSIBLE_OPERATION_MESSAGE));
			}
			List<String> errorMessageList = userInfoValidator.findInvalidData(userInfo);
			String login = userInfo.get(ParameterAndAttribute.LOGIN);
			if (userInfoValidator.isValidLogin(login) && !checkIfLoginFree(login)) {
				errorMessageList.add(MessageKey.ERROR_LOGIN_IS_BUSY_MESSAGE);
			}
			if (!errorMessageList.isEmpty()) {
				throw new InvalidDataException("invalid data", errorMessageList);
			}
			boolean result;
			try {
				String encryptedPassword = PasswordEncryption.encrypt(userInfo.get(ParameterAndAttribute.PASSWORD));
				userInfo.put(ParameterAndAttribute.PASSWORD, encryptedPassword);
				User user = UserBuilder.getInstance().build(userInfo);
				user.setRole(UserRole.STUDENT);
				user.setStatus(UserStatus.INACTIVE);
				userDao.create(user);
				String link = ResourceBundle.getBundle(BUNDLE_NAME).getString(PATH_APP) + PagePath.CONFIRM_REGISTRATION;
				result = MailSender.send(user.getLogin(), MessageKey.REGISTRATION_MESSAGE_SUBJECT,
						MessageKey.REGISTRATION_MESSAGE_TEXT + link + user.getUserId());
			} catch (DaoException e) {
				throw new ServiceException("user creation error", e);
			}
			return result;
		}
		
		@Override
		public boolean activation(String userId) throws ServiceException {
			if (!idValidator.isValidId(userId)) {
				return false;
			}
			boolean userActivated;
			try {
				userActivated = userDao.changeUserStatus(Long.valueOf(userId), UserStatus.INACTIVE, UserStatus.ACTIVE);
			} catch (DaoException e) {
				throw new ServiceException("user activation error", e);
			}
			return userActivated;
		}

		@Override
		public Optional<User> authorization(String login, String password) throws ServiceException {
			
			  if (!userInfoValidator.isValidLogin(login) ||
			  !userInfoValidator.isValidPassword(password)) { return Optional.empty(); }
			  Optional<User> userOptional; try { userOptional =
			  userDao.findUserByLogin(login); if (userOptional.isPresent()) { User user =
			  userOptional.get(); String encryptedPassword =
			  PasswordEncryption.encrypt(password); if
			  (!StringUtils.equals(encryptedPassword, user.getPassword())) { userOptional =
			  Optional.empty(); } } } catch (DaoException e) { throw new
			  ServiceException("users search error", e); 
			  } 
			  return userOptional;
			 
		
		}
		@Override
		public boolean changeForgottenPassword(String login) throws ServiceException {
			if (!userInfoValidator.isValidLogin(login)) {
				return false;
			}
			boolean passwordChanged;
			try {
				if (checkIfLoginFree(login)) {
					return false;
				}
				String newPassword = generatePassword();
				String encryptedPassword = PasswordEncryption.encrypt(newPassword);
				passwordChanged = userDao.updatePassword(login, encryptedPassword);
				if (passwordChanged) {
					MailSender.send(login, MessageKey.CHANGE_PASSWORD_MESSAGE_SUBJECT,
							MessageKey.CHANGE_PASSWORD_MESSAGE_TEXT + newPassword);
				}
			} catch (DaoException e) {
				throw new ServiceException("password change error", e);
			}
			return passwordChanged;
		}
		
		@Override
		public boolean changePassword(String login, String currentPassword, String newPassword)
				throws ServiceException, InvalidDataException {
			if (!userInfoValidator.isValidLogin(login)) {
				throw new InvalidDataException("incorrect login",
						Arrays.asList(MessageKey.ERROR_IMPOSSIBLE_OPERATION_MESSAGE));
			}
			if (!userInfoValidator.isValidPassword(newPassword)) {
				throw new InvalidDataException("incorrect password", Arrays.asList(MessageKey.ERROR_PASSWORD_MESSAGE));
			}
			if (!userInfoValidator.isValidPassword(currentPassword)) {
				return false;
			}
			boolean passwordChanged;
			try {
				String encryptedPassword = PasswordEncryption.encrypt(currentPassword);
				String encryptedNewPassword = PasswordEncryption.encrypt(newPassword);
				passwordChanged = userDao.updatePassword(login, encryptedNewPassword, encryptedPassword);
			} catch (DaoException e) {
				throw new ServiceException("password change error", e);
			}
			return passwordChanged;
		}
		@Override
		public boolean changeUserData(Map<String, String> userInfo) throws ServiceException, InvalidDataException {
			if (MapUtils.isEmpty(userInfo)) {
				throw new InvalidDataException("invalid data",
						Arrays.asList(MessageKey.ERROR_IMPOSSIBLE_OPERATION_MESSAGE));
			}
			String userId = userInfo.get(ParameterAndAttribute.USER_ID);
			String currentLogin = userInfo.get(ParameterAndAttribute.CURRENT_LOGIN);
			if (!idValidator.isValidId(userId) || !userInfoValidator.isValidLogin(currentLogin)) {
				throw new InvalidDataException("impossible operation",
						Arrays.asList(MessageKey.ERROR_IMPOSSIBLE_OPERATION_MESSAGE));
			}
			List<String> errorMessageList = userInfoValidator.findInvalidData(userInfo);
			String login = userInfo.get(ParameterAndAttribute.LOGIN);
			if (userInfoValidator.isValidLogin(login) && !StringUtils.equals(login, currentLogin)
					&& !checkIfLoginFree(login)) {
				errorMessageList.add(MessageKey.ERROR_LOGIN_IS_BUSY_MESSAGE);
			}
			if (!errorMessageList.isEmpty()) {
				throw new InvalidDataException("invalid data", errorMessageList);
			}
			boolean userChanged;
			try {
				String encryptedPassword = PasswordEncryption.encrypt(userInfo.get(ParameterAndAttribute.PASSWORD));
				userInfo.put(ParameterAndAttribute.PASSWORD, encryptedPassword);
				User user = UserBuilder.getInstance().build(userInfo);
				user.setUserId(Long.valueOf(userId));
				userChanged = userDao.update(user);
			} catch (DaoException e) {
				throw new ServiceException("user chanding error", e);
			}
			return userChanged;
		}
		@Override
		public boolean blockUser(String userId) throws ServiceException {
			if (!idValidator.isValidId(userId)) {
				return false;
			}
			boolean userBlocked;
			try {
				userBlocked = userDao.changeUserStatus(Long.valueOf(userId), UserStatus.ACTIVE, UserStatus.BLOCKED);
			} catch (DaoException e) {
				throw new ServiceException("user blocking error", e);
			}
			return userBlocked;
		}
		@Override
		public boolean unblockUser(String userId) throws ServiceException {
			if (!idValidator.isValidId(userId)) {
				return false;
			}
			boolean userUnblocked;
			try {
				userUnblocked = userDao.changeUserStatus(Long.valueOf(userId), UserStatus.BLOCKED, UserStatus.ACTIVE);
			} catch (DaoException e) {
				throw new ServiceException("user unblocking error", e);
			}
			return userUnblocked;
		}
		@Override
		public boolean sendMessage(String email, String message) throws ServiceException {
			if ((userInfoValidator.isValidLogin(email) && checkIfLoginFree(email))) {
				return false;
			}
			return MailSender.send(email, MessageKey.INFO_MESSAGE_SUBJECT, message);
		}
		@Override
		public List<User> takeAllUsers() throws ServiceException {
			List<User> users;
			try {
				users = userDao.findAll();
			} catch (DaoException e) {
				throw new ServiceException("users search error", e);
			}
			return users;
		}

		@Override
		public Optional<User> takeUserById(String userId) throws ServiceException {
			if (!idValidator.isValidId(userId)) {
				return Optional.empty();
			}
			Optional<User> userOptional;
			try {
				userOptional = userDao.findUserById(userId);
			} catch (DaoException e) {
				throw new ServiceException("user search error", e);
			}
			return userOptional;
		}
		@Override
		public Optional<User> takeUserByLogin(String login) throws ServiceException {
			if (!userInfoValidator.isValidLogin(login)) {
				return Optional.empty();
			}
			Optional<User> userOptional;
			try {
				userOptional = userDao.findUserByLogin(login);
			} catch (DaoException e) {
				throw new ServiceException("user search error", e);
			}
			return userOptional;
		}

		
		/**
		 * Checks the existence of a user with the given login
		 * 
		 * @param login {@link String} user login
		 * @return boolean true if login is free, else false
		 * @throws ServiceException if {@link DaoException} occurs
		 */
		private boolean checkIfLoginFree(String login) throws ServiceException {
			Optional<User> userOptional;
			try {
				userOptional = userDao.findUserByLogin(login);
			} catch (DaoException e) {
				throw new ServiceException("impossible to check whether the login is free", e);
			}
			return userOptional.isEmpty();
		}

		/**
		 * Generates password
		 * 
		 * @return {@link String} generated new password for the user
		 */
		private String generatePassword() {
			return RandomStringUtils.randomAlphanumeric(NUMBER_PASSWORD_CHARACTERS);
		}
}
