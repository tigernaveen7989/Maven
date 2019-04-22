package com.ea.wwce.automation.eahelp.tests;

import java.util.HashMap;
import java.util.Map;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import com.ea.wwce.automation.base.util.dataprovider.DataProviders;
import io.qameta.allure.Description;

/**
 * 
 * @author M1022570 Scenario : Article not available message should be displayed
 *         correctly in Dutch locale.
 * 
 */

public class EAHelpAritlcesPageScenariosTest extends EAHelpBaseTest {

	@Test(description = "Article not available message should be displayed correctly in Dutch locale", groups = {
			"Regression", "Sanity" })
	@Description("Article not available message should be displayed correctly in Dutch locale")
	public void verifyArticlePageErrorMessageForDELocaleTest(ITestContext context) throws InterruptedException {

		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "38953,40233,40234";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		// load EAHELP
		eaHelpLoginPage.loadEAHelp(context.getAttribute("BASE_SERVICE_URL").toString().replace("en", "de")
				+ testData.get("Article").toString());

		// Verify error page is shown correctly for DE locale
		assertTrue(eaHelpArticlePage.isErrorPageShownCorrectly(),
				"EA Help Article Page is shown correctly for DE locale");

		// EA Help Article Page content is shown in Dutch for de locale
		assertTrue(eaHelpArticlePage.verifyErrorContentInDeLocale(),
				"EA Help Article Page content is shown in Dutch for de locale");

		// load EAHELP
		eaHelpLoginPage.loadEAHelp(testData.get("Article_1").toString());

		// EA Help Article Page contains image and video
		assertTrue(eaHelpArticlePage.verifyImageVideoPresent(), "EA Help Article Page contains image and video");

		// Assert all
		assertAll();
	}

}
