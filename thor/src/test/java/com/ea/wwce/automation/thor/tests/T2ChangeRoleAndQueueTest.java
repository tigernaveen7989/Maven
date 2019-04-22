package com.ea.wwce.automation.thor.tests;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;


import io.qameta.allure.Description;

public class T2ChangeRoleAndQueueTest extends ThorBaseTest {

	public static Logger logger = Logger.getLogger(T2ChangeRoleAndQueueTest.class);
	@Test(description = "Thor 281 - Verify that T2 Advisor able to change Role and Queue when click on Manage Queue",groups={"Regression"})
	@Description("Verify Manage Queue Link functionality")
	public void verifyManageQueue(ITestContext context) throws InterruptedException {
		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;
	
		//Provide the ID of the test case. This is the ID generated in the TestRail when the manual test case is created.
		testID = "15517";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Manage Queue Link functionality" + testID);
	
		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);
	
		// validate login
		thorLoginPage.loginToTHOR(testData.get("T2username").toString(), testData.get("T2password").toString());
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
		omniChannelWidgetPage.clickOnOmniMinimizeButton();				
		//Get Cases count from Queue
		int intQueueCnt=thorPetitionPage.getCasesCountFrmQueue();
		//System.out.println("Case Count from Queue "+intQueueCnt);
		
		//After completing action verify Queued Tab
		assertTrue(thorPetitionPage.verifyQueuedCount(), "Verify Queued Tab records count before Role change");	
		//Click on Manage Queue button
		thorPetitionPage.ManageQueue();
				
		//Select queue from Fill your Queue modal
		boolean strStatus=fillUrQueuePage.verifyExistanceOfFillUrQueueDialog();
		if (strStatus) 
			fillUrQueuePage.selectDifferentQueueCheckBox(testData.get("queueName").toString(),testData.get("queueName1").toString());
		else
			assertFalse(strStatus," Select Your Queue modal not displayed");
		
		
		//Verify Queued tab visibility
		assertTrue(thorPetitionPage.islnkQueuedTabVisible(), "Verify Queued Tab Link visibility after pull the Queue");
		//Change Advisor status to Available in Omni
		thorPetitionPage.clickOnOmniChannelButton();
		omniChannelWidgetPage.changeOmniStatusToAvailable();
		int intCaseCnt1=omniChannelWidgetPage.getCasesCountFrmOmniWidget();
		//System.out.println("Case Count "+intCaseCnt1);
		omniChannelWidgetPage.clickOnOmniMinimizeButton();		
		
		//Get Cases count from Queue
		int intQueueCnt1=thorPetitionPage.getCasesCountFrmQueue();
		//System.out.println("Case Count from Queue "+intQueueCnt1);
		//After completing action verify Queued Tab
		assertTrue(thorPetitionPage.verifyQueuedCount(), "Verify Queued Tab records count after Role change");	
		assertAll();
	}

}
