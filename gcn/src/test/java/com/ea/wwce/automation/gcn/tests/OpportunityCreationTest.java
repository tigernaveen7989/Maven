package com.ea.wwce.automation.gcn.tests;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

public class OpportunityCreationTest extends GcnBaseTest{
	
	public static Logger logger = Logger.getLogger(OpportunityCreationTest.class);

	@Test
	public void verifyOpportunityCreation(ITestContext context) throws InterruptedException {
		
		Map<String, Object> testData = new HashMap<String, Object>();
		String testID="global";
			
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);
		
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);
		
		gcnAdminHomePage.launchAdminAndSignIn();
		
		gcnAdminPage=gcnAdminLoginPage.verifyLogin(testData.get("username").toString(), testData.get("password").toString());

		gcnAdminPage.navToAdminOpportunityPage();
		
		assertTrue(adminOpportunityListPage.verifyOpportunityCreation(testData.get("Visibility_yes").toString(),
				testData.get("ApprovalReq_no").toString(), testData.get("OppoDesc1").toString(),
				testData.get("Location2").toString(), testData.get("Country").toString(),
				testData.get("TravelTimes1").toString(), testData.get("ApprovalManager1").toString(),
				testData.get("EventDesc1").toString()), "Opportunity Creation Error");
		
		assertTrue(adminOpportunityListPage.verifyPublishingOpportunity(testData.get("publishedStatus").toString()), "Error Publishing Opportunity");
		
		adminOppDetailsPage.goToOppUsers();
		
		assertTrue(adminOppUsersPage.verifyNavigationToOppUsersPage(), "Error Navigating to Opportunity Users Page");
		
		assertTrue(adminOppUsersPage.addUsersToOpportunity(), "Error adding/inviting users");
		
		assertAll();
	}
}
