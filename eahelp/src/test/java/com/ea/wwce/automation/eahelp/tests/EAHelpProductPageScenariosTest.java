package com.ea.wwce.automation.eahelp.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

public class EAHelpProductPageScenariosTest extends EAHelpBaseTest {

	@Test
	public void verifyProductSearchScenariosTest(ITestContext context) {

		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "40040,40241,40211,40213,40219,40220,40930,39996,40011";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		// load EAHELP
		eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString());

		// click on Games tab
		eaHelpHomePage.clickOnGamesTab();

		// Search product
		eaHelpHomePage.searchProductsFromGamesList(testData.get("Product_1").toString());

		// Verify product page - server status
		assertTrue(eaHelpProductPage.verfiyServerStatusIsShown(), "Verify server status is shown");

		// Verify product page - article is present
		assertTrue(eaHelpProductPage.verfiyArticleName(testData.get("Article_1").toString()),
				"Verify server status is shown");

		// Verify product page -platform dropdown list present
		assertTrue(eaHelpProductPage.isPlatformDropDownListPresent(), "Verify platform dropdown list is present");

		// Verify product page -category dropdown list present
		assertTrue(eaHelpProductPage.verifyCategoryDropDownPresent(), "Verify category dropdown list is present");

		// Verify product page -AHQ button present
		assertTrue(eaHelpProductPage.isAHQButtonPresent(), "Verify AHQ button is present");

		// Verify product page -contact us button present
		assertTrue(eaHelpProductPage.isContactButtonPresent(), "Verify contact us button is present");

		// click on Games tab
		eaHelpHomePage.clickOnGamesTab();

		// Search product
		eaHelpHomePage.searchProductsFromGamesList(testData.get("Product_2").toString());

		// Verify product page -contact driver image present
		assertTrue(eaHelpProductPage.isContactImagePresent(), "Verify contact driver image is present");

		// click on Games tab
		eaHelpHomePage.clickOnGamesTab();

		// Search product
		eaHelpHomePage.searchProductsFromGamesList(testData.get("Product_3").toString());

		// Verify product page -AHQ button present
		assertTrue(eaHelpProductPage.isAHQButtonPresent(), "Verify AHQ button is present");

		// Verify product page -contact driver image present
		assertFalse(eaHelpProductPage.isContactImagePresent(), "Verify contact driver image is present");

		// Assert all
		assertAll();

	}

	
}
