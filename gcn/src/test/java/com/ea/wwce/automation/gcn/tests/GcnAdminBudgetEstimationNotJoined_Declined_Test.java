package com.ea.wwce.automation.gcn.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

public class GcnAdminBudgetEstimationNotJoined_Declined_Test extends GcnBaseTest{
	
	/*This class is to Budget Estimation for Opportunity .
	 * estimation for all stipend.
	 * 
	 * */

	
	@Test
	public void verifyOppBudgetEstimation(ITestContext context) throws InterruptedException {
		
		Map<String, Object> testData = new HashMap<String, Object>();

		String testID="20437,20438";

		
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);
		
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);	
		
		gcnAdminHomePage.launchAdminAndSignIn();
		
		gcnAdminPage=gcnAdminLoginPage.verifyLogin(testData.get("username").toString(), testData.get("password").toString());
		
		gcnAdminPage.navToAdminOpportunityPage();
		
		adminOppDetailsPage=adminOpportunityListPage.goToOpportunityByTitle("NBA LIVE Mobile_Opportunity_1571");
				
		assertTrue(adminOppDetailsPage.verifyNavigationToOppoDetailsPage(), "Error landing into Opp Details");
		
		adminOppBudgetPage=adminOppDetailsPage.clickBudgetEstimationLink();
		
		assertTrue(adminOppBudgetPage.verifyNavigationToBudgetEstPage(),"Error landing into Budget estimation page");
		
		assertTrue(adminOppBudgetPage.isNotJoinedUserSelectable(), "Not Joined user is Selected");
		
		assertTrue(adminOppBudgetPage.isDeclinedUserSelectable(), "Declined user is Selected");
		
		Thread.sleep(3000);
		
		assertAll();
	}
	

}
