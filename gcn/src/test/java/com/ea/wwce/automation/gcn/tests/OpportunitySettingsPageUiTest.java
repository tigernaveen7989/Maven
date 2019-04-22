package com.ea.wwce.automation.gcn.tests;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

public class OpportunitySettingsPageUiTest extends GcnBaseTest{

	public static Logger logger = Logger.getLogger(OpportunitySettingsPageUiTest.class);
	
	/*This class is to check the UI for Opportunity Settings Page.
	 * 
	 * TC: 16193 - Verify the functionality of Prev button in the opportunity settings screen
	 * 
	 * TC: 16194 - Verify the functionality of Next button in the opportunity settings screen
	 *  
	 * 
	 * */
		
	@Test
	public void verifyOpportunitySettingsUi(ITestContext context) throws InterruptedException {
		
		Map<String, Object> testData = new HashMap<String, Object>();
		String testID="16193,16194";
		
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);
		
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);
		
		gcnAdminHomePage.launchAdminAndSignIn();
		
		gcnAdminPage=gcnAdminLoginPage.verifyLogin(testData.get("username").toString(), testData.get("password").toString());
		
		// Navigate to Admin Opportunities Page
		gcnAdminPage.navToAdminOpportunityPage();
		
		adminOpportunityListPage.goToAnyOpportunity();
		
		assertTrue(adminOppDetailsPage.verifyNavigationToOppoDetailsPage(), "Opportunity Details Page Not Loaded");
		adminOppDetailsPage.goToOppSettings();
		
		// **************************************************************
		assertTrue(adminOppSettingsPage.verifyNavigationToOppSettingsPage(), "UI Elements in Opp settings missing");
		
	
		//assertTrue(newOpportunityGeneralPage.isOppPreviewImgDisplayed(), "Opportunity Preview Image is Not Displayed");
		
		assertTrue(newOpportunityGeneralPage.verifyOppGeneralTabUi(), "General tab ui elements missing");
		newOpportunityGeneralPage.clickOnImgNxtBtn();
		assertTrue(newOpportunityGeneralPage.isImgPrevBtnDisplayed(), "Prev button for Images is Not Displayed");
		
		assertTrue(newOpportunityGeneralPage.verifyGalleryImgDisplayed(), "Gallery images are not Listed");

		newOpportunityGeneralPage.selPreviewImg();
		
		newOpportunityGeneralPage.clickOnImgPreBtn();
		assertTrue(newOpportunityGeneralPage.verifyGalleryImgDisplayed(), "Gallery images are not Listed");
		
		assertTrue(newOpportunityGeneralPage.isOppPreviewImgDisplayed(), "Opp Preview Image not Set");
		
		newOpportunityGeneralPage.createORSaveOpportunity();		
				
		assertAll();
	}
	
}
