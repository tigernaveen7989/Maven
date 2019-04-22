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
 *              selected in ICR landing from article page
 * 
 */

public class EAHelpTaggedArticlesTest extends EAHelpBaseTest {

	@Test(description = "Verify that product platfrom and category is auto selected", groups = { "Regression",
			"Sanity" })
	@Description("Verify that product platfrom and category is auto selected")
	public void verifyCaseInfoIsDisplayedForTaggedArticles(ITestContext context) {
		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "39269";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		// load EAHELP
		eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString());

		// validate login
		eaHelpLoginPage.loginToEAHelp(testData.get("username").toString(), testData.get("password").toString());

		// load EAHELP
		eaHelpLoginPage.loadEAHelp(
				context.getAttribute("BASE_SERVICE_URL").toString() + testData.get("TaggedArticle_URL").toString());

		// Click on contacts us button
		eaHelpArticlePage.clickOnContactUsButton();
		
		eaHelpChannelSelectionPage.isICRPageLoaded();

		// Verify select product from channel selection page
		assertNotNull(eaHelpChannelSelectionPage.getSelectedProduct(), "Verify product is selected");

		// Verify select platform from channel selection page
		assertNotNull(eaHelpChannelSelectionPage.getSelectedPlatform(), "Verify platform is selected");

		// Verify select category from channel selection page
		assertNotNull(eaHelpChannelSelectionPage.getSelectedCategory(), "Verify category is selected");

		// Verify select sub category from channel selection page
		assertNotNull(eaHelpChannelSelectionPage.getSelectedSubCategory(), "Verify subcategory is selected");

		// Assert all
		assertAll();
	}

}
