package com.ea.wwce.automation.gcn.tests;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

public class OpportunitySettingsPageTravelTest extends GcnBaseTest{

	public static Logger logger = Logger.getLogger(OpportunitySettingsPageTravelTest.class);
	
	/*This class is to check Opportunity Settings Page Scenarios.
	 * 
	 * TC: 16187 - Verify image update feature for existing Opportunity from listed images
	 * TC: 17562 - Verify the fields Start Time, End Time, Location,country and Has Travel are displayed in the Events page for new and existing Opporunities
	 * TC: 17563 - Verify the functionality of Start Time and End Time with valid date range
	 * */
		
	@Test
	public void verifyOpportunityTravelSettings(ITestContext context) throws InterruptedException {
		
		Map<String, Object> testData = new HashMap<String, Object>();
		String testID="42433,42435,42522,42529";
		
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
						
		assertTrue(adminOppSettingsPage.verifyNavigationToOppSettingsPage(), "UI Elements in Opp settings missing");	
	
		String title=newOpportunityGeneralPage.getOppTitle();
		
		adminOppSettingsPage.goToOppEventsTab();
		newOpportunityEventPage.disableTravel();
		newOpportunityEventPage.createOrSaveOpportunity();
		gcnAdminPage.navToAdminOpportunityPage();	
		
		assertTrue(adminOpportunityListPage.verifyOppAvailable(title), "Opportunity Not Listed");
		adminOpportunityListPage.goToOpportunityByTitle(title);
		assertFalse(adminOppDetailsPage.isBudgetLinkDisplayed(), "budget link available");
		
		adminOppDetailsPage.goToOppSettings();
		adminOppSettingsPage.goToOppEventsTab();
		newOpportunityEventPage.enableTravel();
		newOpportunityEventPage.createOrSaveOpportunity();
		gcnAdminPage.navToAdminOpportunityPage();
		adminOpportunityListPage.goToOpportunityByTitle(title);
		assertTrue(adminOppDetailsPage.isBudgetLinkDisplayed(), "budget link missing");
		
		adminOppDetailsPage.selOppStatus(testData.get("publishedStatus").toString());
		
		Thread.sleep(2000);
		
		assertAll();
	}
	
}
