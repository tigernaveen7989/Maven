package com.ea.wwce.automation.gcn.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

public class GcnAdminBudgetEstimateTestUi extends GcnBaseTest{
	
	/*This class is to check the Footer Section Budget Estimation Screen of Opportunities.
	 * 
	 * 
	 * */

	
	@Test
	public void verifyOppBudgetEstimatePageTestUi(ITestContext context) throws InterruptedException {
		
		Map<String, Object> testData = new HashMap<String, Object>();
		String testID="20459,20513,42468,42470,42471,42484";
		
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);
		
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);
		
		gcnAdminHomePage.launchAdminAndSignIn();
		
		gcnAdminPage=gcnAdminLoginPage.verifyLogin(testData.get("username").toString(), testData.get("password").toString());
		

		gcnAdminPage.navToAdminOpportunityPage();

		adminOppDetailsPage=adminOpportunityListPage.goToOpportunityByTitle("Madden NFL 18_Opportunity_4719");
				
		// Navigate to Budget Estimation Screen.
		adminOppBudgetPage=adminOppDetailsPage.clickBudgetEstimationLink();
		Thread.sleep(3000);
		
		/*assertTrue(adminOppPaymentsPage.isEaAdminFooterTextDisplayed(), "Footer Text Not Displayed");
		assertTrue(adminOppPaymentsPage.isEaFooterTextDisplayed(), "Footer Text Powered by is Not Displayed");
		assertTrue(adminOppPaymentsPage.isEaWhiteLogoDisplayed(), "Footer EA Logo is Not Displayed");*/
		
		/*assertTrue(adminOppBudgetPage.saveBtnDisabled(), "Save button is Not Disabled");
		assertTrue(adminOppBudgetPage.verifyEmptyUserList(), "User list is empty");*/
		
		assertTrue(adminOppBudgetPage.verifyAllJoinedUserSelected(), "All Joined User Not Selected");
		
		assertTrue(adminOppBudgetPage.verifyModifyBtn_OptionsDisplayed(), "Flagging Buttons Not Displayed.");
				
		assertAll();
	}
	
}
