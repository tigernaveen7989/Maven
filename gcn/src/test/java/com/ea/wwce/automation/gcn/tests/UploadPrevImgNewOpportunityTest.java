package com.ea.wwce.automation.gcn.tests;

import java.awt.AWTException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

public class UploadPrevImgNewOpportunityTest extends GcnBaseTest{

	public static Logger logger = Logger.getLogger(UploadPrevImgNewOpportunityTest.class);
	
	/*This class is to check the creation of new Opportunity, uploading preview image from local drive.
	 * 
	 * TC: 16185 - Verify image update/change feature for new Opportunity
	 * 
	 * */

	
	@Test
	public void verifyUploadPrevImgToNewOpp(ITestContext context) throws InterruptedException, AWTException {
		
		Map<String, Object> testData = new HashMap<String, Object>();
		String testID="16186";
				
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);
		
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);
		
		gcnAdminHomePage.launchAdminAndSignIn();
		
		gcnAdminPage=gcnAdminLoginPage.verifyLogin(testData.get("username").toString(), testData.get("password").toString());
		
		// Navigate to Admin Opportunities Page
		gcnAdminPage.navToAdminOpportunityPage();
		
		adminOpportunityListPage.clickOnCreateNew();
				
		newOpportunityGeneralPage.clickOnImgNxtBtn();
		assertTrue(newOpportunityGeneralPage.isImgPrevBtnDisplayed(), "Prev button not Displayed");
		
		//assertTrue(newOpportunityGeneralPage.isImgHolderDisplayed(), "Image Holder not Displayed");
		
		String opp1=newOpportunityGeneralPage.fillOppGeneralTabWithoutImg(testData.get("Visibility_yes").toString(),
				testData.get("ApprovalReq_no").toString(),testData.get("OppoDesc2").toString());
								
		//assertTrue(newOpportunityGeneralPage.isOppPreviewImgDisplayed(), "Opportunity Preview Image is Not Displayed");
		
		String f1=newOpportunityGeneralPage.uploadImage();
		
		//adminNewOpportunityPage.clickOnEventsTab();
		
		adminOppSettingsPage.goToOppEventsTab();
		
		newOpportunityEventPage.enableEvent();
		
		assertFalse(newOpportunityEventPage.checkBannedCountries(), "Check for Banned Countries Listed ");
				
		newOpportunityEventPage.fillOppEventsTab(testData.get("Location1").toString(),testData.get("Country").toString(),
				testData.get("TravelTimes1").toString(),testData.get("ApprovalManager1").toString(),testData.get("EventDesc1").toString());
		newOpportunityEventPage.createOrSaveOpportunity();
		
		assertTrue(adminOpportunityListPage.verifyOppAvailable(opp1), "Opportunity Not Listed");
		
		adminOpportunityListPage.goToOpportunityByTitle(opp1);
		
		adminOppDetailsPage.goToOppSettings();
		
		assertTrue(adminOppSettingsPage.verifyNavigationToOppSettingsPage(), "Could not Navigate to Opp settings Page");
		
		newOpportunityGeneralPage.getPrevImgLink();
		
		assertTrue(newOpportunityGeneralPage.getPrevImgLink().contains(f1), "Uploaded image is NOT set as Preview Img");
			
		assertAll();
	}

}
