package com.ea.wwce.automation.gcn.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

public class GcnAdminBudgetEstimateTest3 extends GcnBaseTest{
	
	/*This class is to check the UI Elements in Budget Estimation Screen of Opportunities.
	 * 
	 * 
	 * */

	
	@Test
	public void verifyOppBudgetEstimatePageTest3(ITestContext context) throws InterruptedException {
		
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

		adminOppBudgetPage.getUserNames();
		assertTrue(adminOppBudgetPage.isUserNameSorted(),"User Name List Not Sorted");
		
		adminOppBudgetPage.getStatusAsList();
		assertTrue(adminOppBudgetPage.isStatusSorted(), "Status Not Sorted");
		
		adminOppBudgetPage.getFullNameList();
		assertTrue(adminOppBudgetPage.isFullNameSorted(),"Full Name Not Sorted");
		
		assertTrue(adminOppBudgetPage.isFullNameReverseSorted(),"Full Name Not Reverse Sorted");
		
		assertAll();
	}
	
	
}
