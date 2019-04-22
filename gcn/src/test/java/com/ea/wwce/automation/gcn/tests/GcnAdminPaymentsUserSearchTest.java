package com.ea.wwce.automation.gcn.tests;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

import io.qameta.allure.Description;

public class GcnAdminPaymentsUserSearchTest extends GcnBaseTest{
	
	public static Logger logger=Logger.getLogger(GcnAdminPaymentsUserSearchTest.class);
	
	/*This class is to check the Opportunity Payments scenarios.*/
	
	@Test
	@Description("Verify Search Functionality and Cancel button functionality")
	public void verifyOppPaymentsPage(ITestContext context) {
		
		Map<String, Object> testData = new HashMap<String, Object>();
		String testID="17653";

		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);
		
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);
		
		gcnAdminHomePage.launchAdminAndSignIn();
		gcnAdminPage=gcnAdminLoginPage.verifyLogin(testData.get("username").toString(), testData.get("password").toString());
		gcnAdminPage.navToAdminOpportunityPage();
		
		adminOppDetailsPage=adminOpportunityListPage.goToOpportunityByTitle("Star Wars Heroes_Opportunity_5995");
		adminOppPaymentsPage=adminOppDetailsPage.clickOnPaymentsLink();
		assertTrue(adminOppPaymentsPage.verifyNavigationToPaymentsPage(), "Error landing into Payments Page");
		
		// check for availability of navigation links
		assertTrue(gcnAdminPage.verifyAvailabilityOfMenuLinks(),"All Menu Links Not Displayed");
		
		// Check for default text in user search box.
		// Magnifier img is not an element in DOM, hence cannot be verified.
		assertEquals(adminOppPaymentsPage.searchBoxDefaultText(), "Search for a user", "Default text Not Matching");
		
		// search user and check if user is displayed.
		assertTrue(adminOppPaymentsPage.searchUserDisplayed("Ranger"), "User Search Un-successfull");
				
		assertFalse(adminOppPaymentsPage.searchUserDisplayed("wqw"), "No User to Display");
				
		// Search user with user name in lowercase
		assertTrue(adminOppPaymentsPage.searchUserDisplayed("battle"), "User Search Un-successfull");
		
		adminOpportunityListPage=adminOppPaymentsPage.clickOnCancel();
		
		assertTrue(adminOpportunityListPage.verifyNavigationToOppoListPage(), "Error landing into Opportunity List Page");
		
	
	
	}
	

}
