package com.ea.wwce.automation.gcn.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;
import com.ea.wwce.automation.gcn.config.GcnDataConstants;

import io.qameta.allure.Description;

public class GcnAdminBudgetEstimateTest extends GcnBaseTest{
	
	/*This class is to check the UI Elements in Budget Estimation Screen of Opportunities.
	 * 
	 * 
	 * */

	
	@Test
	@Description("Verify Navigation and UI of Budget Estimation Page.")
	public void verifyOppBudgetEstimatePage(ITestContext context) {
		
		Map<String, Object> testData = new HashMap<String, Object>();

		String testID="20426,20427,20431,42463,42466,42467";
		
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);
		
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);
		
		gcnAdminHomePage.launchAdminAndSignIn();
		
		gcnAdminPage=gcnAdminLoginPage.verifyLogin(testData.get("username").toString(), testData.get("password").toString());
		
		gcnAdminPage.navToAdminOpportunityPage();
		
		adminOppDetailsPage=adminOpportunityListPage.goToAnyOpportunity();
		
		// Navigate to Budget Estimation Screen.
		adminOppBudgetPage=adminOppDetailsPage.clickBudgetEstimationLink();
			
		assertEquals(adminOppBudgetPage.getPageTitle(), GcnDataConstants.actBudgetPageTitle, "Title Mis Match");
		assertTrue(adminOppBudgetPage.verifyNavigationToBudgetEstPage(), "Error Landing into Budget Estimation Page");
		
		// Check for Opportunity Name,Date,Image
		assertTrue(adminOppBudgetPage.verifyOpportunityDetails(), "Opportunity Details Missing.");
		
		// Check for Main Menu Navigation Links
		assertTrue(gcnAdminPage.verifyAvailabilityOfMenuLinks(),"Admin Page Menu Links not Displayed");
		
		// Main menu Navigation and Functionality Check
		
		// Check Users Page Navigation.
		adminUsersPage=gcnAdminPage.clickOnUsersLink();
		
		assertTrue(adminUsersPage.verifyNavigationToUsersPage(), "Error landing into Opportunity Users Page");

		adminUsersPage.navigateBackTo();
		assertTrue(adminOppBudgetPage.verifyNavigationToBudgetEstPage(), "Error Landing into Budget Estimation Page");
		
		// Check for Admin page navigation.
		gcnAdminPage.clickOnAdminLink();
		assertTrue(gcnAdminPage.verifyNavigationToAdminPage(), "Error Landing into Admin Home Page");
		
		gcnAdminPage.navigateBackTo();
		assertTrue(adminOppBudgetPage.verifyNavigationToBudgetEstPage(), "Error Landing into Budget Estimation Page");
		
		// Opportunity Page Navigation.
		gcnAdminPage.navToAdminOpportunityPage();
		assertTrue(adminOpportunityListPage.verifyNavigationToOppoListPage(), "Error Landing into Opportunity List Page");

		adminOpportunityListPage.navigateBackTo();
		assertTrue(adminOppBudgetPage.verifyNavigationToBudgetEstPage(), "Error Landing into Budget Estimation Page");
		
		// Content Page Navigation
		gcnAdminPage.clickOnContentLink();
		assertTrue(gcnAdminContentPage.verifyNavigationToContentPage(), "Error Landing into Content Page");
		
		gcnAdminContentPage.navigateBackTo();
		assertTrue(adminOppBudgetPage.verifyNavigationToBudgetEstPage(), "Error Landing into Budget Estimation Page");
		
		// Reports Page Navigation
		gcnAdminPage.clickOnReportsLink();
		assertTrue(gcnAdminReportsPage.verifyNavigationToReportsPage(), "Error Landing into Reports Page");
		
		gcnAdminReportsPage.navigateBackTo();
		assertTrue(adminOppBudgetPage.verifyNavigationToBudgetEstPage(), "Error Landing into Budget Estimation Page");
		
		// Operations Page Navigation
		gcnAdminPage.clickOnOperationsLink();
		assertTrue(gcnAdminOperationsPage.verifyNavigationToOperationsPage(), "Error Landing into Operations Page");
		
		gcnAdminOperationsPage.navigateBackTo();
		assertTrue(adminOppBudgetPage.verifyNavigationToBudgetEstPage(), "Error Landing into Budget Estimation Page");
		
		// Functionality of Logout Link
		gcnAdminPage.logoutAdmin();
		assertTrue(gcnAdminHomePage.verifyNavigationToAdminHomePage(), "Error Landing into Admin Home Site Page");
		
		
		assertAll();
	}

}
