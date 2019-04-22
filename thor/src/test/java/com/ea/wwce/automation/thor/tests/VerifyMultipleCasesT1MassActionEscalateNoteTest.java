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

public class VerifyMultipleCasesT1MassActionEscalateNoteTest extends ThorBaseTest {

	public static Logger logger = Logger.getLogger(VerifyMultipleCasesT1MassActionEscalateNoteTest.class);
	@Test(description = "Thor 92, Thor-483 -Perform mass Note applicable on petition cases",groups={"regression"})
	@Description("Verify Mass Note Escalate")
	public void verifyMassActionEscalateNote(ITestContext context) {

		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		//Provide the ID of the test case. This is the ID generated in the TestRail when the manual test case is created.
		testID = "16036,16936";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Mass action Escalate Note Test" + testID);

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
		
		
		
		//set the filter components in Queued Tab
		int rcount=thorPetitionPage.getQueueCountAfterFilter(testData.get("filterName").toString(),testData.get("filterValue").toString());
		String selectedcount=testData.get("selectedcount").toString();
		int count=Integer.parseInt(selectedcount);
		Assert.assertTrue((rcount>=count),"Threre is no required number of Cases in the Queue as per the Filter.");
				 
		 thorPetitionPage.selectFirstPetitionFromQueue();
		//Work around for Case details Tab display
		 if(!(thorPetitionPage.verifyDisplayOfPetitionPreview()))
		 {	thorPetitionPage.clickOnQueueTab();
		 	thorPetitionPage.selectFirstPetitionFromQueue();
		 }
		 String strPetitionPreviewRHS=thorPetitionPage.getPetitionPreviewDetails();
		 String [] arrOfStr = strPetitionPreviewRHS.split(",");
		 String CaseNumber1=arrOfStr[0];
		 //System.out.println("Case Number "+CaseNumber1);
		 //Select 2nd row and get case number from case
		 thorPetitionPage.selectPetitionFromQueue(2);
		//Work around for Case details Tab display 
		 if(!(thorPetitionPage.verifyDisplayOfPetitionPreview()))
		 {	thorPetitionPage.clickOnQueueTab();
		 	thorPetitionPage.selectPetitionFromQueue(2);
		 }
		 String strSecondPetitionPreviewRHS=thorPetitionPage.getPetitionPreviewDetails();
		 String [] arrOfStr2 = strSecondPetitionPreviewRHS.split(",");
		 String CaseNumber2=arrOfStr2[0];
		//System.out.println("Case Number "+CaseNumber2);		 		 
		 
		//Select multiple check boxes		 
		 assertTrue((thorPetitionPage.SelectMultipleCases(rcount, count )), "Failed to verify Mass selection");
		 String massActionCount=thorPetitionPage.getMassActionCount();
		 String MassCaseCount=massActionCount.substring(2);
		 String MassCaseselText=MassCaseCount.trim();
		 		  
		 //Verify total Cases selected after Mass selection
		 assertTrue((MassCaseselText.equals("cases selected")), "Verify Mass Action records count after select Check boxes");
		 //Perform Action(Escalate) on Mass Action
		 thorPetitionPage.massEscalatePetitionAction(testData.get("massActionVal").toString(),testData.get("massNotes").toString(),testData.get("selectQVal").toString());
				 
		 //Get UTC System date after commit the case
		 String strNotesUTCDate=thorPetitionPageDetails.getUTCDateTimeAsString(testData.get("dateformat").toString(),testData.get("timeZone").toString());
		 String [] arrOfUTCDate = strNotesUTCDate.split(":");
		 //System.out.println("Current UTC Date "+arrOfUTCDate[0]);
		 
		 //Search Player by Case Number
		 thorPetitionPage.searchPlayer("Case Number", CaseNumber1);
		 //Click on Search Case Tab
		 thorPetitionPageDetails.clickOnSearchCaseTab(CaseNumber1);
		 //After Search Verify Search is success or not
		 String strTemp=thorPetitionPageDetails.getPetitionDetailsFrmDetailPage();
		 String strArr[]=strTemp.split(",");
		 //System.out.println(strArr[0]);
		 assertTrue(strArr[0].equals(CaseNumber1),"Search By Case ID is not successful");
		 //Get Recent notes
		 String recentNotes=thorPetitionPageDetails.getCaseNotesFrmPastActivity();
		 //System.out.println(recentNotes);
		 assertTrue(recentNotes.contains(testData.get("massNotes").toString()),"Mass Notes not displayed in Case1 PastActivity");
		
		 //Get ADvisor name from User Profile
		 thorPetitionPage.clickOnUserProfile();
		 String strAdvisorName=thorPetitionPage.getAdvisorName();
		 //System.out.println(strAdvisorName);
		
		 //Verify Advisor Name
		 String advisorName=thorPetitionPageDetails.getAdvisorNameFrmPastActivity();
		// System.out.println(advisorName);
		 assertTrue(advisorName.equals(strAdvisorName),"Advisor Name not displayed in Case1 PastActivity");
		 //Close Search Case Tab
		 thorPetitionPageDetails.closeSearchCaseTab(CaseNumber1);
		
		 //Search Player by Case Number2
		 thorPetitionPage.searchPlayer("Case Number", CaseNumber2);
		 //Click on Search Case Tab
		 thorPetitionPageDetails.clickOnSearchCaseTab(CaseNumber2);
		 //After Search Verify Search is success or not
		 String strTemp1=thorPetitionPageDetails.getPetitionDetailsFrmDetailPage();
		 String strArr1[]=strTemp1.split(",");
		 //System.out.println(strArr1[0]);
		 assertTrue(strArr1[0].equals(CaseNumber2),"Search By Case ID is not successful");
		 //Get Recent notes
		 String CaseNotes=thorPetitionPageDetails.getCaseNotesFrmPastActivity();
		 //System.out.println(CaseNotes);
		 assertTrue(CaseNotes.contains(testData.get("massNotes").toString()),"Mass Notes not displayed in Case2 PastActivity");
		 //Verify Advisor Name
		 String advisorName1=thorPetitionPageDetails.getAdvisorNameFrmPastActivity();
		 //System.out.println(advisorName1);
		 assertTrue(advisorName1.equals(strAdvisorName),"Advisor Name not displayed in Case2 PastActivity");
		 //Close Search Case Tab
		 thorPetitionPageDetails.closeSearchCaseTab(CaseNumber2);		
		 
		 /*      Verify Case Events in SF                      */
		//Navigate to SF classic
		thorPetitionPage.clickOnUserProfile();
		thorPetitionPage.switchToSFClassic();
		//Search for case in SF and Click on that case
		classicHomePage.searchSFObject(arrOfStr[0]);
		classicSearchPage.clickOnSearchdSFObject(arrOfStr[0]);
		//Verify case details from Cases object
		String strCaseNum=classicCasesPage.getCaseDetailAnyFieldVale("Case Number","innerText");
		assertTrue((strCaseNum.equals(arrOfStr[0])), "Case no in Thor application"+strCaseNum+" not matched with SF Cases object case no");
		//Get Case comments Table Count
		String strcomments=classicCasesPage.getCaseComments();
		String[] arrOfStrComments = strcomments.split("\\)", 2); 
		//String status= arrOfStrcommets[0];
		String comment=arrOfStrComments[1];
		String[] escalatecomment=comment.split("\\|");
		String comments=escalatecomment[1].trim(); 
		//System.out.println(escalatecomment[0]);
		//System.out.println(escalatecomment[1]);	
		String actionstatus=testData.get("massActionVal").toString();
		String notes=testData.get("massNotes").toString();	
		
		assertTrue((escalatecomment[0]).contains("Escalate"), "Action Status in Thor application"+actionstatus+" not matched with SF Cases object action status");
		assertTrue((notes.contains(comments)), "Case comments in Thor application"+notes+" not matched with SF Cases object Case comments");
		
		//Get CRM Events Table Count
		int intCnt=classicCasesPage.getCRMEventsCount();
		//Click on last event and verify
		classicCasesPage.clickOnCRMEvent(intCnt);
		//Get details from CRM Event table
		String strCRMEventDetails=testData.get("crmEventDetails").toString();
		String [] arrCRMEventDetails=strCRMEventDetails.split(",");
		for(int j=0;j<arrCRMEventDetails.length;j++)
		{
			String strText=classicCRMEventsPage.getCRMEventDetailAnyFieldVale(arrCRMEventDetails[j],"innerText");
			switch (arrCRMEventDetails[j])
			{
			case "EventCategory": assertTrue((strText.equals("StatusUpdate")), "CRM Event Category not displayed as StatusUpdate");
						 		  break;
			case "EventType": assertTrue((strText.equals("CaseEdit")), "CRM Event Type not displayed as CaseEdit");
			 			      break;
			case "EventNote": assertTrue((strText.contains(testData.get("massNotes").toString())), "Mass Notes not matched with CRM Events Notes");
 			 			      break;
				
			}
			
		}
		//Verify Case Event Details Table values(Old and new values)
		assertTrue(classicCRMEventsPage.getCaseEventDetailsFrmTbl(),"All values in Case Event Detail Table not displayed");
		//Switch to SF Lightning Link
		classicCRMEventsPage.clickOnSFLightningLnk();
	 
	    assertAll();
		 
	}
}
