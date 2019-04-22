package com.ea.wwce.automation.gcn.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

public class GcnAdminBudgetEstimateTest6 extends GcnBaseTest{
	
	/*This class is to check the Footer Section Budget Estimation Screen of Opportunities.
	 * 
	 * 
	 * */

	
	@Test
	public void verifyOppBudgetEstimatePageTest(ITestContext context) throws InterruptedException {
		
		Map<String, Object> testData = new HashMap<String, Object>();
		String testID="global";
		
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);
		
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);
		
		gcnAdminHomePage.launchAdminAndSignIn();
		
		gcnAdminPage=gcnAdminLoginPage.verifyLogin(testData.get("username").toString(), testData.get("password").toString());
		

		gcnAdminPage.navToAdminOpportunityPage();

		adminOppDetailsPage=adminOpportunityListPage.goToOpportunityByTitle("");
				
		// Navigate to Budget Estimation Screen.
		adminOppBudgetPage=adminOppDetailsPage.clickBudgetEstimationLink();
		Thread.sleep(3000);
		
		assertTrue(adminOppBudgetPage.isDefaultMarkAsStipendForJoined(), "Default not Flagged as Stipend");
		
		assertTrue(adminOppBudgetPage.isNotJoinedUserSelectable(), "Not Joined check box is Selectable");
		
		assertTrue(adminOppBudgetPage.verifyAllJoinedUserSelected(), "Joined check box is Not Selectable");
		
		assertTrue(adminOppBudgetPage.isDefaultMarkForNotJoined(), "Not Joined user should not be Marked for Payment");
		
		
	
		Thread.sleep(3000);
		assertAll();
	}

}
