package com.ea.wwce.automation.gcn.tests;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

public class OpportunityEventPageUiTest extends GcnBaseTest{
	
	public static Logger logger=Logger.getLogger(OpportunityEventPageUiTest.class);
	
	/*This class is to check the UI for Existing Opportunity Events Tab.
	 * 
	 * TC: 17562 - Verify the fields Start Time, End Time, Location,country and Has Travel are displayed in the Events page for new and existing Opporunities
	 * */
	
	@Test
	public void fillEventTab(ITestContext context) throws InterruptedException {
		
		Map<String, Object> testData = new HashMap<String, Object>();
		String testID="42425,42427,42428";
		
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
		assertTrue(adminOppSettingsPage.verifyNavigationToOppSettingsPage(), "Error landing into Opportunity Settings Page");		
		//adminNewOpportunityPage.clickOnEventsTab();
		
		adminOppSettingsPage.goToOppEventsTab();

		assertTrue(newOpportunityEventPage.verifyEventPageUi(), "UI elements missing in Events Tab");

		assertFalse(newOpportunityEventPage.checkBannedCountries(), "Check for Banned Countries Listed");
		
		assertTrue(newOpportunityEventPage.verifyEventDisable(), "Event could not be Disabled");
		
		//newOpportunityEventPage.createOrSaveOpportunity();
					
		assertAll();
	}
	
	
}
