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

public class T2ContentActionAndEvents extends ThorBaseTest {

	public static Logger logger = Logger.getLogger(T2ContentActionAndEvents.class);
	@Test(description = "Thor 665 - Verify that T2 Advisor is able edit product and Category values",groups={"Regression"})
	@Description("Verify T2 advisor edit product and Category values")
	public void verifyT2AdvisorEditProductAndCategoryValues(ITestContext context) throws InterruptedException {

	Map<String, Object> testData = new HashMap<String, Object>();
	String testID;

	//Provide the ID of the test case. This is the ID generated in the TestRail when the manual test case is created.
	testID = "17604";
	context.setAttribute("testcase_id", testID);
	logger.info("validating T2 Advisor edit product and Category functionality" + testID);

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
	//String strPetitionActionVal=testData.get("petitionActionVal").toString();
	
	//Apply Filter on Target Persona Id
	int rcount=thorPetitionPage.getQueueCountAfterFilter(testData.get("filterName").toString(),testData.get("filterValue").toString());
	Assert.assertTrue((rcount>=1),"Threre is no Cases Available in the Queue as per the Filter.");
			
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
	//Perform Action(Resolve) on one petition
	boolean hideFlag=thorPetitionPage.petitionEditAndAction(testData.get("productItem").toString(),testData.get("categoryItem").toString(),testData.get("petitionActionVal").toString(),testData.get("petitionNotes").toString());
	//After completing action verify Queued Tab Count
	int afterQueuedCnt=thorPetitionPage.getQueueTableCount();
	Assert.assertTrue((rcount>afterQueuedCnt), "Verify Queued Tab records count after Commit");

	/*      Verify Case Events in SF                      */
	//Navigate to SF classic				
	thorPetitionPage.clickOnUserProfile();
	thorPetitionPage.switchToSFClassic();
	//Search for case in SF and Click on that case
	//System.out.println(arrOfStr[0]);
	classicHomePage.searchSFObject(arrOfStr[0]);
	classicSearchPage.clickOnSearchdSFObject(arrOfStr[0]);
	//Verify case details from Cases object
	String strCaseDetails=testData.get("caseDetails").toString();
	String [] arrCaseDetails=strCaseDetails.split(",");
	String strCRMEventDetails=testData.get("crmEventDetails").toString();
	String [] arrCRMEventDetails=strCRMEventDetails.split(",");
	for(int i=0;i<arrCaseDetails.length;i++)
	{
		String strText=classicCasesPage.getCaseDetailAnyFieldVale(arrCaseDetails[i],"innerText");
		if (arrCaseDetails[i].equals("Case Number"))
			assertTrue((strText.equals(arrOfStr[i])), arrCaseDetails[i]+" in Thor application"+strText+" not matched with SF Cases object "+arrCaseDetails[i]);
		if (arrCaseDetails[i].equals("Product"))
			assertTrue((strText.equals(testData.get("productItem").toString())), "Product in Thor application"+strText+" not matched with SF Cases object product");
		if (arrCaseDetails[i].equals("Category"))
			assertTrue((strText.equals(testData.get("categoryItem").toString())), "Category in Thor application"+strText+" not matched with SF Cases object Category");
	}
	//Get CRM Events Table Count
	int intCnt=classicCasesPage.getCRMEventsCount();
	if (hideFlag)
	{	int intCount=intCnt-2;
		classicCasesPage.clickOnCRMEvent(intCount);
		for(int j=0;j<arrCRMEventDetails.length;j++)
		{
			String strText=classicCRMEventsPage.getCRMEventDetailAnyFieldVale(arrCRMEventDetails[j],"innerText");
			switch (arrCRMEventDetails[j])
			{
			case "Case": assertTrue((strText.equals(arrOfStr[0])), "Case no in Thor application"+arrOfStr[0]+" not matched with SF Cases CRM Events");
						 break;
			case "EventCategory": assertTrue((strText.equals("Hide")), "CRM Event Category not displayed as Hide");
						 		  break;
			case "EventType": assertTrue((strText.equals("AccountEdit")), "CRM Event Type not displayed as AccountEdit");
			 			      break;
			case "EventNote": assertTrue((strText.contains(testData.get("petitionNotes").toString())), "Case Notes not matched with CRM Events Notes");
 			 			      break;
				
			}
			
		}
		//Verify Case Event Details Table values(Old and new values)
		assertTrue(classicCRMEventsPage.getAccountEventDetailsFrmTbl(),"All values in CRM Event Detail Table not displayed");
	}
	else
	{
		//Click on last event and verify
		classicCasesPage.clickOnCRMEvent(intCnt);
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
		assertTrue(classicCRMEventsPage.getCaseEventDetailsFrmTbl(),"All values in Case Event Detail Table not displayed");
	}
	//Switch to SF Lightning Link
	classicCRMEventsPage.clickOnSFLightningLnk();
	assertAll();
	}

	
}
