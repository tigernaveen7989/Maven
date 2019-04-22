package com.ea.wwce.automation.eahelp.tests.e2e;

import java.util.HashMap;
import java.util.Map;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;
import com.ea.wwce.automation.eahelp.tests.EAHelpBaseTest;

public class EAHelpVerifyICRPageContentForCZLocaleTest extends EAHelpBaseTest {

	@Test
	public void verifyICRPageContentForCZLocale(ITestContext context) {

		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "40470";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		// load EAHELP
		eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString().replace("en", "cz"));

		// validate login
		eaHelpLoginPage.loginToEAHelp(testData.get("username").toString(), testData.get("password").toString());

		// load EAHELP
		eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString().replace("en", "cz")
				+ testData.get("EmailChannelURL").toString());
		
		eaHelpChannelSelectionPage.verifySubjectFieldIsVisible(60);
		
		// Verify loader is present
		eaHelpChannelSelectionPage.verifyPageLoaderIsDisplayed(10);

		// Verify ICR page is loaded
		eaHelpChannelSelectionPage.isICRPageLoaded();

		// Get Message on CZ ICR page
		String msg = eaHelpChannelSelectionPage.getMessageOnCZICRPage();

		// Verify message
		assertTrue(msg.contains(testData.get("Message_1").toString()), "Verify Message is present on CZ ICR page");

		// Verify message
		assertTrue(msg.contains(testData.get("Message_2").toString()), "Verify Message is present on CZ ICR page");

		// Verify message
		assertTrue(eaHelpChannelSelectionPage.isCZICRPageInEnglish(testData.get("Text_1").toString(),
				testData.get("Text_2").toString(), testData.get("Text_3").toString(),
				testData.get("Text_4").toString()), "Verify Message is present on CZ ICR page");

		// Assert all
		assertAll();

	}
}
