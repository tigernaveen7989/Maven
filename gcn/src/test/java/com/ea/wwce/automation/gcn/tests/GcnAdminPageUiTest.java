package com.ea.wwce.automation.gcn.tests;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

public class GcnAdminPageUiTest extends GcnBaseTest{
	
	public static Logger logger=Logger.getLogger(GcnAdminPageUiTest.class);
	
	/*This class is to check the UI elements in GCN Admin site after login.
	 * 
	 * TC: 16180 - Verify Admin is navigated to respective homepage after login
	 * 
	 * */
	
	
	// Test Methods to check UI Elements.
	// Login to Application
	@Test
	public void adminPageUiTest(ITestContext context) {
		Map<String, Object> testData = new HashMap<String, Object>();
		String testID="21058,21060,21075,21246,21301,40986,42420";
		
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);
		
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);
		
		gcnAdminHomePage.launchAdminAndSignIn();
		
		gcnAdminPage=gcnAdminLoginPage.verifyLogin(testData.get("username").toString(), testData.get("password").toString());
			
		// Assertions
		assertTrue(gcnAdminPage.verifyAvailabilityOfMenuLinks(),"All Menu Links Not Displayed");
		
		assertTrue(gcnAdminPage.verifyActionItemUi(),"UI elements of Action Items section are missing");
		
		gcnAdminPage.clickOnUsersLink();
		assertTrue(adminUsersPage.verifyNavigationToUsersPage(), "Error landing into Users Page");
		adminUsersPage.navigateBackTo();
		
		gcnAdminPage.clickOnAdminLink();
		assertTrue(gcnAdminPage.verifyNavigationToAdminPage(), "Error Landing into Admin Home Page");
		gcnAdminPage.navigateBackTo();
		
		gcnAdminPage.navToAdminOpportunityPage();
		assertTrue(adminOpportunityListPage.verifyNavigationToOppoListPage(), "Error Landing into Opportunity List Page");
		adminOpportunityListPage.navigateBackTo();
		
		gcnAdminPage.clickOnContentLink();
		assertTrue(gcnAdminContentPage.verifyNavigationToContentPage(), "Error Landing into Content Page");
		gcnAdminContentPage.navigateBackTo();
		
		gcnAdminPage.clickOnReportsLink();
		assertTrue(gcnAdminReportsPage.verifyNavigationToReportsPage(), "Error Landing into Reports Page");
		gcnAdminReportsPage.navigateBackTo();
		
		gcnAdminPage.clickOnOperationsLink();
		assertTrue(gcnAdminOperationsPage.verifyNavigationToOperationsPage(), "Error Landing into Operations Page");
		gcnAdminOperationsPage.navigateBackTo();
				
		// Footer link checks
		
		assertTrue(adminOppPaymentsPage.isEaAdminFooterTextDisplayed(), "Footer Text Not Displayed");
		assertTrue(gcnAdminPage.isEaFooterTextDisplayed(), "Footer Text Powered by is Not Displayed");
		assertTrue(gcnAdminPage.isEaWhiteLogoDisplayed(), "Footer EA Logo is Not Displayed");
		
		// Functionality of Logout Link
		gcnAdminPage.logoutAdmin();
		assertTrue(gcnAdminHomePage.verifyNavigationToAdminHomePage(), "Error Landing into Admin Home Site Page");
		
		
		assertAll();
		
			
		
	}

}
