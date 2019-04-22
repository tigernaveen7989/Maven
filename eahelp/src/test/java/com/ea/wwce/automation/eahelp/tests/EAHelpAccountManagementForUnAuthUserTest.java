package com.ea.wwce.automation.eahelp.tests;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import io.qameta.allure.Description;

public class EAHelpAccountManagementForUnAuthUserTest extends EAHelpBaseTest {

	public static Logger logger = Logger.getLogger(EAHelpAccountManagementForUnAuthUserTest.class);

	/**
	 * Verify that Manage your account icon is present on the new EA help site home
	 * page. Verify that user can view a knowledge guidance page containing help for
	 * Change /Recover password tab is given functionality. Verify that the user is
	 * prompted to login if he wants to complete "Change in your email address"
	 * Verify that the user is prompted to login if he wants to edit Order
	 * Management Verify that the user is prompted to login if he wants to redeem
	 * any product codes. Verify that user can view a knowledge guidance page
	 * containing help for Account Security Functionality. Verify "Identity
	 * Management" tab functionality. Verify that the user is prompted to login if
	 * he wants to navigate to My Cases. verify unauthenticated user can see
	 * Change/Recover Password, Change your email address, Order Management, Redeem
	 * a Code, Account security
	 **/

	@Test(description = "Verify Manage Your Account ", groups = { "Regression", "Sanity" })
	@Description("Verify Account Management options for Unauthenticated User")
	public void verifyAccountManagementForUnAuthenticatedUsers(ITestContext context) throws InterruptedException {

		// Provide the ID of the test case.This is the ID generated in the
		// TestRail when
		// the manual test case is created.
		String testID = "40312,40313,40314,40315,40316,40317,40318,40319";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// load EAHELP
		eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString());

		// verify manage account info for unauthenticated user
		eaHelpAccountManagementPage.clickManageAccount();

		// verify change/recover password info for unauthenticated user
		eaHelpAccountManagementPage.recoverPassword();
		assertTrue(eaHelpAccountManagementPage.VerifyloginPage(), "Verify login page is visible");
		// verify email address for unauthenticated user
		eaHelpAccountManagementPage.homePageEmailAddress();
		assertTrue(eaHelpAccountManagementPage.VerifyloginPage(), "Verify login page is visible");
		// verify order management for unauthenticated user
		eaHelpAccountManagementPage.orderManagement();
		assertTrue(eaHelpAccountManagementPage.VerifyloginPage(), "Verify login page is visible");

		// verify redeem code info for unauthenticated user
		eaHelpAccountManagementPage.redeemCode();
		assertTrue(eaHelpAccountManagementPage.VerifyloginPage(), "Verify login page is visible");

		// verify account security info for unauthenticated user
		eaHelpAccountManagementPage.accountSecurity();
		assertTrue(eaHelpAccountManagementPage.VerifyloginPage(), "Verify login page is visible");

		// verify identity management is displayed for unauthenticated user
		assertTrue(eaHelpAccountManagementPage.isIdentityManagementVisible(),
				"verify identity management is displayed for unauthenticated user");

		// verify my case for unauthenticated user
		assertTrue(eaHelpAccountManagementPage.isMyCasesVisible(), " verify my case optionfor unauthenticated user");

		// verify login page is shown if we try to access my cases
		eaHelpAccountManagementPage.myCases();
		assertTrue(eaHelpAccountManagementPage.VerifyloginPage(), "Verify login page is visible");

		// Assert all
		assertAll();
	}

}