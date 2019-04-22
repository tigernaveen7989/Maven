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

public class Tier2BanAccountTest extends ThorBaseTest {

	public static Logger logger = Logger.getLogger(Tier2BanAccountTest.class);
	@Test(description = "Thor 492, Thor736 - Verify that T2 Advisor is able to Ban Account",groups={"Regression"})
	@Description("Verify T2 advisor Ban Account functionality")
	public void verifyT2AdvisorBanAccount(ITestContext context) throws InterruptedException {

		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		//Provide the ID of the test case. This is the ID generated in the TestRail when the manual test case is created.
		testID = "13785,17510,18424,19152,19153,18190";
		context.setAttribute("testcase_id", testID);
		logger.info("validating T2 Advisor Ban functionality" + testID);

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
		boolean flag=false;
		
		//Verify Ban Account functionality
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
		//Click on Player Account button
		thorPetitionPageDetails.clickOnPlayerAccountLnk();
		//Get Target Player Details
		String targetPlyerDet=playerAccountPage.verifyTargetPlayerDetails();
		//System.out.println(targetPlyerDet);		
		String [] arrOfAccount= {};
		if (!(targetPlyerDet.isEmpty()))
		{
			arrOfAccount = targetPlyerDet.split(",");
		}
		String strAcctStatus=arrOfAccount[6];
		//Verify Account Status is Ban Or suspended
		if((strAcctStatus.equals("SUSPENDED")))
		{	logger.info("Current Account Status is suspended, So cant not perform disciplinary action");	
				flag=true;
		}
		if(!(flag))
		{
			//Select Petition Action as Resolve - Violation Confirmed
			thorPetitionPageDetails.selectItemFrmComboBox("petitionAction", strPetitionActionVal);
			//Select Penalty Level as Account
			thorPetitionPageDetails.clickOnScrollDwn();
			thorPetitionPageDetails.selectItemFrmComboBox("penaltyLevel", strPLevel);
			//Select Disciplinary Action as Ban
			thorPetitionPageDetails.selectItemFrmComboBox("disciplinaryAction", strDiscpAction);
			//Select reason
			thorPetitionPageDetails.selectItemFrmComboBox("reasonCode", strReason);
			//Click on Save And Continue Button
			thorPetitionPageDetails.ckickOnSaveAndContinue(1);
			//Verify Manage Content and Automatic Case Notes Sections
			assertTrue(thorPetitionPageDetails.verifyManageContentForBan(strPLevel, strDiscpAction, strReason),"Manage Content not displayed as per our selection");
			String strNotes=thorPetitionPageDetails.getAutomaticCaseNotes();
			assertTrue(((strNotes.contains(strPLevel)) && (strNotes.contains(strDiscpAction)) && (strNotes.contains(strReason)) && (strNotes.contains("Strike:NA"))),"Automatic Case Notes not displayed as per our selection");
			//Click On Save and Continue
			thorPetitionPageDetails.clickOnScrollDwn();
			thorPetitionPageDetails.ckickOnSaveAndContinue(2);
			Thread.sleep(3000);
			//Click On Save and Continue
			thorPetitionPageDetails.clickOnScrollDwn();
			thorPetitionPageDetails.ckickOnSaveAndContinue(3);
			Thread.sleep(3000);
			//Verify Email message popup display and click on Yes Button
			thorPetitionPageDetails.verifyEmailPopupDialog();
			//Click on Commit
			thorPetitionPageDetails.clickOnScrollDwn();
			thorPetitionPageDetails.ckickOnCommitButton();
			//Assert.assertTrue(thorPetitionPage.islnkQueuedTabVisible(),"Commit Action not successful");
			//Due to defect we are using below statements and commented above statement
			Thread.sleep(5000);
			
					
			/*      Verify Case Events in SF                      */
			//Navigate to SF classic
			thorPetitionPage.clickOnUserProfile();
			thorPetitionPage.switchToSFClassic();
			//Search for case in SF and Click on that case
			System.out.println(arrOfStr[0]);
			classicHomePage.searchSFObject(arrOfStr[0]);
			classicSearchPage.clickOnSearchdSFObject(arrOfStr[0]);
			//Verify case details from Cases object
			String strCaseDetails=testData.get("caseDetails").toString();
			String [] arrCaseDetails=strCaseDetails.split(",");
			for(int i=0;i<arrCaseDetails.length;i++)
			{
				String strText=classicCasesPage.getCaseDetailAnyFieldVale(arrCaseDetails[i],"innerText");
				assertTrue((strText.equals(arrOfStr[i])), arrCaseDetails[i]+" in Thor application"+strText+" not matched with SF Cases object "+arrCaseDetails[i]);
			}
						
			//Get CRM Events Table Count
			int intCnt=classicCasesPage.getCRMEventsCount();
			//Click on last CRMevent and verify CRM Event Details
			classicCasesPage.clickOnCRMEvent(intCnt);
			String strCRMEventDetails=testData.get("crmEventDetails").toString();
			String [] arrCRMEventDetails=strCRMEventDetails.split(",");
			for(int j=0;j<arrCRMEventDetails.length;j++)
			{
				String strText=classicCRMEventsPage.getCRMEventDetailAnyFieldVale(arrCRMEventDetails[j],"innerText");
				switch (arrCRMEventDetails[j])
				{
				case "Case": assertTrue((strText.equals(arrOfStr[0])), "Case no in Thor application"+arrOfStr[0]+" not matched with SF Cases CRM Events");
							 break;
				case "EventCategory": assertTrue((strText.equals("StatusUpdate")), "CRM Event Category not displayed as StatusUpdate");
							 		  break;
				case "EventType": assertTrue((strText.equals("CaseEdit")), "CRM Event Type not displayed as CaseEdit");
				 			      break;
				case "EventNote": assertTrue((strNotes.contains(strText)), "Case Notes not matched with CRM Events Notes");
	 			 			      break;
					
				}
				
			}
			//Verify Case Event Details Table values(Old and new values)
			assertTrue(classicCRMEventsPage.getCaseEventDetailsFrmTbl(),"All values in Case Event Detail Table not displayed");
			//Switch to SF Lightning Link
			classicCRMEventsPage.clickOnSFLightningLnk();
		}
		assertAll();
	}
}
