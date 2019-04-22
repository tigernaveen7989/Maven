package com.wwce.ea.automation.integration.tests.tsm;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;
import com.ea.wwce.automation.integration.config.IntegrationDataConstants;

import io.qameta.allure.Description;

/**
 * 
 * @author rgandham
 * @description This Test is to Verify Auth Chat channel Deflection Message in EAHelp ICR Page.
 *              
 */

public class VerifyAuthChatChannelDeflectionMessageTest extends TSMIntegrationBaseTest {
	
	public static Logger logger = Logger.getLogger(VerifyAuthChatChannelDeflectionMessageTest.class);

	@Test(description = "Verify AuthChat channel Deflection Message", groups = { "Regression", "Sanity" })
	@Description("Verify AuthChat channel Deflection Message")
	public void verifyAuthChatchannelDeflectionMessage(ITestContext context) throws InterruptedException {

		Map<String, Object> testData = new HashMap<String, Object>();
		Map<String, WebDriver> mDriverInstance = new HashMap<String, WebDriver>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "18901, 18944, 18945";
		context.setAttribute("testcase_id", testID);
		logger.info("Verify AuthChat channel Deflection Message" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		// Load EA Help website
		eaHelpLoginPage.loadEAHelp(IntegrationDataConstants.INTEGRATION_EAHELP_TSM_AUT_URL);
		// validate login
		eaHelpLoginPage.loginToEAHelp(testData.get("username").toString(), testData.get("password").toString());
		// select product
		eaHelpGameLibraryPage.selectProduct(testData.get("product").toString());

		// Click on contact us button
		eaHelpProductPage.clickOnContactUsButton();
		// click on create new case button
		eaHelpCaseInformationPage.clickOnCreateNewCaseButton();
		// Select platform
		eaHelpCaseInformationPage.selectPlatform(testData.get("platform").toString());
		// Select category
		eaHelpCaseInformationPage.selectCategory(testData.get("category").toString());
		// Select sub category
		eaHelpCaseInformationPage.selectSubCategory(testData.get("subcategory").toString());
		// Click on select contact option
		eaHelpCaseInformationPage.clickOnSelectContactOption();
		String defletionmessage = eaHelpChannelSelectionPage.verifyChatdeflection();
		assertTrue(defletionmessage.equalsIgnoreCase(testData.get("defletionmessage").toString()), "Deflection Message is matched");

		// Set driver instances to context
		context.setAttribute("mDriverInstances", mDriverInstance);
		assertAll();

	}
}
