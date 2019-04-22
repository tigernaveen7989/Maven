package com.ea.wwce.automation.thor.tests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
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

public class PetitionCompletedSortingTest extends ThorBaseTest {

	public static Logger logger = Logger.getLogger(PetitionCompletedSortingTest.class);
	@Test(description = "Thor 86 - Verify that adviser is able to sort the petitions(ASC/DSC order) in Queued and completed tabs using Subject, Category, Target persona id, Product, Case Created.",groups={"regression"})
	@Description("Verify Petition Completed Sorting")
	public void verifyCompletedSorting(ITestContext context) {

		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		//Provide the ID of the test case. This is the ID generated in the TestRail when the manual test case is created.
		testID = "3347";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Petition Completed Sorting Test" + testID);

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
		//Get Cases count from Omni widget
		int intCaseCnt=omniChannelWidgetPage.getCasesCountFrmOmniWidget();
		//System.out.println("Case Count from Omni "+intCaseCnt);
		omniChannelWidgetPage.clickOnOmniMinimizeButton();
		//Get Cases count from Queue
		int intQueueCnt=thorPetitionPage.getCasesCountFrmQueue();
		//System.out.println("Case Count from Queue "+intQueueCnt);
		
		//Select first petition from queue
		thorPetitionPage.selectFirstPetitionFromQueue();
		//Work around for Case details Tab display
		if(!(thorPetitionPage.verifyDisplayOfPetitionPreview()))
		{	thorPetitionPage.clickOnQueueTab();
			thorPetitionPage.selectFirstPetitionFromQueue();
		}
		
				 
		//After completing action verify Completed Tab	
		 assertTrue(thorPetitionPage.verifyCompletedQueueCount(), "Verify Completed Tab records count");
		
		
		
		
		//Verify Case created column is sorted in ascending order by default
		List<String> columnValuesList=new ArrayList<String>();
		columnValuesList=thorPetitionPage.getCompletedTableColumnValuesInList(5);
		assertTrue(thorPetitionPage.verifyIsColumnListSorted(columnValuesList),"Case Created Column not in Ascending order by default");
		columnValuesList=null;
		
		//Click on Case Created Column sort Ascending button and verify is it in descending order
		thorPetitionPage.clickOnColumnSortAscButton(8);
		columnValuesList=thorPetitionPage.getCompletedTableColumnValuesInList(5);
		Collections.reverse(columnValuesList);
		assertTrue(thorPetitionPage.verifyIsColumnListSorted(columnValuesList),"Case Created Column not in descending order");
		columnValuesList=null;
				
		//Click on subject Column sort Ascending button and verify is it in descending order
		thorPetitionPage.clickOnColumnSortAscButton(5);
		columnValuesList=thorPetitionPage.getCompletedTableColumnValuesInList(1);
		Collections.reverse(columnValuesList);
		assertTrue(thorPetitionPage.verifyIsColumnListSorted(columnValuesList),"Subject Column not in Descending order");
		columnValuesList=null;
				
		//Click on subject Column sort Descending button and verify is it in Ascending order
		thorPetitionPage.clickOnColumnSortDescButton(5);
		columnValuesList=thorPetitionPage.getCompletedTableColumnValuesInList(1);
		assertTrue(thorPetitionPage.verifyIsColumnListSorted(columnValuesList),"Subject Column not in Ascending order");
		columnValuesList=null;
				
		//Click on Category Column sort Ascending button and verify is it in descending order
		thorPetitionPage.clickOnColumnSortAscButton(6);
		columnValuesList=thorPetitionPage.getCompletedTableColumnValuesInList(2);
		Collections.reverse(columnValuesList);
		assertTrue(thorPetitionPage.verifyIsColumnListSorted(columnValuesList),"Category Column not in Descending order");
		columnValuesList=null;
				
		//Click on Category Column sort Descending button and verify is it in Ascending order
		thorPetitionPage.clickOnColumnSortDescButton(6);
		columnValuesList=thorPetitionPage.getCompletedTableColumnValuesInList(2);
		assertTrue(thorPetitionPage.verifyIsColumnListSorted(columnValuesList),"Category Column not in Ascending order");
		columnValuesList=null;
				
		//Click on Product Column sort Ascending button and verify is it in descending order
		thorPetitionPage.clickOnColumnSortAscButton(7);
		columnValuesList=thorPetitionPage.getCompletedTableColumnValuesInList(4);
		Collections.reverse(columnValuesList);
		assertTrue(thorPetitionPage.verifyIsColumnListSorted(columnValuesList),"Product Column not in Descending order");
		columnValuesList=null;
				
		//Click on Product Column sort Descending button and verify is it in Ascending order
		thorPetitionPage.clickOnColumnSortDescButton(7);
		columnValuesList=thorPetitionPage.getCompletedTableColumnValuesInList(4);
		assertTrue(thorPetitionPage.verifyIsColumnListSorted(columnValuesList),"Product Column not in Ascending order");
		columnValuesList=null;
		
		assertAll();

	}

}
