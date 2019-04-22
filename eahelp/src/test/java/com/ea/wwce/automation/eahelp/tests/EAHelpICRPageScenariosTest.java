package com.ea.wwce.automation.eahelp.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

import io.qameta.allure.Description;

public class EAHelpICRPageScenariosTest extends EAHelpBaseTest {

	/**
	 * Verify that platform is prepopulate In ICR if product owned on only one
	 * platform.
	 */

	@Test(description = "Verify that platform is prepopulated In ICR page", groups = { "Regression", "Sanity" })
	@Description("Verify that platform is prepopulated In ICR page")
	public void verifyPrepopulatePlatformIsDisplayed(ITestContext context) {

		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "39268";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);
		// load EAHELP
		eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString());

		// validate login
		eaHelpLoginPage.loginToEAHelp(testData.get("username").toString(), testData.get("password").toString());

		// select product
		eaHelpGameLibraryPage.selectProduct(testData.get("Chat_Product").toString());

		// Click on contact us button
		eaHelpProductPage.clickOnContactUsButton();

		// click on create new case button
		eaHelpCaseInformationPage.clickOnCreateNewCaseButton();

		// Select category
		eaHelpCaseInformationPage.selectCategory(testData.get("Chat_Category").toString());

		// Select sub category
		eaHelpCaseInformationPage.selectSubCategory(testData.get("Chat_Subcategory").toString());

		// Click on select contact option
		eaHelpCaseInformationPage.clickOnSelectContactOption();
		
		eaHelpChannelSelectionPage.isICRPageLoaded();

		// Verify pogo platform is displayed for selected product
		String platform = eaHelpChannelSelectionPage.getSelectedPlatform();

		// Verify expected platform is pre populated
		assertEquals(platform.split(" ")[2].trim(), testData.get("PlatformName").toString(),
				"Verify platform is prepopulate In ICR if product owned on only one platform.");

		// Assert all
		assertAll();

	}

}
