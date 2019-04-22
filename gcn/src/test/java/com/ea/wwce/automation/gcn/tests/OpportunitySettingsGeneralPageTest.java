package com.ea.wwce.automation.gcn.tests;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

public class OpportunitySettingsGeneralPageTest extends GcnBaseTest{
	
	/*This Class is to test the negative cases.
	 * - To test for error messages dispalyed if user leaves mandatory fields in the events and General tab blank
	 * This is only for existing Opportunities.
	 * */

	public static Logger logger = Logger.getLogger(OpportunitySettingsGeneralPageTest.class);
		
	@Test
	public void verifyOpportunityGeneralSettings(ITestContext context) throws InterruptedException {
		
		Map<String, Object> testData = new HashMap<String, Object>();
		String testID="global";
		
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
		
		newOpportunityGeneralPage.clearAllGeneralFields();
		
		assertTrue(newOpportunityGeneralPage.isErrorFrameDisplayed(), "Error Frame Not Displayed");
		assertTrue(newOpportunityGeneralPage.checkForErrorsDisplayed(),"Errors Not Displayed");
		
		newOpportunityGeneralPage.fillOppGeneralInvalidDate();	
		
		Thread.sleep(3000);
		assertTrue(newOpportunityGeneralPage.isErrorFrameDisplayed(), "Error Frame Not Displayed");
		assertTrue(newOpportunityGeneralPage.checkForErrorsDisplayed(),"Errors Not Displayed");
		
		
		newOpportunityGeneralPage.setStartDateTime();
		newOpportunityGeneralPage.setEndDateTime();
				
		newOpportunityGeneralPage.createORSaveOpportunity();
				
		Thread.sleep(5000);
		
		assertAll();
	}
	
}
