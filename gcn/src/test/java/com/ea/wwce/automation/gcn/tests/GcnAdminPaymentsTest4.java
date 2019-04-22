package com.ea.wwce.automation.gcn.tests;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

public class GcnAdminPaymentsTest4 extends GcnBaseTest{
	
	public static Logger logger=Logger.getLogger(GcnAdminPaymentsTest4.class);
	
	/*This class is to check the below test cases related to payments page.
	 * TC: 17687 - Verify the details of Payment label  in the Payment Review page
	 * TC: 17688 - Verify the details of Payment review in the Payment Review page
	 * 
	 * */
	
	
	@Test
	public void verifyOppPaymentsPage2(ITestContext context) throws InterruptedException {
		
		Map<String, Object> testData = new HashMap<String, Object>();
		//String testID="17687,17688";

		String testID="17687,17688";
		
		context.setAttribute("testcase_id", testID);
		logger.info("gvalidating Verify Login Test" + testID);
		
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);
		
		gcnAdminHomePage.launchAdminAndSignIn();
		
		gcnAdminPage=gcnAdminLoginPage.verifyLogin(testData.get("username").toString(), testData.get("password").toString());
		
		gcnAdminPage.navToAdminOpportunityPage();

		adminOppDetailsPage=adminOpportunityListPage.goToOpportunityByTitle("");

		adminOppPaymentsPage=adminOppDetailsPage.clickOnPaymentsLink();
		assertTrue(adminOppPaymentsPage.verifyNavigationToPaymentsPage(), "Error landing into Payments Page");
		
			
		// Get user name with Valid tax documents.
		adminOppPaymentsPage.getTaxDocStatus("Valid");
		
		// Check Payments are made in $ only.
		assertTrue(adminOppPaymentsPage.checkPaymentCurrencyType(), "Payment not Made in Dollors");
		
		int n1=adminOppPaymentsPage.noOfUsersToBePaid();
		adminOppPaymentsPage.payAll();
		
		int n2=adminOppPaymentsPage.getPaymentValue();
		Thread.sleep(3000);
		adminOppPaymentsReviewPage=adminOppPaymentsPage.goToPaymentPreview();
		Thread.sleep(3000);
		assertTrue(adminOppPaymentsReviewPage.checkPaymentCurrencyType(), "Payment not Made in Dollors");
			
		int n3=adminOppPaymentsReviewPage.checkPaySummary();
		
		Assert.assertEquals(n2, n3);
		Assert.assertEquals(n1, adminOppPaymentsReviewPage.getTotalUsers());
		
		Thread.sleep(3000);
		assertAll();
	
	}
	
}
