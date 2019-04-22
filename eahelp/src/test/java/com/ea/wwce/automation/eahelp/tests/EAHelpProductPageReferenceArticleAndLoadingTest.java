package com.ea.wwce.automation.eahelp.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

import io.qameta.allure.Description;

public class EAHelpProductPageReferenceArticleAndLoadingTest extends EAHelpBaseTest {

	/**
	 * 1) Verify that If a user clicks on an additional search result while
	 * viewing a previous article, the articles should tab in the article
	 * viewer( in Related Articles section on right pane)
	 * 
	 * 2)Verify that user views server status on the product page Verify that
	 * user can click through multiple articles and all gets loaded sucecssfully
	 * on the screen (with no performance issue)
	 * 
	 * 
	 */

	@Test(description = "Verify product page reference articles test scenarios", groups = { "Regression", "Sanity" })
	@Description("Verify product page reference articles test scenarios")
	public void verifyArticlesReferencesAndMultipleArticlesLoding(ITestContext context) {
		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "40387,40389,40235,40239";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		// load EAHELP
		eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString());

		// Login to omega
		eaHelpLoginPage.loginToEAHelp(testData.get("username").toString(), testData.get("password").toString());

		
		context.setAttribute("testcase_id", testID);
		logger.info("Validating category based articles " + testID);

		// Load the test data for the test ID
		testData = dataProvider.getTestData(testID);

		// Select product
		eaHelpGameLibraryPage.selectProduct(testData.get("productName").toString());

		// Search for article
		eaHelpProductPage.searchArticle(testData.get("searchArticle").toString());

		// click on article name
		eaHelpProductPage.clickOnArticle(testData.get("searchArticle").toString());

		if (!eaHelpProductPage.verfiyArticleName(testData.get("articleName").toString())) {
			eaHelpProductPage.refreshPage();
		}

		// Verify parent article is present
		assertTrue(eaHelpProductPage.verfiyArticleName(testData.get("articleName").toString()),
				"Verfiy reference article is displayed");

		// click on article name
		eaHelpProductPage.clickOnArticle(testData.get("articleName").toString());

		if (!eaHelpProductPage.verfiyArticleName(testData.get("searchArticle").toString())) {
			eaHelpProductPage.refreshPage();
		}

		// Verify parent article is present
		assertTrue(eaHelpProductPage.verfiyArticleName(testData.get("searchArticle").toString()),
				"Verfiy reference article is displayed");

		// do assert all
		assertAll();

	}

}
