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

public class T1ContentActionAndEventsViewCaseEscalateTest extends ThorBaseTest {

	public static Logger logger = Logger.getLogger(T1ContentActionAndEventsViewCaseEscalateTest.class);
	@Test(description = "Thor 665 -  Old values and new values to be captured in Events.",groups={"Regression"})
	@Description("Verify T1 advisor edit product, Platform and Category values from Details page")
	public void verifyT1AdvisorEditProductPlatformAndCategoryValues(ITestContext context) throws InterruptedException {

	Map<String, Object> testData = new HashMap<String, Object>();
	String testID;

	//Provide the ID of the test case. This is the ID generated in the TestRail when the manual test case is created.
	testID = "17603,3387";
	context.setAttribute("testcase_id", testID);
	logger.info("validating T1 Advisor edit product, Platform and Category functionality" + testID);
	
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
	//Click on ViewCase Details button
	thorPetitionPage.clickOnViewCaseDetailsButton();
	Assert.assertTrue(thorPetitionPageDetails.verifyDisplayOfPetitionDetails(),"petition Details Section Not displayed");
	//Get All details from Petition Preview
	String strPetitionDetails=thorPetitionPageDetails.getPetitionDetailsFrmDetailPage();
	String [] arrOfStr = strPetitionDetails.split(",");
	//System.out.println(arrOfStr[0]);
	assertTrue(thorPetitionPageDetails.isCategoryDisplayed(),"Verify Category Displayed successfully");
	assertTrue(thorPetitionPageDetails.isProductDisplayed(),"Verify Product Displayed successfully");
	assertTrue(thorPetitionPageDetails.isPlatformDisplayed(),"Verify Platform Displayed successfully");				
	thorPetitionPageDetails.T1CaseEditAndAction(testData.get("petitionActionVal").toString(), testData.get("petitionNotes").toString(), testData.get("SelectQueuVal").toString(),testData.get("productItem").toString(),testData.get("platformItem").toString(),testData.get("categoryItem").toString());
	//Assert.assertTrue(thorPetitionPage.islnkQueuedTabVisible(),"Commit Action not successful");
	//Due to defect commented above statement added below wait
	Thread.sleep(3000);
	
	/*      Verify Case Events in SF                      */
	//Navigate to SF classic				
	thorPetitionPage.clickOnUserProfile();
	thorPetitionPage.switchToSFClassic();
	//Search for case in SF and Click on that case
	//System.out.println(arrOfStr[0]);
	//System.out.println(arrOfStr[1]);
	//System.out.println(arrOfStr[2]);
	classicHomePage.searchSFObject(arrOfStr[0]);
	classicSearchPage.clickOnSearchdSFObject(arrOfStr[0]);
	//Verify case details from Cases object
	String strCaseDetails=testData.get("caseDetails").toString();
	String [] arrCaseDetails=strCaseDetails.split(",");
	for(int i=0;i<arrCaseDetails.length;i++)
	{
		String strText=classicCasesPage.getCaseDetailAnyFieldVale(arrCaseDetails[i],"innerText");
		if (arrCaseDetails[i].equals("Case Number"))
			assertTrue((strText.equals(arrOfStr[i])), arrCaseDetails[i]+" in Thor application"+strText+" not matched with SF Cases object "+arrCaseDetails[i]);
		if (arrCaseDetails[i].equals("Product"))
			assertTrue((strText.equals(testData.get("productItem").toString())), "Product in Thor application"+strText+" not matched with SF Cases object product");
		if (arrCaseDetails[i].equals("Category"))
			assertTrue((strText.equals(testData.get("categoryItem").toString())), "Category in Thor application"+strText+" not matched with SF Cases object Category");
		if (arrCaseDetails[i].equals("platform"))
			assertTrue((strText.equals(testData.get("platformItem").toString())), "Platform in Thor application"+strText+" not matched with SF Cases object Platform");
	}
	//Get CRM Events Table Count
	int intCnt=classicCasesPage.getCRMEventsCount();
	//Click on last event and verify
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
	assertTrue(classicCRMEventsPage.getCaseEventDetailsFrmTbl(),"All values in Case Event Detail Table not displayed");
	
	//Switch to SF Lightning Link
	classicCRMEventsPage.clickOnSFLightningLnk();			
	assertAll();
}

	
}
