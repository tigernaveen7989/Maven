package com.ea.wwce.automation.gcn.tests;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

public class GcnAdminOppPageTestUi extends GcnBaseTest{
	
	public static Logger logger=Logger.getLogger(GcnAdminOppPageTestUi.class);
	
	/*This class is to check the below test cases related to Opportunity list page.
	 * 
	 * 
	 * */
	
	@Test
	public void verifyOppListPageUi(ITestContext context) throws InterruptedException {
		
		Map<String, Object> testData = new HashMap<String, Object>();
		//String testID="17687,17688";
		
		String testID="42436,42499,42533,42568";
		
		context.setAttribute("testcase_id", testID);
		logger.info("gvalidating Verify Login Test" + testID);
		
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);
		
		gcnAdminHomePage.launchAdminAndSignIn();
		
		gcnAdminPage=gcnAdminLoginPage.verifyLogin(testData.get("username").toString(), testData.get("password").toString());
		
		gcnAdminPage.navToAdminOpportunityPage();
		
		assertTrue(adminOpportunityListPage.verifyNavigationToOppoListPage(), "Error in Opp list page");
		
		//  Check for Footer information
		
		assertTrue(adminOppPaymentsPage.isEaAdminFooterTextDisplayed(), "Footer Text Not Displayed");
		assertTrue(gcnAdminPage.isEaFooterTextDisplayed(), "Footer Text Powered by is Not Displayed");
		assertTrue(gcnAdminPage.isEaWhiteLogoDisplayed(), "Footer EA Logo is Not Displayed");
			
		
		assertAll();
	
	}
	
}
