package com.arekhava.languageschool.validator;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.arekhava.languageschool.model.service.validator.UserInfoValidator;



public class UserInfoValidatorTest {

	@DataProvider(name = "validLogin")
	public static Object[][] validLogin() {
		return new Object[][] { { "no5110059@gmail.com" }, { "language.school.web.web@mail.ru" }, { "oval.raisa@tut.by" } };
	}

	@Test(dataProvider = "validLogin")
	public void isValidLoginPositiveTest(String login) {
		Assert.assertTrue(UserInfoValidator.isValidLogin(login));
	}

	@DataProvider(name = "invalidLogin")
	public static Object[][] invalidLogin() {
		return new Object[][] { { "natallia" }, { null }, { "no5110059@gmailcom" } };
	}

	@Test(dataProvider = "invalidLogin")
	public void isValidLoginNegativeTest(String login) {
		Assert.assertFalse(UserInfoValidator.isValidLogin(login));
	}

	@DataProvider(name = "validPassword")
	public static Object[][] validPassword() {
		return new Object[][] { { "123456" }, { "nata1111" }, { "inna100" } };
	}

	@Test(dataProvider = "validPassword")
	public void isValidPasswordPositiveTest(String password) {
		Assert.assertTrue(UserInfoValidator.isValidPassword(password));
	}

	@DataProvider(name = "invalidPassword")
	public static Object[][] invalidPassword() {
		return new Object[][] { { "1" }, { "наталья" }, { null } };
	}

	@Test(dataProvider = "invalidPassword")
	public void isValidPasswordNegativeTest(String password) {
		Assert.assertFalse(UserInfoValidator.isValidPassword(password));
	}

	@DataProvider(name = "validName")
	public static Object[][] validName() {
		return new Object[][] { { "Mariya" }, { "Наталья Орехова" }, { "Наталья-Арина" } };
	}

	@Test(dataProvider = "validName")
	public void isValidNamePositiveTest(String name) {
		Assert.assertTrue(UserInfoValidator.isValidName(name));
	}

	@DataProvider(name = "invalidName")
	public static Object[][] invalidName() {
		return new Object[][] { { "Nata123" }, { null }, { "Таня/Аня" } };
	}

	@Test(dataProvider = "invalidName")
	public void isValidNameNegativeTest(String name) {
		Assert.assertFalse(UserInfoValidator.isValidName(name));
	}

	@DataProvider(name = "validPhone")
	public static Object[][] validPhone() {
		return new Object[][] { { "+375295110059" }, { "+375292952528" }, { "+375298145601" } };
	}

	@Test(dataProvider = "validPhone")
	public void isValidPhonePositiveTest(String phone) {
		Assert.assertTrue(UserInfoValidator.isValidPhone(phone));
	}

	@DataProvider(name = "invalidPhone")
	public static Object[][] invalidPhone() {
		return new Object[][] { { "+375298" }, { null }, { "375295110059" } };
	}

	@Test(dataProvider = "invalidPhone")
	public void isValidPhoneNegativeTest(String phone) {
		Assert.assertFalse(UserInfoValidator.isValidPhone(phone));
	}
}
