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

public class EAHelpAccountManagementChangeRecoverTest extends EAHelpBaseTest {

	public static Logger logger = Logger.getLogger(EAHelpAccountManagementChangeRecoverTest.class);

	/**
	 * Verify that user can is prompted with a security question to proceed
	 * ahead with changing or recovering old password. Verify that user can
	 * proceed ahead only on answering correctly and clicking Continue. Verify
	 * that user is taken back to the non editable acount settings page on
	 * failing to give correct answer to security question. Verify that on
	 * answering correctly, user can navigate to Change/Recover password in edit
	 * mode. Verify that user can change current password through this page.
	 * Verify that user is prompted for current password, new password and
	 * retype new password options Verify that password does not get changed if
	 * user provides an incorrect current password and then new password. Verify
	 * that password does not get changed if user provides a correct current
	 * password but new password does not meet the complexity constraints.
	 * Verify that password does not get changed if all above are correct but
	 * answer to security question is incorrect. Verify that the password gets
	 * changed properly provided current, new password meet all contraints and
	 * security question is also correct.
	 **/

	/**
	 * @param context
	 * @throws InterruptedException
	 **/
	@Test(description = "Verify Change/Recover Password Your Account ", groups = { "Regression", "Sanity" })
	@Description("Verify Change/Recover Password for Authenticated User")
	public void verifyChangeRecoverAccountManagementCase(ITestContext context) throws InterruptedException {

		Map<String, Object> testData = new HashMap<String, Object>();
		// Provide the ID of the test case. This is the ID generated in the
		// TestRail
		// when the manual test case is created.
		String testID = "40326,40327,40328,40329,40333,40336,40337,40338,40339,40340,40341,40342";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		// load EAHELP
		eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString());
		eaHelpLoginPage.loginToEAHelp(testData.get("account_username").toString(),
				testData.get("account_passowrd").toString());

		// Verify change and recovery password functionality
		// eaHelpAccountManagement.ChangeOrRecoverForAuthenticatedUser(testData);
		eaHelpAccountManagementPage.clickManageAccount();
		eaHelpAccountManagementPage.recoverPassword();
		eaHelpAccountManagementPage.switchWindowbyToAccountPageTitle();
		eaHelpAccountManagementPage.editSecuritySectionButton();
		// enter wrong answer to security question
		eaHelpAccountManagementPage.securityQuestion(testData.get("securityQuestionWrong_FirstJob").toString());
		// check the error msg for wrong answer to security question
		eaHelpAccountManagementPage.securityQuestionErrorMessage();
		// enter correct answer to the security question
		eaHelpAccountManagementPage.securityQuestion(testData.get("securityQuestion_FirstJob").toString());
		// enter wrong current password and check the functionality
		eaHelpAccountManagementPage.changePassword(testData.get("wrongCurrentPassword").toString(),
				testData.get("newPassword").toString(), testData.get("newPassword").toString());
		// verify error msg
		eaHelpAccountManagementPage.invalidCurrentPassword();
		// enter diff confirm new password
		eaHelpAccountManagementPage.changePassword(testData.get("currentPassword").toString(),
				testData.get("newPassword").toString(), testData.get("wrongNewPassword").toString());
		// check error msg
		eaHelpAccountManagementPage.invalidPasswordMatch();
		Thread.sleep(1000);
		// enter both wrong current password and confirm new password
		eaHelpAccountManagementPage.changePassword(testData.get("wrongCurrentPassword").toString(),
				testData.get("newPassword").toString(), testData.get("wrongNewPassword").toString());
		// check invalid password match erro msg
		eaHelpAccountManagementPage.invalidPasswordMatch();
		Thread.sleep(1000);
		// enter less complex new password
		eaHelpAccountManagementPage.changePassword(testData.get("currentPassword").toString(),
				testData.get("lessComplexNewPassword").toString(), testData.get("lessComplexNewPassword").toString());

		Thread.sleep(1000);
		// check invalid password criteria error msg
		eaHelpAccountManagementPage.invalidPasswordCriteria();
		eaHelpAccountManagementPage.changePassword(testData.get("currentPassword").toString(),
				testData.get("newPassword").toString(), testData.get("newPassword").toString());
		// switch to home page
		eaHelpAccountManagementPage.switchWindowToEAHelpHomePageTitle();
		
		driver.manage().deleteAllCookies();
		
		// logout from EAHelp
		eaHelpLoginPage.logoutFromEAHelp();
		// login using new credentials
		eaHelpLoginPage.loginToEAHelp(testData.get("account_username").toString(),
				testData.get("newPassword").toString());
		
		//Verify user name is not present
		assertNotNull(eaHelpHomePage.getUserName(),"Verify username is present");
		
		//assert all
		assertAll();
		
	}

}
