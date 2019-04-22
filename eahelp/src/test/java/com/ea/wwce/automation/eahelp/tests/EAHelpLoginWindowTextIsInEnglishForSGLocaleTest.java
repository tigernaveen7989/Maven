package com.ea.wwce.automation.eahelp.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;
import com.ea.wwce.automation.eahelp.tests.EAHelpBaseTest;

import io.qameta.allure.Description;

public class EAHelpLoginWindowTextIsInEnglishForSGLocaleTest extends EAHelpBaseTest {

	@Test(description = "Verify that origin login window populated from universal top bar login button shows text in english for SG locale", groups = {
			"Regression", "Sanity" })
	@Description("Verify that login button shows text in english for SG locale")
	public void verifyLoginWindowTextIsInEnglishForSGLocale(ITestContext context) {
		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		String lang = "";

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "39088";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		// load EAHELP
		eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString().replace("en", "sg"));

		// Get login window language
		lang = eaHelpLoginPage.loginWindowLanguage();

		// Verify login window content is in english for SG locale
		assertTrue(lang.equals(testData.get("lang").toString()),
				"Verify login window content is in english for SG locale");

		assertAll();

	}

}
