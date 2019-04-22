package com.ea.wwce.automation.gcn.tests;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

public class GcnGcInvalidLoginTest extends GcnBaseTest{
	
	public static Logger logger=Logger.getLogger(GcnGcInvalidLoginTest.class);
	
	/*This class is to check the Invalid Login scenario for Game Changers Site.
	 * 
	 * TC: 16173 - Verify Game Changer login into GCN with Invalid Credentials.
	 * 
	 * */

	
	@Test
	public void gcnGcInvalidLoginTest(ITestContext context) {
		
		Map<String, Object> testData = new HashMap<String, Object>();
		String testID="16173";
		
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);
		
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);
		
		gcnGcHomePage.launchGcAndSignIn();
		
		assertTrue(gcnGcLoginPage.verifyInvalidLogin(testData.get("invalidusername").toString(), testData.get("password").toString()),"Message for wrong format of emailid");
				
		assertTrue(gcnGcLoginPage.verifyInvalidLogin(testData.get("invalidusername1").toString(), testData.get("invalidpassword").toString()),"Message for wrong emailid or password");
				
		assertTrue(gcnGcLoginPage.verifyInvalidLogin(testData.get("invalidusername1").toString(), testData.get("password").toString()),"Message for wrong emailid or password");
				
		assertAll();
						
		
	}
	

}
