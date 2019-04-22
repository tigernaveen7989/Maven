package com.ea.wwce.automation.eahelp.tests.e2e;

import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import com.ea.wwce.automation.base.util.dataprovider.DataProviders;
import com.ea.wwce.automation.eahelp.enums.CaseType;
import com.ea.wwce.automation.eahelp.tests.EAHelpBaseTest;
import io.qameta.allure.Description;

/**
 * 
 * @author sadabala Create email case for EN locale
 *
 */

public class EAHelpEmailCaseUsingClubPogoUserTest extends EAHelpBaseTest {

	public static Logger logger = Logger.getLogger(EAHelpEmailCaseUsingClubPogoUserTest.class);

	@Test(description = "Create email case using pogo user", groups = { "Regression", "Sanity" })
	@Description("Create email case using pogo user")
	public void verifyEmailCaseCreation(ITestContext context) throws InterruptedException {

		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "40081,40145";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		// load EAHELP
		eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString());

		/*
		 * // creating email case String caseNumber =
		 * eaHelpChannelSelectionPage.createAuthCase(testData, CaseType.email);
		 */

		// validate login
		eaHelpLoginPage.loginToEAHelp(testData.get("Club_Pogo_userName").toString(),
				testData.get("Club_Pogo_password").toString());

		// verify page is loaded
		eaHelpLoginPage.verifyPageIsLoaded();

		Thread.sleep(3000);

		// select product
		eaHelpGameLibraryPage.selectProduct(testData.get("product").toString());

		// Click on contact us button
		eaHelpProductPage.clickOnContactUsButton();

		// click on create new case button
		eaHelpCaseInformationPage.clickOnCreateNewCaseButton();

		// Select platform
		eaHelpCaseInformationPage.selectPlatform(testData.get("platform").toString());

		Thread.sleep(2000);

		// Select category
		eaHelpCaseInformationPage.selectCategory(testData.get("category").toString());

		Thread.sleep(2000);

		// Select sub category
		eaHelpCaseInformationPage.selectSubCategory(testData.get("subcategory").toString());

		Thread.sleep(2000);

		// Click on select contact option
		eaHelpCaseInformationPage.clickOnSelectContactOption();

		// Enter subject and description
		eaHelpChannelSelectionPage.submitChannelForm(CaseType.email, testData.get("subject").toString(),
				testData.get("description").toString(), testData.get("phonenumber").toString());

		// Get case number from case confirmation page
		String caseNumber = eaHelpCaseConfirmationPage.getCaseNumber().replace("#", "");

		// verify Case number should not be null
		assertNotNull(caseNumber, "Verify case number is created successfully");

		// assert all
		assertAll();

	}

}
