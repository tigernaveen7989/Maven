package com.ea.wwce.automation.eahelp.tests.e2e;

import java.util.HashMap;
import java.util.Map;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import com.ea.wwce.automation.base.util.dataprovider.DataProviders;
import com.ea.wwce.automation.eahelp.tests.EAHelpBaseTest;
import io.qameta.allure.Description;

public class EAHelpICRPageToAHQNavigationTest extends EAHelpBaseTest {

	@Test(description = "Verify EAHelp to AHQ ICR page navigation", groups = { "Regression", "Sanity" })
	@Description("Verify EAHelp to AHQ ICR page navigation")
	public void verifyEAHelpToAHQProductPageNavigation(ITestContext context) throws InterruptedException {

		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "40460,40463";
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
		eaHelpCaseInformationPage.selectCategory(testData.get("category").toString());

		// Select sub category
		eaHelpCaseInformationPage.selectSubCategory(testData.get("subcategory").toString());

		// Click on select contact option
		eaHelpCaseInformationPage.clickOnSelectContactOption();

		// Submit unauth login to form
		eaHelpUnAuthCaseInformationPage.submitUnAuthLoginForm(testData.get("FName").toString(),
				testData.get("LName").toString(), testData.get("Email").toString());
		
		eaHelpProductPage.moveToAHQSection();
		
		Thread.sleep(2000);

		// Click on AHQ button
		eaHelpProductPage.clickOnAHQButton();

		Thread.sleep(8000);

		// Switch to AHQ page
		eaHelpProductPage.switchWindowByTitle(testData.get("Ahq_ProductPage_Title").toString());

		// Verify page title
		assertEquals(eaHelpProductPage.getPageTitle(), testData.get("Ahq_ProductPage_Title").toString(),
				"Verify AHQ Product page title");

		// close AHQ window
		eaHelpProductPage.closeCurrentWindow();

		// Switch to EA Help page
		eaHelpProductPage.switchWindowByTitle(testData.get("ICRpageTitle").toString());

		// load EAHELP
		// eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString());

		// Login to EA Help
		eaHelpLoginPage.loginToEAHelp(testData.get("username").toString(), testData.get("password").toString());
		/*
		 * // select product
		 * eaHelpHomePage.selectProduct(testData.get("product").toString());
		 * 
		 * // Click on contact us button eaHelpProductPage.clickOnContactUsButton();
		 * 
		 * // click on create new case button
		 * eaHelpCaseInformationPage.clickOnCreateNewCaseButton();
		 * 
		 * // Select platform
		 * eaHelpCaseInformationPage.selectPlatform(testData.get("platform").
		 * toString());
		 * 
		 * // Select category
		 * eaHelpCaseInformationPage.selectCategory(testData.get("category").
		 * toString());
		 * 
		 * // Select sub category
		 * eaHelpCaseInformationPage.selectSubCategory(testData.get(
		 * "subcategory").toString());
		 * 
		 * // Click on select contact option
		 * eaHelpCaseInformationPage.clickOnSelectContactOption();
		 */
		// eaHelpChannelSelectionPage.verifyPageLoaderIsDisplayed(60);

		eaHelpChannelSelectionPage.verifySubjectFieldIsVisible(60);

		// Verify loader is present
		eaHelpChannelSelectionPage.verifyPageLoaderIsDisplayed(2);

		// Verify ICR Page is loaded
		assertTrue(eaHelpChannelSelectionPage.isICRPageLoaded(), "Verify ICR Page is loaded");

		Thread.sleep(8000);

		eaHelpProductPage.moveToAHQSection();
			
		Thread.sleep(2000);

		// Click on AHQ button
		eaHelpProductPage.clickOnAHQButton();		

		// Switch to AHQ page
		eaHelpProductPage.switchWindowByTitle(testData.get("Ahq_ProductPage_Title").toString());

		// verify user name on AHQ
		assertEquals(ahqLoginPage.getUserName(), testData.get("user_name").toString(), "Verify username on AHQ");

		// Verify page title
		assertEquals(eaHelpProductPage.getPageTitle(), testData.get("Ahq_ProductPage_Title").toString(),
				"Verify AHQ Product page title");

		// Assert all
		assertAll();

	}

}
