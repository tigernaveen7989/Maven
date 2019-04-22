package com.ea.wwce.automation.thor.tests;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;
import io.qameta.allure.Description;

public class PetitionFilterComponentTest extends ThorBaseTest {

	public static Logger logger = Logger.getLogger(PetitionFilterComponentTest.class);
	@Test(description = "Thor 88, Thor 471, Thor 472 - Verify Petition Unified Queue view and Filter component",groups={"regression"})
	@Description("Verify petition Filter Component")
	public void verifyFilterComponentTest(ITestContext context) throws InterruptedException {

		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		//Provide the ID of the test case. This is the ID generated in the TestRail when the manual test case is created.
		testID = "3368,4433,14105";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Petition Filter component" + testID);

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
		
		//Verify All filter components in Queued Tab
		assertTrue(thorPetitionPage.selectCategoryFilterAndVerifyQueueTable(testData.get("categoryName").toString()), "Cases not displayed as per Category Filter");
		assertTrue(thorPetitionPage.selectProductFilterAndVerifyQueueTable(testData.get("productName").toString()), "Cases not displayed as per Product Filter");
		assertTrue(thorPetitionPage.selectPersonaFilterAndVerifyQueueTable(testData.get("personaId").toString()), "Cases not displayed as per Target Persona Filter");
		
		//Click on Completed tab
		thorPetitionPage.ClickOnCompletedQueueTab();
		
		//Clear the Queued tab filters and apply new filters in Completed Tab
		thorPetitionPage.resetFilterOption("category", "Categories");
		thorPetitionPage.resetFilterOption("product", "Products");
		thorPetitionPage.resetFilterOption("petition-age", "Target personas");
		
		//Verify All filter components in Completed Tab
		assertTrue(thorPetitionPage.selectCategoryFilterAndVerifyCompletedTable(testData.get("c_categoryName").toString()), "Completed Cases not displayed as per Category Filter");
		assertTrue(thorPetitionPage.selectProductFilterAndVerifyCompletedTable(testData.get("c_productName").toString()), "CompletedCases not displayed as per Product Filter");
		assertTrue(thorPetitionPage.selectPersonaFilterAndVerifyCompletedTable(testData.get("c_personaId").toString()), "Completed Cases not displayed as per Target Persona Filter");
		assertAll();

	}

}
