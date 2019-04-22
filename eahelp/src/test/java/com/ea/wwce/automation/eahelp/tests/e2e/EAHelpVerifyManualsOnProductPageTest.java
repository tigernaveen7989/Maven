package com.ea.wwce.automation.eahelp.tests.e2e;

import java.util.HashMap;
import java.util.Map;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;
import com.ea.wwce.automation.eahelp.tests.EAHelpBaseTest;

import io.qameta.allure.Description;

public class EAHelpVerifyManualsOnProductPageTest extends EAHelpBaseTest {

	@Test(description = "Verify manuals section on product page", groups = { "Regression", "Sanity" })
	@Description("Verify manuals section on product page")
	public void verifyManualsOnProductPage(ITestContext context) {

		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "40466,40922,41300,41301";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		// load EAHELP
		eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString());

		// Login to omega
		eaHelpLoginPage.loginToEAHelp(testData.get("username").toString(), testData.get("password").toString());

		// Select a product
		eaHelpGameLibraryPage.selectProduct(testData.get("Manuals_Product").toString());

		// verify manuals element
		eaHelpProductPage.clickOnManuals();

		// Verify manuals are present
		assertTrue(eaHelpProductPage.verifyArticlesListInManuals(),
				"Verify article list is present in Manuals section");

		// load EAHELP
		eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString());
	
		// Select a product
		eaHelpGameLibraryPage.selectProduct(testData.get("Manuals_Product_1").toString());

		// verify manuals element
		eaHelpProductPage.clickOnManuals();

		// Verify manuals are present
		assertTrue(eaHelpProductPage.verifyManualsIsDisplayed(),
				"Verify article list is present in Manuals section");

		// Assert all
		assertAll();

	}

}
