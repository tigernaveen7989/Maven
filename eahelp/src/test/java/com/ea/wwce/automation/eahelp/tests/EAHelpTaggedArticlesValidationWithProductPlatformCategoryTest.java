package com.ea.wwce.automation.eahelp.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

import io.qameta.allure.Description;

/**
 * 
 * @author M1022570
 * @description - FY17-20589 Verify that product platfrom and category is auto
 *              selected in ICR landing article search page
 * 
 */

public class EAHelpTaggedArticlesValidationWithProductPlatformCategoryTest extends EAHelpBaseTest {

	/**
	 * Verify that product platfrom and category is auto selected in ICR landing
	 * article search page
	 * 
	 * @param context
	 * @throws InterruptedException
	 */

	@Test(description = "Verify that product platfrom and category is auto selected", groups = { "Regression",
			"Sanity" })
	@Description("Verify that product platfrom and category is auto selected")
	public void verifyCaseInfoIsDisplayedForArticleSearch(ITestContext context) throws InterruptedException {
		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "39270";
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
		eaHelpGameLibraryPage.selectProduct(testData.get("Article_Product").toString());

		// Select category
		eaHelpProductPage.selectCategory(testData.get("article_category").toString());

		Thread.sleep(2000);

		// Select category
		eaHelpProductPage.selectPlatform(testData.get("platform").toString());

		Thread.sleep(5000);

		// Click on contact us button
		eaHelpProductPage.clickOnContactUsButton();

		// click on create new case button
		eaHelpCaseInformationPage.clickOnCreateNewCaseButton();

		// Verify selected product from channel selection page
		assertTrue(eaHelpCaseInformationPage.isSelectedProductPresent(), "Verify platform is selected");

		// Verify selected platform from channel selection page
		assertTrue(eaHelpCaseInformationPage.isSelectedPlatformPresent(), "Verify product is selected");
		
		Thread.sleep(3000);

		// Verify selected category from channel selection page
		assertTrue(eaHelpCaseInformationPage.isSelectedCategoryPresent(), "Verify category is selected");

		/*
		 * // Select sub category
		 * eaHelpCaseInformationPage.selectSubCategory(testData.get(
		 * "subcategory").toString());
		 * 
		 * // Click on select contact option
		 * eaHelpCaseInformationPage.clickOnSelectContactOption();
		 * 
		 * // Verify selected product from channel selection page
		 * assertNotNull(eaHelpChannelSelectionPage.getSelectedProduct(),
		 * "Verify product is selected");
		 * 
		 * // Verify selected platform from channel selection page
		 * assertNotNull(eaHelpChannelSelectionPage.getSelectedPlatform(),
		 * "Verify platform is selected");
		 * 
		 * // Verify selected category from channel selection page
		 * assertNotNull(eaHelpChannelSelectionPage.getSelectedCategory(),
		 * "Verify category is selected");
		 * 
		 * // Verify selected sub category from channel selection page
		 * assertNotNull(eaHelpChannelSelectionPage.getSelectedSubCategory(),
		 * "Verify subcategory is selected");
		 */

		// Assert all
		assertAll();
	}

}
