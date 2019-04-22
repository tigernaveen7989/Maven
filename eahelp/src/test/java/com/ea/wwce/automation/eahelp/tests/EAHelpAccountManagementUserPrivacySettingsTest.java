package com.ea.wwce.automation.eahelp.tests;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

import io.qameta.allure.Description;

public class EAHelpAccountManagementUserPrivacySettingsTest extends EAHelpBaseTest {

	public static Logger logger = Logger.getLogger(EAHelpAccountManagementUserPrivacySettingsTest.class);

	/**
	 * @author sadabala Verify that user is able to modify who can see his
	 *         profile through the account settings. Verify that user is able to
	 *         set the checkbox if other users should see his real name on his
	 *         account profile. Verify that the section Allow users to search
	 *         for me by is present. Verify that Email address checkbox is
	 *         present under this. Verify that Xbox live user id checkbox is
	 *         present under this. Verify that Playstation @ Network Online ID
	 *         checkbox is present under this. Verify that a section called
	 *         Block list is present. Verify that Block list section contains a
	 *         text area to block the users whom user does not wish to show his
	 *         updated. Verify that this section contains an instruction text
	 *         'Blocked users will not be able to see your profile…." Verify
	 *         that Block a user textbox is present below Block a list
	 **/
	@Test(description = "Verify privacy settings of user ", groups = { "Regression", "Sanity" })
	@Description("creating a testcase to check user privacy settings")
	public void verifyUserPrivacysettings(ITestContext context) {
		Map<String, Object> testData = new HashMap<String, Object>();
		// Provide the ID of the test case. This is the ID generated in the
		// TestRail
		// when the manual test case is created.
		String testID = "40343,40345,40346,40347,40348,40349,40350,40351,40352,40353,40354,40355,40356";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		// login to Help
		eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString());
		eaHelpLoginPage.loginToEAHelp(testData.get("account_username").toString(),
				testData.get("account_passowrd").toString());

		// move till privacy settings
		eaHelpAccountManagementPage.clickManageAccount();
		eaHelpAccountManagementPage.recoverPassword();
		eaHelpAccountManagementPage.switchWindowbyToAccountPageTitle();

		// Verify privacy settings functionality
		eaHelpAccountManagementPage.clickOnprivacySettingsTab();
		// check profile visibility settings by changing the visibility to
		// friends
		eaHelpAccountManagementPage.verifyprivacySettingsProfileVisibility();
		// check show real name checkbox in Privacy Settings
		eaHelpAccountManagementPage.checkShowRealName();
		// verify search section is available
		eaHelpAccountManagementPage.isAllowUserToSearchSectionVisible();
		// verify email address section is available
		eaHelpAccountManagementPage.isemailAddressVisible();
		// verify xBoxLive is available
		eaHelpAccountManagementPage.isXBoxLiveVisible();
		// verify psn is available
		eaHelpAccountManagementPage.ispsnVisible();
		// verify originBlockedUserListSection is available
		eaHelpAccountManagementPage.isoriginBlockedUserListSectionVisible();
		// verify block User TextBox Title Msg is available
		assertTrue(eaHelpAccountManagementPage.isBlockUserTextBoxTitleMsgVisible(),
				"verify block User TextBox Title Msg is available");
		// assert all
		assertAll();

	}

}
