package com.ea.wwce.automation.eahelp.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

import io.qameta.allure.Description;

public class EAHelpProductMappedCategoriesDisplayedForArLocaleTest extends EAHelpBaseTest {

	@Test(description = "Verify product mapped categories for AR locale ", groups = { "Regression", "Sanity" })
	@Description("Verify product mapped categories for AR locale")
	public void verifyUnAuthUserNotRedirectedToLoginPage(ITestContext context) throws InterruptedException {

		Map<String, Object> testData = new HashMap<String, Object>();

		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "39510";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		// load EAHELP
		eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString().replace("en", "ar"));

		// validate login
		eaHelpLoginPage.loginToEAHelp(testData.get("username").toString(), testData.get("password").toString());

		// select product
		eaHelpGameLibraryPage.selectProduct(testData.get("product").toString());

		// Click on contact us button
		eaHelpProductPage.clickOnContactUsButton();

		// click on create new case button
		eaHelpCaseInformationPage.clickOnCreateNewCaseButton();

		// Select platform
		eaHelpCaseInformationPage.selectPlatform(testData.get("platform").toString());

		// Verify product mapped category is present
		assertTrue(eaHelpCaseInformationPage.isCategoryPresent(testData.get("category-1").toString()),
				"Verify product mapped category is present for Arabic locale");

		// Verify product mapped category is present
		assertTrue(eaHelpCaseInformationPage.isCategoryPresent(testData.get("category-2").toString()),
				"Verify product mapped category is present for Arabic locale");

		// Verify product mapped category is present
		assertTrue(eaHelpCaseInformationPage.isCategoryPresent(testData.get("category-3").toString()),
				"Verify product mapped category is present for Arabic locale");

		// Verify product mapped category is present
		assertTrue(eaHelpCaseInformationPage.isCategoryPresent(testData.get("category-4").toString()),
				"Verify product mapped category is present for Arabic locale");

		// Verify product mapped category is present
		assertTrue(eaHelpCaseInformationPage.isCategoryPresent(testData.get("category-5").toString()),
				"Verify product mapped category is present for Arabic locale");

		// Verify product mapped category is present
		assertTrue(eaHelpCaseInformationPage.isCategoryPresent(testData.get("category-6").toString()),
				"Verify product mapped category is present for Arabic locale");

		// Verify product mapped category is present
		assertTrue(eaHelpCaseInformationPage.isCategoryPresent(testData.get("category-7").toString()),
				"Verify product mapped category is present for Arabic locale");

		// Verify product mapped category is present
		assertTrue(eaHelpCaseInformationPage.isCategoryPresent(testData.get("category-8").toString()),
				"Verify product mapped category is present for Arabic locale");

		// Verify product mapped category is present
		assertTrue(eaHelpCaseInformationPage.isCategoryPresent(testData.get("category-9").toString()),
				"Verify product mapped category is present for Arabic locale");

		// Assert all
		assertAll();

	}
}
