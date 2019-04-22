package com.ea.wwce.automation.gcn.tests;

import java.awt.AWTException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

public class OpportunityImgUploadTest extends GcnBaseTest{
	public static Logger logger=Logger.getLogger(OpportunityImgUploadTest.class);
	
	/*Thic class is to check the upload of image to Opportunity for existing Opportunity.
	 * 
	 * TC: 16188 - Verify image update feature for existing Opportunity from local Pc
	 * */

	@Test
	public void verifyOppImgUpload(ITestContext context) throws AWTException, InterruptedException {
		Map<String, Object> testData = new HashMap<String, Object>();
		String testID="16188,16189";
		
		//String testID="16188";

		
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);
			
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);
		
		gcnAdminHomePage.launchAdminAndSignIn();
		
		gcnAdminPage=gcnAdminLoginPage.verifyLogin(testData.get("username").toString(), testData.get("password").toString());
	
		// Navigate to Admin Opportunities Page
		gcnAdminPage.navToAdminOpportunityPage();
		// Navigate to Opportunity details page
		adminOpportunityListPage.goToAnyOpportunity();
		
		assertTrue(adminOppDetailsPage.verifyNavigationToOppoDetailsPage(), "Opp details Page Loaded");
		
		// Navigate to Opportunity Settings Page
		adminOppDetailsPage.goToOppSettings();
		
		assertTrue(newOpportunityGeneralPage.isTitleBoxDisplayed(), "Landed into General Tab");
		
		String opp1=newOpportunityGeneralPage.getOppTitle();
				
		assertTrue(newOpportunityGeneralPage.isUploadedImgDisplayed(), "Image Uploading is Failed");
		
		String f1=newOpportunityGeneralPage.getPrevImgLink();
		
		newOpportunityGeneralPage.createORSaveOpportunity();
		
		gcnAdminPage.navToAdminOpportunityPage();
		
		adminOpportunityListPage.goToOpportunityByTitle(opp1);
		adminOppDetailsPage.goToOppSettings();
		
		String opp2=newOpportunityGeneralPage.getOppTitle();
		
		assertEquals(opp1, opp2, "Navigated to Different Opportunity");

		assertTrue(newOpportunityGeneralPage.isOppPreviewImgDisplayed(), "Opportunity Preview Image is Not Displayed");
		
		String f2=newOpportunityGeneralPage.getPrevImgLink();
		
		assertEquals(f1, f2, "Uploaded Image is Not Set as Preview Image");
		
		
		assertAll();
	}

}
