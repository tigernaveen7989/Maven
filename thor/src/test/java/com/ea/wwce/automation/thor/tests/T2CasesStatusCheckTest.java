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
import com.ea.wwce.automation.thor.pageobjects.ThorPetitionDetailsPage;

import io.qameta.allure.Description;

public class T2CasesStatusCheckTest extends ThorBaseTest {

	public static Logger logger = Logger.getLogger(T2CasesStatusCheckTest.class);
	@Test(description = "Thor 86 - Verify that Case Status for all rows",groups={"Regression"})
	@Description("Verify Petitions Cases Status in T2")
	public void verifyT2PetationsCaseStatus(ITestContext context) throws InterruptedException {
		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;
	
		//Provide the ID of the test case. This is the ID generated in the TestRail when the manual test case is created.
		testID = "3379";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Petitions case Status" + testID);
	
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
		
		//Apply Filter on Target Persona Id
		int rcount=thorPetitionPage.getQueueCountAfterFilter(testData.get("filterName").toString(),testData.get("filterValue").toString());
		Assert.assertTrue((rcount>=1),"Threre is no Cases Available in the Queue as per the Filter.");
		
		for(int i=1;i<=rcount;i++)
		{	thorPetitionPage.selectPetitionFromQueue(i);
			thorPetitionPage.verifyDisplayOfPetitionPreview();
			thorPetitionPage.clickOnViewCaseDetailsButton();
			String strstatus=thorPetitionPageDetails.getCaseStatus();
			assertTrue((strstatus.contains("Escalated")||strstatus.contains("Transferred")||strstatus.contains("Owned by-Advisor")),"T2 Case Status not displayed");
			thorPetitionPageDetails.navigateBacktoPreviewPane();
		}		
				
		assertAll();
	}

}
