package com.ea.wwce.automation.eahelp.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

import io.qameta.allure.Description;

public class EAHelpProductPageArticlesTest extends EAHelpBaseTest {

	/**
	 * Verify that clicking on a category displays results from SF knowledge and AHQ
	 * that have been tagged for that category.* - since this functionality is
	 * updated with AEM, we should see tagged articles with category from AEM
	 * 
	 * Verify that when a customer selects a content it gets displayed in a way
	 * where the filters and search results are also displayed
	 * 
	 * Verify that user views server status on the product page
	 * 
	 */

	@Test(description = "Verify product page article test scenarios", groups = { "Regression", "Sanity" })
	@Description("Verify product page article test scenarios")
	public void verifyArticlesDisplayedBasedOnCategorySelection(ITestContext context) {
		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "40385,40386,40388,40228,40229,40230,40231,40044";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		// load EAHELP
		eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString());

		// Login to omega
		eaHelpLoginPage.loginToEAHelp(testData.get("username").toString(), testData.get("password").toString());

		context.setAttribute("testcase_id", testID);
		logger.info("Validating category based articles " + testID);

		// Load the test data for the test ID
		testData = dataProvider.getTestData(testID);

		// Select product
		eaHelpGameLibraryPage.selectProduct(testData.get("productName").toString());

		// Select category
		eaHelpProductPage.selectCategory(testData.get("category").toString());

		// verify article name
		assertTrue(eaHelpProductPage.verfiyArticleName(testData.get("articleName").toString()),
				"Verfiy category based article is displayed");

		// Verify server status
		// assertTrue(eaHelpProductPage.verfiyServerStatusIsShown(), " Verify server
		// status is displayed");

		// Select category
		eaHelpProductPage.selectCategory(testData.get("category1").toString());

		// Select platform
		eaHelpProductPage.selectPlatform(testData.get("platform").toString());

		// verify article name
		assertTrue(eaHelpProductPage.verfiyArticleName(testData.get("articleName1").toString()),
				"Verfiy category based article is displayed");

		/**
		 * Verify that user is navigated to Contact us link after selection of
		 * categories and products.
		 * 
		 */
		assertTrue(eaHelpProductPage.isContactButtonPresent(), "Verify Contatc us button is present");

		// do assert all
		assertAll();

	}

}
