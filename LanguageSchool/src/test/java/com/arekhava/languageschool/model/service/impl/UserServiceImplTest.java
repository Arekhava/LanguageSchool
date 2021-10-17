package com.arekhava.languageschool.model.service.impl;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.arekhava.languageschool.controller.command.ParameterAndAttribute;
import com.arekhava.languageschool.entity.User;
import com.arekhava.languageschool.entity.UserStatus;
import com.arekhava.languageschool.model.dao.DaoException;
import com.arekhava.languageschool.model.dao.UserDao;
import com.arekhava.languageschool.model.service.InvalidDataException;
import com.arekhava.languageschool.model.service.ServiceException;


public class UserServiceImplTest {
	@Mock
	private UserDao userDao;
	private Map<String, String> userInfo;
	private User user;
	private AutoCloseable autoCloseable;
	
	@InjectMocks
	private UserServiceImpl userService;
	
	@BeforeClass
	public void setUp() {
		userInfo = new HashMap<>();
		userInfo.put(ParameterAndAttribute.LOGIN, "language.school.web.web@mail.ru");
		userInfo.put(ParameterAndAttribute.PASSWORD, "nata1111");
		userInfo.put(ParameterAndAttribute.USER_NAME, "Nata");
		userInfo.put(ParameterAndAttribute.PHONE, "+375295110059");
		userInfo.put(ParameterAndAttribute.USER_ID, "1");
		userInfo.put(ParameterAndAttribute.CURRENT_LOGIN, "no1307441@gmail.com");
		user = new User();
		user.setLogin("no1307441@gmail.com");
		user.setPassword("e78a71e6e06141d7ae58d300fe5972db7aba587f");
		user.setName("Nata");
		user.setPhone("+375295110059");
		user.setUserId(1L);
	}
	

	@BeforeMethod
	public void init() {
		autoCloseable = MockitoAnnotations.openMocks(this);
	}

	@AfterMethod
	public void tierDown() throws Exception {
		autoCloseable.close();
	}
	
	@Test
	public void registrationPositiveTest() throws DaoException, ServiceException, InvalidDataException {
		userInfo.put(ParameterAndAttribute.PASSWORD, "nata1111");
		when(userDao.findUserByLogin(anyString())).thenReturn(Optional.empty());
		doNothing().when(userDao).create(isA(User.class));
		Assert.assertTrue(userService.registration(userInfo));
		verify(userDao, times(1)).create(isA(User.class));
		verify(userDao, times(1)).findUserByLogin(anyString());
	}
	
	@Test(expectedExceptions = InvalidDataException.class)
	public void registrationInvalidDataExceptionTest() throws ServiceException, InvalidDataException {
		userService.registration(null);
	}

	@Test(expectedExceptions = ServiceException.class)
	public void registrationServiceExceptionTest() throws ServiceException, InvalidDataException, DaoException {
		userInfo.put(ParameterAndAttribute.PASSWORD, "nata1111");
		when(userDao.findUserByLogin(anyString())).thenReturn(Optional.empty());
		doThrow(DaoException.class).when(userDao).create(isA(User.class));
		userService.registration(userInfo);
	}
	
	@Test
	public void activationPositiveTest() throws ServiceException, DaoException {
		when(userDao.changeUserStatus(anyLong(), isA(UserStatus.class), isA(UserStatus.class))).thenReturn(true);
		Assert.assertTrue(userService.activation("1"));
	}

	@Test
	public void activationNegativeTest() throws ServiceException, DaoException {
		when(userDao.changeUserStatus(anyLong(), isA(UserStatus.class), isA(UserStatus.class))).thenReturn(false);
		Assert.assertFalse(userService.activation("1"));
	}

	@Test(expectedExceptions = ServiceException.class)
	public void activationServiceExceptionTest() throws DaoException, ServiceException {
		when(userDao.changeUserStatus(anyLong(), isA(UserStatus.class), isA(UserStatus.class)))
				.thenThrow(DaoException.class);
		userService.activation("1");
	}

	@Test
	public void authorizationPositiveTest() throws ServiceException, DaoException {
		Optional<User> expected = Optional.of(user);
		when(userDao.findUserByLogin(anyString())).thenReturn(expected);
		Optional<User> actual = userService.authorization("language.school.web.web@mail.ru", "nata1111");
		Assert.assertEquals(actual, expected);
	}


	@Test(expectedExceptions = ServiceException.class)
	public void authorizationServiceExceptionTest() throws ServiceException, DaoException {
		when(userDao.findUserByLogin(anyString())).thenThrow(DaoException.class);
		userService.authorization("no1397441@gmail.com", "nata1111");
	}

	@Test
	public void changeForgottenPasswordPositiveTest() throws ServiceException, DaoException {
		when(userDao.findUserByLogin(anyString())).thenReturn(Optional.of(user));
		when(userDao.updatePassword(anyString(), anyString())).thenReturn(true);
		Assert.assertTrue(userService.changeForgottenPassword("language.school.web.web@mail.ru"));
	}

	@Test
	public void changeForgottenPasswordNegativeTest() throws DaoException, ServiceException {
		when(userDao.findUserByLogin(anyString())).thenReturn(Optional.of(user));
		when(userDao.updatePassword(anyString(), anyString())).thenReturn(false);
		Assert.assertFalse(userService.changeForgottenPassword("language.school.web.web@mail.ru"));
	}

	@Test(expectedExceptions = ServiceException.class)
	public void changeForgottenPasswordServiceExceptionTest() throws DaoException, ServiceException {
		when(userDao.findUserByLogin(anyString())).thenReturn(Optional.of(user));
		when(userDao.updatePassword(anyString(), anyString())).thenThrow(DaoException.class);
		Assert.assertFalse(userService.changeForgottenPassword("language.school.web.web@mail.ru"));
	}

	@Test
	public void changePasswordPositiveTest() throws DaoException, ServiceException, InvalidDataException {
		when(userDao.updatePassword(anyString(), anyString(), anyString())).thenReturn(true);
		Assert.assertTrue(userService.changePassword("no1397441@gmail.com", "nata1111", "nata1111"));
	}

	@Test
	public void changePasswordNegativeTest() throws DaoException, ServiceException, InvalidDataException {
		when(userDao.updatePassword(anyString(), anyString(), anyString())).thenReturn(false);
		Assert.assertFalse(userService.changePassword("no1397441@gmail.com", "nata1111", "nata1111"));
	}

	@Test(expectedExceptions = InvalidDataException.class)
	public void changePasswordInvalidDataExceptionTest() throws ServiceException, InvalidDataException {
		userService.changePassword("111", "nata1111", "111111");
	}

	@Test(expectedExceptions = ServiceException.class)
	public void changePasswordServiceExceptionTest() throws DaoException, ServiceException, InvalidDataException {
		when(userDao.updatePassword(anyString(), anyString(), anyString())).thenThrow(DaoException.class);
		Assert.assertFalse(userService.changePassword("no1397441@gmail.com", "nata1111", "nata1111"));
	}

	@Test
	public void changeUserDataPositiveTest() throws DaoException, ServiceException, InvalidDataException {
		userInfo.put(ParameterAndAttribute.PASSWORD, "nata1111");
		when(userDao.findUserByLogin(anyString())).thenReturn(Optional.empty());
		when(userDao.update(isA(User.class))).thenReturn(true);
		Assert.assertTrue(userService.changeUserData(userInfo));
	}

	@Test
	public void changeUserDataNegativeTest() throws DaoException, ServiceException, InvalidDataException {
		userInfo.put(ParameterAndAttribute.PASSWORD, "nata1111");
		when(userDao.findUserByLogin(anyString())).thenReturn(Optional.empty());
		when(userDao.update(isA(User.class))).thenReturn(false);
		Assert.assertFalse(userService.changeUserData(userInfo));
	}

	@Test(expectedExceptions = InvalidDataException.class)
	public void changeUserDataInvalidDataExceptionTest() throws ServiceException, InvalidDataException {
		userService.changeUserData(null);
	}

	@Test(expectedExceptions = ServiceException.class)
	public void changeUserDataServiceExceptionTest() throws DaoException, ServiceException, InvalidDataException {
		userInfo.put(ParameterAndAttribute.PASSWORD, "nata1111");
		when(userDao.findUserByLogin(anyString())).thenReturn(Optional.empty());
		when(userDao.update(isA(User.class))).thenThrow(DaoException.class);
		userService.changeUserData(userInfo);
	}

	@Test
	public void blockUserPositiveTest() throws ServiceException, DaoException {
		when(userDao.changeUserStatus(anyLong(), isA(UserStatus.class), isA(UserStatus.class))).thenReturn(true);
		Assert.assertTrue(userService.blockUser("1"));
	}

	@Test
	public void blockUserNegativeTest() throws ServiceException, DaoException {
		when(userDao.changeUserStatus(anyLong(), isA(UserStatus.class), isA(UserStatus.class))).thenReturn(false);
		Assert.assertFalse(userService.blockUser("1"));
	}

	@Test(expectedExceptions = ServiceException.class)
	public void blockUserServiceExceptionTest() throws ServiceException, DaoException {
		when(userDao.changeUserStatus(anyLong(), isA(UserStatus.class), isA(UserStatus.class)))
				.thenThrow(DaoException.class);
		Assert.assertTrue(userService.blockUser("1"));
	}

	@Test
	public void unblockUserPositiveTest() throws ServiceException, DaoException {
		when(userDao.changeUserStatus(anyLong(), isA(UserStatus.class), isA(UserStatus.class))).thenReturn(true);
		Assert.assertTrue(userService.unblockUser("1"));
	}

	@Test
	public void unblockUserNegativeTest() throws ServiceException, DaoException {
		when(userDao.changeUserStatus(anyLong(), isA(UserStatus.class), isA(UserStatus.class))).thenReturn(false);
		Assert.assertFalse(userService.unblockUser("1"));
	}

	@Test(expectedExceptions = ServiceException.class)
	public void unblockUserServiceExceptionTest() throws ServiceException, DaoException {
		when(userDao.changeUserStatus(anyLong(), isA(UserStatus.class), isA(UserStatus.class)))
				.thenThrow(DaoException.class);
		Assert.assertTrue(userService.unblockUser("1"));
	}

	@Test
	public void sendMessagePositiveTest() throws ServiceException, DaoException {
		when(userDao.findUserByLogin(anyString())).thenReturn(Optional.of(user));
		Assert.assertTrue(userService.sendMessage("language.school.web.web@mail.ru", "Hello"));
	}

	@Test
	public void sendMessageNegativeTest() throws ServiceException, DaoException {
		when(userDao.findUserByLogin(anyString())).thenReturn(Optional.empty());
		Assert.assertFalse(userService.sendMessage("language.school.web.web@mail.ru", "Hello"));
	}

	@Test(expectedExceptions = ServiceException.class)
	public void sendMessageServiceExceptioTest() throws ServiceException, DaoException {
		when(userDao.findUserByLogin(anyString())).thenThrow(DaoException.class);
		userService.sendMessage("language.school.web.web@mail.ru", "Hello");
	}

	@Test
	public void takeAllUsersPositiveTest() throws ServiceException, DaoException {
		List<User> expected = Arrays.asList(user);
		when(userDao.findAll()).thenReturn(expected);
		List<User> actual = userService.takeAllUsers();
		Assert.assertEquals(actual, expected);
	}

	@Test
	public void takeAllUsersNegativeTest() throws ServiceException, DaoException {
		List<User> expected = Collections.emptyList();
		when(userDao.findAll()).thenReturn(expected);
		List<User> actual = userService.takeAllUsers();
		Assert.assertEquals(actual, expected);
	}

	@Test(expectedExceptions = ServiceException.class)
	public void takeAllUsersServiceExceptionTest() throws ServiceException, DaoException {
		when(userDao.findAll()).thenThrow(DaoException.class);
		userService.takeAllUsers();
	}

	@Test
	public void takeUserByIdPositiveTest() throws DaoException, ServiceException {
		Optional<User> expected = Optional.of(user);
		when(userDao.findUserById(anyString())).thenReturn(expected);
		Optional<User> actual = userService.takeUserById("1");
		Assert.assertEquals(actual, expected);
	}

	@Test
	public void takeUserByIdNegativeTest() throws DaoException, ServiceException {
		Optional<User> expected = Optional.empty();
		when(userDao.findUserById(anyString())).thenReturn(expected);
		Optional<User> actual = userService.takeUserById("1");
		Assert.assertEquals(actual, expected);
	}

	@Test(expectedExceptions = ServiceException.class)
	public void takeUserByIdServiceExceptionTest() throws DaoException, ServiceException {
		when(userDao.findUserById(anyString())).thenThrow(DaoException.class);
		userService.takeUserById("1");
	}

	@Test
	public void takeUserByLoginPositiveTest() throws DaoException, ServiceException {
		Optional<User> expected = Optional.of(user);
		when(userDao.findUserByLogin(anyString())).thenReturn(expected);
		Optional<User> actual = userService.takeUserByLogin("no1307441@gmail.com");
		Assert.assertEquals(actual, expected);
	}

	@Test
	public void takeUserByLoginNegativeTest() throws DaoException, ServiceException {
		Optional<User> expected = Optional.empty();
		when(userDao.findUserByLogin(anyString())).thenReturn(expected);
		Optional<User> actual = userService.takeUserByLogin("no1307441@gmail.com");
		Assert.assertEquals(actual, expected);
	}

	@Test(expectedExceptions = ServiceException.class)
	public void takeUserByLoginServiceExceptionTest() throws DaoException, ServiceException {
		when(userDao.findUserByLogin(anyString())).thenThrow(DaoException.class);
		userService.takeUserByLogin("no1307441@gmail.com");
	}
}

