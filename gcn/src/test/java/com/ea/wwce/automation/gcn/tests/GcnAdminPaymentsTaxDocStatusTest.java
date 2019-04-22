package com.ea.wwce.automation.gcn.tests;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;
import com.ea.wwce.automation.gcn.config.GcnDataConstants;

import io.qameta.allure.Description;

public class GcnAdminPaymentsTaxDocStatusTest extends GcnBaseTest{
	
	public static Logger logger=Logger.getLogger(GcnAdminPaymentsTaxDocStatusTest.class);
	
	/*This class is to check status of Tax Documents in Payments Page  
	 * 
	 * */

	@Test
	@Description("Verify the Status of Tax Documents and payment currency type")
	public void verifyOppPaymentsTaxDocStatus(ITestContext context) throws InterruptedException {
		
		Map<String, Object> testData = new HashMap<String, Object>();
		String testID="17657,17659,17660";
				
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);
		
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);
		
		gcnAdminHomePage.launchAdminAndSignIn();
		gcnAdminPage=gcnAdminLoginPage.verifyLogin(testData.get("username").toString(), testData.get("password").toString());	
		gcnAdminPage.navToAdminOpportunityPage();

		adminOppDetailsPage=adminOpportunityListPage.goToOpportunityByTitle("Star Wars Heroes_Opportunity_5995");
		adminOppDetailsPage.clickOnPaymentsLink();
		assertEquals(adminOppPaymentsPage.getPageTitle(), GcnDataConstants.actAdminPaymentPageTitle, "Title MisMatch");
			
		// Get user name with Valid tax documents.
		assertTrue(adminOppPaymentsPage.getTaxDocStatus("Valid"), "No User with Valid Tax Document");
		
		// Check Payments are made in $ only.
		assertTrue(adminOppPaymentsPage.checkPaymentCurrencyType(), "Payment not Made in Dollors");
		
		assertFalse(adminOppPaymentsPage.verifyCheckBoxForNonValidTaxDocUsers(), "User with Non-Valid Tax Doc can be Selected");
		
		/*//adminOppPaymentsPage.noOfUsersToBePaid();
		adminOppPaymentsPage.payAll();
		
		adminOppPaymentsPage.getPaymentValue();
		
		// Uncheck all and see total payable amount is Zero.
		adminOppPaymentsPage.clickSelectAllChkBox();
	
		assertEquals(Integer.toString(adminOppPaymentsPage.getPaymentValue()), "0", "Non Zero after uncheck All");*/
		
		adminOppPaymentsPage.clickOnCancel();
		assertTrue(adminOpportunityListPage.verifyNavigationToOppoListPage(), "Not returned to Opportunity List Page");
		

		assertAll();
	
	}
	
}
