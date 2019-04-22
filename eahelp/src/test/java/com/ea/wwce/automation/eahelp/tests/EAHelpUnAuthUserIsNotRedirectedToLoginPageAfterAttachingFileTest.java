package com.ea.wwce.automation.eahelp.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;
import com.ea.wwce.automation.eahelp.enums.CaseType;

import io.qameta.allure.Description;

/**
 * Scenario : Verify that unauthenticated flow link is displayed above the login
 * box
 * 
 * Verify unauth user not redirecred to login page after attaching file
 */

public class EAHelpUnAuthUserIsNotRedirectedToLoginPageAfterAttachingFileTest extends EAHelpBaseTest {

	@Test(description = "Verify unauth user not redirecred to login page after attaching file", groups = { "Regression",
			"Sanity" })
	@Description("Verify unauth user not redirecred to login page after attaching file")
	public void verifyUnAuthUserNotRedirectedToLoginPage(ITestContext context) throws InterruptedException {

		Map<String, Object> testData = new HashMap<String, Object>();

		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "40415,38979";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		// load EAHELP
		eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString());

		// select product
		eaHelpGameLibraryPage.selectProduct(testData.get("product").toString());

		// Click on contact us button
		eaHelpProductPage.clickOnContactUsButton();

		// Select platform
		eaHelpCaseInformationPage.selectPlatform(testData.get("platform").toString());

		// Select category
		eaHelpCaseInformationPage.selectCategory(testData.get("ncategory").toString());

		// Select sub category
		eaHelpCaseInformationPage.selectSubCategory(testData.get("nsubcategory").toString());

		// Click on select contact option
		eaHelpCaseInformationPage.clickOnSelectContactOption();

		/**
		 * Scenario : Verify that unauthenticated flow link is displayed above
		 * the login box
		 */
		assertTrue(eaHelpUnAuthCaseInformationPage.isNeedHelpLinkTopOfLoginWindow(),
				"Verify need help link is on top of login window");

		// Submit unauth login to form
		eaHelpUnAuthCaseInformationPage.submitUnAuthLoginForm(testData.get("FName").toString(),
				testData.get("LName").toString(), testData.get("Email").toString());

		// attach file "file1":"Blank1KB.txt",
		eaHelpChannelSelectionPage.attachfile(testData.get("file1").toString());

		// Verify attached file name is showing correctly
		eaHelpChannelSelectionPage.verifyFileName(testData.get("file1").toString());

		eaHelpChannelSelectionPage.submitChannelForm(CaseType.email, testData.get("subject").toString(),
				testData.get("description").toString(), testData.get("phonenumber").toString());

		// Get case number from case confirmation page
		assertNotNull(eaHelpCaseConfirmationPage.getCaseNumber().replace("#", ""),
				"Verify case number is created with attachement for unauth user");

		// Assert all
		assertAll();

	}
}
