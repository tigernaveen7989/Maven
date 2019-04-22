package com.ea.wwce.automation.gcn.tests;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;
import com.ea.wwce.automation.gcn.config.GcnDataConstants;

public class GcnAdminPaymentByUserTest extends GcnBaseTest{
	
	public static Logger logger=Logger.getLogger(GcnAdminPaymentByUserTest.class);
	
	/*This Test Class is to Verify the Payment Status Message for Game Changers 
	after payment is successful from the tooltip.*/
	

	@Test
	public void getPaymentStatus(ITestContext context) throws InterruptedException {
		Map<String, Object> testData = new HashMap<String, Object>();
		String testID="global";
		
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);
				
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);
		
		gcnAdminHomePage.launchAdminAndSignIn();
		
		gcnAdminPage=gcnAdminLoginPage.verifyLogin(testData.get("username").toString(), testData.get("password").toString());
				
		gcnAdminPage.navToAdminOpportunityPage();
		
		assertTrue(adminOpportunityListPage.verifyNavigationToOppoListPage(), "Error landing into Opportunity List Page");
		
		Thread.sleep(4000);

		adminOpportunityListPage.goToOpportunityByTitle("title");

		assertTrue(adminOppDetailsPage.verifyNavigationToOppoDetailsPage(), "Error Landing into Opportunity Details Page");
		
		adminOppDetailsPage.clickOnPaymentsLink();
		assertTrue(adminOppPaymentsPage.verifyNavigationToPaymentsPage(), "Error Landing into Opportunit Payments Page");
		// payment process
		
		adminOppPaymentsPage.verifySelectingCheckBoxByUserName(testData.get("nihaluid").toString());
		assertTrue(adminOppPaymentsPage.isNextBtnDisplayed(), "Next button is Not Displayed");
		
		adminOppPaymentsReviewPage=adminOppPaymentsPage.goToPaymentPreview();
		assertTrue(adminOppPaymentsReviewPage.verifyNavigationToPaymentsReviewPage(), "Error Landing into Payments Review Page");
		
		
		assertTrue(adminOppPaymentsReviewPage.checkDefPayDescMsg(), "Default Payment Desc is Not Displayed");
		adminOppPaymentsReviewPage.setPayDesc("payments for opportunity");
		
		adminOppPaymentsReviewPage.clickOnSubmitBtn();
		assertTrue(adminOppPaymentsPage.isPaySuccessMsgDisplayed(), "Payment Message");

		assertEquals(adminOppPaymentsPage.paySuccessMsgContent(), GcnDataConstants.paySuccessM, "Incorrect Success Message");
		
		assertTrue(adminOppPaymentsPage.verifyNavigationToPaymentsPage(), "Error landing into Opp Payments Page");
		
		adminOppPaymentsPage.checkPaymentStatusToolTip();
		
		adminOppPaymentsPage.checkPaymentStatusByUserName(testData.get("nihaluid").toString());
		
		Thread.sleep(2000);
		
		// check payment status
		
		assertAll();
		
	}
	

}
