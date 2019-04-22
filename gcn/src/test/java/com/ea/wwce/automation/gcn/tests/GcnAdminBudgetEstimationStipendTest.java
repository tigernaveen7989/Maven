package com.ea.wwce.automation.gcn.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;
import com.ea.wwce.automation.gcn.config.GcnDataConstants;

public class GcnAdminBudgetEstimationStipendTest extends GcnBaseTest{
	
	/*This class is to Budget Estimation for Opportunity .
	 * estimation for all stipend.
	 * 
	 * */

	
	@Test
	public void verifyOppBudgetEstimation(ITestContext context) throws InterruptedException {
		
		Map<String, Object> testData = new HashMap<String, Object>();

		String testID="20432,20434,20435,20436,20447,20458,20504,42482";

		
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);
		
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);	
		
		gcnAdminHomePage.launchAdminAndSignIn();
		
		gcnAdminPage=gcnAdminLoginPage.verifyLogin(testData.get("username").toString(), testData.get("password").toString());
		
		gcnAdminPage.navToAdminOpportunityPage();
		
		adminOppDetailsPage=adminOpportunityListPage.goToOpportunityByTitle("Anthem the Final");
				
		assertTrue(adminOppDetailsPage.verifyNavigationToOppoDetailsPage(), "Error landing into Opp Details");
		
		adminOppBudgetPage=adminOppDetailsPage.clickBudgetEstimationLink();
		
		assertTrue(adminOppBudgetPage.verifyNavigationToBudgetEstPage(),"Error landing into Budget estimation page");
		
		//assertTrue(adminOppBudgetPage.isJoinedUserSelectable(), "Error in Selecting joined User");
		
		assertTrue(adminOppBudgetPage.saveBudgetEstimationForAll(GcnDataConstants.budgetEstSuccessMsg,
				testData.get("amount_st").toString(),testData.get("paytype1").toString()), "Error saving Budget Estimation");
			
		// Check for Opportunity Name
		assertTrue(adminOppBudgetPage.verifyOpportunityDetails(), "Opportunity Details Missing.");
		
		assertTrue(adminOppBudgetPage.verifySetAmountForAll(testData.get("amount_st").toString()), "Error Setting Amount");
				
		assertAll();
	}
	

}
