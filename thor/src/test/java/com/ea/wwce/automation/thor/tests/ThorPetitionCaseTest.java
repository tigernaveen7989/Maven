package com.ea.wwce.automation.thor.tests;

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

public class ThorPetitionCaseTest extends ThorBaseTest {

	public static Logger logger = Logger.getLogger(ThorPetitionCaseTest.class);
	@Test(description = "Thor 86, 87, 103, 288 - Verify that Queued and Completed tabs are displayed when adviser logs in to Thor",groups={"Regression"})
	@Description("Verify Petition Queue Page")
	public void verifyPetationQueuePage(ITestContext context) throws InterruptedException {
		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;
	
		//Provide the ID of the test case. This is the ID generated in the TestRail when the manual test case is created.
		testID = "3143,3376,3569,3570,18976,3146";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Petition Queue" + testID);
	
		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);
	
		// validate login
		thorLoginPage.loginToTHOR(testData.get("username").toString(), testData.get("password").toString());
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
		//Verify Advisor AHT and Performance values from THOR
		assertTrue(!(thorPetitionPage.verifyAHT().isEmpty()),"AHT not displayed");
		assertTrue(!(thorPetitionPage.verifyPerformance().isEmpty()),"AHT not displayed");
				
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
		
		//Apply Filter on Target Persona Id
		int rcount=thorPetitionPage.getQueueCountAfterFilter(testData.get("filterName").toString(),testData.get("filterValue").toString());
		Assert.assertTrue((rcount>=2),"Threre is no Cases Available in the Queue as per the Filter.");
						
		//Perform Action(Escalate) on one petition
		boolean hideFlag1=thorPetitionPage.petitionAction(testData.get("petitionActionVal").toString(),testData.get("petitionNotes").toString());
		int afterQueuedCnt=thorPetitionPage.getQueueTableCount();
		//After completing action verify Queued Tab Count
		Assert.assertTrue((rcount>afterQueuedCnt), "Verify Queued Tab records count after Escalate");
		
		//click on completed tab and get count from Completed table		
		//Get count from completed tab
		int beforeCompletedCnt=thorPetitionPage.getCompletedCount();
		//click on Queued tab
		thorPetitionPage.ClickOnQueuedTab();		
				
		//Perform Action(Resolve) on one petition
		boolean hideFlag2=thorPetitionPage.petitionAction(testData.get("petitionActionVal2").toString(),testData.get("petitionNotes").toString());
		//get count from completed tab after action
		int AfterCompletedCnt=thorPetitionPage.getCompletedCount();
		//After completing action verify Completed Tab Count
		assertTrue((AfterCompletedCnt==beforeCompletedCnt+1), "Verify Completed Tab records count after Escalate");
		//After completing action verify Queued Table
		int afterQueuedCnt1=thorPetitionPage.getQueueTableCount();
		Assert.assertTrue((afterQueuedCnt>afterQueuedCnt1), "Verify Queued Tab records count after Resolve");
		assertAll();
	}
}
