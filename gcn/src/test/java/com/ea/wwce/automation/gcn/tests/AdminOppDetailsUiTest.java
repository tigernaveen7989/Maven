package com.ea.wwce.automation.gcn.tests;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

import io.qameta.allure.Description;

public class AdminOppDetailsUiTest extends GcnBaseTest{
	
	/*This Class is to test the availability of links present in the page and navigate 
	to make sure the links navigate to proper pages.*/
	
	public static Logger logger=Logger.getLogger(AdminOppDetailsUiTest.class);
	

	
	@Test
	@Description("Verify the UI elements in Opportunity Details Page")
	public void adminOppDetailsUiTest(ITestContext context) throws InterruptedException {
		
		Map<String, Object> testData = new HashMap<String, Object>();
		String testID="42422";
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);
		
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);
		
		gcnAdminHomePage.launchAdminAndSignIn();
		
		gcnAdminLoginPage.verifyLogin(testData.get("username").toString(), testData.get("password").toString());
		
		gcnAdminPage.navToAdminOpportunityPage();
		adminOpportunityListPage.verifyNavigationToOppoListPage();
		// Change the Opportunity Details in AdminOpportunityListPage Class

		adminOpportunityListPage.goToAnyOpportunity();
		
		
		assertTrue(adminOppDetailsPage.verifyNavigationToOppoDetailsPage(), "Ui Elements in OPp details page missing");
				
		// UI Verifications.
		
		assertTrue(adminOppDetailsPage.verifyAvailabilityOfLeftNavLinks(), "Left Navigation Links are Not Displayed.");
		
		adminOppPaymentsPage=adminOppDetailsPage.clickOnPaymentsLink();
			
		assertTrue(adminOppPaymentsPage.verifyNavigationToPaymentsPage(), "Error Landing into Payments Page");
		
		adminOppPaymentsPage.navigateBackTo();
		assertTrue(adminOppDetailsPage.verifyNavigationToOppoDetailsPage(), "Error Landing into Opportunity Overview Page");
				
		adminOppDetailsPage.goToOppSettings();
		
		assertTrue(adminOppSettingsPage.verifyNavigationToOppSettingsPage(), "Error landing into Opportunity Settings Page");
		
		adminOppDetailsPage.goToOppUsers();
		assertTrue(adminOppUsersPage.verifyNavigationToOppUsersPage(), "Error landing into Opportunity Users Page");
		
		adminOppDetailsPage.goToOverviewPage();
		
		assertTrue(adminOppDetailsPage.verifyNavigationToOppoDetailsPage(), "Error Landing into Opportunity Overview Page");
		
		assertAll();
		
	}
	
	
}
