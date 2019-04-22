package com.ea.wwce.automation.eahelp.tests;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

import io.qameta.allure.Description;

/**
 * This class is to verify account management change recover tests
 * 
 * @author sadabala
 *
 */

public class EAHelpAccountManagementResetPasswordOptionVisibleTest extends EAHelpBaseTest {

	public static Logger logger = Logger.getLogger(EAHelpAccountManagementResetPasswordOptionVisibleTest.class);

	/**
	 * Verify that user is navigated to'Reset Your Password' if he clicks on
	 * Forget Password link on login page
	 **/

	@Test(description = "Verify Change/Recover Password Your Account ", groups = { "Regression", "Sanity" })
	@Description("Verify Change/Recover Password for Authenticated User")
	public void verifyResetPassword(ITestContext context) throws InterruptedException {

		Map<String, Object> testData = new HashMap<String, Object>();
		// Provide the ID of the test case. This is the ID generated in the
		// TestRail
		// when the manual test case is created.
		String testID = "40344";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		// load EAHELP
		eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString());
		
		// check reset password page displayed when user clicks on forgot
		// password
		assertTrue(eaHelpLoginPage.resetPassword(), "Verify password option is visible");

		// assert all
		assertAll();

	}

}
