package com.ea.wwce.automation.eahelp.tests;

import java.util.HashMap;
import java.util.Map;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import com.ea.wwce.automation.base.util.dataprovider.DataProviders;
import io.qameta.allure.Description;

/**
 * 
 * @author M1022570 Verify that for 2 word search term both word should come
 *         within Proximity factor 7 distance.
 */
public class EAHelpVerifyThatFor2WordSearchTerm extends EAHelpBaseTest {

	//@Test(description = "Verify that for 2 word search term", groups = { "Regression", "Sanity" })
	@Description("Verify that for 2 word search term")
	public void verifyTwoWordSearchTerm(ITestContext context) {

		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "39264";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		// load EAHELP
		eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString());

		// verify search item
		eaHelpHomePage.globalSearch(testData.get("SearchTerm").toString());

		// Verify search item is present
		String txt = eaHelpProductPage.getTextFromBreadCrumb();

		// verify search item
		assertEquals(txt, testData.get("SearchedTerm").toString(), "Verify searched item is shown");

		// assert all
		assertAll();
	}

}
