package com.ea.wwce.automation.gcn.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

public class GcnAdminBudgetEstimateTest4 extends GcnBaseTest{
	
	/*This class is to check the UI Elements in Budget Estimation Screen of Opportunities.
	 * 
	 * 
	 * */

	
	@Test
	public void verifyOppBudgetEstimatePageTest4(ITestContext context) throws InterruptedException {
		
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
				
		assertTrue(adminOppBudgetPage.verifyFullNameByUserName(testData.get("useridgowri").toString(), testData.get("fullNameGowri").toString()), "Full Name Not Displayed");
		
		

		adminOppBudgetPage.goToUserProfileByUserName(testData.get("useridgowri").toString());
		
		//assertTrue(testData.get("useridgowri").toString().equalsIgnoreCase(adminViewUserProfile.getUserNameLabelText()), "User Name Mismatch");
		
		assertEquals(adminViewUserProfile.getFullName(), testData.get("fullNameGowri").toString(), "Full Name Mismatch");
		adminViewUserProfile.navigateBackTo();
		
		
		
		
		assertAll();
	}
	

}
