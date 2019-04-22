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

public class MinorStrikesTest extends ThorBaseTest {

	public static Logger logger = Logger.getLogger(MinorStrikesTest.class);
	@Test(description = "Thor 490 - Verify that Advisor takes Add Minor Strike on the target account events gets logged",groups={"Regression"})
	@Description("Verify Add Minor Strike to Target Account functionality")
	public void AddMinorStrikeToTargetAccount(ITestContext context) throws InterruptedException {

		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		//Provide the ID of the test case. This is the ID generated in the TestRail when the manual test case is created.
		testID = "16286,21077";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Add Minor strike to Target Account" + testID);

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
		//Get Cases count from Omni widget
		int intCaseCnt=omniChannelWidgetPage.getCasesCountFrmOmniWidget();
		//System.out.println("Case Count from Omni "+intCaseCnt);
		omniChannelWidgetPage.clickOnOmniMinimizeButton();
		
		//Get Cases count from Queue
		int intQueueCnt=thorPetitionPage.getCasesCountFrmQueue();
		//System.out.println("Case Count from Queue "+intQueueCnt);
		
		//Apply Filter on Target Persona Id
		int rcount=thorPetitionPage.getQueueCountAfterFilter(testData.get("filterName").toString(),testData.get("filterValue").toString());
		Assert.assertTrue((rcount>=1),"Threre is no Cases Available in the Queue as per the Filter.");
		
		String strPetitionActionVal=testData.get("petitionActionVal").toString();
		String strPLevel=testData.get("penaltyLevel").toString();
		String strDiscpAction=testData.get("disciplinaryAction").toString();
		String strReason=testData.get("reason").toString();
		String strStrikeType=testData.get("strikeType").toString();
				
		//Verify Add Minor  Strike to Target Account functionality
		//Select first row from Queue Table
		thorPetitionPage.selectFirstPetitionFromQueue();
		//Work around for Case details Tab display
		if(!(thorPetitionPage.verifyDisplayOfPetitionPreview()))
		{	thorPetitionPage.clickOnQueueTab();
			thorPetitionPage.selectFirstPetitionFromQueue();
		}
		//Get Details from Petition Preview
		String strPetitionPreviewRHS=thorPetitionPage.getPetitionPreviewDetails();
		String [] arrOfStr = strPetitionPreviewRHS.split(",");
		//System.out.println(arrOfStr[0]);
		//Click on View Case Details button
		thorPetitionPage.clickOnViewCaseDetailsButton();
		//Select Petition Action as Resolve - Violation Confirmed
		thorPetitionPageDetails.selectItemFrmComboBox("petitionAction", strPetitionActionVal);
		//Get the Existing Major Strike no
		int intExistingMinorStrikeNo=thorPetitionPageDetails.getMinorStrikeCnt();
		int index=intExistingMinorStrikeNo+1;
		//System.out.println(intExistingMinorStrikeNo);
		//Add Minor Strike to Target Account
		thorPetitionPageDetails.selectStrikeType(strStrikeType);
		//Get default strike name
		String defaultStrike=thorPetitionPageDetails.getDefaultStrikeName();
		//System.out.println(defaultStrike);
		assertTrue(defaultStrike.contains("Current"),"Default Strike name not displayed Current");
		if(intExistingMinorStrikeNo>=10)
			logger.info("We can not add minor strike count more than 10");
		else
		{
		thorPetitionPageDetails.selectStrikeNameByIndex(index);
		//Select Penalty Level as Account
		thorPetitionPageDetails.clickOnScrollDwn();
		thorPetitionPageDetails.selectItemFrmComboBox("penaltyLevel", strPLevel);
		//Select Disciplinary Action as Ban
		thorPetitionPageDetails.selectItemFrmComboBox("disciplinaryAction", strDiscpAction);
		//Select reason
		thorPetitionPageDetails.selectItemFrmComboBox("reasonCode", strReason);
		//Click on Save And Continue Button
		thorPetitionPageDetails.ckickOnSaveAndContinue(1);
		Thread.sleep(5000);
		int intNewMinorStrikeNo=0;
		intNewMinorStrikeNo=thorPetitionPageDetails.getMinorStrikeCnt();
		Assert.assertTrue(intNewMinorStrikeNo==(intExistingMinorStrikeNo+1),"Minor Strike not added to the Account");
		}
		assertAll();
					
	}

	
}
