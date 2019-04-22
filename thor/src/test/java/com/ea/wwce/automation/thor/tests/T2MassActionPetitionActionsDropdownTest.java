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

public class T2MassActionPetitionActionsDropdownTest extends ThorBaseTest {

	public static Logger logger = Logger.getLogger(T2MassActionPetitionActionsDropdownTest.class);
	@Test(description = "Thor 92 -Perform mass case actions on petition cases",groups={"regression"})
	@Description("Verify drop down values of Mass action for T2 Advisor")
	public void verifyMassActionDropDownValues(ITestContext context) {

		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		//Provide the ID of the test case. This is the ID generated in the TestRail when the manual test case is created.
		testID = "16935";
		context.setAttribute("testcase_id", testID);
		logger.info("validating drop down values of Mass action for T2 Advisor Test" + testID);

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

		//Select first petition from queue
		thorPetitionPage.selectFirstPetitionFromQueue();
		//Work around for Case details Tab display
		if(!(thorPetitionPage.verifyDisplayOfPetitionPreview()))
		{	thorPetitionPage.clickOnQueueTab();
			thorPetitionPage.selectFirstPetitionFromQueue();
		}
		//Get the Case Number 
		 String strPetitionPreviewRHS=thorPetitionPage.getPetitionPreviewDetails();
		 String [] arrOfStr = strPetitionPreviewRHS.split(",");
		 String CaseNumber=arrOfStr[0];
		 //System.out.println("Case Number "+CaseNumber);
		 
		//Click on All check box
		 thorPetitionPage.clickOnAllCheckBox();
		 String massActionCount=thorPetitionPage.getMassActionCount();
		 String  MassCaseCnt=massActionCount.substring(2);
		 String MassCaseCount=MassCaseCnt.trim();		 
		 //System.out.println("Mass Action Count "+MassCaseCount);
		 
		 //assertTrue(MassCaseCount.contains("cases selected"), "Verify Mass Action records count after select Mass Action");
		 assertTrue((MassCaseCount.equals("cases selected")), "Verify Mass Action records count after select Mass Action");
		 
		 //Verify Petition actions dropdown values
		 String Values=thorPetitionPage.gettextfromdropdown();
		 String [] arrvalues=Values.split(",");
				 
		 //verify first dropdown value
		 assertTrue((arrvalues[0]).contains(testData.get("massActionVal").toString()), "Petition Action drop down dropdown value in Thor application"+arrvalues[0]+" not matched with Petition Action value");
		//verify second dropdown value
		 assertTrue((arrvalues[1]).contains(testData.get("massActionVal3").toString()), "Petition Action drop down dropdown value in Thor application"+arrvalues[1]+" not matched with Petition Action value");
		//verify third dropdown value
		 assertTrue((arrvalues[2]).contains(testData.get("massActionVal4").toString()), "Petition Action drop down dropdown value in Thor application"+arrvalues[2]+" not matched with Petition Action value");
		//verify fourth dropdown value
		 assertTrue((arrvalues[3]).contains(testData.get("massActionVal2").toString()), "Petition Action drop down dropdown value in Thor application"+arrvalues[3]+" not matched with Petition Action value");
				
		 assertAll();
		 
	}

}
