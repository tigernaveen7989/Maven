package com.ea.wwce.automation.gcn.tests;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

public class GcnAdminPaymentsTestUi extends GcnBaseTest{
	
	public static Logger logger=Logger.getLogger(GcnAdminPaymentsTestUi.class);
	
	/*This class is to check the below test cases related to payments page.
	 * 
	 * To check the Payment status of users
	 * */
	
	@Test
	public void verifyOppPaymentsPageUi(ITestContext context) throws InterruptedException {
		
		Map<String, Object> testData = new HashMap<String, Object>();
		//String testID="";
		
		String testID="19889,21243,17644,17651";
		
		context.setAttribute("testcase_id", testID);
		logger.info("gvalidating Verify Login Test" + testID);
		
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);
		
		gcnAdminHomePage.launchAdminAndSignIn();
		gcnAdminLoginPage.verifyLogin(testData.get("username").toString(), testData.get("password").toString());
		gcnAdminPage.navToAdminOpportunityPage();
		adminOppDetailsPage=adminOpportunityListPage.goToAnyOpportunity();

		adminOppPaymentsPage=adminOppDetailsPage.clickOnPaymentsLink();
		assertTrue(adminOppPaymentsPage.verifyNavigationToPaymentsPage(), "Error landing into Payments Page");
		assertTrue(adminOppPaymentsPage.verifyPaymentPageUi(), "Ui Elements Missing in Payments Page");
		
		//  Check for Footer information
		
		assertTrue(adminOppPaymentsPage.isEaAdminFooterTextDisplayed(), "Footer Text Not Displayed");
		assertTrue(adminOppPaymentsPage.isEaFooterTextDisplayed(), "Footer Text Powered by is Not Displayed");
		assertTrue(adminOppPaymentsPage.isEaWhiteLogoDisplayed(), "Footer EA Logo is Not Displayed");
			
		// Get user name with Valid tax documents.
		adminOppPaymentsPage.getTaxDocStatus("Valid");
		
		adminOppPaymentsPage.checkPaymentStatus("No PayPal Settings");
		
		adminOppPaymentsPage.checkPaymentStatus("Payment Successful");
		
		
		Thread.sleep(3000);
		assertAll();
	
	}
	
}
