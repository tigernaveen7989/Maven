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

public class T1MassActionResolveTest extends ThorBaseTest {

	public static Logger logger = Logger.getLogger(T1MassActionResolveTest.class);
	@Test(description = "Thor 92, Thor-486, Thor-666 -Perform mass case actions on petition cases",groups={"regression"})
	@Description("Verify Mass Action Resolve")
	public void verifyMassActionResolve(ITestContext context) {

		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		//Provide the ID of the test case. This is the ID generated in the TestRail when the manual test case is created.
		testID = "16106,16861,16004,17290,15131,16934,3625";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Mass Resolve Test" + testID);

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
		
		String selectedcount=testData.get("selectedcount").toString();
		int count=Integer.parseInt(selectedcount);
		
		int rcount=thorPetitionPage.getQueueCountAfterFilter(testData.get("filterName").toString(),testData.get("filterValue").toString());
		Assert.assertTrue((rcount>=count),"Threre is no Cases Available in the Queue as per the Filter.");

		//Select first petition from queue
		thorPetitionPage.selectFirstPetitionFromQueue();
		//Work around for Case details Tab display
		if(!(thorPetitionPage.verifyDisplayOfPetitionPreview()))
		{	thorPetitionPage.clickOnQueueTab();
			thorPetitionPage.selectFirstPetitionFromQueue();
		}
		 String strPetitionPreviewRHS=thorPetitionPage.getPetitionPreviewDetails();
		 String [] arrOfStr = strPetitionPreviewRHS.split(",");
		 String CaseNumber=arrOfStr[0];
		 //System.out.println("Case Number "+CaseNumber);
		 
		//Select Multiple check box
		 thorPetitionPage.SelectMultipleCases(rcount, count);
		 String massActionCount=thorPetitionPage.getMassActionCount();
		 String  MassCaseCnt=massActionCount.substring(2);
		 String MassCaseCount=MassCaseCnt.trim();		 
		 //System.out.println("Mass Action Count "+MassCaseCount);
		 
		 //assertTrue(MassCaseCount.contains("cases selected"), "Verify Mass Action records count after select Mass Action");
		 assertTrue((MassCaseCount.equals("cases selected")), "Verify Mass Action records count after select Mass Action");
		 
		//verify mass action dropdown values
		 String Values=thorPetitionPage.gettextfromdropdown();
		 String [] arrvalues=Values.split(",");  
		//verify first dropdown value
		 assertTrue((arrvalues[0]).contains(testData.get("massActionVal").toString()), "Petition Action drop down dropdown value in Thor application"+arrvalues[0]+" not matched with Petition Action value");
		//verify second dropdown value
		 assertTrue((arrvalues[1]).contains(testData.get("massActionAnotherVal").toString()), "Petition Action drop down dropdown value in Thor application"+arrvalues[1]+" not matched with Petition Action value");
		 
		 //Perform Action(Resolve) on Mass Action
		boolean flag= thorPetitionPage.masspResolvePetitionAction(testData.get("massActionVal").toString(),testData.get("massNotes").toString());
		 //After completing Mass Resolve action verify Queued Tab
		 Assert.assertTrue(flag, "Mass Resolve Action not done properly");
		 //Verify Queue count after Mass Resolve action
		 int intQueueCntAftr=thorPetitionPage.getQueueTableCount();
		 if(intQueueCntAftr>0)
		  	logger.info("Verify Queued Tab records count after Escalate Mass Action " + intQueueCntAftr);
	 	 
					
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
		String[] resolvecomment=comment.split("\\|");
		String comments=resolvecomment[1].trim(); 
		//System.out.println(resolvecomment[0]);
		//System.out.println(resolvecomment[1]);			
							
		String actionstatus=testData.get("massActionVal").toString();
		String notes=testData.get("massNotes").toString();			
		assertTrue((resolvecomment[0].contains("Resolve")), "Action Status in Thor application"+actionstatus+" not matched with SF Cases object action status");
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
