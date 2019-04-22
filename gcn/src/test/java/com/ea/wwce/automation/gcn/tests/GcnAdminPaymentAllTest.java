package com.ea.wwce.automation.gcn.tests;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;
import com.ea.wwce.automation.gcn.config.GcnDataConstants;

public class GcnAdminPaymentAllTest extends GcnBaseTest{

	public static Logger logger=Logger.getLogger(GcnAdminPaymentAllTest.class);
	
	/*This class is to check the Payments to All scenario.
	 * 
	 * TC: 17667 - Verify the functionality of Next button in Opportunity Payee page
	 * TC: 17692 - Verify the Confirmation message for Successful payment
	 * TC: 17691 - Verify the functionality of SUBMIT PAYMENT button
	 * TC: 17679 - Verify the Next button in the Confirm Payee details page
	 * 
	 * */
	
	
	@Test
	public void paymentTest(ITestContext context) throws InterruptedException {
		
		Map<String, Object> testData = new HashMap<String, Object>();
		String testID="17667,17692,17691,17679";
		
		// 17667,17692,17691,17679,
		
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);
				
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);
		
		gcnAdminHomePage.launchAdminAndSignIn();
		
		gcnAdminPage=gcnAdminLoginPage.verifyLogin(testData.get("username").toString(), testData.get("password").toString());
				
		gcnAdminPage.navToAdminOpportunityPage();

		adminOpportunityListPage.goToOpportunityByTitle("title");
		
		assertTrue(adminOppDetailsPage.verifyNavigationToOppoDetailsPage(), "Error Landing into Opportunity Details Page");
		
		adminOppDetailsPage.clickOnPaymentsLink();
		
		assertTrue(adminOppPaymentsPage.verifyNavigationToPaymentsPage(), "Error landing into Opportunity Payments Page");
		
		// Get user name with Valid tax documents.
		adminOppPaymentsPage.getTaxDocStatus("Valid");
		// Check Payments are made in $ only.
		assertTrue(adminOppPaymentsPage.checkPaymentCurrencyType(), "Payment not Made in Dollors");
	
		adminOppPaymentsPage.payAll();
		
		adminOppPaymentsReviewPage=adminOppPaymentsPage.goToPaymentPreview();
				
		assertTrue(adminOppPaymentsReviewPage.verifyPaymentPreivewPageUi(), "UI elements in Payments Preivew page missing");
		
		adminOppPaymentsReviewPage.clickOnSubmitBtn();
		assertTrue(adminOppPaymentsPage.isPaySuccessMsgDisplayed(),"Payment Success Msg Not Displayed");
		
		assertEquals(adminOppPaymentsPage.paySuccessMsgContent(), GcnDataConstants.paySuccessM, "Incorrect Success Message");
				
		Thread.sleep(2000);
		assertAll();
	}
	
	
}
