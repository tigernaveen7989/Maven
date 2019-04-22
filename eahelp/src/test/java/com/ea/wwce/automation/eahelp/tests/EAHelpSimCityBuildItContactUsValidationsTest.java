package com.ea.wwce.automation.eahelp.tests;

import java.util.HashMap;
import java.util.Map;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import com.ea.wwce.automation.base.util.dataprovider.DataProviders;
import io.qameta.allure.Description;

public class EAHelpSimCityBuildItContactUsValidationsTest extends EAHelpBaseTest {

	@Test(description = "Verify that customer is routed to defined page for simcity-buildit", groups = { "Regression",
			"Sanity" })
	@Description("Verify that customer is routed to defined page for simcity-buildit")
	public void verifySimCityBuildItContactUsValidations(ITestContext context) {
		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "39126";
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
		eaHelpGameLibraryPage.selectProduct(testData.get("simcitybuildItProduct").toString());

		// Click on contanct us page
		eaHelpProductPage.clickOnContactUsButton();
		
		eaHelpChannelSelectionPage.isICRPageLoaded();

		// Verify simcity validations
		assertTrue(eaHelpChannelSelectionPage.validateSimCityICRPage(testData.get("locale").toString(),
				testData.get("validation_1").toString(), testData.get("validation_2").toString(),
				testData.get("validation_3").toString()), "Verify simcity validations");

		// assert all
		assertAll();

	}

}
