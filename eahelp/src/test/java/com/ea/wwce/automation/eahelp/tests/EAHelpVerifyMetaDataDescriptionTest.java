package com.ea.wwce.automation.eahelp.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

import io.qameta.allure.Description;

/**
 * "Scenario: Verify that Title tags and Meta description are appropriate for
 * all locales for http://qa.help.ea.com/en/ link "
 * 
 * @author M1022570
 *
 */
public class EAHelpVerifyMetaDataDescriptionTest extends EAHelpBaseTest {

	@Test(description = "Verify that Title tags and Meta description are appropriate ", groups = { "Regression", "Sanity" })
	@Description("Verify that Title tags and Meta description are appropriate")
	public void verifyMetaDataDescription(ITestContext context) {

		Map<String, Object> testData = new HashMap<String, Object>();
		String[] locale;
		String[] title;
		String atitle;
		String metaDesc;
		// Provide the ID of the test case. This is the ID generated in the
		// TestRail
		// when the manual test case is created.
		String testID = "39247";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		// load EAHELP
		eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString());
		eaHelpLoginPage.loginToEAHelp(testData.get("username").toString(), testData.get("password").toString());

		locale = testData.get("locales").toString().split(",");
		title = testData.get("title").toString().split(",");

		for (int i = 0; i < locale.length; i++) {
			atitle = eaHelpLoginPage.getPageTitle();
			assertTrue(atitle.equals(title[0]), "Verify page title");
			metaDesc = eaHelpHomePage.getMeteDescription();
			assertTrue(metaDesc.equals(testData.get("meta-desc" + i).toString()), "Verify meta description");

			if (i < locale.length - 1) {
				eaHelpLoginPage
						.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString().replace("/en", locale[i+ 1] ));
			}

		}

	}

}
