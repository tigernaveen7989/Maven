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

public class T1MassActionEscalateTest extends ThorBaseTest {

	public static Logger logger = Logger.getLogger(T1MassActionEscalateTest.class);
	@Test(description = "Thor 92, Thor-483 -Perform mass case actions on petition cases",groups={"regression"})
	@Description("Verify Mass Action Escalate")
	public void verifyMassActionEscalate(ITestContext context) {

		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		//Provide the ID of the test case. This is the ID generated in the TestRail when the manual test case is created.
		testID = "16105,16111,18607";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Mass action Escalate Test" + testID);

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
		 String MassCaseCount=massActionCount.substring(2);
		 String MassCaseselText=MassCaseCount.trim();
		 		  
		 //Verify total Cases selected after Mass selection
		 assertTrue((MassCaseselText.equals("cases selected")), "Verify Mass Action records count after select Check boxes");
		 //Perform Action(Escalate) on Mass Action
		 boolean flag=thorPetitionPage.massEscalatePetitionAction(testData.get("massActionVal").toString(),testData.get("massNotes").toString(),testData.get("SelectQueuVal").toString());
		 //After completing Mass Escalate action verify Queued Tab
		 Assert.assertTrue(flag, "Mass Escalate  Action not done"); 
		 //Verify next set of cases loading
		 int intQueueCntAftr=thorPetitionPage.getQueueTableCount();
		 if(intQueueCntAftr>0)
		  	logger.info("Verify Queued Tab records count after Escalate Mass Action " + intQueueCntAftr);
		 //Verify Queue count after Mass Resolve action
		 //assertTrue((thorPetitionPage.getQueeTablesize()), "Not Loaded next set of cases after completing current set of cases");
 		
					
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
		//Switch to SF Lightning Link
		classicCRMEventsPage.clickOnSFLightningLnk();
		assertAll();
		 
	}

}
