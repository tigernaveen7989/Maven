package com.ea.wwce.automation.gcn.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

public class GcnAdminBudgetEstimationUserTest extends GcnBaseTest{
	
	/*This class is to Budget Estimation for Opportunity .
	 * estimation for all stipend.
	 * 
	 * */

	
	@Test
	public void verifyOppBudgetEstimationUserTest(ITestContext context) throws InterruptedException {
		
		Map<String, Object> testData = new HashMap<String, Object>();

		String testID="20442,42477,42480";

		
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
		
		assertTrue(adminOppBudgetPage.verifyFullNameByUserName(testData.get("uid").toString(),
				testData.get("fullname_roman").toString()), "Full Name and UserName Mismatch");
		
		assertAll();
	}
	

}
