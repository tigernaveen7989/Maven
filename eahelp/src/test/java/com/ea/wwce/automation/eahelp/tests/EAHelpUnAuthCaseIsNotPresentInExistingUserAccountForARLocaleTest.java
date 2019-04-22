package com.ea.wwce.automation.eahelp.tests;

import java.util.HashMap;
import java.util.Map;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import com.ea.wwce.automation.base.util.dataprovider.DataProviders;
import com.ea.wwce.automation.eahelp.enums.CaseType;
import io.qameta.allure.Description;

/**
 * 
 * @author M1022570 Scenario : Verify that unauthenticated email case using
 *         existing Origin user does not display in My-cases section when logged
 *         into Origin with existing different user.
 * 
 */

public class EAHelpUnAuthCaseIsNotPresentInExistingUserAccountForARLocaleTest extends EAHelpBaseTest {

	@Test(description = "Verify that unauthenticated not display in My-cases section", groups = { "Regression",
			"Sanity" })
	@Description("Verify that unauthenticated not display in My-cases section")
	public void verifyUnAuthUserNotRedirectedToLoginPage(ITestContext context) throws InterruptedException {

		Map<String, Object> testData = new HashMap<String, Object>();

		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "39511";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		// load EAHELP
		eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString().replace("en", "ar"));

		// select product
		eaHelpGameLibraryPage.selectProduct(testData.get("product").toString());

		// Click on contact us button
		eaHelpProductPage.clickOnContactUsButton();

		// Select platform
		eaHelpCaseInformationPage.selectPlatform(testData.get("platform").toString());

		// Select category
		eaHelpCaseInformationPage.selectCategory(testData.get("category").toString());

		// Select sub category
		eaHelpCaseInformationPage.selectSubCategory(testData.get("subcategory").toString());

		// Click on select contact option
		eaHelpCaseInformationPage.clickOnSelectContactOption();

		// Submit unauth login to form
		eaHelpUnAuthCaseInformationPage.submitUnAuthLoginForm(testData.get("FName").toString(),
				testData.get("LName").toString(), testData.get("Email").toString());

		eaHelpChannelSelectionPage.submitChannelForm(CaseType.email, testData.get("subject").toString(),
				testData.get("description").toString(), testData.get("phonenumber").toString());

		// Get case number from case confirmation page
		String caseNumber = eaHelpCaseConfirmationPage.getCaseNumber().replace("#", "");

		// verify case number is created
		assertNotNull(caseNumber, "Verify case number is created");

		// validate login
		eaHelpLoginPage.loginToEAHelp(testData.get("username").toString(), testData.get("password").toString());

		// Click on my cases link
		eaHelpHomePage.clickOnMyCaseLinkFromAccountsMenu();

		// Verify case number is present
		assertFalse(eaHelpMyCasesPage.isCaseNumberIsPresent(caseNumber), "verify case number is not present");

		// Assert all
		assertAll();

	}
}
