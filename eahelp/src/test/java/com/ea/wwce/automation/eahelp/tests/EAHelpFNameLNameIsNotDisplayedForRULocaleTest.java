package com.ea.wwce.automation.eahelp.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

import io.qameta.allure.Description;

/**
 * 
 * @author M1022570 Scenario: Verify that the 'First Name' and 'Last Name' are
 *         not displayed to unauthenticated RU user when on the Contact Us FB
 *         form page.
 * 
 */

public class EAHelpFNameLNameIsNotDisplayedForRULocaleTest extends EAHelpBaseTest {

	@Test(description = "Verify the 'First Name' and 'Last Name' are not displayed", groups = "Regression")
	@Description("Verify the 'First Name' and 'Last Name' are not displayed")
	public void verifyFNameLNameIsNotDisplayedForArLocaleTest(ITestContext context) {

		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "38611";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		// load EAHELP
		eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString().replace("en", "ru"));

		// select product
		eaHelpGameLibraryPage.selectProduct(testData.get("product").toString());

		// Click on contact us button
		eaHelpProductPage.clickOnContactUsButton();

		// click on create new case button
		//eaHelpCaseInformationPage.clickOnCreateNewCaseButton();

		// Select platform
		eaHelpCaseInformationPage.selectPlatform(testData.get("platform").toString());

		// Select category
		eaHelpCaseInformationPage.selectCategory(testData.get("category").toString());

		// Select sub category
		eaHelpCaseInformationPage.selectSubCategory(testData.get("subcategory").toString());

		// Click on select contact option
		eaHelpCaseInformationPage.clickOnSelectContactOption();

		// Click on ea help un auth need help link
		eaHelpUnAuthCaseInformationPage.clickOnNeedHelpLink();

		// Verify first name is not displayed
		assertFalse(eaHelpUnAuthCaseInformationPage.verifyFNameIsNotDisplayed(), "Verify first name is not displayed");

		// Verify Last name is not displayed
		assertFalse(eaHelpUnAuthCaseInformationPage.verifyLNameIsNotDisplayed(), "Verify Last name is not displayed");

		// assert null
		assertAll();

	}

}
