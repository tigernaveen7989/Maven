package com.ea.wwce.automation.gcn.tests;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

public class GcnGcLogoutTest extends GcnBaseTest{

	public static Logger logger=Logger.getLogger(GcnGcLogoutTest.class);
	
	/*This class is to check Logout scenario for Game Changer.
	 * 
	 * TC: 16172 - Verify Game Changer is able to logout from GCN application
	 * 
	 * */
	
	
	@Test
	public void gcLogout(ITestContext context) {
		Map<String, Object> testData = new HashMap<String, Object>();
		String testID="16172";
		
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);
				
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);
		
		gcnGcHomePage.launchGcAndSignIn();
		
		gcnGcPage=gcnGcLoginPage.verifyLogin(testData.get("username").toString(), testData.get("password").toString());
		
		gcnGcPage.clickOnLogout();
		assertTrue(gcnGcHomePage.verifyNavigationToGcHomePage(), "Error Landing into Game Changer Home Page Site");
		
		assertAll();
		
	}
		
}
