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

public class Tier2ThorPetitionCaseTest extends ThorBaseTest {

	public static Logger logger = Logger.getLogger(Tier2ThorPetitionCaseTest.class);
	@Test(description = "Thor 492, Thor 86, Thor-103, Thor 287 - Verify that for T2 adviser Queued tabs displays all the petition that are to be processed along with count of petition and T2 advisor should perform petition actions",groups={"Regression"})
	@Description("Verify Petition Actions from T2 Queue Page")
	public void verifyPetationQueuePageForT2Advisor(ITestContext context) throws InterruptedException {

		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		//Provide the ID of the test case. This is the ID generated in the TestRail when the manual test case is created.
		testID = "4809,13773,13775,3626,3636";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Petition Actions from T2 Queue page" + testID);

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
		Assert.assertTrue((rcount>=2),"Threre is no Cases Available in the Queue as per the Filter.");
				
		//Perform Action(Transfer - Violation Found) on one petition
		boolean hideFlag1=thorPetitionPage.petitionAction(testData.get("petitionActionVal").toString(),testData.get("petitionNotes").toString(),testData.get("hide").toString());
		//Get Queue Table count after action
		int Qcount=thorPetitionPage.getQueueTableCount();
		//After completing action verify Queued Table
		Assert.assertTrue(rcount==Qcount+1, "Verify Queued Table records count after Transfer"); 
		
		
		//Select first row from Queue Table
		thorPetitionPage.selectFirstPetitionFromQueue();
		//Work around for Case details Tab display
		if(!(thorPetitionPage.verifyDisplayOfPetitionPreview()))
		{	thorPetitionPage.clickOnQueueTab();
			thorPetitionPage.selectFirstPetitionFromQueue();
		}
		//Get Details from Petition Preview
		String strPetitionPreviewRHS=thorPetitionPage.getPetitionPreviewDetails();
		//System.out.println(strPetitionPreviewRHS);
		String [] arrOfStr = strPetitionPreviewRHS.split(",");
		
		//Perform Action(Resolve) on one petition
		boolean hideFlag2=thorPetitionPage.petitionAction(testData.get("petitionActionVal2").toString(),testData.get("petitionNotes").toString(),testData.get("hide").toString());
		//Get Queue Table count
		int Queuecount=thorPetitionPage.getQueueTableCount();
		//After completing action verify Queued Table
		Assert.assertTrue(Qcount==Queuecount+1, "Verify Queued Table records count after Transfer"); 
		
		
		
		
		/*      Verify Case Events in SF                      */
		//Navigate to SF classic
		thorPetitionPage.clickOnUserProfile();
		thorPetitionPage.switchToSFClassic();
		//Search for case in SF and Click on that case
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
		if (hideFlag2)
		{	int intCount=intCnt-2;
			//Click on Hide event and verify
		    classicCasesPage.clickOnCRMEvent(intCount);
		    String strEventCategoryFrmCRM=classicCRMEventsPage.getCRMEventDetailAnyFieldVale("EventCategory","innerText");
		    String strEventNoteFrmCRM=classicCRMEventsPage.getCRMEventDetailAnyFieldVale("EventNote","innerText");;
		    assertTrue((strEventCategoryFrmCRM.equals("Hide")), "CRM Event Category not displayed as StatusUpdate");
		    assertTrue((strEventNoteFrmCRM.contains(testData.get("petitionNotes").toString())), "Hide Notes not matched with CRM Events Notes "); 
		  //Verify Hide Event Details Table values(Old and new values)
		    assertTrue(classicCRMEventsPage.getAccountEventDetailsFrmTbl(),"All values in CRM Event Detail Table not displayed"); 
		}
		else
		{	//Click on last event and verify
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
				case "EventNote": assertTrue((strText.contains(testData.get("petitionNotes").toString())), "Case Notes not matched with CRM Events Notes");
	 			 			      break;
					
				}
				
			}
			//Verify Case Event Details Table values(Old and new values)
			boolean flag=classicCRMEventsPage.getCaseEventDetailsFrmTbl();
			//System.out.println(flag);
			assertTrue(flag,"All values in Case Event Detail Table not displayed");
		}
		
		//Switch to SF Lightning Link
		classicCRMEventsPage.clickOnSFLightningLnk();
		assertAll();
	}
}
