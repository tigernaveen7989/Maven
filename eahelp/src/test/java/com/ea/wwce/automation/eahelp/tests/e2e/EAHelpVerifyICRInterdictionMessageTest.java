package com.ea.wwce.automation.eahelp.tests.e2e;

import java.util.HashMap;
import java.util.Map;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;
import com.ea.wwce.automation.eahelp.tests.EAHelpBaseTest;

public class EAHelpVerifyICRInterdictionMessageTest extends EAHelpBaseTest {

	@Test
	public void verifyICRInterDictionMessage(ITestContext context) throws InterruptedException {

		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "40417,40418";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		// load EAHELP
		eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString());

		// validate login
		eaHelpLoginPage.loginToEAHelp(testData.get("username").toString(), testData.get("password").toString());

		// Select channel configuration
		eaHelpChannelSelectionPage.selectCaseConfiguration(testData.get("inproduct").toString(),
				testData.get("platform").toString(), testData.get("category").toString(),
				testData.get("subcategory").toString());

		// verify ICR interdiction message
		assertTrue(eaHelpCaseInformationPage.isInterdictionMessagePresent(), "Verify interdiction message ");
		
		//assert all
		assertAll();

	}

}
