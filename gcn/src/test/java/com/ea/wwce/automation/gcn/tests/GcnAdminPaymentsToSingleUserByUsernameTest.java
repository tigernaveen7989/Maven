package com.ea.wwce.automation.gcn.tests;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

public class GcnAdminPaymentsToSingleUserByUsernameTest extends GcnBaseTest{
	
	public static Logger logger=Logger.getLogger(GcnAdminPaymentsToSingleUserByUsernameTest.class);
	
	/*This class is to check the updation of Payment value when checkbox is checked and unchecked.
	 * TC: 17665 - Verify the details of PAYMENT TOTAL in Opportunity Payee page
	 * */

	
	@Test
	public void verifyOppPaymentsToSingleUserByUsername(ITestContext context) throws InterruptedException {
		
		Map<String, Object> testData = new HashMap<String, Object>();
		
		String testID="17665";
		//String testID="global";

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
		
		/*adminOppPaymentsPage.payByUserName(testData.get("userskomalgc4").toString());
		Thread.sleep(3000);*/
		// Uncheck the selected user to see the amount is reset to 0.
		adminOppPaymentsPage.verifySelectingCheckBoxByUserName(testData.get("komaluid").toString());
		assertEquals(Integer.toString(adminOppPaymentsPage.getPaymentValue()), "100", "Non Zero after uncheck All");
		Thread.sleep(3000);
		adminOppPaymentsPage.verifySelectingCheckBoxByUserName(testData.get("komaluid").toString());
		assertEquals(Integer.toString(adminOppPaymentsPage.getPaymentValue()), "0", "Non Zero after uncheck All");
		
		Thread.sleep(3000);
		/*
		
		// Menu Navigation and Functionality check.
		
		// Check Users Page Navigation.
		adminUsersPage=gcnAdminPage.clickOnUsersLink();
		assertTrue(adminUsersPage.verifyNavigationToUsersPage(), "Error landing into Users Page");

		adminUsersPage.navigateBackTo();
		assertTrue(adminOppPaymentsPage.verifyNavigationToPaymentsPage(), "Not Returned to Opp Payments Screen");


		// Check for Admin page navigation.
		gcnAdminPage.clickOnAdminLink();
		assertTrue(gcnAdminPage.verifyNavigationToAdminPage(), "Error Landing into Admin Page");
	
		gcnAdminPage.navigateBackTo();
		assertTrue(adminOppPaymentsPage.verifyNavigationToPaymentsPage(), "Not Returned to Opp Payments Screen");


		// Opportunity Page Navigation.
		gcnAdminPage.navToAdminOpportunityPage();
		assertTrue(adminOpportunityListPage.verifyNavigationToOppoListPage(), "Error Landing into Opportunity LIst Page");

		adminOpportunityListPage.navigateBackTo();
		assertTrue(adminOppPaymentsPage.verifyNavigationToPaymentsPage(), "Not Returned to Opp Payments Screen");

		// Content Page Navigation
		gcnAdminPage.clickOnContentLink();
		assertTrue(gcnAdminContentPage.verifyNavigationToContentPage(), "Error landing into Content Page");
	
		gcnAdminContentPage.navigateBackTo();
		assertTrue(adminOppPaymentsPage.verifyNavigationToPaymentsPage(), "Not Returned to Opp Payments Screen");

		// Reports Page Navigation

		gcnAdminPage.clickOnReportsLink();
		assertTrue(gcnAdminReportsPage.verifyNavigationToReportsPage(), "Error Landing into Reports Page");
		
		gcnAdminReportsPage.navigateBackTo();
		assertTrue(adminOppPaymentsPage.verifyNavigationToPaymentsPage(), "Not Returned to Opp Payments Screen");

		// Operations Page Navigation
		gcnAdminPage.clickOnOperationsLink();
		assertTrue(gcnAdminOperationsPage.verifyNavigationToOperationsPage(), "Error landing into Operations Page");
		
		gcnAdminOperationsPage.navigateBackTo();
		assertTrue(adminOppPaymentsPage.verifyNavigationToPaymentsPage(), "Not Returned to Opp Payments Screen");

		// Functionality of Logout Link
		gcnAdminPage.logoutAdmin();
		assertTrue(gcnAdminHomePage.verifyNavigationToAdminHomePage(), "Error landing into Admin Home page site.");*/
			
		assertAll();
	
	}
	

}
