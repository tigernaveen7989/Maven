package com.ea.wwce.automation.gcn.tests;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.ea.wwce.automation.base.util.dataprovider.DataProviders;

public class GcnAdminLogoutTest extends GcnBaseTest{
	
	public static Logger logger=Logger.getLogger(GcnAdminLogoutTest.class);
	
	/*This class is to check the Logout functionality of GCN Admin site.
	 * 
	 * TC: 16175 - 
	 * TC: 16182 - 
	 * 
	 * */

	
	@Test
	public void gcnAdminLogoutTest(ITestContext context){
		
		Map<String, Object> testData = new HashMap<String, Object>();
		String testID="16175";
		
		// 16175,16182
		context.setAttribute("testcase_id", testID);
		logger.info("validating Verify Login Test" + testID);
				
		DataProviders dataProvider = (DataProviders) context.getAttribute("dataProvider");
		testData = dataProvider.getTestData(testID);
		
		gcnAdminHomePage.launchAdminAndSignIn();
		
		gcnAdminPage=gcnAdminLoginPage.verifyLogin(testData.get("username").toString(), testData.get("password").toString());
			
		gcnAdminPage.logoutAdmin();
		
		assertTrue(gcnAdminHomePage.verifyNavigationToAdminHomePage(), "SignOut Successful");
		
		assertAll();
						
	}
	


}
