package com.ea.wwce.automation.gcn.tests;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

public class NewOpportunityGeneralPageUiTest extends GcnBaseTest{
	
	public static Logger logger=Logger.getLogger(NewOpportunityGeneralPageUiTest.class);
	
	/*This class is to check the UI for General Tab of Opportunities.
	 * 
	 * TC: 17562 - Verify the fields Start Time, End Time, Location,country and Has Travel are displayed in the Events page for new and existing Opporunities 
	 * */
		
	@Test
	public void verifyNewOpportunityGeneralTab(ITestContext context) throws InterruptedException {
		Map<String, Object> testData = new HashMap<String, Object>();

		String testID="17562,17583";
	
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);
	
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);
		
		gcnAdminHomePage.launchAdminAndSignIn();
		
		gcnAdminPage=gcnAdminLoginPage.verifyLogin(testData.get("username").toString(), testData.get("password").toString());
		
		// Navigate to Admin Opportunities Page
		gcnAdminPage.navToAdminOpportunityPage();
		
		adminOpportunityListPage.clickOnCreateNew();
		
		// Steps to Create New Opportunity
		
		assertTrue(newOpportunityGeneralPage.isTitleBoxDisplayed(), "Title Box Not Displayed");
		assertTrue(newOpportunityGeneralPage.verifyOppGeneralTabUi(), "Opportunity General Tab UI Elements Missing");
		//assertTrue(newOpportunityGeneralPage.isOppDescBoxDisplayed(), "Opportunity Desc Box Not Displayed");
		assertTrue(newOpportunityGeneralPage.isOppPreviewImgDisplayed(), "Default Opportunity Preview Image Not Displayed");
		assertTrue(newOpportunityGeneralPage.isCreateBtnDisplayed(), "Opportunity Create Button Not Displayed");
		
		
		Thread.sleep(3000);
				
		assertAll();
	}
	
	// method to parameterize the date goes here
	

	
}
