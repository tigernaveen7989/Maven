package com.ea.wwce.automation.thor.tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;


import io.qameta.allure.Description;

public class CasesVerificationInSFQueueTest extends ThorBaseTest {

	public static Logger logger = Logger.getLogger(CasesVerificationInSFQueueTest.class);
	@Test(description = "Thor 281 - Verify that after selecting the role and queues, the cases assigned to Advisor are from queues selected . ",groups={"Regression"})
	@Description("Verify cases assigned to Advisor against SF queue")
	public void verifyCaseAssignedToAdvisorAgainstSFqueue(ITestContext context) throws InterruptedException {
		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;
	
		//Provide the ID of the test case. This is the ID generated in the TestRail when the manual test case is created.
		testID = "15493";
		context.setAttribute("testcase_id", testID);
		logger.info("validating cases assigned to Advisor against SF queue" + testID);
	
		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);
	
		// validate login
		thorLoginPage.loginToTHOR(testData.get("udadmin_username").toString(), testData.get("udadmin_password").toString());
		//Verify page after successful login
		thorPetitionPage.islnkQueuedTabVisible();
		assertTrue(thorPetitionPage.islnkQueuedTabVisible(), "Verify Queued Tab Link visibility");
		//Click on Select Role button
		thorPetitionPage.clickOnSelectRoleButton();
		
		//Select Role from Select your Role modal
		boolean strStatus1=chooseUrRolePage.verifyExistanceOfChooseUrRoleDialog();
		if (strStatus1) 
			chooseUrRolePage.selectRoleRadioButton(testData.get("roleName").toString());
		else
			assertFalse(strStatus1," Select Your Role modal not displayed");
		
		//Select queue from Fill your Queue modal
		boolean strStatus2=fillUrQueuePage.verifyExistanceOfFillUrQueueDialog();
		if (strStatus2)
			fillUrQueuePage.selectQueueCheckBox(testData.get("queueName").toString());
		else
			assertFalse(strStatus2," Select Your Queue modal not displayed");
		
		//Verify Queued tab visibility
		assertTrue(thorPetitionPage.islnkQueuedTabVisible(), "Verify Queued Tab Link visibility after pull the Queue");
		//Change Advisor status to Available in Omni
		thorPetitionPage.clickOnOmniChannelButton();
		omniChannelWidgetPage.changeOmniStatusToAvailable();
		int intCaseCnt=omniChannelWidgetPage.getCasesCountFrmOmniWidget();
		//System.out.println("Case Count "+intCaseCnt);
		//Get Cases names from Omni widget
		String[] caseNums=omniChannelWidgetPage.getCaseNumsInArray();
		omniChannelWidgetPage.clickOnOmniMinimizeButton();
		//Get ADvisor name from User Profile
		thorPetitionPage.clickOnUserProfile();
		String strAdvisorName=thorPetitionPage.getAdvisorName();
		//System.out.println(strAdvisorName);
		//Navigate to SF classic				
		thorPetitionPage.switchToSFClassic();
		//Click on Setup Page in SF
		classicHomePage.clickOnSetup();
		//Search Queue on Setup page
		classicSetupPage.searchObjectInSetup(testData.get("queueName").toString());
		//Click on Searched Queue
		classicSetupPage.clickOnQueue(testData.get("queueName").toString());
		//Click on Advisor
		classicSetupPage.clickOnAdvisor(strAdvisorName, testData.get("queueName").toString());
		//Verify existance of Case nums in Advisor page
		int intLength=caseNums.length;
		for(int i=0;i<intLength;i++) 
		{ String strTemp=caseNums[i];
		  boolean flag=classicSetupPage.existanceOfCaseNumLink(strTemp);
		  assertTrue(flag, "Case Num displayed in Omni widget "+strTemp+" not displayed in Queue "+testData.get("queueName").toString());	
		}
				
		//Switch to SF Lightning Link
		classicCRMEventsPage.clickOnSFLightningLnk();	
				
		assertAll();
	}

}
