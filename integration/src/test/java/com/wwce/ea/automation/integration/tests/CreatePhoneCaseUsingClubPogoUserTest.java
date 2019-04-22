package com.wwce.ea.automation.integration.tests;

import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import com.ea.wwce.automation.base.util.dataprovider.DataProviders;
import com.ea.wwce.automation.eahelp.enums.CaseType;
import com.ea.wwce.automation.integration.config.IntegrationDataConstants;
import io.qameta.allure.Description;

/**
 * 
 * @author sadabala Create phone case for ENGLISH locale
 */

public class CreatePhoneCaseUsingClubPogoUserTest extends IntegrationBaseTest {

	public static Logger logger = Logger.getLogger(CreatePhoneCaseUsingClubPogoUserTest.class);
	Map<String, WebDriver> mDriverInstance = new HashMap<String, WebDriver>();

	@Test(description = "Create phone case for ENGLISH locale ", groups = { "Regression", "Sanity" })
	@Description("Create phone case for ENGLISH locale")
	public void verifyGetCallOptionIsEnabled(ITestContext context) throws InterruptedException {

		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "40484";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		// Login as a phone advsior
		omegaRolesContainerPage.loginAsAdvsior(IntegrationDataConstants.INTEGRATION_OMEGA_AUT_URL,
				testData.get("omegausername").toString(), testData.get("omegapassword").toString(),
				testData.get("RoleName").toString());

		// LOAD OMEGA ISNTANCE
		mDriverInstance.put("OMEGA", this.driver);

		// Create driver instance to open omega website
		this.driver = this.loadNewInstance(context);

		// Load EA Help website
		eaHelpLoginPage.loadEAHelp(IntegrationDataConstants.INTEGRATION_EAHELP_AUT_URL);

		// LOAD EAHELP ISNTANCE
		mDriverInstance.put("EAHELP", this.driver);

		// Set driver instances to context
		context.setAttribute("mDriverInstances", mDriverInstance);

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

		// Verify Get call button is enabled
		assertTrue(eaHelpChannelSelectionPage.fillCaseDetailsAndVerifyChannelButtonIsEnabled(CaseType.phone,
				testData.get("subject").toString(), testData.get("description").toString(),
				testData.get("phonenumber").toString()), "Verify Get call element is enabled");

		// Assert all
		assertAll();

	}

}
