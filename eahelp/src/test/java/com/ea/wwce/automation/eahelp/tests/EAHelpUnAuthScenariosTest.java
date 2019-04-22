package com.ea.wwce.automation.eahelp.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

import io.qameta.allure.Description;

/**
 * 
 * @author M1022570 Scenario : Verify that unauthenticated user is redirected
 *         directly to channel selection page on clicking contact us button on
 *         product page. -Star Wars: The Old Republic
 * 
 */
public class EAHelpUnAuthScenariosTest extends EAHelpBaseTest {

	@Test(description = "Verify that unauthenticated user is redirected directly to channel selection page", groups = {
			"Regression", "Sanity" })
	@Description("Verify that unauthenticated user is redirected directly to channel selection page")
	public void verifyUnauthenticatedFlowForStatWarsProduct(ITestContext context) throws InterruptedException {

		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "38937";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		// load EAHELP
		eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString());

		// select product
		eaHelpGameLibraryPage.selectProduct(testData.get("Star_Product").toString());

		// Click on contact us button
		eaHelpProductPage.clickOnContactUsButton();

		// Verify icr page elements for star war product
		assertTrue(eaHelpUnAuthCaseInformationPage.verifyICRPageForStarWarProduct(),
				"verify icr page elements for star war product");

		// assert all
		assertAll();

	}
	
	

}
