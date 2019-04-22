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

public class RecentCaseNotesVerificationTest extends ThorBaseTest {

	public static Logger logger = Logger.getLogger(RecentCaseNotesVerificationTest.class);
	@Test(description = "Thor 113, 93 - Verify Recent Case Notes under Passt Activity",groups={"regression"})
	@Description("Verify Recent Case Notes")
	public void verifyRecentCaseNotes(ITestContext context) {

		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		//Provide the ID of the test case. This is the ID generated in the TestRail when the manual test case is created.
		testID = "3375,3608,3389";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Recent Case Notes Test" + testID);

		// Load the test data for the test ID
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);
		
		// validate login
		thorLoginPage.loginToTHOR(testData.get("username").toString(), testData.get("password").toString());
		//Verify page after successful login
		thorPetitionPage.islnkQueuedTabVisible();
		assertTrue(thorPetitionPage.islnkQueuedTabVisible(), "Verify Queued Tab Link visibility successfully");
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
		assertTrue(thorPetitionPage.islnkQueuedTabVisible(), "Verify Queued Tab Link visibility successfully after pull the Queue");
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
		
		//Select first row from Queue Table
		thorPetitionPage.selectFirstPetitionFromQueue();
		if(!(thorPetitionPage.verifyDisplayOfPetitionPreview()))
		{	thorPetitionPage.clickOnQueueTab();
			thorPetitionPage.selectFirstPetitionFromQueue();
		}
		//Get the Case Number 
		String strPetitionPreviewRHS=thorPetitionPage.getPetitionPreviewDetails();
		 String [] arrOfString = strPetitionPreviewRHS.split(",");
		 String CaseNumber=arrOfString[0];
		 //System.out.println("Case Number "+CaseNumber);
		 
		//Perform Action(Resolve) on one petition
		 thorPetitionPage.petitionAction(testData.get("petitionActionVal").toString(),testData.get("petitionNotes").toString());
		//After completing action verify Queued Tab Count
		int afterQueuedCnt=thorPetitionPage.getQueueTableCount();
		Assert.assertTrue((rcount>afterQueuedCnt), "Verify Queued Tab records count after Escalate");
		//Get UTC System date after commit the case
		String strNotesUTCDate=thorPetitionPageDetails.getUTCDateTimeAsString(testData.get("dateformat").toString(),testData.get("timeZone").toString());
		String [] arrOfUTCDate = strNotesUTCDate.split(":");
		//System.out.println("Current UTC Date "+arrOfUTCDate[0]);
		
		//Search Player by Case Number
		thorPetitionPage.searchPlayer("Case Number", CaseNumber);
		//Click on Search Case Tab
		thorPetitionPageDetails.clickOnSearchCaseTab(CaseNumber);
		//After Search Verify Search is success or not
		String strTemp=thorPetitionPageDetails.getPetitionDetailsFrmDetailPage();
		String strArr[]=strTemp.split(",");
		//System.out.println(strArr[0]);
		assertTrue(strArr[0].equals(CaseNumber),"Search By Case ID is not successful");
		String recentNotes=thorPetitionPageDetails.getCaseNotesFrmPastActivity();
		//System.out.println(recentNotes);
		assertTrue(recentNotes.contains(testData.get("petitionNotes").toString()),"Recent Notes not displayed in PastActivity");
		
		//verify Case status
		String caseStatus=thorPetitionPageDetails.getCaseStatusFrmPastActivity();
		//System.out.println(caseStatus);
		assertTrue(caseStatus.equals(testData.get("petitionStatus").toString()),"Case Status not displayed in PastActivity");
		//Get ADvisor name from User Profile
		thorPetitionPage.clickOnUserProfile();
		String strAdvisorName=thorPetitionPage.getAdvisorName();
		//System.out.println(strAdvisorName);
		
		//Verify Advisor Name
		String advisorName=thorPetitionPageDetails.getAdvisorNameFrmPastActivity();
		//System.out.println(advisorName);
		assertTrue(advisorName.equals(strAdvisorName),"Advisor Name not displayed in PastActivity");
				
		//Verify Case Date
		String caseDate=thorPetitionPageDetails.getDateFrmPastActivity();
		//System.out.println(caseDate);
		assertTrue(arrOfUTCDate[0].contains(caseDate),"Case Date displayed in PastActivity");
		//Close Search Case Tab
		thorPetitionPageDetails.closeSearchCaseTab(CaseNumber);
		assertAll();

	}

}
