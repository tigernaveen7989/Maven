package com.ea.wwce.automation.eahelp.tests.e2e;

import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import com.ea.wwce.automation.base.util.dataprovider.DataProviders;
import com.ea.wwce.automation.eahelp.enums.CaseType;
import com.ea.wwce.automation.eahelp.tests.EAHelpBaseTest;
import io.qameta.allure.Description;

/**
 * 
 * @author sadabala
 * Create an email case for DANMARK locale
 */

public class EAHelpEmailCaseForDKLocaleTest extends EAHelpBaseTest {

	public static Logger logger = Logger.getLogger(EAHelpEmailCaseForDKLocaleTest.class);

	@Test(description = "Verify the creation of Email case for DANMARK Locale", groups = { "Regression", "Sanity" })
	@Description("Create email case from EA Help portal for DANMARK Locale")
	public void verifyEmailCaseCreation(ITestContext context) throws InterruptedException {

		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "40421";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		// load EAHELP
		eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString().replace("en", "dk"));

		// creating email case
		String caseNumber = eaHelpChannelSelectionPage.createAuthCase(testData, CaseType.email);

		// verify Case number should not be null
		assertNotNull(caseNumber, "Verify case number is created successfully");

		// assert all
		assertAll();

	}

}
