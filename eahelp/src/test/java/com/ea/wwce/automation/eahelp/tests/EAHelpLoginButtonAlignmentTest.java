package com.ea.wwce.automation.eahelp.tests;

import java.util.HashMap;
import java.util.Map;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

/**
 * Verify that the Login URL is changed
 * 
 * @author M1022570
 *
 */
public class EAHelpLoginButtonAlignmentTest extends EAHelpBaseTest {

	
	/**
	 * Scenario: Verify that the login button is aligned properly on My cases
	 * login page.
	 */

	@Test
	public void verifyLoginButtonIsAligedProperlyOnMyCasesLoginPage(ITestContext context) throws InterruptedException {

		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "38969";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		// load EAHELP
		eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString());
		
		//Click on my cases from home page accounts tab
		eaHelpHomePage.clickOnMyCaseLinkFromAccountsMenu();
		
		//Verify Login button is aligned with Origin image
		String width=eaHelpMyCasesPage.verifyLoginButtonIsAlginedWithOriginLogo();
		
		//Verify Orign login box width is as expected
		assertEquals(width, testData.get("width").toString(), "Verify Orign login box width is as expected");
		
		//assert all
		assertAll();

	}

}
