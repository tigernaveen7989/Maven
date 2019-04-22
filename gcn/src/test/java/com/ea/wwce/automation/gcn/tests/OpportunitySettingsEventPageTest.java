package com.ea.wwce.automation.gcn.tests;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

public class OpportunitySettingsEventPageTest extends GcnBaseTest{
	
	/*This Class is to test the negative cases.
	 * - To test for error messages dispalyed if user leaves mandatory fields in the events and General tab blank
	 * This is only for existing Opportunities.
	 * 
	 * TC: 17564 - Verify the functionality of Start Time and End Time with Invalid date range
	 * 
	 * */

	public static Logger logger = Logger.getLogger(OpportunitySettingsEventPageTest.class);
	
	
	@Test
	public void verifyOpportunityEventSettings(ITestContext context) throws InterruptedException {
		
		Map<String, Object> testData = new HashMap<String, Object>();
		String testID="17563,17564,17569";
		
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);
				
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);
		
		gcnAdminHomePage.launchAdminAndSignIn();
		
		gcnAdminPage=gcnAdminLoginPage.verifyLogin(testData.get("username").toString(), testData.get("password").toString());
		
		// Navigate to Admin Opportunities Page
		gcnAdminPage.navToAdminOpportunityPage();
		
		adminOpportunityListPage.goToAnyOpportunity();
		
		assertTrue(adminOppDetailsPage.verifyNavigationToOppoDetailsPage(), "Opp details Page Loaded");
		
		adminOppDetailsPage.goToOppSettings();
		assertTrue(newOpportunityGeneralPage.isTitleBoxDisplayed(), "Landed into General Tab");
		
		adminOppSettingsPage.goToOppEventsTab();	
		
		newOpportunityEventPage.clearAllEventFields();
			
		assertTrue(newOpportunityEventPage.isErrorFrameDisplayed(), "Error Frame Not Displayed");
		assertTrue(newOpportunityEventPage.checkForErrorsDisplayed(),"Errors Not Displayed");
		
		
		// Check for Invalid Date
		newOpportunityEventPage.fillOppEventsTabInvalidDate(testData.get("Location2").toString(),
				testData.get("Country").toString(),testData.get("TravelTimes2").toString(),testData.get("ApprovalManager1").toString(),
				testData.get("EventDesc1").toString());
		
		assertTrue(newOpportunityEventPage.isErrorFrameDisplayed(), "Error Frame Not Displayed");
		assertTrue(newOpportunityEventPage.checkForErrorsDisplayed(),"Errors Not Displayed");
		
		// Enter Valid Date
		newOpportunityEventPage.setStartDateTime();
		newOpportunityEventPage.setEndDateTime();
		newOpportunityEventPage.createOrSaveOpportunity();
				
		Thread.sleep(2000);
		
		assertAll();
	}
	
}
