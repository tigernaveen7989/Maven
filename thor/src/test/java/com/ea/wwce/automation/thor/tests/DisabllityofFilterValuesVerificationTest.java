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

public class DisabllityofFilterValuesVerificationTest extends ThorBaseTest {

	public static Logger logger = Logger.getLogger(DisabllityofFilterValuesVerificationTest.class);
	@Test(description = "Thor 472 - Verify Petition Disablity filter verification test",groups={"regression"})
	@Description("Verify Disablity petition Filter Component")
	public void verifyFilterComponentTest(ITestContext context) throws InterruptedException {

		Map<String, Object> testData = new HashMap<String, Object>();
		String testID;

		//Provide the ID of the test case. This is the ID generated in the TestRail when the manual test case is created.
		testID = "14106";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Disablity Petition Filter component" + testID);

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
		//omniChannelWidgetPage.changeOmniStatusToAvailable();
		//int intCaseCnt=omniChannelWidgetPage.getCasesCountFrmOmniWidget();
		//System.out.println("Case Count "+intCaseCnt);
		
		omniChannelWidgetPage.clickOnOmniMinimizeButton();
		
		//Verify Categories dropdown values
		 String Category=thorPetitionPage.getCategorieslistfromdropdown(testData.get("categoryName").toString());
		 String [] catval=Category.split(",");
		 
		//Verify Categories filter components in Queued Tab
		assertTrue(catval[0].isEmpty(),"Category Filter Disability Not done successfully");
			
		//Verify Product dropdown values
		 String products=thorPetitionPage.getProductslistfromdropdown(testData.get("productName").toString());
		 String [] prodval=products.split(",");
			 
		//Verify Product filter components in Queued Tab
		assertTrue(prodval[0].isEmpty(), "Product Filter Disability Not done successfully");
				
		//Verify Personas dropdown values
		 String persona=thorPetitionPage.getTargetPersonaslistfromdropdown(testData.get("personaId").toString());
		 String [] personaval=persona.split(",");
				 
		//Verify Personasid filter components in Queued Tab
		assertTrue(personaval[0].isEmpty(), "PersonaId Filter Disability Not done successfully");
		
		assertAll();
		
	}
}
