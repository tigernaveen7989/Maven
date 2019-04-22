package com.ea.wwce.automation.eahelp.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

import io.qameta.allure.Description;

/**
 * Scenario:Verify that the content in Arabic locale is Right to left Arabic
 * content only
 * 
 * @author M1022570
 *
 */
public class EAHelpArabicLocaleScenariosTest extends EAHelpBaseTest {

	@Test(description = "Verify that the content in Arabic locale is Right to left Arabic", groups = { "Regression", "Sanity" })
	@Description("Verify that the content in Arabic locale is Right to left Arabic")
	public void verifyArabicContentIsAlingedRightToLeft(ITestContext context) {

		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "39522";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		// load EAHELP
		eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString().replace("en", "ar"));

		// Get content direction on Arabic locale
		String align = eaHelpHomePage.getContentDirection();

		// Verify content is aligned right to left
		assertEquals(align, testData.get("Arabic_content_Alignment").toString(),
				"Verify content is alinged Right to left on Arabic locale home page");

		// assert all
		assertAll();

	}

	/**
	 * Scenario: Verify that advisor can configure Chat deflection message in
	 * arabic locale
	 * 
	 */

	@Test(priority=1)
	public void verifyDeflectionMessageOnICRPage(ITestContext context) {

		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "39523";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		// load EAHELP
		eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString().replace("en", "ar"));

		// Get content direction on Arabic locale
		String align = eaHelpHomePage.getContentDirection();

		// Verify content is aligned right to left
		assertEquals(align, testData.get("Arabic_content_Alignment").toString(),
				"Verify content is alinged Right to left on Arabic locale home page");

		// assert all
		assertAll();
	}

}
