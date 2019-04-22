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

public class PetitionCaseDetailViewTest extends ThorBaseTest {

	public static Logger logger = Logger.getLogger(PetitionCaseDetailViewTest.class);
	@Test(description = "Thor 89, Thor 93, Thor 283 - Verify that adviser is able to view petition details of a customer under case details section, Supporting Petitions Count ",groups={"regression"})
	@Description("Verify Petition Case Details")
	public void verifyCaseDetails(ITestContext context) {

		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		//Provide the ID of the test case. This is the ID generated in the TestRail when the manual test case is created.
		testID = "3155,3386,4693,3392";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Petition Case Details Test" + testID);

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
		//Assert.assertTrue(thorPetitionPage.verifyDisplayOfPetitionPreview(),"petition Preview Section Not displayed");
		if(!(thorPetitionPage.verifyDisplayOfPetitionPreview()))
		{	thorPetitionPage.clickOnQueueTab();}
		//Get Details from Petition Preview
		String strPetitionPreviewRHS=thorPetitionPage.getPetitionPreviewDetails();
		//Click on ViewCase Details button
		thorPetitionPage.clickOnViewCaseDetailsButton();
		//Verify Case Details display
		
		assertTrue(thorPetitionPageDetails.isCaseIDDisplayed(),"Verify Case ID Displayed successfully");
		assertTrue(thorPetitionPageDetails.isUserContentTypeDisplayed(),"Verify User Content Type Displayed successfully");
		assertTrue(thorPetitionPageDetails.isCategoryDisplayed(),"Verify Category Displayed successfully");
		assertTrue(thorPetitionPageDetails.isProductDisplayed(),"Verify Product Displayed successfully");
		assertTrue(thorPetitionPageDetails.isPlatformDisplayed(),"Verify Platform Displayed successfully");
		assertTrue(thorPetitionPageDetails.isPastActivityDisplayed(),"Verify Past Activity Displayed successfully");
		
		//Verify Petition Preview RHS details against Petition Details page
		String strPetitionDetails=thorPetitionPageDetails.getPetitionDetailsFrmDetailPage();
		//System.out.println(strPetitionDetails);
		//System.out.println(strPetitionPreviewRHS);
		if (strPetitionPreviewRHS.equalsIgnoreCase(strPetitionDetails))
			assertTrue(true,"Same Petition Details displayed in petition preview and petition detail page");	
		else
			assertTrue(false,"Same Petition Details not displayed in petition preview and petition detail page");	
		//Verify Supporting Petitions Count in a Case
		int intRowCnt=thorPetitionPageDetails.getSupportingPetitionsCount();
		if (intRowCnt==0)  assertTrue(true,"Petition cases has no supporting petitions");
		if (intRowCnt>=1)  assertTrue(true,"Petition cases has "+intRowCnt+" supporting petitions");
		if (intRowCnt<0)  assertTrue(false,"Problem in getting Supporting Petitions count");
		
		//Verify Target Player Details
		String strTargetPlyID=thorPetitionPageDetails.getTargetPlayerID();
		String strTargetPlyPersonaID=thorPetitionPageDetails.getTargetPlayerPersonaID();
		String strTargetPlyEmailID=thorPetitionPageDetails.getTargetPlayerEmailID();
		//System.out.println(strTargetPlyID+strTargetPlyPersonaID+strTargetPlyEmailID);
		assertTrue(!(strTargetPlyID.isEmpty()),"Target Player ID not displayed");
		assertTrue(!(strTargetPlyPersonaID.isEmpty()),"Target Player Persona ID not displayed");
		assertTrue(!(strTargetPlyEmailID.isEmpty()),"Target Player Email ID not displayed");
		//Verify Petitioner Details
		String strPetitionerID=thorPetitionPageDetails.getPetitionerID();
		String strPetitionerPersonaID=thorPetitionPageDetails.getPetitionerPersonaID();
		String strPetitionerLogInID=thorPetitionPageDetails.getPetitionerLogInID();
		//System.out.println(strPetitionerID+strPetitionerPersonaID+strPetitionerLogInID);
		assertTrue(!(strPetitionerID.isEmpty()),"Petitioner ID not displayed");
		assertTrue(!(strPetitionerPersonaID.isEmpty()),"Petitioner Persona ID not displayed");
		assertTrue(!(strPetitionerLogInID.isEmpty()),"Petitioner LogIn ID not displayed");
		assertAll();

	}

}
