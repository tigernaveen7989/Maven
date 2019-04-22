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
 * @author sadabala Verify that page does not go into infinite loop after coming
 *         back to product library from home page after email case creation.
 * 
 */

public class EAHelpVerifyProductLoadingAfterEmailCaseTest extends EAHelpBaseTest {

	public static Logger logger = Logger.getLogger(EAHelpVerifyProductLoadingAfterEmailCaseTest.class);

	@Test(description = "Verify that page does not go into infinite loop after coming\r\n"
			+ " *         back to product library from home page", groups = { "Regression", "Sanity" })
	@Description("Verify that page does not go into infinite loop after coming\r\n"
			+ " *         back to product library from home page")
	public void verifyEmailCaseCreation(ITestContext context) throws InterruptedException {

		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "40018";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		// load EAHELP
		eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString());

		// creating email case
		String caseNumber = eaHelpChannelSelectionPage.createAuthCase(testData, CaseType.email);

		// verify Case number should not be null
		assertNotNull(caseNumber, "Verify case number is created successfully");

		// click on home page logo
		eaHelpHomePage.clickOnLogoImage();

		// Search product
		eaHelpGameLibraryPage.selectProduct(testData.get("product").toString());

		// Verify search item is present
		String txt = eaHelpProductPage.getTextFromBreadCrumb().trim();

		// verify search item
		assertEquals(txt, testData.get("product").toString(), "Verify searched item is shown");

		// assert all
		assertAll();

	}

}
