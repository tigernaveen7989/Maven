package com.ea.wwce.automation.gcn.tests;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

public class GcnGcOpportunityDetailsPageTest extends GcnBaseTest{
	
	public static Logger logger=Logger.getLogger(GcnGcOpportunityDetailsPageTest.class);
	
	/*This Test Class is to verify the warning messages in Game Changer profile page/profile page/ payments settings page.
	 * when mandatory fields like paypal emailid,country,tax id is NOT updated/ or left blank and trying to Save the Page.
	 * 
	 * TC: 
	 * 
	 * */

	
	@Test(priority=1)
	public void gcnGcOpportunityDetailsTest(ITestContext context) throws InterruptedException {
		Map<String,Object> testData=new HashMap<String,Object>();
		String testID="23005,23006,23007,23008,23009,23010,23011,23012,23014,23015,23018";
		
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);
		
		DataProviders dataProvider=(DataProviders)context.getAttribute("dataProvider");
		testData=dataProvider.getTestData(testID);
		
		gcnGcHomePage.launchGcAndSignIn();
		
		gcnGcLoginPage.verifyLogin(testData.get("usernihalgc2").toString(),testData.get("passnihalgc2").toString());
		
		gcOpportunityPage=gcnGcPage.goToOpportunitiesPage();
		
		assertTrue(gcOpportunityPage.verifyNavigationToJoinedOpportunity(), "Error in Navigating to Joined Opp");
		
		assertTrue(gcOppDetailsPage.verifyPaySettingsMsgDisplayed(), "Payment Settings Caution MSG not Displayed.");
		
		//assertFalse(gcOpportunityPage.verifyNavigationToNotJoinedOpp(), "Error in Navigating to Not Joined Opp");
						
		assertAll();	
		
	}
	
}
