package com.ea.wwce.automation.gcn.tests;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;


public class GcnGcLoginTest extends GcnBaseTest{

	public static Logger logger=Logger.getLogger(GcnGcLoginTest.class);
	
	/*This class is to check valid Login Scenario for Game Changer.
	 * 
	 * TC: 16171 - Verify Game Changer is able to login into GCN application
	 * 
	 * */
	
	
	@Test
	public void gcnGcValidLoginTest(ITestContext context) throws InterruptedException {
		
		Map<String, Object> testData = new HashMap<String, Object>();
		String testID="16171";
		
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);
				
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);
		
		gcnGcHomePage.launchGcAndSignIn();
				
		gcnGcLoginPage.verifyLogin(testData.get("username").toString(), testData.get("password").toString());
		
		assertTrue(gcnGcPage.verifyNavigationToGcPage(), "Game Changer Login Failed.");

		assertTrue(gcnGcPage.verifyGcPageTitle(), "GC Page Title Mismatch");
		
		assertAll();			
		
	}
	
}
