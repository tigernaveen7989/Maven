package com.ea.wwce.automation.gcn.tests;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

public class GcnAdminInvalidLoginTest extends GcnBaseTest{
	
	public static Logger logger=Logger.getLogger(GcnAdminInvalidLoginTest.class);
	
	/*This class is to check the Invalid Login Scenario for GCN Admin Site.
	 * TC: 16176 - Verify Admin login into GCN Admin Application with Invalid credentials.
	 * 
	 * 
	 * */

	
	@Test
	public void gcnAdminInvalidLoginTest(ITestContext context){
		
		Map<String, Object> testData = new HashMap<String, Object>();
		String testID="16176";
		
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);
		
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);
		
		gcnAdminHomePage.launchAdminAndSignIn();
						
		assertTrue(gcnAdminLoginPage.verifyInvalidLogin(testData.get("invalidusername").toString(), testData.get("password").toString()), "Message for wrong format of emailid");
		
		assertTrue(gcnAdminLoginPage.verifyInvalidLogin(testData.get("invalidusername1").toString(), testData.get("invalidpassword").toString()),"Message for wrong emailid or password");
		
		assertTrue(gcnAdminLoginPage.verifyInvalidLogin(testData.get("invalidusername1").toString(), testData.get("password").toString()),"Message for wrong emailid or password");
				
		assertAll();
						
	}
	

}
