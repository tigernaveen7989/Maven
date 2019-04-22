package com.wwce.ea.automation.integration.tests.tsm;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.pageobjects.BasePageObject;
import com.ea.wwce.automation.base.util.dataprovider.DataProviders;
import com.ea.wwce.automation.eahelp.enums.CaseType;
import com.ea.wwce.automation.integration.config.IntegrationDataConstants;

import io.qameta.allure.Description;

/**
 * 
 * @author rgandham
 * @description This Test is to Verify Edit Account Basic info of the player.
 * 
 */
public class VerifyEditAccountBasicInfoTest extends TSMIntegrationBaseTest {

	public static Logger logger = Logger.getLogger(VerifyEditAccountBasicInfoTest.class);

	// Map<String, WebDriver> driverInstance = new HashMap<String, WebDriver>();

	@Test(description = "Verfiy Edit Account Basic info ", groups = { "Regression", "Sanity" })
	@Description("Verfiy Edit Account Basic info ")
	public void verifyEditAccountbasicinfo(ITestContext context) throws InterruptedException {
		Map<String, WebDriver> mDriverInstance = new HashMap<String, WebDriver>();
		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "21195, 20801, 20982, 20985, 20986, 20988, 20989, 20990";
		context.setAttribute("testcase_id", testID);
		logger.info("verify Edit Account Basic info Test " + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		// Load EA Help website
		eaHelpLoginPage.loadEAHelp(IntegrationDataConstants.INTEGRATION_EAHELP_TSM_AUT_URL);
		// Create Email case
		String caseNumber = eaHelpChannelSelectionPage.createAuthCase(testData, CaseType.email);
		assertNotNull(caseNumber, "Verify case number is not null");

		// LOAD TSM ISNTANCE
		mDriverInstance.put("EAHELP", this.driver);
		// Create driver instance to open tsm website
		this.driver = this.loadNewInstance(context);
		
		String firstNameWithRandomNumber = testData.get("firstname").toString() + " "
				+ BasePageObject.generateRandomNumber(1, 9999);
		String lastNameWithRandomNumber = testData.get("lastname").toString() + " "
				+ BasePageObject.generateRandomNumber(1, 9999);
		
	//	String caseNumber = context.getAttribute("caseNumber").toString();
		
		// Login to TSM
		tsmLoginPage.loginToTSM(IntegrationDataConstants.INTEGRATION_TSM_AUT_URL, testData.get("username_tsm").toString(), testData.get("password_tsm").toString());
		// LOAD TSM ISNTANCE
		mDriverInstance.put("TSM", this.driver);
		// Click on Omni Channel
		tsmAdvisorStatesPage.clickOmniChannel();
		// Change Advisor presence status to Available Email
		tsmAdvisorStatesPage.selectOmniStatusAvailableEmail();
		// Close omni channel
		tsmAdvisorStatesPage.CloseOmnichannel();
		// closing existing cases
		tsmAccountBasicInfoPage.closeexistingcases();
		// Search caseID in TSM application
		assertTrue(tsmAccountBasicInfoPage.searchCaseId(caseNumber), "Case details are displayed");
		// Click on Account tab
		tsmAccountBasicInfoPage.clkOnAccount();
		tsmAccountBasicInfoPage.editbasicInfo(firstNameWithRandomNumber, lastNameWithRandomNumber,
				testData.get("dateofbirth").toString(), testData.get("country").toString(),
				testData.get("language").toString(), testData.get("primaryemail").toString(),
				testData.get("secondaryemail").toString(), testData.get("phonenumber").toString(),
				testData.get("Customervalue").toString());
		
		assertTrue(
				tsmAccountBasicInfoPage.verifyAccountBasicInfoPage(firstNameWithRandomNumber, lastNameWithRandomNumber,
						testData.get("country").toString(), testData.get("dateofbirth").toString(),
						testData.get("persona").toString(), testData.get("nucleusID").toString(),
						testData.get("primaryemail").toString(), testData.get("phonenumber").toString(),
						testData.get("language").toString(), testData.get("Customervalue").toString()),
				"Account details are matched");

		// Close Case in TSM
		tsmAccountBasicInfoPage.closeCase(caseNumber);
		// Set driver instances to context
		context.setAttribute("mDriverInstances", mDriverInstance);
		assertAll();
	}
}
