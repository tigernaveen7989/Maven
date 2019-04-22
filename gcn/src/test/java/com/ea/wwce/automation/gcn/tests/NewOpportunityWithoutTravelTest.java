package com.ea.wwce.automation.gcn.tests;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

public class NewOpportunityWithoutTravelTest extends GcnBaseTest{

	public static Logger logger = Logger.getLogger(NewOpportunityWithoutTravelTest.class);
	
	/*This class is to check the creation of new Opportunity, selecting previous image from
	 * existing gallery.	 * 
	 * TC: 16185 - Verify image update/change feature for new Opportunity
	 * TC: 17583 - Verify the Preview image functionality for the New Opportunity
	 * 
	 * */

	
	@Test
	public void createNewOpportunity(ITestContext context) throws InterruptedException {
		
		Map<String, Object> testData = new HashMap<String, Object>();
		String testID="42434";
				
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
				
		String[] arr1=newOpportunityGeneralPage.fillOppGeneralTab(testData.get("Visibility_yes").toString(),
				testData.get("ApprovalReq_no").toString(),testData.get("OppoDesc1").toString());
		
		
		adminOppSettingsPage.goToOppEventsTab();
		newOpportunityEventPage.enableEvent();
						
		newOpportunityEventPage.fillOppEventsTab(testData.get("Location1").toString(),testData.get("Country").toString());
		
		newOpportunityEventPage.createOrSaveOpportunity();
					
		assertTrue(adminOpportunityListPage.verifyNavigationToOppoListPage(),"Error in Opportunity Page loading");
		
		assertTrue(adminOpportunityListPage.verifyOppAvailable(arr1[0]), "Opportunity Not Listed");
		
		adminOpportunityListPage.goToOpportunityByTitle(arr1[0]);
		
		assertFalse(adminOppDetailsPage.verifyAvailabilityOfLeftNavLinks(), "All Left Navigation links Displayed");
						
		assertAll();
	}

}
