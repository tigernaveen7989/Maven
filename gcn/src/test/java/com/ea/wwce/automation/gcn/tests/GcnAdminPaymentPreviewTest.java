package com.ea.wwce.automation.gcn.tests;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

public class GcnAdminPaymentPreviewTest extends GcnBaseTest{

	public static Logger logger=Logger.getLogger(GcnAdminPaymentPreviewTest.class);
	
	/*This class is to check Opportunity payment and Preview screen.*/
		
	/*TC: 17672 - Verify the Opportunity name in the confirm payee details page.
	 *TC: 17679 - Verify the Next button in the Confirm Payee details page.
	 *TC: 17677 - Verify the Back button functionality in the Confirm Payee details page.
	 *TC: 17683 - Verify the Opportunity name in the Payment Review page.
	 *TC: 17684 - Verify the Payment description field in the Payment Review page.
	 *TC: 17689 - Verify the Back button functionality in the Payment review page
	 *TC: 
	 *
	 * */
	

	@Test
	public void verifyOppPaymentsPreviewPage(ITestContext context) {
		
		Map<String, Object> testData = new HashMap<String, Object>();
		String testID="17672,17673,17683,17684,17689";

		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);
		
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);
		
		gcnAdminHomePage.launchAdminAndSignIn();
		
		gcnAdminPage=gcnAdminLoginPage.verifyLogin(testData.get("username").toString(), testData.get("password").toString());
				
		gcnAdminPage.navToAdminOpportunityPage();

		/*adminOpportunityListPage.isOppDisplayed();
		String opn1=adminOpportunityListPage.getOpportunityName();*/
		
		adminOpportunityListPage.goToOpportunityByTitle("Star Wars Heroes_Opportunity_5995");
		adminOppDetailsPage.clickOnPaymentsLink();
		assertTrue(adminOppPaymentsPage.verifyNavigationToPaymentsPage(), "Error landing into Payments Page");
		
		String opn2 = adminOppPaymentsPage.getOppName();
		//assertTrue(adminOppPaymentsPage.checkOppNameFromOppListPage(), "Opportunity Name Mismatch");
		adminOppPaymentsPage.payAll();

		//assertEquals(opn1, opn2, "Opportunity Name MisMatch");
		//adminOppPaymentsPage.getPaymentValue();
		
		assertTrue(adminOppPaymentsReviewPage.verifyPaymentPreivewPageUi(), "UI elements in Payments Preivew page missing");
		
		assertTrue(adminOppPaymentsReviewPage.checkDefPayDescMsg(), "Incorrect default text");
		
		adminOppPaymentsReviewPage.setPayDesc("payments for opportunity");
		
		String opn3=adminOppPaymentsReviewPage.getOppNameInPreviewPage();

		assertEquals(opn2, opn3, "Opportunity Name MisMatch");
		
		adminOppPaymentsPage=adminOppPaymentsReviewPage.clickOnBackBtn();
		assertTrue(adminOppPaymentsPage.verifyNavigationToPaymentsPage(), "Error landing into Payments Page");
		//assertTrue(adminOppPaymentsPage.isCancelBtnDisplayed(), "Opportunity Payments Page not Loaded");
	
		assertAll();
	}
	

}
