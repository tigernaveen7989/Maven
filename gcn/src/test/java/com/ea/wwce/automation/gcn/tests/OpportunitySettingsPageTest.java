package com.ea.wwce.automation.gcn.tests;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

public class OpportunitySettingsPageTest extends GcnBaseTest{

	public static Logger logger = Logger.getLogger(OpportunitySettingsPageTest.class);
	
	/*This class is to check Opportunity Settings Page Scenarios.
	 * 
	 * TC: 16187 - Verify image update feature for existing Opportunity from listed images
	 * 
	 * */
		
	@Test
	public void verifyOpportunitySettings(ITestContext context) throws InterruptedException {
		
		Map<String, Object> testData = new HashMap<String, Object>();
		String testID="16187";
		
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
				
		assertTrue(adminOppSettingsPage.verifyNavigationToOppSettingsPage(), "UI Elements in Opp settings missing");
		
		newOpportunityGeneralPage.clickOnImgNxtBtn();
		assertTrue(newOpportunityGeneralPage.isImgPrevBtnDisplayed(), "Prev button for Images is Not Displayed");
		
		//assertTrue(newOpportunityGeneralPage.isOppPreviewImgDisplayed(), "Opp Preview Image not Set");
		
	
		String[] arr1=newOpportunityGeneralPage.fillOppGeneralTab(testData.get("Visibility_yes").toString(),
				testData.get("ApprovalReq_no").toString(),testData.get("OppoDesc2").toString());
				
		assertTrue(newOpportunityGeneralPage.isOppPreviewImgDisplayed(), "Opportunity Preview Image is Not Displayed");
		
		newOpportunityGeneralPage.createORSaveOpportunity();
		
		gcnAdminPage.navToAdminOpportunityPage();		
		
		assertTrue(adminOpportunityListPage.verifyOppAvailable(arr1[0]), "Opportunity Not Listed");
		
		adminOpportunityListPage.goToOpportunityByTitle(arr1[0]);
		
		adminOppDetailsPage.goToOppSettings();
		
		assertTrue(newOpportunityGeneralPage.isOppPreviewImgDisplayed(), "Opportunity Preview Image is Not Displayed");
		
		assertEquals(arr1[1],newOpportunityGeneralPage.getPrevImgLink(), "Mismatch in selected preview Image");
		
		Thread.sleep(2000);
		
		assertAll();
	}
	
}
