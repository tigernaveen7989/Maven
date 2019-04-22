package com.ea.wwce.automation.gcn.tests;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

public class GcnGcPaymentSettingsUiTest4 extends GcnBaseTest{
	
	public static Logger logger=Logger.getLogger(GcnGcPaymentSettingsUiTest4.class);
	
	/*This Test Class is to verify the warning messages in Game Changer profile page/profile page/ payments settings page.
	 * when mandatory fields like paypal emailid,country,tax id is NOT updated/ or left blank and trying to Save the Page.
	 * 
	 * TC: 
	 * 
	 * */

	
	@Test(priority=1)
	public void gcnGcPaymentSettingsUiTest4(ITestContext context) throws InterruptedException {
		Map<String,Object> testData=new HashMap<String,Object>();
		String testID="18536,20734,20752,20754,20755,20756,20757,20758";
		
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);
		
		DataProviders dataProvider=(DataProviders)context.getAttribute("dataProvider");
		testData=dataProvider.getTestData(testID);
		
		gcnGcHomePage.launchGcAndSignIn();
		
		gcnGcLoginPage.verifyLogin(testData.get("usernihalgc2").toString(),testData.get("passnihalgc2").toString());
		
		gcOpportunityPage=gcnGcPage.goToOpportunitiesPage();
		
		assertTrue(gcOpportunityPage.verifyNavigationToJoinedOpportunity(), "Error in Navigating to Joined Opp");
				
		gcOppDetailsPage.goToPaymentSettings();
		
		assertTrue(gcnGcPaymentSettingsPage.verifyNavigationToPaymentSettingsPage(), "Error landing into payment settings page");
		
		//check navigation to Legal Notices Page.
		gcnGcPage.clickOnFooterLegalNotices();
		assertTrue(gcLegalNoticesPage.verifyNavigationToLegalNoticesPage(), "Error landing into Legal Notices Page");

		gcLegalNoticesPage.navigateBackTo();

		// Check navigating to EULA page
		gcnGcPage.clickOnFooterEulaLink();
		assertTrue(gcEulaPage.verifyNavigationToEulaPage(), "Error landing into EULA Page");

		gcEulaPage.navigateBackTo();

		// Check navigation to Service Update page.
		gcnGcPage.clickOnFooterServiceUpdatesLink();
		assertTrue(gcServiceUpdatesPage.verifyNavigationToServiceUpdatePage(), "Error Landing into Service Update Page");

		gcServiceUpdatesPage.navigateBackTo();

		// Check Navigation to Terms of Services Page
		gcnGcPage.clickOnFooterTermsLink();
		assertTrue(gcTermsPage.verifyNavigationToTermsPage(), "Error Landing into Terms of Services Page");

		gcTermsPage.navigateBackTo();

		// Check Navigation to Privacy and Cookie Policy
		gcnGcPage.clickOnFooterPrivacyLink();
		assertTrue(gcPrivacyPage.verifyNavigationToPrivacyPage(), "Error Landing into Privacy Page");
		
		gcPrivacyPage.navigateBackTo();
		
		// check navigation to Home page.
		gcnGcPage.clickOnFooterHome();
		assertTrue(gcnGcPage.verifyNavigationToGcPage(), "Error Navigating to Home Page");

		gcnGcPage.navigateBackTo();

		
		assertAll();	
		
	}
	
}
