package com.ea.wwce.automation.gcn.tests;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

public class NewOpportunityEventPageUiTest extends GcnBaseTest{
	
	public static Logger logger=Logger.getLogger(NewOpportunityEventPageUiTest.class);
	
	/*This class is to check the UI for New Opportunity Events Tab.
	 * 
	 * TC: 17562 - Verify the fields Start Time, End Time, Location,country and Has Travel are displayed in the Events page for new and existing Opporunities
	 * */
	
	@Test
	public void verifyNewOppotunityEventTab(ITestContext context) throws InterruptedException {
		
		Map<String, Object> testData = new HashMap<String, Object>();
		String testID="42423,42424,17562";
		
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);
		
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);
		
		gcnAdminHomePage.launchAdminAndSignIn();
		
		gcnAdminPage=gcnAdminLoginPage.verifyLogin(testData.get("username").toString(), testData.get("password").toString());
		
		// Navigate to Admin Opportunities Page
		gcnAdminPage.navToAdminOpportunityPage();
				
		adminOpportunityListPage.clickOnCreateNew();
		assertTrue(newOpportunityGeneralPage.isTitleBoxDisplayed(), "Title Field Displayed");
		
		//adminNewOpportunityPage.clickOnEventsTab();
		adminOppSettingsPage.goToOppEventsTab();
		
		assertTrue(newOpportunityEventPage.verifyDefaultEventTabMsgDisplayed(),"Default Enable Msg Not Displayed");
		
		assertTrue(newOpportunityEventPage.verifyEventPageUi(),"UI Elements missing in Event Tab");
		assertAll();
	}
	
	
}
