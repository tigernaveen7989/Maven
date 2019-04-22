package com.ea.wwce.automation.eahelp.tests.e2e;

import java.util.HashMap;
import java.util.Map;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;
import com.ea.wwce.automation.eahelp.tests.EAHelpBaseTest;

import io.qameta.allure.Description;

public class EAHelpVerifyDeflectionMessageTest extends EAHelpBaseTest {


	@Test(description = "Verify deflection message on ICR page", groups = { "Regression","Sanity" })
	@Description("Verify deflection message on ICR page")
	public void verifyDeflectionMessageOnICRPage(ITestContext context) throws InterruptedException {

		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "40465";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		// load EAHELP
		eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString());

		// validate login
		eaHelpLoginPage.loginToEAHelp(testData.get("username").toString(), testData.get("password").toString());
		
		Thread.sleep(8000);

		// load EAHELP
		eaHelpLoginPage.loadEAHelp(
				context.getAttribute("BASE_SERVICE_URL").toString() + testData.get("Deflection_URL").toString());

		// Verify loader is present
		eaHelpChannelSelectionPage.isICRPageLoaded();

		// Verify Deflection message is displayed
		assertEquals(eaHelpChannelSelectionPage.verifyDeflectionMessage(),
				testData.get("Deflection_Message").toString(), "Verify deflection message");

		// assert all
		assertAll();

	}

}
