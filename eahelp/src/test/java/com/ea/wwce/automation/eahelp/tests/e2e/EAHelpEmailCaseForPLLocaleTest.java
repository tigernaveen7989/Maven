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
 *Create email case for POLSKA locale
 */

public class EAHelpEmailCaseForPLLocaleTest extends EAHelpBaseTest {

	public static Logger logger = Logger.getLogger(EAHelpEmailCaseForPLLocaleTest.class);

	@Test(description = "Verify the creation of Email case for POLSKA locale", groups = { "Regression", "Sanity" })
	@Description("Create email case for POLSKA locale")
	public void verifyEmailCaseCreation(ITestContext context) throws InterruptedException {

		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "15341";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		// load EAHELP
		eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString().replace("en", "pl"));
		
		// creating email case
		String caseNumber = eaHelpChannelSelectionPage.createAuthCase(testData,
				CaseType.email);

		// verify Case number should not be null
		assertNotNull(caseNumber, "Verify case number is created successfully");

		// assert all
		assertAll();

	}

}
