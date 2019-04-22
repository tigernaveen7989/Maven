package com.ea.wwce.automation.gcn.tests;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

public class GcnAdminPaymentSingleTest extends GcnBaseTest{

	public static Logger logger=Logger.getLogger(GcnAdminPaymentSingleTest.class);
	
	/*This class is to check Opportunity Payments to single user.*/
	

	
	@Test
	public void paymentSingleTest(ITestContext context) throws InterruptedException {
		
		/*TC: 17675 - Verify the details of Payment label in the Confirm Payee details Page
		 *TC: 17690 - Verify the Payment total label details in the Payment Review page 
		 *TC: 17661 - Verify the functionality of Individual selection of Game changers using Select check box
		 * 
		 * */
		
		
		Map<String, Object> testData = new HashMap<String, Object>();
		String testID="17674,17678,17686,17690";
	
			
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);
		
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);
		
		gcnAdminHomePage.launchAdminAndSignIn();
		
		gcnAdminPage=gcnAdminLoginPage.verifyLogin(testData.get("username").toString(), testData.get("password").toString());
		
		gcnAdminPage.navToAdminOpportunityPage();
		
		assertTrue(adminOpportunityListPage.verifyNavigationToOppoListPage(), "Error loading Opportunity list page");
		
		adminOpportunityListPage.goToOpportunityByTitle("Star Wars Heroes_Opportunity_5995");

		adminOppDetailsPage.clickOnPaymentsLink();
		assertTrue(adminOppPaymentsPage.verifyNavigationToPaymentsPage(), "Error landing into Payments Page");
			
		// Verification for Top menu links
		assertTrue(gcnAdminPage.verifyAvailabilityOfMenuLinks(),"All Menu Links Not Displayed");
		
		assertTrue(adminOppPaymentsPage.verifySinglePayment(), "Error in Single Payment");
		
		assertTrue(adminOppPaymentsReviewPage.verifyNavigationToPaymentsReviewPage(), "Error landing into Payment Review Page");
		
		assertTrue(adminOppPaymentsReviewPage.checkDefPayDescMsg(), "Incorrect default text");
		
		adminOppPaymentsReviewPage.setPayDesc("payments for opportunity");
		
		assertTrue(adminOppPaymentsReviewPage.checkPaymentCurrencyType(), "Currency Type is not USD");
		
		/*adminOppPaymentsReviewPage.clickOnSubmitBtn();
		assertTrue(adminOppPaymentsPage.isPaySuccessMsgDisplayed(), "Payment Message");
		
		assertEquals(adminOppPaymentsPage.paySuccessMsgContent(), GcnDataConstants.paySuccessM, "Incorrect Success Message");
		
		Thread.sleep(3000);*/
		assertAll();
	}
		
}
