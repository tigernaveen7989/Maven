package com.ea.wwce.automation.eahelp.tests.e2e;

import java.util.HashMap;
import java.util.Map;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import com.ea.wwce.automation.base.util.dataprovider.DataProviders;
import com.ea.wwce.automation.eahelp.tests.EAHelpBaseTest;
import io.qameta.allure.Description;

public class EAHelpHomePageToAHQNavigationTest extends EAHelpBaseTest {

	@Test(description = "Verify EAHelp to AHQ navigation from home page", groups = { "Regression", "Sanity" })
	@Description("Verify EAHelp to AHQ navigation from home page ")
	public void verifyEAHelpToAHQProductPageNavigation(ITestContext context) throws InterruptedException {

		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "40461,40464,40157,40175";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		// load EAHELP
		eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString());

		// Click on AHQ button
		eaHelpHomePage.clickOnAHQBanner();

		// Switch to AHQ page
		eaHelpProductPage.switchWindowByTitle(testData.get("Ahq_ProductPage_Title").toString());

		// Verify page title
		assertEquals(eaHelpProductPage.getPageTitle(), testData.get("Ahq_ProductPage_Title").toString(),
				"Verify AHQ Product page title");

		// close AHQ window
		eaHelpProductPage.closeCurrentWindow();

		// Switch to AHQ page
		eaHelpProductPage.switchWindowByTitle(testData.get("PageTitle").toString());

		// Login to EA Help
		eaHelpLoginPage.loginToEAHelp(testData.get("username").toString(), testData.get("password").toString());

		Thread.sleep(8000);

		// Click on AHQ button
		eaHelpHomePage.clickOnAHQBanner();

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
