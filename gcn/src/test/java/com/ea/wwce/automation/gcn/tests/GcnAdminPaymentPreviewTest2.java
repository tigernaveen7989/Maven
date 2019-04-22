package com.ea.wwce.automation.gcn.tests;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

public class GcnAdminPaymentPreviewTest2 extends GcnBaseTest{

	public static Logger logger=Logger.getLogger(GcnAdminPaymentPreviewTest2.class);
	
	/*This class is to check Opportunity payment and Preview screen.*/
	
	
	/*TC: 17680 - Verify the navigation menu in the Payment Review page.
	 *TC: 17681 - Verify the functionality of links in navigation menu in Payment Review Page
	 *TC: 17669 - Verify the functionality of links in navigation menu for Confirm Payee details page
	 *TC: 17671 - Verify the page heading in the confirm payee details page
	 *TC: 17678 - Verify the Payment total label details in the Confirm Payee details page
	 *
	 *
	 * */
	
	@Test
	public void verifyOppPaymentsPreviewPage(ITestContext context) {
		
		Map<String, Object> testData = new HashMap<String, Object>();
		String testID="17680,17681,17669,17671,17678";
		
			
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);
		
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);
		
		gcnAdminHomePage.launchAdminAndSignIn();
		
		gcnAdminPage=gcnAdminLoginPage.verifyLogin(testData.get("username").toString(), testData.get("password").toString());

		gcnAdminPage.navToAdminOpportunityPage();

		//adminOpportunityListPage.isOppDisplayed();
				
		adminOpportunityListPage.goToOpportunityByTitle("");

		adminOppDetailsPage.clickOnPaymentsLink();
		assertTrue(adminOppPaymentsPage.verifyNavigationToPaymentsPage(), "Error landing into Payments Page");
		
		adminOppPaymentsPage.payAll();
		
		assertTrue(adminOppPaymentsReviewPage.verifyPaymentPreivewPageUi(), "UI elements in Payments Preivew page missing");
		assertTrue(adminOppPaymentsReviewPage.isTotalPaymentSummaryDisplayed(), "Total Payment summary is displayed > 0");
		
		// check for availability of navigation links
		assertTrue(gcnAdminPage.verifyAvailabilityOfMenuLinks(),"All Menu Links Not Displayed");
		
		// Menu Navigation and Functionality check.
		
		// Check Users Page Navigation.
		adminUsersPage=gcnAdminPage.clickOnUsersLink();
		
		assertTrue(adminUsersPage.verifyNavigationToUsersPage(), "Error landing into Users Page");
				
		adminUsersPage.navigateBackTo();
		assertTrue(adminOppPaymentsReviewPage.verifyNavigationToPaymentsReviewPage(), "Error Landing into Payment Review Page");
			
		// Check for Admin page navigation.
		gcnAdminPage.clickOnAdminLink();
		assertTrue(gcnAdminPage.verifyNavigationToAdminPage(), "Error Landing into Admin Page");
	
		gcnAdminPage.navigateBackTo();
		assertTrue(adminOppPaymentsReviewPage.verifyNavigationToPaymentsReviewPage(), "Error Landing into Payment Review Page");
	
		// Opportunity Page Navigation.
		gcnAdminPage.navToAdminOpportunityPage();
		assertTrue(adminOpportunityListPage.verifyNavigationToOppoListPage(), "Error Landing into Opportunity LIst Page");
		
		gcnAdminPage.navigateBackTo();
		assertTrue(adminOppPaymentsReviewPage.verifyNavigationToPaymentsReviewPage(), "Error Landing into Payment Review Page");
		
		// Content Page Navigation
		
		gcnAdminPage.clickOnContentLink();
		assertTrue(gcnAdminContentPage.verifyNavigationToContentPage(), "Error landing into Content Page");
		
		gcnAdminPage.navigateBackTo();
		assertTrue(adminOppPaymentsReviewPage.verifyNavigationToPaymentsReviewPage(), "Error Landing into Payment Review Page");
		
		// Reports Page Navigation
		
		gcnAdminPage.clickOnReportsLink();
		assertTrue(gcnAdminReportsPage.verifyNavigationToReportsPage(), "Error Landing into Reports Page");
		
		gcnAdminPage.navigateBackTo();
		assertTrue(adminOppPaymentsReviewPage.verifyNavigationToPaymentsReviewPage(), "Error Landing into Payment Review Page");
		
		// Operations Page Navigation
		gcnAdminPage.clickOnOperationsLink();
		assertTrue(gcnAdminOperationsPage.verifyNavigationToOperationsPage(), "Error landing into Operations Page");
	
		gcnAdminPage.navigateBackTo();
		assertTrue(adminOppPaymentsReviewPage.verifyNavigationToPaymentsReviewPage(), "Error Landing into Payment Review Page");
		
		// Functionality of Logout Link
		gcnAdminPage.logoutAdmin();
		assertTrue(gcnAdminHomePage.verifyNavigationToAdminHomePage(), "Error landing into Admin Home page site.");
		
		
		assertAll();
	}
	

}
