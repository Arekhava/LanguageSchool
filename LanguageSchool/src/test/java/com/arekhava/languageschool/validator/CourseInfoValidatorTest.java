package com.arekhava.languageschool.validator;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.arekhava.languageschool.model.service.validator.CourseInfoValidator;


public class CourseInfoValidatorTest {


		@DataProvider(name = "validPrice")
		public static Object[][] validPrice() {
			return new Object[][] { { "300" }, { "170.50" }, { "6565656.00" }, { "3" } };
		}

		@Test(dataProvider = "validPrice")
		public void isValidPricePositiveTest(String price) {
			Assert.assertTrue(CourseInfoValidator.isValidPrice(price));
		}

		@DataProvider(name = "invalidPrice")
		public static Object[][] invalidPrice() {
			return new Object[][] { { "0" }, { "hg" }, { "6565656." }, { "1245778785454777454" } };
		}

		@Test(dataProvider = "invalidPrice")
		public void isValidPriceNegativeTest(String price) {
			Assert.assertFalse(CourseInfoValidator.isValidPrice(price));
		}

		@DataProvider(name = "validImageName")
		public static Object[][] validImageName() {
			return new Object[][] { { "sddsad.jpg" }, { "155.jpg" }, { "fg14sgs5s.jpg" } };
		}

		@Test(dataProvider = "validImageName")
		public void isValidImageNamePositiveTest(String imageName) {
			Assert.assertTrue(CourseInfoValidator.isValidImageName(imageName));
		}

		@DataProvider(name = "invalidImageName")
		public static Object[][] inalidImageName() {
			return new Object[][] { { "dgrgg" }, { null }, { "fgfgfg.exe" } };
		}

		@Test(dataProvider = "invalidImageName")
		public void isValidImageNameNegativeTest(String imageName) {
			Assert.assertFalse(CourseInfoValidator.isValidImageName(imageName));
		}

		@DataProvider(name = "validName")
		public static Object[][] validName() {
			return new Object[][] { { "Desk BLACK" }, { "Стол Натуральный" }, { "Cupboard <MONTESSORI>" } };
		}

		@Test(dataProvider = "validName")
		public void isValidNamePositiveTest(String name) {
			Assert.assertTrue(CourseInfoValidator.isValidName(name));
		}

		@DataProvider(name = "invalidName")
		public static Object[][] invalidName() {
			return new Object[][] { { "" }, { null }, { "dfdgfgfgtfhg htfgyfh fghgfhrshr tyrdstytryyh  ghthtrhthth" } };
		}

		@Test(dataProvider = "invalidName")
		public void isValidNameNegativeTest(String name) {
			Assert.assertFalse(CourseInfoValidator.isValidName(name));
		}

		@DataProvider(name = "validQuantity")
		public static Object[][] validQuantity() {
			return new Object[][] { { "1" }, { "0" }, { "99" } };
		}

		
	}
