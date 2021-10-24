package com.arekhava.languageschool.validator;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.arekhava.languageschool.model.service.validator.IdValidator;
import com.arekhava.languageschool.model.service.validator.SubscriptionInfoValidator;
import com.arekhava.languageschool.model.service.validator.UserInfoValidator;
import com.arekhava.languageschool.model.service.validator.impl.IdValidatorImpl;
import com.arekhava.languageschool.model.service.validator.impl.SubscriptionInfoValidatorImpl;
import com.arekhava.languageschool.model.service.validator.impl.UserInfoValidatorImpl;



public class IdValidatorTest {
	
	private IdValidator idValidator = new IdValidatorImpl();
	

	@DataProvider(name = "validId")
	public static Object[][] validId() {
		return new Object[][] { { "25" }, { "35289" }, { "123456987" }, { "08" } };
	}

	@Test(dataProvider = "validId")
	public void isValidIdPositiveTest(String id) {
		Assert.assertTrue(idValidator.isValidId(id));
	}

	@DataProvider(name = "invalidId")
	public static Object[][] invalidId() {
		return new Object[][] { { null }, { "jpp" }, { "1234569875456565654545466" }, { "35h" } };
	}

	@Test(dataProvider = "invalidId")
	public void isValidIdNegativeTest(String id) {
		Assert.assertFalse(idValidator.isValidId(id));
	}
}
