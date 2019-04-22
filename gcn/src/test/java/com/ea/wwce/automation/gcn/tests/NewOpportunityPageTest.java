package com.ea.wwce.automation.gcn.tests;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

public class NewOpportunityPageTest extends GcnBaseTest{

	public static Logger logger = Logger.getLogger(NewOpportunityPageTest.class);
	
	/*This class is to check the creation of new Opportunity, selecting previous image from
	 * existing gallery.	 * 
	 * TC: 16185 - Verify image update/change feature for new Opportunity
	 * TC: 17583 - Verify the Preview image functionality for the New Opportunity
	 * 
	 * */

	
	@Test
	public void createNewOpportunity(ITestContext context) throws InterruptedException {
		
		Map<String, Object> testData = new HashMap<String, Object>();
		String testID="16185,17583,42431,42432";
		
		//16185,16194,17583
		
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);
		
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);
		
		gcnAdminHomePage.launchAdminAndSignIn();
		
		gcnAdminPage=gcnAdminLoginPage.verifyLogin(testData.get("username").toString(), testData.get("password").toString());
		
		// Navigate to Admin Opportunities Page
		gcnAdminPage.navToAdminOpportunityPage();
		
		adminOpportunityListPage.clickOnCreateNew();
		
		assertTrue(newOpportunityGeneralPage.isOppPreviewImgDisplayed(), "Opportunity Preview Image is Not Displayed");
		
		assertTrue(newOpportunityGeneralPage.verifyOppGeneralTabUi(), "Missing UI elements in general tab");
		
		assertTrue(newOpportunityGeneralPage.verifyDefaultImageSet(), "First image is not set as default");
		
		newOpportunityGeneralPage.clickOnImgNxtBtn();
		assertTrue(newOpportunityGeneralPage.isImgPrevBtnDisplayed(), "Prev button not Displayed");
		
		String[] arr1=newOpportunityGeneralPage.fillOppGeneralTab(testData.get("Visibility_yes").toString(),
				testData.get("ApprovalReq_no").toString(),testData.get("OppoDesc1").toString());
		
		assertTrue(newOpportunityGeneralPage.isOppPreviewImgDisplayed(), "Opportunity Preview Image is Not Displayed");
		
		//adminNewOpportunityPage.clickOnEventsTab();
		
		adminOppSettingsPage.goToOppEventsTab();
		
		newOpportunityEventPage.enableEvent();
		assertFalse(newOpportunityEventPage.checkBannedCountries(), "Check for Banned Countries Listed ");
				
		newOpportunityEventPage.fillOppEventsTab(testData.get("Location1").toString(),testData.get("Country").toString(),
				testData.get("TravelTimes1").toString(),testData.get("ApprovalManager1").toString(),testData.get("EventDesc1").toString());
		
		newOpportunityEventPage.createOrSaveOpportunity();
		
		assertTrue(adminOpportunityListPage.verifyOppAvailable(arr1[0]), "Opportunity Not Listed");
		
		adminOpportunityListPage.goToOpportunityByTitle(arr1[0]);
		
		assertTrue(adminOppDetailsPage.verifyAvailabilityOfLeftNavLinks(), "Left Navigation links missing");
		
		adminOppDetailsPage.goToOppSettings();
		
		assertTrue(newOpportunityGeneralPage.isOppPreviewImgDisplayed(), "Opportunity Preview Image is Not Displayed");
		
		assertEquals(arr1[1],newOpportunityGeneralPage.getPrevImgLink(), "Mismatch in selected preview Image");	
				
		assertAll();
	}

}
