package com.ea.wwce.automation.eahelp.tests;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

import io.qameta.allure.Description;

public class EAHelpRedeemCodeVerificationTest extends EAHelpBaseTest {

	public static Logger logger = Logger.getLogger(EAHelpRedeemCodeVerificationTest.class);

	/**
	 * Scenario: Verify that "Redeem code" icon under Manage accounts translates
	 * correctly.
	 * 
	 * @param context
	 * @throws InterruptedException
	 */

	@Test(description = "Verify Manage Your Account ", groups = { "Regression", "Sanity" })
	@Description("Verify that Redeem code icon under Manage accounts translates correctly")
	public void verifyRedeemCodeIconForDiffLocales(ITestContext context) throws InterruptedException {

		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;
		String[] locales = null;
		String[] codes = null;
		String code = null;

		// Provide the ID of the test case.This is the ID generated in the
		// TestRail when
		// the manual test case is created.
		testID = "36316";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		// load EAHELP
		eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString());

		// verify manage account info for unauthenticated user
		eaHelpAccountManagementPage.clickManageAccount();

		locales = testData.get("locale").toString().split(",");
		codes = testData.get("codeText").toString().split(",");

		for (int i = 0; i < locales.length; i++) {
			// verify redeem code info for unauthenticated user
			code = eaHelpAccountManagementPage.getRedeemCodeText().toString().trim();
			System.out.println(code);
			System.out.println(codes[i]);
			assertTrue(code.equalsIgnoreCase(codes[i]),
					"Verify code is shown correctly");

			if (i < locales.length - 1) {
				// load EAHELP
				eaHelpLoginPage.loadEAHelp(
						context.getAttribute("BASE_SERVICE_URL").toString().replace("/en", (locales[i + 1])));
				// verify manage account info for unauthenticated user
				eaHelpAccountManagementPage.clickManageAccount();

			}

		}

		assertAll();
	}

}
