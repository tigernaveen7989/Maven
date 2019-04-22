package com.ea.wwce.automation.eahelp.tests.e2e;

import java.util.HashMap;
import java.util.Map;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import com.ea.wwce.automation.base.util.dataprovider.DataProviders;
import com.ea.wwce.automation.eahelp.tests.EAHelpBaseTest;
import io.qameta.allure.Description;

public class EAHelpProductPageToAHQNavigationTest extends EAHelpBaseTest {

	@Test(description = "Verify EAHelp to AHQ product page navigation", groups = { "Regression", "Sanity" })
	@Description("Verify EAHelp to AHQ product page navigation")
	public void verifyEAHelpToAHQProductPageNavigation(ITestContext context) {

		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "40459,40462,40174";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		// load EAHELP
		eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString());

		// select product
		eaHelpGameLibraryPage.selectProduct(testData.get("product").toString());

		// Click on AHQ button
		eaHelpProductPage.clickOnAHQButton();

		// Switch to AHQ page
		eaHelpProductPage.switchWindowByTitle(testData.get("Ahq_ProductPage_Title").toString());

		// Verify page title - Not logged in view
		assertEquals(eaHelpProductPage.getPageTitle(), testData.get("Ahq_ProductPage_Title").toString(),
				"Verify AHQ Product page title");

		// close current window
		eaHelpProductPage.closeCurrentWindow();

		// Switch to EA Help home page
		eaHelpProductPage.switchWindowByTitle(testData.get("ProductPage_Title").toString());

		// load EAHELP
		eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString());

		// Login to EA Help
		eaHelpLoginPage.loginToEAHelp(testData.get("username").toString(), testData.get("password").toString());

		// select product
		eaHelpGameLibraryPage.selectProduct(testData.get("product").toString());

		// Click on AHQ button
		eaHelpProductPage.clickOnAHQButton();

		// Switch to AHQ page
		eaHelpProductPage.switchWindowByTitle(testData.get("Ahq_ProductPage_Title").toString());

		// verify user name on AHQ
		assertEquals(ahqLoginPage.getUserName(), testData.get("user_name").toString(), "Verify username on AHQ");

		// Verify page title
		assertEquals(eaHelpProductPage.getPageTitle(), testData.get("Ahq_ProductPage_Title").toString(),
				"Verify AHQ Product page title");

		// Assert all
		assertAll();

	}

}
