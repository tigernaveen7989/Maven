package com.ea.wwce.automation.eahelp.tests;

import java.util.HashMap;
import java.util.Map;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

import io.qameta.allure.Description;

/**
 * 
 * @author M1022570 Verify that server status color would be shown as green when
 *         all servers are displayed Verify that server status would be expanded
 *         when user either of the server is Stressed status Verify that server
 *         status would be expanded when user either of the server is Down
 *         status Verify that server status would be expanded when user either
 *         of the server is Down status Verify that server status would be
 *         expanded when user all servers are in Down status Verify that rolled
 *         up server status gets displayed when all servers are UP
 * 
 */

public class EAHelpServerStatusTest extends EAHelpBaseTest {

	@Test(description = "Verify Server status test scenarios", groups = { "Regression", "Sanity" })
	@Description("Verify Server status test scenarios")
	public void verifyServerStatus(ITestContext context) {

		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "40405,40406,40407,40408,40409,40410,40411,40412,40413,40414,40926,40927,40928";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		// load EAHELP
		eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString());

		// validate login
		eaHelpLoginPage.loginToEAHelp(testData.get("username").toString(), testData.get("password").toString());

		// testID = "12";
		context.setAttribute("testcase_id", testID);
		logger.info("Validating category based articles " + testID);

		// Load the test data for the test ID
		testData = dataProvider.getTestData(testID);

		// Select product
		eaHelpGameLibraryPage.selectProduct(testData.get("productName").toString());

		// Verify server status
		String txt = eaHelpProductPage.verfiyServerStatus(testData.get("propertyName").toString());

		// verify server status is in red in color when it is down
		assertEquals(txt, testData.get("propertyValue").toString(), "verify server status is down and red in color");

		// Verify that server status would be expanded when either of the server
		// is Down status
		// Verify that server status would be expanded when user either of the
		// server is Down status
		// Verify that server status would be expanded when user all servers are
		// in Down status
		assertTrue(eaHelpProductPage.verifyServerStatusIsDown(), "Verify server status is down");

		// Verify platforms status are down and visible
		assertTrue(eaHelpProductPage.verifyPlatformStatusIsDown(testData.get("icon1").toString()),
				"Verify platform pc status is down");

		assertTrue(eaHelpProductPage.verifyPlatformStatusIsDown(testData.get("icon4").toString()),
				"Verify platform pc status is down");

		assertTrue(eaHelpProductPage.verifyPlatformStatusIsDown(testData.get("icon5").toString()),
				"Verify platform pc status is down");

		// load EAHELP
		eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString());

		// Select product
		eaHelpGameLibraryPage.selectProduct(testData.get("productName1").toString());

		// Verify server status
		txt = eaHelpProductPage.verfiyServerStatus(testData.get("propertyName").toString());

		// Verify that rolled up server status gets displayed when all servers
		// are UP
		assertTrue(eaHelpProductPage.verifyServerStatusIsUp(),
				"Verify that rolled up server status gets displayed when all servers are UP");

		/**
		 * Verify that server status color would be shown as green when all
		 * servers are displayed
		 **/

		// verify server status is up and green in color
		assertEquals(txt, testData.get("propertyValue1").toString(), "verify server status is up and green in color");

		// click on server status
		eaHelpProductPage.clickOnServerStatus();

		// verify platform-pc icons are present in server status widget
		assertNotNull(
				eaHelpProductPage.verifyPlatformIconsInWidget(testData.get("icon1").toString(),
						testData.get("propertyName1").toString()),
				"verify platform-pc icons are present in server status widget");

		assertNotNull(
				eaHelpProductPage.verifyPlatformIconsInWidget(testData.get("icon2").toString(),
						testData.get("propertyName1").toString()),
				"verify platform-ps3 icons are present in server status widget");

		assertNotNull(
				eaHelpProductPage.verifyPlatformIconsInWidget(testData.get("icon3").toString(),
						testData.get("propertyName1").toString()),
				"verify platform-xb12 icons are present in server status widget");

		// Verify server status is red
		assertAll();

	}
}
