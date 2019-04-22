package com.ea.wwce.automation.eahelp.tests;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

import io.qameta.allure.Description;

public class EAHelpAccountManagementEditUserSectionTest extends EAHelpBaseTest {

	public static Logger logger = Logger.getLogger(EAHelpAccountManagementEditUserSectionTest.class);

	/**
	 * Verify that edit profile page contains a Basic information section Verify
	 * that user is prompted with a security question to proceed ahead with editing
	 * profile. Verify that user can proceed ahead only on answering correctly and
	 * clicking Continue. Verify that user is taken back to the non editable acount
	 * settings page on failing to give correct answer to security question. Verify
	 * that on answering correctly, user can navigate to Basic information in edit
	 * mode. Verify that user can change Origin ID through edit basic info. Verify
	 * that user can change Real name through edit basic info. Verify that user can
	 * change Email through edit basic info.
	 **/

	@Test(description = "Verify about me of user ", groups = { "Regression", "Sanity" })
	@Description("creating a testcase to check user info")
	public void verifyUserAboutMeSection(ITestContext context) {
		Map<String, Object> testData = new HashMap<String, Object>();

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail
		// when the manual test case is created.
		String testID = "40325,40334,40335,40330,40331,40332";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		// Login to EA HELP
		eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString());
		eaHelpLoginPage.loginToEAHelp(testData.get("account_username").toString(),
				testData.get("account_passowrd").toString());

		// move till edit profile
		eaHelpAccountManagementPage.clickManageAccount();
		eaHelpAccountManagementPage.recoverPassword();
		eaHelpAccountManagementPage.switchWindowbyToAccountPageTitle();
		// click on edit profile aboutMe Tab
		eaHelpAccountManagementPage.clickEditProfileAboutMeTab();
		// click on edit profile basic info section
		eaHelpAccountManagementPage.clickEditProfilebasicInfoSection();
		// security question
		eaHelpAccountManagementPage
				.securityQuestionInAboutMeSection(testData.get("securityQuestion_FirstJob").toString());
		// verify basicInfoOriginId is available
		assertTrue(eaHelpAccountManagementPage.isBasicInfoOriginIdVisible(), "Verify basicInfoOriginId is available");
		// verify emailId is available
		// eaHelpAccountManagementPage.isEmailIdVisible();
		// verify firstName is available
		assertTrue(eaHelpAccountManagementPage.isFirstNameVisible(), "verify firstName is available");
		// verify saveBtn is available
		assertTrue(eaHelpAccountManagementPage.isSaveBtnVisible(), "verify saveBtn is available");
		// assert all
		assertAll();
	}

	/**
	 * Verify that if admin changes the first name of user through Manage your
	 * account- Edit account details, the updated name is displayed on refreshing
	 * the home page.
	 */

	public void verifyFirstNameIsUpdated() {

	}

}
