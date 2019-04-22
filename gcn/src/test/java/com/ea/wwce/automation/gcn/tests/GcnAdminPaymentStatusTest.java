package com.ea.wwce.automation.gcn.tests;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;
import com.ea.wwce.automation.gcn.config.GcnDataConstants;

import io.qameta.allure.Description;

public class GcnAdminPaymentStatusTest extends GcnBaseTest{
	
	public static Logger logger=Logger.getLogger(GcnAdminPaymentStatusTest.class);
	
	/*This Test Class is to Verify the Payment Status Message for Game Changers 
	after payment is successful from the tooltip.*/

	@Test()
	@Description("Verify Payment Status and Success Message")
	public void getPaymentStatus(ITestContext context) throws InterruptedException {
		Map<String, Object> testData = new HashMap<String, Object>();
		String testID="17692,17691";
		
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);
		
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);
		
		gcnAdminHomePage.launchAdminAndSignIn();
		gcnAdminPage=gcnAdminLoginPage.verifyLogin(testData.get("username").toString(), testData.get("password").toString());

		gcnAdminPage.navToAdminOpportunityPage();
		assertTrue(adminOpportunityListPage.verifyNavigationToOppoListPage(), "Error landing into Opportunity Page");
		
		adminOpportunityListPage.goToOpportunityByTitle("Star Wars Heroes_Opportunity_5995");
		assertTrue(adminOppDetailsPage.verifyNavigationToOppoDetailsPage(), "Error landing into Opportunity Details Page");
		
		adminOppDetailsPage.clickOnPaymentsLink();
		assertTrue(adminOppPaymentsPage.verifyNavigationToPaymentsPage(), "Error landing into Payments Page");
	
		// payment process
		
		assertTrue(adminOppPaymentsPage.verifySinglePayment(),"Error processing Single payment");
				
		adminOppPaymentsReviewPage.clickOnSubmitBtn();
		
		assertTrue(adminOppPaymentsPage.isPaySuccessMsgDisplayed(), "Payment Message");
		
		assertEquals(adminOppPaymentsPage.paySuccessMsgContent(), GcnDataConstants.paySuccessM, "Incorrect Success Message");
		
		assertTrue(adminOppPaymentsPage.verifyNavigationToPaymentsPage(), "Payments Page loaded successfully");
				
		//Check Reference NO of Payment
		adminOppPaymentsPage.checkPaymentStatusToolTip();
		
		
		// check payment status
		
		assertAll();
		
	}
	


}
