package com.ea.wwce.automation.gcn.tests;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;
import com.ea.wwce.automation.gcn.config.GcnDataConstants;

public class GcnAdminPaymentMultiTest extends GcnBaseTest{

	public static Logger logger=Logger.getLogger(GcnAdminPaymentMultiTest.class);
	
	/*This class is to check the multiple payments scenario for opportunities.
	 * 
	 * TC: 17644 - Verify the functionality of Payments button in Opportunity Page
	 * TC: 17648 - Verify the navigation menu for Payee payment page
	 * */
	
	
	@Test
	public void paymentTest(ITestContext context) throws InterruptedException {
		
		Map<String, Object> testData = new HashMap<String, Object>();
		String testID="17644,17648";
		
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);
				
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);
		
		gcnAdminHomePage.launchAdminAndSignIn();
		
		gcnAdminPage=gcnAdminLoginPage.verifyLogin(testData.get("username").toString(), testData.get("password").toString());
				
		gcnAdminPage.navToAdminOpportunityPage();

		adminOpportunityListPage.goToOpportunityByTitle("");

		adminOppDetailsPage.clickOnPaymentsLink();
		
		assertTrue(adminOppPaymentsPage.verifyNavigationToPaymentsPage(), "Error landing into Payments Page");


		// Verification for Top menu links
		assertTrue(gcnAdminPage.verifyAvailabilityOfMenuLinks(),"All Menu Links Not Displayed");
		
		// UI Verifications
		
		assertTrue(adminOppPaymentsPage.verifyPaymentPageUi(), "Payments Page UI elements Missing");
						
		//Thread.sleep(5000);
		adminOppPaymentsPage.noOfUsersToBePaid();
		adminOppPaymentsPage.multiplePay();
		adminOppPaymentsPage.getPaymentValue();
		adminOppPaymentsPage.goToPaymentPreview();
		
		
		adminOppPaymentsReviewPage.clickOnSubmitBtn();
		assertTrue(adminOppPaymentsPage.isPaySuccessMsgDisplayed(), "Payment success message Not Displayed");
		assertEquals(adminOppPaymentsPage.paySuccessMsgContent(), GcnDataConstants.paySuccessM, "Incorrect Success Message");
		
		Thread.sleep(5000);
		assertAll();
	}
	
	
}
