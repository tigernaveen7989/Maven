package com.ea.wwce.automation.eahelp.tests;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

import io.qameta.allure.Description;

public class EAHelpAccountManagementForAuthUserTest extends EAHelpBaseTest {

	public static Logger logger = Logger.getLogger(EAHelpAccountManagementForAuthUserTest.class);

	/**
	 * verify authenticated user can see Change/Recover Password, Change your
	 * email address, Order Management, Redeem a Code, Account security
	 **/
	@Test(description = "Verify Manage Your Account ", groups = { "Regression", "Sanity" })
	@Description("Verify Manage Your Account")
	public void verifyAccountManagementVisibilityCaseCreationForUnAuthenticatedUsers(ITestContext context)
			throws InterruptedException {

		Map<String, Object> testData = new HashMap<String, Object>();
		// Provide the ID of the test case. This is the ID generated in the
		// TestRail
		// when the manual test case is created.
		String testID = "40320,40321,40322,40323,40324";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		// load EAHELP
		eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString());

		eaHelpLoginPage.loginToEAHelp(testData.get("username").toString(), testData.get("password").toString());

		// verify my account info for unauthenticated user
		eaHelpAccountManagementPage.clickManageAccount();

		eaHelpAccountManagementPage.isChangeOrRecoverPasswordVisible();

		assertTrue(eaHelpAccountManagementPage.isChangeEmailAddressVisible(), " Verify change email address visible");

		assertTrue(eaHelpAccountManagementPage.isOrderManagementVisible(), "Verify order management is visible ");

		assertTrue(eaHelpAccountManagementPage.isredeemCodeVisible(), "Verify redeem code option is visible");

		assertTrue(eaHelpAccountManagementPage.isAccountSecurityVisible(), "Verify account security is visible");

		assertTrue(eaHelpAccountManagementPage.isIdentityManagementVisible(), "Verify identity management is visible");

		assertTrue(eaHelpAccountManagementPage.isMyCasesVisible(),
				"Verify my account options are visible for auth user");

		assertAll();
	}

}