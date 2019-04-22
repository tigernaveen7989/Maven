package com.ea.wwce.automation.gcn.tests;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

public class GcnGcPaymentSettingsUiTest5 extends GcnBaseTest{
	
	public static Logger logger=Logger.getLogger(GcnGcPaymentSettingsUiTest5.class);
	
	/*This Test Class is to verify the links available in the Footer section.
	 * 
	 * TC: 18553 - Verify the Footer in the My Profile & Enter your Payment details Screens
	 * 
	 * */
	

	
	@Test(priority=1)
	public void gcnGcPaymentSettingsUiTest5(ITestContext context) throws InterruptedException {
		Map<String,Object> testData=new HashMap<String,Object>();
		String testID="18553,20727,20729,20730,20731,20732,20733";
		
		//18553
				
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);
	
		DataProviders dataProvider=(DataProviders)context.getAttribute("dataProvider");
		testData=dataProvider.getTestData(testID);
		
		gcnGcHomePage.launchGcAndSignIn();
		
		gcnGcPage=gcnGcLoginPage.verifyLogin(testData.get("usernihalgc2").toString(), testData.get("passnihalgc2").toString());

		gcnGcPage.clickOnProfileLink();
		gcnGcProfilePage.clickOnPaymentSettingsLink();
		assertTrue(gcnGcPaymentSettingsPage.verifyNavigationToPaymentSettingsPage(), "Error landing into Payment Settings Page");
		
		// check navigation to Home page.
		gcnGcPage.clickOnFooterHome();
		assertTrue(gcnGcPage.verifyNavigationToGcPage(), "Error Navigating to Home Page");
		
		gcnGcPage.navigateBackTo();
		assertTrue(gcnGcPaymentSettingsPage.verifyNavigationToPaymentSettingsPage(), "Error landing into Payment Settings Page");
		
		//check navigation to Legal Notices Page.
		gcnGcPage.clickOnFooterLegalNotices();
		assertTrue(gcLegalNoticesPage.verifyNavigationToLegalNoticesPage(), "Error landing into Legal Notices Page");
		
		gcLegalNoticesPage.navigateBackTo();
		assertTrue(gcnGcPaymentSettingsPage.verifyNavigationToPaymentSettingsPage(), "Error landing into Payment Settings Page");
		// Check navigating to EULA page
		gcnGcPage.clickOnFooterEulaLink();
		assertTrue(gcEulaPage.verifyNavigationToEulaPage(), "Error landing into EULA Page");
		
		gcEulaPage.navigateBackTo();
		assertTrue(gcnGcPaymentSettingsPage.verifyNavigationToPaymentSettingsPage(), "Error landing into Payment Settings Page");
		
		// Check navigation to Service Update page.
		gcnGcPage.clickOnFooterServiceUpdatesLink();
		assertTrue(gcServiceUpdatesPage.verifyNavigationToServiceUpdatePage(), "Error Landing into Service Update Page");
		
		gcServiceUpdatesPage.navigateBackTo();
		assertTrue(gcnGcPaymentSettingsPage.verifyNavigationToPaymentSettingsPage(), "Error landing into Payment Settings Page");
		
		// Check Navigation to Terms of Services Page
		gcnGcPage.clickOnFooterTermsLink();
		assertTrue(gcTermsPage.verifyNavigationToTermsPage(), "Error Landing into Terms of Services Page");
		
		gcTermsPage.navigateBackTo();
		assertTrue(gcnGcPaymentSettingsPage.verifyNavigationToPaymentSettingsPage(), "Error landing into Payment Settings Page");
		// Check Navigation to Privacy and Cookie Policy
		gcnGcPage.clickOnFooterPrivacyLink();
		assertTrue(gcPrivacyPage.verifyNavigationToPrivacyPage(), "Error Landing into Privacy Page");
		
		gcPrivacyPage.navigateBackTo();
		assertTrue(gcnGcPaymentSettingsPage.verifyNavigationToPaymentSettingsPage(), "Error landing into Payment Settings Page");
		
		assertAll();	
		
	}
	

}
