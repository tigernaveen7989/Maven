package com.wwce.ea.automation.integration.tests.vop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;
import com.ea.wwce.automation.eahelp.enums.CaseType;
import com.ea.wwce.automation.eahelp.pageobjects.EAHelpMyCasesPage;
import com.ea.wwce.automation.integration.config.IntegrationDataConstants;
import com.ea.wwce.automation.integration.pageobjects.EACustomerSatisfactionSurveyPage;
import com.ea.wwce.automation.integration.pageobjects.GmailHomePage;

import io.qameta.allure.Description;

/**
 * This class is to create email case
 * switch to ea help
 * goto account
 * search the case number
 * click on end case
 * Login into Gmail
 * no survey email should be send
 * @author Naveen Kumar
 *
 */   

public class VerifyUnAuthEmailCaseCustomerEndCaseExternalEmailInHouseSurveyTest extends VOPIntegrationBaseTest {

	@Test(description = "Verify Un Auth Email Case Customer Ends Case In House Survey", groups = { "Regression",
			"Sanity", "Production_Email" })
	@Description("Verify Un Auth Email Case Customer Ends Case In House Survey")
	public void VerifyUnAuthEmailCaseCustomerEndCaseExternalEmailInHouseSurveyTest(ITestContext context) throws InterruptedException {

		Map<String, Object> testData = new HashMap<String, Object>();
		Map<String, WebDriver> mDriverInstance = new HashMap<String, WebDriver>();
		String testID;

		// Provide the ID of the test case. This is the ID generated in the
		// TestRail when the manual test case is created.
		testID = "49637";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);

		// Load EA Help website
		eaHelpLoginPage.loadEAHelp(IntegrationDataConstants.INTEGRATION_EAHELP_VOP_AUT_URL);

		// LOAD EAHELP ISNTANCE
		mDriverInstance.put("EAHelp", this.driver);

		// creating email case
		String caseNumber = eaHelpChannelSelectionPage.createUnAuthCase(testData, CaseType.email);

		// verify Case number should not be null
		assertNotNull(caseNumber, "Verify case number is created successfully");

		// Create driver instance to open gmail website
		this.driver = this.loadNewInstance(context);

		gmailLoginPage.loadGmail(IntegrationDataConstants.INTEGRATION_GMAIL_URL);

		gmailLoginPage.loginToGmail(testData.get("gmailemail").toString(), testData.get("gmailpassword").toString());

		Thread.sleep(5000);

		// LOAD GMAIL ISNTANCE
		mDriverInstance.put("GMAIL", this.driver);

		// Set driver instances to context
		context.setAttribute("mDriverInstances", mDriverInstance);

		// driver will wait till gmail email list loads
		gmailHomePage.loadGmailList();

		Thread.sleep(10000);

		// count the survey mail received or not after changing status to waiting on customer
		int count = gmailHomePage.countSurveyMails(testData.get("gmailsubject").toString() + " " + caseNumber.replace("#", ""));
		assertEquals(count, 0, "0 survey mail is expected. "+count+" survey mail is received");
		
		//click on unread email
		gmailHomePage.clickOnUnReadMail(testData.get("gmailsubject1").toString());
		
		//read access key
		String accessKey = gmailHomePage.getAccessKey();
		
		//go back to main page
		gmailHomePage.clickOnBackButton();

		// switch the instance to omega
		this.switchToInstance(mDriverInstance.get("EAHelp"));

		// click on my cases from account menu
		eaHelpHomePage.clickOnMyCaseLinkFromAccountsMenu();

		Thread.sleep(5000);
		
		eaHelpMyCasesUnAuthPage.accessCaseDetailsFromUnAuthMyCasesPage(caseNumber.replace("#", ""), accessKey);

		// click on case details link
		eaHelpMyCasesPage.clickOnCaseDetailsLink(caseNumber.replace("#", ""));

		// click on end case button
		eaHelpMyCasesPage.clickOnEndCaseButton();
		eaHelpMyCasesPage.clickOnSubmitButton();
		
		this.switchToInstance(mDriverInstance.get("GMAIL"));

		this.driver.navigate().refresh();

		// driver will wait till gmail email list loads
		gmailHomePage.loadGmailList();

		Thread.sleep(10000);

		// count the survey mail received or not after changing status to
		// waiting on customer
		count = gmailHomePage
				.countSurveyMails(testData.get("gmailsubject").toString() + " " + caseNumber.replace("#", ""));
		assertEquals(count, 0, "0 survey mail is expected. "+count+" survey mail is received");

		// Assert all
		assertAll();
	}
}
